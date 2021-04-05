package controller;

import dao.ShowDao;
import dao.connection.ConnectionPool;
import entity.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class ShowController {

    public ObservableList<Show> loadShows(){
        ObservableList<Show> observableList = FXCollections.observableArrayList();
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            ShowDao showDao = new ShowDao(connectionPool);
            List<Show> lista = showDao.retrieveAll();
            observableList.addAll(lista);
        }catch (SQLException e){
            System.err.println(e.getLocalizedMessage());
        }
        return observableList;
    }
}
