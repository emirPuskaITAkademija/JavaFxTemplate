import controller.ShowController;
import dao.ShowDao;
import dao.connection.ConnectionPool;
import dialog.MessageDialog;
import entity.Show;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * <p>Kreiraje menija</p>
 * File, Edit, View..
 */
public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    private BorderPane borderPane;
    @Override
    public void start(Stage stage) throws Exception {
        borderPane = new BorderPane();
        MenuBar menuBar = new MenuBar();
        //Menu: File
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New...");
        MenuItem openMenuItem = new MenuItem("Open...");
        Menu openRecentMenu = new Menu("Open recent");
        MenuItem coreJavaItem = new MenuItem("Core Java Programming");
        MenuItem advancedJavaItem = new MenuItem("Advanced Java Programming");

        MenuItem swingItem = new MenuItem("Swing Project");
        MenuItem javafxItem = new MenuItem("JavaFx project");
        openRecentMenu.getItems().addAll(coreJavaItem, advancedJavaItem,new SeparatorMenuItem(),swingItem, javafxItem);
        ObservableList<MenuItem> fileMenuItems = fileMenu.getItems();
        fileMenuItems.addAll(newMenuItem, openMenuItem, openRecentMenu);
        ObservableList<Menu> menus = menuBar.getMenus();
        menus.add(fileMenu);
        //Menu:Edit  : _Edit -> otvara se menu na ALT+E
        Menu editMenu = new Menu("_Edit");
        MenuItem findMenuItem = new MenuItem("Find");
        findMenuItem.setOnAction(event -> System.out.println("Izvrši neku metodu na find..."));
        MenuItem findUsageMenuItem = new MenuItem("Find usage");
        editMenu.getItems().addAll(findMenuItem, findUsageMenuItem);
        menus.add(editMenu);
        //Menu: Help
        Menu helpMenu = new Menu("_Help");
        CheckMenuItem javafxTemplateMenuItem = new CheckMenuItem("JavaFxTemplate");
        javafxTemplateMenuItem.setOnAction(e->{
            if(javafxTemplateMenuItem.isSelected()){
                System.out.println("Odreaguj na stanje kada je selektovan...");
            }else{
                System.out.println("Odreaguj kad NIJE selektovan...");
            }
        });
        javafxTemplateMenuItem.setSelected(true);
        CheckMenuItem swingProject = new CheckMenuItem("SwingFxTemplate");
        helpMenu.getItems().addAll(javafxTemplateMenuItem, swingProject);
        menus.add(helpMenu);

        //RADIO menu
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioMenuItem beginner = new RadioMenuItem("Beginner");
        beginner.setToggleGroup(toggleGroup);
        RadioMenuItem intermediate = new RadioMenuItem("Intermediate");
        intermediate.setToggleGroup(toggleGroup);
        RadioMenuItem advanced = new RadioMenuItem("Advanced");
        advanced.setToggleGroup(toggleGroup);
        Menu levelMenu = new Menu("Level");
        levelMenu.getItems().addAll(beginner, intermediate, advanced);
        menus.add(levelMenu);

        borderPane.setTop(menuBar);
        Scene scene = new Scene(borderPane, 300, 250);
        stage.setTitle("Kreiranje menija");
        stage.setScene(scene);
        stage.show();
    }
}

/**
 * <comp> 1. Container...JFrame, JPanel</comp>
 * <comp> 2. UI controls..JButton,JLabel, JCheckBox, JRadioButton, JComboBox..JTable..</comp>
 * <comp> 3. LayoutManager: BorderLayout, FlowLayout, BoxLayout, SpringLayout, GridLayout,..</comp>
 *
 * <comp> 1. Container koji su ujedno i LayoutManager: BorderPane, VBox, HBox, GridPane, StackPane, ...</comp>
 * <comp> 2. UI controls..Button,Label</comp>
 *
 *
 * <p>CheckBox</p>
 * <p>ChoiceBox</p>
 * <p>ComboBox</p>
 * <p>ListView</p>
 * <p>TreeView<p/>
 * <p>TableView</p>
 * ROOT - MAIN
 * BRANCHES -> potomci nekog elementa
 * na istom nivou ->
 *
 * <p> List<Show>    ->  ObservableList<Show>   -> TableView<Show>  </p>
 * <p>
 * <p>
 * LEAF
 */


