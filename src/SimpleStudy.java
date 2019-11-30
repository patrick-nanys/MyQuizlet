import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Collections;

abstract class SimpleStudy extends Study {

    private ArrayList<TermAndDefinition> remaining;
    private ArrayList<TermAndDefinition> familiars;
    private TermAndDefinition current;

    private Label remainingNum;
    private Label familiarNum;
    private Label masteredNum;

    SimpleStudy(View view) {
        super(view);
        familiars = new ArrayList<>();

        setupTop();
    }

    ArrayList<TermAndDefinition> getRemaining() { return remaining; }
    ArrayList<TermAndDefinition> getFamiliars() { return familiars; }
    TermAndDefinition getCurrent() { return current; }

    void loadStudySet(StudySet studySet) {
        remaining = studySet.getTermsAndDefinitions();
        Collections.shuffle(remaining);
        remainingNum.setText(String.valueOf(remaining.size()));
        familiarNum.setText("0");
        masteredNum.setText("0");
        setNext();
    }

    void setNext() {
        current = getNewTermAndDefinition();
    }

    private TermAndDefinition getNewTermAndDefinition() {
        if(Integer.parseInt(familiarNum.getText()) < 10 && remaining.size() != 0) {
            return remaining.get(0);
        } else if(familiars.size() != 0) {
            return familiars.get(0);
        }
        return new TermAndDefinition("", "");
    }

    void gotIt() {
        if(remaining.size() != 0 && current == remaining.get(0)) {
            familiars.add(current);
            int numOfFamiliar = Integer.parseInt(familiarNum.getText());
            familiarNum.setText(String.valueOf(numOfFamiliar + 1));

            remaining.remove(0);
            remainingNum.setText(String.valueOf(remaining.size()));
        } else if(familiars.size() != 0) {
            int numOfMastered = Integer.parseInt(masteredNum.getText());
            masteredNum.setText(String.valueOf(numOfMastered + 1));

            familiars.remove(0);
            int numOfFamiliar = Integer.parseInt(familiarNum.getText());
            familiarNum.setText(String.valueOf(numOfFamiliar - 1));
        }
        setNext();
    }

    void didNotGetIt() {
        if(remaining.size() != 0 && current == remaining.get(0)) {
            remaining.remove(0);
            remaining.add(current);
        } else if(familiars.size() != 0) {
            familiars.remove(0);
            familiars.add(current);
        }
        setNext();
    }

    private void setupTop() {
        HBox topLabels = new HBox();
        topLabels.setId("main-hbox");
        topLabels.setAlignment(Pos.CENTER);
        topLabels.setSpacing(50);
        topLabels.setPadding(new Insets(10, 10, 10, 10));

        Label remainingLabel = new Label("Remaining:");
        Label familiarLabel = new Label("Familiar:");
        Label masteredLabel = new Label("Mastered:");

        remainingLabel.setPadding(new Insets(0, 10, 0, 10));
        familiarLabel.setPadding(new Insets(0, 10, 0, 10));
        masteredLabel.setPadding(new Insets(0, 10, 0, 10));

        remainingNum = new Label("10");
        familiarNum = new Label("20");
        masteredNum = new Label("30");

        HBox remainingBox = new HBox();
        HBox familiarBox = new HBox();
        HBox masteredBox = new HBox();

        remainingBox.setAlignment(Pos.CENTER);
        familiarBox.setAlignment(Pos.CENTER);
        masteredBox.setAlignment(Pos.CENTER);

        remainingBox.getChildren().addAll(remainingLabel, remainingNum);
        familiarBox.getChildren().addAll(familiarLabel, familiarNum);
        masteredBox.getChildren().addAll(masteredLabel, masteredNum);

        topLabels.getChildren().addAll(remainingBox, familiarBox, masteredBox);

        setLayoutTop(topLabels);
    }
}
