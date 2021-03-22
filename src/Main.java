import dialog.ConfirmationDialog;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
  <comp> 1. Container...JFrame, JPanel</comp>
  <comp> 2. UI controls..JButton,JLabel, JCheckBox, JRadioButton, JComboBox..JTable..</comp>
  <comp> 3. LayoutManager: BorderLayout, FlowLayout, BoxLayout, SpringLayout, GridLayout,..</comp>

  <comp> 1. Container koji su ujedno i LayoutManager: BorderPane, VBox, HBox, GridPane, StackPane, ...</comp>
  <comp> 2. UI controls..Button,Label</comp>

 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 0);

        TextField usernameTextField = new TextField();
        GridPane.setConstraints(usernameTextField, 1, 0);

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(usernameLabel, 0, 1);

        PasswordField passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 1, 1);

        gridPane.getChildren().addAll(usernameLabel, usernameTextField, passwordField, passwordLabel);

        Scene scene = new Scene(gridPane, 300, 300);
        stage.setScene(scene);
        stage.setTitle("Forma");
        stage.show();
    }


}
