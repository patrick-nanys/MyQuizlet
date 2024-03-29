package main.java.view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.model.TermAndDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class StudyLearn extends SimpleStudy {

    private VBox radioMain;
    private Label termLabel;
    private RadioButton[] radioButtons;
    private Label[] definitions;
    private int correctIndex;

    private VBox writingMain;
    private Label definitionLabel;
    private TextField answer;

    /**
     * Beallitja azt ost a megadott nezettel egy beallitja az ablak kozepso reszet
     * @param view nezet
     */
    StudyLearn(View view) {
        super(view);

        setupCenter();
    }

    /**
     * Bealltja a kovetkezo kifejezes-definicio part
     */
    @Override
    void setNext() {
        super.setNext();
        if(getRemaining().size() != 0 && getRemaining().get(0) == getCurrent()) {
            setDefinitions();
            termLabel.setText(getCurrent().term);
            displayRadioTest();
        } else {
            definitionLabel.setText(getCurrent().definition);
            displayWritingTest();
        }
    }

    /**
     * A feleletvalasztos resznel beallitja a definiciokat osszekeverve.
     */
    private void setDefinitions() {
        ArrayList<TermAndDefinition> all = new ArrayList<>();
        all.addAll(getRemaining());
        all.addAll(getFamiliars());

        Collections.shuffle(all);

        Random rand = new Random();

        correctIndex = rand.nextInt(4);
        definitions[correctIndex].setText(getCurrent().definition);

        for (int i = 0; i < 4; i++) {
            if(i != correctIndex) {
                if(all.get(i).definition.equals(getCurrent().definition)) {
                    definitions[i].setText(all.get(correctIndex).definition);
                } else {
                    definitions[i].setText(all.get(i).definition);
                }
            }
        }
    }

    /**
     * Beallitja az ablak kozepso reszen elhelyezkedo feladat reszeket
     */
    private void setupCenter() {
        setupRadioTest();
        setupWritingTest();
    }

    /**
     * Beallitja a feleletvalasztos feladat kinezetet.
     */
    private void setupRadioTest() {
        radioMain = new VBox();
        radioMain.setAlignment(Pos.CENTER);
        radioMain.setSpacing(100);
        radioMain.setStyle(" -fx-background-color: #FFFFFF; ");

        termLabel = new Label();
        termLabel.setWrapText(true);

        radioMain.getChildren().add(termLabel);

        VBox choicesAndValidate = new VBox();
        choicesAndValidate.setAlignment(Pos.CENTER);
        choicesAndValidate.setSpacing(20);

        VBox choiceBox = new VBox();
        choiceBox.setSpacing(10);

        HBox[] hBoxes = new HBox[4];
        radioButtons = new RadioButton[4];
        ToggleGroup answerGroup = new ToggleGroup();
        definitions = new Label[4];

        for (int i = 0; i < 4; i++) {
            hBoxes[i] = new HBox();
            hBoxes[i].setSpacing(10);
            radioButtons[i] = new RadioButton();
            radioButtons[i].setToggleGroup(answerGroup);
            definitions[i] = new Label();

            hBoxes[i].setAlignment(Pos.CENTER);
            hBoxes[i].getChildren().addAll(radioButtons[i], definitions[i]);
            choiceBox.getChildren().add(hBoxes[i]);
        }

        Button validate = new Button("Validate");
        validate.setOnAction(actionEvent -> validateRadioAnswer());

        choicesAndValidate.getChildren().addAll(choiceBox, validate);
        radioMain.getChildren().add(choicesAndValidate);
    }

    /**
     * Beallitja az irasos feladat kinezetet.
     */
    private void setupWritingTest() {
        writingMain = new VBox();
        writingMain.setAlignment(Pos.CENTER);
        writingMain.setSpacing(100);
        writingMain.setStyle(" -fx-background-color: #FFFFFF; ");

        definitionLabel = new Label();
        definitionLabel.setWrapText(true);

        answer = new TextField();
        answer.setMaxWidth(300);
        answer.setPrefColumnCount(20);

        Button validate = new Button("Validate");
        validate.setOnAction(actionEvent -> validateWritingAnswer());

        writingMain.getChildren().addAll(definitionLabel, answer, validate);
    }

    /**
     * A feleletvalaszot feladatot allitja be az ablak kozepso reszere.
     */
    private void displayRadioTest() { setLayoutCenter(radioMain); }

    /**
     * Az irasos feladatot allitja be az ablak kozepso reszere.
     */
    private void displayWritingTest() { setLayoutCenter(writingMain); }

    /**
     * Ellenorzi a feleletvalasztos feladat megoldasat.
     */
    private void validateRadioAnswer() {
        if(radioButtons[correctIndex].isSelected()) {
            gotIt();
        } else {
            AlertBox.display("The correct answer was: " + getCurrent().definition);
            didNotGetIt();
        }
        for (int i = 0; i < 4; i++) {
            radioButtons[i].setSelected(false);
        }
    }

    /**
     * Ellenorzi az irasos feladat megoldasat.
     */
    private void validateWritingAnswer() {
        if(!getCurrent().definition.equals("")) {
            if(answer.getText().equals(getCurrent().term)) {
                gotIt();
            } else {
                AlertBox.display("The correct answer was: " + getCurrent().term);
                didNotGetIt();
            }
            answer.setText("");
        }
    }
}
