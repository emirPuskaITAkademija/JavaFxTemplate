package dialog;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Svrha postojanja ove klase je mogućnost prikaza neke info
 * poruke koja ima i title korisniku vaše aplikacije.
 */
public class MessageDialog {
    private final String title;
    private final String message;

    public MessageDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    /**
     * display() method creates completely new window.
     * <p>
     * We must have here creation of completely new Stage object
     * </p>
     */
    public void display() {
        Stage messageStage = new Stage();
        messageStage.initModality(Modality.APPLICATION_MODAL);
        messageStage.setTitle(title);
        messageStage.setMinWidth(350);
        messageStage.setMinHeight(200);

        Label messageLabel = new Label();
        messageLabel.setText(message);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> messageStage.close());

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(messageLabel, closeButton);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        messageStage.setScene(scene);
        messageStage.showAndWait();
    }
}
