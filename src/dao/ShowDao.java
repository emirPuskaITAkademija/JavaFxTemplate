package dao;

import dao.connection.ConnectionPool;
import entity.Show;

import java.sql.*;
import java.util.*;

public class ShowDao implements Dao<Show>{
    private ConnectionPool connectionPool;

    public ShowDao(ConnectionPool connectionPool){
        this.connectionPool = Optional
                .ofNullable(connectionPool)
                .orElseThrow(()->new RuntimeException("Null ConnectionPool is not allowed"));
    }

    @Override
    public Show create(Show show) throws SQLException {
        String sqlInsert = "INSERT INTO shows (show_title, num_seasons,initial_year) VALUES(?,?,?)";
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, show.getShowTitle());
            ps.setInt(2, show.getNumberOfSeasons());
            ps.setInt(3, show.getInitialYear());
            int affected = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int lastGeneratedKey = rs.getInt(1);
                show.setShowId(lastGeneratedKey);
            }

        }catch (SQLException exception){
            System.err.println(exception.getMessage());
        }
        connectionPool.releaseConnection(connection);
        return show;
    }

    @Override
    public Show retrieve(int primaryKey) throws SQLException {
        String sqlQuery = "SELECT * FROM shows WHERE shows.show_id=?";
        Connection connection = connectionPool.getConnection();
        Show show = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);            ){
            preparedStatement.setInt(1, primaryKey);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int showId = resultSet.getInt(1);
                String showTitle = resultSet.getString(2);
                int numberOfSeason = resultSet.getInt(3);
                int year = resultSet.getInt(4);
                show = new Show(showId, showTitle, numberOfSeason, year);
                break;
            }
        }catch (SQLException exception){
            System.err.println(exception.getMessage());
        }
        connectionPool.releaseConnection(connection);
        return show;
    }

    @Override
    public List<Show> retrieveAll() throws SQLException {
        String sqlQuery = "SELECT *FROM shows";
        List<Show> showList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sqlQuery)){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int showId = resultSet.getInt(1);
                String showTitle = resultSet.getString(2);
                int numberOfSeason = resultSet.getInt(3);
                int year = resultSet.getInt(4);
                Show show = new Show(showId, showTitle, numberOfSeason, year);
                showList.add(show);
            }
        }catch (SQLException exception){
            System.err.println(exception.getMessage());
        }
        connectionPool.releaseConnection(connection);
        return showList;
    }

    @Override
    public void update(Show entity) throws SQLException {
        String sqlUpdate = "UPDATE shows SET show_title = ? WHERE show_id = ?";
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sqlUpdate)){
            ps.setString(1, entity.getShowTitle());
            ps.setInt(2, entity.getShowId());
            int number = ps.executeUpdate();
            System.out.println("Broj a??uriranih: " + number);
        }catch (SQLException exception){
            System.err.println(exception.getMessage());
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void delete(Show show) throws SQLException {
        System.out.println("IZBRISANI SHOW:" + show);
        String sqlUpdate = "DELETE FROM shows WHERE show_id=?;";
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sqlUpdate)){
            ps.setInt(1, show.getShowId());
            int number = ps.executeUpdate();
            System.out.println("Broj izbrisanih: " + number);
        }catch (SQLException exception){
            System.err.println(exception.getMessage());
        }
        connectionPool.releaseConnection(connection);
    }


    public Vector<String> getColumnNames(){
        String sqlQuery = "SELECT *FROM shows";
        Connection connection = connectionPool.getConnection();
        Vector<String> columnNames = new Vector<>();
        try(PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = ps.executeQuery()){
            //podatke ---> metapodatke
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            for(int i = 1; i<=numberOfColumns; i++){
                String nameOfColumn = resultSetMetaData.getColumnName(i);
                columnNames.addElement(nameOfColumn);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        connectionPool.releaseConnection(connection);
        return columnNames;
    }
}
