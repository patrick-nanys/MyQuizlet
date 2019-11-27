import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class StudyFlashcard {

    private BorderPane layout;
    private ArrayList<TermAndDefinition> termsAndDefinitions;

    StudyFlashcard() {
        layout = new BorderPane();
        setupTop();
        setupLeft();
        setupCenter();
        setupRight();
        setupBottom();
    }

    public void loadStudySet(StudySet studySet) {

    }

    public Scene createScene() {
        Scene scene = new Scene(layout, 800, 520);
        scene.getStylesheets().add("StudyFlashcardStyles.css");
        return scene;
    }

    private void setupTop() {
        HBox topLabels = new HBox();
        topLabels.setAlignment(Pos.CENTER);
        topLabels.setSpacing(50);
        topLabels.setPadding(new Insets(10, 10, 10, 10));

        Label remaining = new Label("Remaining:");
        Label familiar = new Label("Familiar:");
        Label mastered = new Label("Mastered:");

        remaining.setPadding(new Insets(0, 10, 0, 10));
        familiar.setPadding(new Insets(0, 10, 0, 10));
        mastered.setPadding(new Insets(0, 10, 0, 10));

        Label remainingNum = new Label("10");
        Label familiarNum = new Label("20");
        Label masteredNum = new Label("30");

        HBox remainingBox = new HBox();
        HBox familiarBox = new HBox();
        HBox masteredBox = new HBox();

        remainingBox.setAlignment(Pos.CENTER);
        familiarBox.setAlignment(Pos.CENTER);
        masteredBox.setAlignment(Pos.CENTER);

        remainingBox.getChildren().addAll(remaining, remainingNum);
        familiarBox.getChildren().addAll(familiar, familiarNum);
        masteredBox.getChildren().addAll(mastered, masteredNum);

        topLabels.getChildren().addAll(remainingBox, familiarBox, masteredBox);

        layout.setTop(topLabels);
    }

    private void setupLeft() {
        Pane pane = new Pane();

        Button dunno = new Button("Dunno");
        dunno.setAlignment(Pos.CENTER);

        pane.getChildren().add(dunno);

        layout.setLeft(pane);
    }

    private void setupCenter() {
        Pane pane = new Pane();
        pane.setMaxHeight(250);
        pane.setMaxWidth(250);

        Label text = new Label("Some term that someone wants to learn");

        pane.getChildren().add(text);

        layout.setCenter(pane);
    }

    private void setupRight() {
        Pane pane = new Pane();

        Button dunno = new Button("I know this");
        dunno.setAlignment(Pos.CENTER);

        pane.getChildren().add(dunno);

        layout.setRight(pane);
    }

    private void setupBottom() {
        Pane pane = new Pane();

        Button exit = new Button("I'm out");

        pane.getChildren().add(exit);

        layout.setBottom(pane);
    }
}
