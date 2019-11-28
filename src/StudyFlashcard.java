import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class StudyFlashcard extends SimpleStudy {

    private Label termLabel;
    private boolean termActive;

    StudyFlashcard(View view) {
        super(view);
        termLabel = new Label();
        termLabel.setWrapText(true);

        setupLeft();
        setupCenter();
        setupRight();

        getScene().getStylesheets().add("StudyFlashcardStyles.css");
    }

    private void flip() {
        if(termActive) {
            termLabel.setText(getCurrent().definition);
            termActive = false;
        } else {
            termLabel.setText(getCurrent().term);
            termActive = true;
        }
    }

    @Override
    void setNext() {
        super.setNext();
        termLabel.setText(getCurrent().term);
        termActive = true;
    }

    private void setupLeft() {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(30, 30, 30, 30));

        Button dunno = new Button("Dunno");
        dunno.setOnAction(actionEvent -> didNotGetIt());

        pane.getChildren().add(dunno);

        layout.setLeft(pane);
    }

    private void setupCenter() {
        StackPane pane = new StackPane();
        pane.setId("center-pane");
        pane.setMaxHeight(300);
        pane.setMaxWidth(300);
        pane.setAlignment(Pos.CENTER);

        pane.setOnMouseClicked(e -> flip());

        pane.getChildren().add(termLabel);

        layout.setCenter(pane);
    }

    private void setupRight() {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(30, 30, 30, 30));

        Button gotIt = new Button("I know this");
        gotIt.setOnAction(actionEvent -> gotIt());

        pane.getChildren().add(gotIt);

        layout.setRight(pane);
    }
}
