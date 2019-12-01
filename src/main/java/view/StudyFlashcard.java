package main.java.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class StudyFlashcard extends SimpleStudy {

    private Label termLabel;
    private boolean termActive;

    /**
     * Beallitja az ost a megadott nezettel és letrehozza az ablak bal, kozepso és jobb reszet.
     * @param view nezet
     */
    StudyFlashcard(View view) {
        super(view);
        termLabel = new Label();
        termLabel.setWrapText(true);

        setupLeft();
        setupCenter();
        setupRight();
    }

    /**
     * Kifejezesrol definiciora, definiciorol kifejezesre allitja a kifelzett szoveget.
     */
    private void flip() {
        if(termActive) {
            termLabel.setText(getCurrent().definition);
            termActive = false;
        } else {
            termLabel.setText(getCurrent().term);
            termActive = true;
        }
    }

    /**
     * Beallitja a kovetkezo kifejezes-definicio part.
     */
    @Override
    void setNext() {
        super.setNext();
        termLabel.setText(getCurrent().term);
        termActive = true;
    }

    /**
     * Beallitja az ablak bal reszet
     */
    private void setupLeft() {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(30, 30, 30, 30));

        Button dunno = new Button("Dunno");
        dunno.setOnAction(actionEvent -> didNotGetIt());

        pane.getChildren().add(dunno);

        setLayoutLeft(pane);
    }

    /**
     * Beallitja az abalak kozepso reszet.
     */
    private void setupCenter() {
        StackPane pane = new StackPane();
        pane.setStyle("-fx-background-color: #FFFFFF;");
        pane.setMaxHeight(300);
        pane.setMaxWidth(300);
        pane.setAlignment(Pos.CENTER);

        pane.setOnMouseClicked(e -> flip());

        pane.getChildren().add(termLabel);

        setLayoutCenter(pane);
    }

    /**
     * Beallitja az ablak jobb reszet
     */
    private void setupRight() {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(30, 30, 30, 30));

        Button gotIt = new Button("I know this");
        gotIt.setOnAction(actionEvent -> gotIt());

        pane.getChildren().add(gotIt);

        setLayoutRight(pane);
    }
}
