import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;


class StudyTest extends Study {

    private ArrayList<TermAndDefinition> firstTaskTermAndDefinitions;
    private ArrayList<TermAndDefinition> secondTaskTermAndDefinitions;
    private ArrayList<TermAndDefinition> thirdTaskTermAndDefinitions;

    private ArrayList<TermAndDefinition> secondTaskShuffled;
    private ArrayList<TermAndDefinition> thirdTaskShuffled;

    private VBox mainBox;
    private TextField[] termFields;
    private TextField[] characterFields;
    private RadioButton[] trueRadioButtons;
    private RadioButton[] falseRadioButtons;

    StudyTest(View view) {
        super(view);

        firstTaskTermAndDefinitions = new ArrayList<>();
        secondTaskTermAndDefinitions = new ArrayList<>();
        thirdTaskTermAndDefinitions = new ArrayList<>();

        secondTaskShuffled = new ArrayList<>();
        thirdTaskShuffled = new ArrayList<>(thirdTaskTermAndDefinitions);
    }

    void loadStudySet(StudySet studySet) {
        ArrayList<TermAndDefinition> all = studySet.getTermsAndDefinitions();
        Collections.shuffle(all);
        for (int i = 0; i < 5; i++) {
            firstTaskTermAndDefinitions.add(all.get(i));
            secondTaskTermAndDefinitions.add(all.get(i+5));
            thirdTaskTermAndDefinitions.add(all.get(i+10));
        }

        setupCenter();

        Button validate = new Button("Validate");
        validate.setOnAction(actionEvent -> validateAnswers());

        mainBox.getChildren().addAll(validate, new Separator());
    }

    private void setupCenter() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(20);

        setupFirstTask();
        mainBox.getChildren().add(new Separator());
        setupSecondTask();
        mainBox.getChildren().add(new Separator());
        setupThirdTask();

