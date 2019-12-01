import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class PopUpBox {
    private static String enteredText;
    private static boolean pressedOk;

    /**
     * Megjelenit egy felugro ablakot a megadott szoveggel és egy bemeneti mezovel.
     * @param title megjelenitett szoveg
     */
    static void display(String title) {

        pressedOk = false;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label createText = new Label(title);
        TextField field = new TextField();
        field.setMaxWidth(Double.MAX_VALUE);
        Button okButton = new Button("Ok");
        okButton.setOnAction(actionEvent -> {
            pressedOk = true;
            enteredText = field.getText();
            window.close();
        });

        VBox layout = new VBox();
        layout.setSpacing(5);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(createText, field, okButton);
        window.setScene(new Scene(layout));
        window.showAndWait();

    }

    /**
     * Getter a beirt szovegre.
     * @return beirt szoveg
     */
    static String getEnteredText() {
        return enteredText;
    }

    /**
     * Visszater, hogy meg lett-e nyomva az ok gomb.
     * @return meg lett-e nyomva
     */
    static boolean didPressOk() {
        return pressedOk;
    }
}