/*
public class Main extends Application {

    private TableView<Show> showTableView;
    private TextField showTitleTextField;
    private TextField numberOfSeasonField;
    private TextField initialYearField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //KOLONE
        TableColumn<Show, Integer> showIdColumn = new TableColumn<>("Show ID");
        showIdColumn.setMinWidth(50);
        showIdColumn.setCellValueFactory(new PropertyValueFactory<>("showId"));

        TableColumn<Show, String> showTitleColumn = new TableColumn<>("Show Title");
        showTitleColumn.setMinWidth(200);
        showTitleColumn.setCellValueFactory(new PropertyValueFactory<>("showTitle"));

        TableColumn<Show, Integer> numberOfSeasonsColumn = new TableColumn<>("Number of seasons");
        numberOfSeasonsColumn.setMinWidth(70);
        numberOfSeasonsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfSeasons"));

        TableColumn<Show, Integer> initialYearColumn = new TableColumn<>("Initial Year");
        initialYearColumn.setMinWidth(80);
        initialYearColumn.setCellValueFactory(new PropertyValueFactory<>("initialYear"));

        showTableView = new TableView<>();
        //veza s podacima
        showTableView.setItems(new ShowController().loadShows());
        //veza s kolonama
        showTableView.getColumns().addAll(showIdColumn, showTitleColumn, numberOfSeasonsColumn, initialYearColumn);
        HBox formHBox = new HBox(10);
        formHBox.setPadding(new Insets(10, 10, 10, 10));
        showTitleTextField = new TextField();
        showTitleTextField.setPromptText("Title...");
        numberOfSeasonField = new TextField();
        numberOfSeasonField.setPromptText("Number of seasons...");
        numberOfSeasonField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {
                numberOfSeasonField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        initialYearField = new TextField();
        initialYearField.setPromptText("Initial year....");
        initialYearField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                initialYearField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        Button addButton = new Button("Add");
        addButton.setOnAction(this::onAddButtonClicked);
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(this::onDeleteButtonClicked);
        formHBox.getChildren().addAll(showTitleTextField, numberOfSeasonField, initialYearField, addButton, deleteButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(showTableView, formHBox);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Shows");
        stage.show();
    }

    private void onDeleteButtonClicked(Event event){
        ObservableList<Show> allShows = showTableView.getItems();
        ObservableList<Show> selectedShows = showTableView.getSelectionModel().getSelectedItems();
        for(Show show: selectedShows){
            try {
                ConnectionPool connectionPool = new ConnectionPool();
                ShowDao showDao = new ShowDao(connectionPool);
                //ažurirao tabelu u BAZI
                showDao.delete(show);
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        }
        selectedShows.forEach(allShows::remove);
    }

    private void onAddButtonClicked(Event e) {
        if (validatedInputs()) {
            //showId -> BAZA
            Show show = new Show();
            show.setShowTitle(showTitleTextField.getText());
            show.setNumberOfSeasons(Integer.parseInt(numberOfSeasonField.getText()));
            show.setInitialYear(Integer.parseInt(initialYearField.getText()));
            try {
                ConnectionPool connectionPool = new ConnectionPool();
                ShowDao showDao = new ShowDao(connectionPool);
                //ažurirao tabelu u BAZI
                showDao.create(show);
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
            //ažuriram listu koja je povezana sa GUI tabelom
            ObservableList<Show> showObservableList = showTableView.getItems();
            showObservableList.add(show);
            clearInputs();
        }
    }

    private void clearInputs() {
        showTitleTextField.clear();
        numberOfSeasonField.clear();
        initialYearField.clear();
    }

    private boolean validatedInputs() {
        boolean validated = true;
        if (showTitleTextField.getText().isBlank()) {
            new MessageDialog("Form validation", "Nisi unio title").display();
            validated = false;
        } else if (numberOfSeasonField.getText().isBlank()) {
            new MessageDialog("Form validation", "Nisi unio number of seasons").display();
            validated = false;
        } else if (initialYearField.getText().isBlank()) {
            new MessageDialog("Form validation", "Nisi unio inicijalnu godinu").display();
            validated = false;
        }
        return validated;
    }
}
*/


