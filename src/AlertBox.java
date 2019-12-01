import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class AlertBox {

    /**
     * Megjelenit egy felugro ablakot a megadott szoveggel.
     * @param title megjelenitett szoveg
     */
    static void display(String title) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Alert!");

        Label label = new Label(title);
        label.setWrapText(true);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(5);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().add(label);
        window.setScene(new Scene(layout, 300, 100));
        window.showAndWait();

    }
}
