package dialog;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationDialog {

    private boolean answer = false;
    private final String title;
    private final String message;

    public ConfirmationDialog(String title, String message){
        this.title = title;
        this.message = message;
    }

    public boolean display(){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(250);
        stage.setMinHeight(100);
        stage.setTitle(title);

        Label messageLabel = new Label(message);
        Button yesButton = new Button("YES");
        yesButton.setOnAction(e->{
            answer = true;
            stage.close();
        });
        Button noButton = new Button("NO");
        noButton.setOnAction(event -> {
            answer = false;
            stage.close();
        });
        HBox buttonHBox = new HBox(20);
        buttonHBox.getChildren().addAll(yesButton, noButton);

        VBox vBox = new VBox(16);
        vBox.getChildren().addAll(messageLabel, buttonHBox);
        Scene scene = new Scene(vBox, 150, 50);
        stage.setScene(scene);
        stage.showAndWait();
        return answer;
    }
}
