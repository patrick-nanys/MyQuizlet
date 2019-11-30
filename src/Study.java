import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

abstract class Study {
    private View view;

    private Scene scene;
    private BorderPane layout;

    Study(View view) {
        this.view = view;
        layout = new BorderPane();

        setupBottom();

        scene = new Scene(layout, 800, 520);
    }

    Scene getScene() { return scene; }

    void setLayoutTop(Node node) { layout.setTop(node); }
    void setLayoutLeft(Node node) { layout.setLeft(node); }
    void setLayoutRight(Node node) { layout.setRight(node); }
    void setLayoutCenter(Node node) { layout.setCenter(node); }

    private void setupBottom() {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(30, 30, 30, 30));

        Button exit = new Button("I'm out");
        exit.setId("button-exit");
        exit.setOnAction(actionEvent -> view.endStudy());

        pane.getChildren().add(exit);

        layout.setBottom(pane);
    }
}
