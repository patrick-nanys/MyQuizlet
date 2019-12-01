import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

class Study {
    private View view;

    private Scene scene;
    private BorderPane layout;

    /**
     * Beallitja a nezetet a kommunikaciohoz és beallitja az also menu savot.
     * @param view nezet
     */
    Study(View view) {
        this.view = view;
        layout = new BorderPane();

        setupBottom();

        scene = new Scene(layout, 800, 520);
    }

    /**
     * Getter a scene-re
     * @return scene
     */
    Scene getScene() { return scene; }

    /**
     * Setter az ablak felso reszere.
     * @param node elrendezes
     */
    void setLayoutTop(Node node) { layout.setTop(node); }

    /**
     * Setter az ablak bal reszere.
     * @param node elrendezes
     */
    void setLayoutLeft(Node node) { layout.setLeft(node); }

    /**
     * Setter az ablak jobb reszere.
     * @param node elrendezes
     */
    void setLayoutRight(Node node) { layout.setRight(node); }

    /**
     * Setter az ablak kozepso reszere.
     * @param node elrendezes
     */
    void setLayoutCenter(Node node) { layout.setCenter(node); }

    /**
     * Beallitja az ablak also reszet.
     */
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