        scrollPane.setContent(mainBox);
        setLayoutCenter(scrollPane);
    }

    private void setupFirstTask() {
        VBox firstTask  = new VBox();
        firstTask.setAlignment(Pos.CENTER);
        firstTask.setPadding(new Insets(10, 10, 10, 10));
        firstTask.setSpacing(10);

        Label[] subTaskNums = new Label[5];
        Label[] definitionLabels = new Label[5];
        termFields = new TextField[5];

        GridPane[] grids = new GridPane[5];
        for (int i = 0; i < 5; i++) {
            grids[i] = new GridPane();
            subTaskNums[i] = new Label(String.valueOf(i+1));
            definitionLabels[i] = new Label(firstTaskTermAndDefinitions.get(i).definition);
            termFields[i] = new TextField();

            grids[i].setAlignment(Pos.CENTER);
            grids[i].setPadding(new Insets(10, 10, 10, 10));
            grids[i].setVgap(10);
            grids[i].setHgap(20);
            grids[i].add(subTaskNums[i], 0, 0);
            grids[i].add(definitionLabels[i], 1, 0);
            grids[i].add(termFields[i], 1, 1);

            firstTask.getChildren().add(grids[i]);
        }

        mainBox.getChildren().add(firstTask);
    }

    private void setupSecondTask() {
        HBox secondTask = new HBox();
        secondTask.setAlignment(Pos.CENTER);
        secondTask.setPadding(new Insets(10, 10, 10, 10));
        secondTask.setSpacing(10);

        VBox terms = new VBox();
        terms.setAlignment(Pos.CENTER);
        terms.setPadding(new Insets(10, 10, 10, 10));
        terms.setSpacing(10);
        HBox[] termBoxes = new HBox[5];
        Label[] termNum = new Label[5];
        characterFields = new TextField[5];
        Label[] termLabels = new Label[5];

        VBox definitions = new VBox();
        definitions.setAlignment(Pos.CENTER);
        definitions.setPadding(new Insets(10, 10, 10, 10));
        definitions.setSpacing(10);
        HBox[] definitionBoxes = new HBox[5];
        Label[] definitionCharacters = new Label[5];
        Label[] definitionLabels = new Label[5];

        secondTaskShuffled.addAll(secondTaskTermAndDefinitions);
        Collections.shuffle(secondTaskShuffled);

        for (int i = 0; i < 5; i++) {
            termBoxes[i] = new HBox();
            termNum[i] = new Label(String.valueOf(i+1));
            characterFields[i] = new TextField();
            termLabels[i] = new Label(secondTaskTermAndDefinitions.get(i).term);

            termBoxes[i].setPadding(new Insets(10, 10, 10, 10));
            termBoxes[i].setSpacing(10);
            characterFields[i].setPrefColumnCount(1);

            termBoxes[i].getChildren().addAll(termNum[i], characterFields[i], termLabels[i]);
            terms.getChildren().add(termBoxes[i]);

            definitionBoxes[i] = new HBox();
            definitionCharacters[i] = new Label(String.valueOf((char)('A' + i)));
            definitionLabels[i] = new Label(secondTaskShuffled.get(i).definition);

            definitionBoxes[i].setPadding(new Insets(10, 10, 10, 10));
            definitionBoxes[i].setSpacing(10);

            definitionBoxes[i].getChildren().addAll(definitionCharacters[i], definitionLabels[i]);
            definitions.getChildren().add(definitionBoxes[i]);
        }

        secondTask.getChildren().addAll(terms, definitions);
        mainBox.getChildren().add(secondTask);
    }

    private void setupThirdTask() {
        VBox thirdTask  = new VBox();
        thirdTask.setAlignment(Pos.CENTER);
        thirdTask.setPadding(new Insets(10, 10, 10, 10));
        thirdTask.setSpacing(10);

        GridPane[] grids = new GridPane[5];
        Label[] taskNums = new Label[5];
        Label[] definitions = new Label[5];
        Label[] terms = new Label[5];
        VBox[] choices = new VBox[5];
        HBox[] choice = new HBox[10];
        ToggleGroup[] toggleGroups = new ToggleGroup[5];
        trueRadioButtons = new RadioButton[5];
        Label[] trues = new Label[5];
        falseRadioButtons = new RadioButton[5];
        Label[] falses = new Label[5];

        thirdTaskShuffled.addAll(thirdTaskTermAndDefinitions);
        Collections.shuffle(thirdTaskShuffled);

        for (int i = 0; i < 5; i++) {
            grids[i] = new GridPane();
            taskNums[i] = new Label(String.valueOf(i+1));
            terms[i] = new Label(thirdTaskTermAndDefinitions.get(i).term + ":");
            definitions[i] = new Label(thirdTaskShuffled.get(i).definition);
            choices[i] = new VBox();
            choice[i] = new HBox();
            choice[i+5] = new HBox();
            toggleGroups[i] = new ToggleGroup();
            trues[i] = new Label("True");
            trueRadioButtons[i] = new RadioButton();
            falses[i] = new Label("False");
            falseRadioButtons[i] = new RadioButton();

            trueRadioButtons[i].setToggleGroup(toggleGroups[i]);
            falseRadioButtons[i].setToggleGroup(toggleGroups[i]);
            choice[i].getChildren().addAll(trueRadioButtons[i], trues[i]);
            choice[i+5].getChildren().addAll(falseRadioButtons[i], falses[i]);
            choices[i].getChildren().addAll(choice[i], choice[i+5]);

            grids[i].setAlignment(Pos.CENTER);
            grids[i].setPadding(new Insets(10, 10, 10, 10));
            grids[i].setVgap(10);
            grids[i].setHgap(20);
            grids[i].add(taskNums[i], 0, 0);
            grids[i].add(terms[i], 1, 0);
            grids[i].add(definitions[i], 2, 0);
            grids[i].add(choices[i], 1, 1);

            thirdTask.getChildren().add(grids[i]);
        }

        mainBox.getChildren().add(thirdTask);
    }

    private void validateAnswers() {
        int sumOfPoints = 0;
        for (int i = 0; i < 5; i++) {
            // validate first task
            if(termFields[i].getText().equals(firstTaskTermAndDefinitions.get(i).term))
                sumOfPoints++;

            // validate second task
            int indexOfDefinition;
            if(characterFields[i].getText().equals(""))
                indexOfDefinition = 0;
            else
                indexOfDefinition = characterFields[i].getText().charAt(0) - 'A';
            if(secondTaskTermAndDefinitions.get(i).term.equals(secondTaskShuffled.get(indexOfDefinition).term))
                sumOfPoints++;

            // validate third task
            if(thirdTaskTermAndDefinitions.get(i).term.equals(thirdTaskShuffled.get(i).term)) {
                if(trueRadioButtons[i].isSelected())
                    sumOfPoints++;
            } else {
                if(falseRadioButtons[i].isSelected())
                    sumOfPoints++;
            }
        }
        AlertBox.display("You've got " + sumOfPoints + "/15 points.");
    }
}
