import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Collections;

class StudyFlashcard {

    private View view;

    private Scene scene;
    private BorderPane layout;
    private ArrayList<TermAndDefinition> termsAndDefinitions;
    private ArrayList<TermAndDefinition> familiar;
    private TermAndDefinition current;
    private boolean termActive;

    private Label remainingNum;
    private Label familiarNum;
    private Label masteredNum;
    private Label text;

    StudyFlashcard(View view) {
        this.view = view;
        layout = new BorderPane();
        familiar = new ArrayList<>();
        setupTop();
        setupLeft();
        setupCenter();
        setupRight();
        setupBottom();

        scene = new Scene(layout, 800, 520);
        scene.getStylesheets().add("StudyFlashcardStyles.css");
    }

    Scene getScene() {
        return scene;
    }

    void loadStudySet(StudySet studySet) {
        termsAndDefinitions = studySet.getTermsAndDefinitions();
        Collections.shuffle(termsAndDefinitions);
        remainingNum.setText(String.valueOf(termsAndDefinitions.size()));
        familiarNum.setText("0");
        masteredNum.setText("0");
        setNext();
    }

    private void flip() {
        if(termActive) {
            text.setText(current.definition);
            termActive = false;
        } else {
            text.setText(current.term);
            termActive = true;
        }
    }

    private void setNext() {
        current = getNewTermAndDefinition();
        termActive = true;
        text.setText(current.term);
    }

    private TermAndDefinition getNewTermAndDefinition() {
        if(Integer.parseInt(familiarNum.getText()) < 10 && termsAndDefinitions.size() != 0) {
            return termsAndDefinitions.get(0);
        } else if(familiar.size() != 0) {
            return familiar.get(0);
        }
        return new TermAndDefinition("", "");
    }

    private void gotIt() {
        if(termsAndDefinitions.size() != 0 && current == termsAndDefinitions.get(0)) {
            familiar.add(current);
            int numOfFamiliar = Integer.parseInt(familiarNum.getText());
            familiarNum.setText(String.valueOf(numOfFamiliar + 1));

            termsAndDefinitions.remove(0);
            remainingNum.setText(String.valueOf(termsAndDefinitions.size()));
        } else if(familiar.size() != 0) {
            int numOfMastered = Integer.parseInt(masteredNum.getText());
            masteredNum.setText(String.valueOf(numOfMastered + 1));

            familiar.remove(0);
            int numOfFamiliar = Integer.parseInt(familiarNum.getText());
            familiarNum.setText(String.valueOf(numOfFamiliar - 1));
        }
        setNext();
    }

    private void didNotGetIt() {
        if(termsAndDefinitions.size() != 0 && current == termsAndDefinitions.get(0)) {
            termsAndDefinitions.remove(0);
            termsAndDefinitions.add(current);
        } else if(familiar.size() != 0) {
            familiar.remove(0);
            termsAndDefinitions.add(current);
        }
        setNext();
    }

    private void setupTop() {
        HBox topLabels = new HBox();
        topLabels.setId("main-hbox");
        topLabels.setAlignment(Pos.CENTER);
        topLabels.setSpacing(50);
        topLabels.setPadding(new Insets(10, 10, 10, 10));

        Label remaining = new Label("Remaining:");
        Label familiar = new Label("Familiar:");
        Label mastered = new Label("Mastered:");

        remaining.setPadding(new Insets(0, 10, 0, 10));
        familiar.setPadding(new Insets(0, 10, 0, 10));
        mastered.setPadding(new Insets(0, 10, 0, 10));

        remainingNum = new Label("10");
        familiarNum = new Label("20");
        masteredNum = new Label("30");

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

        text = new Label();

        pane.getChildren().add(text);

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

    private void setupBottom() {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(30, 30, 30, 30));

        Button exit = new Button("I'm out");
        exit.setId("button-exit");
        exit.setOnAction(actionEvent -> view.endStudyFlashcard());

        pane.getChildren().add(exit);

        layout.setBottom(pane);
    }
}
