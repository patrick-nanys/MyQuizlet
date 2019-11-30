import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;

class View {

    private Stage primaryStage;
    private MainMenu mainMenu;
    private StudyFlashcard studyFlashcard;
    private StudyLearn studyLearn;
    private StudyTest studyTest;

    View(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MyQuizlet");
    }

    void showView() {
        primaryStage.show();
    }

    void setupMainMenu(Controller controller, ArrayList<Pair<String, String>> hierarchyMap) {
        mainMenu = new MainMenu(controller);
        mainMenu.loadFolderTree(hierarchyMap, controller);
        mainMenu.setupLeftMenu();
    }

    void showMainMenu() {
        primaryStage.setScene(mainMenu.getScene());
    }

    void addItemToMainTree(String treeItemValue, String folderName) throws NullPointerException {
        mainMenu.addItemToTree(treeItemValue, folderName);
    }

    void removeItemFromMainTree(String treeItemValue, String folderName) throws NullPointerException {
        mainMenu.removeItemFromTree(treeItemValue, folderName);
    }

    ArrayList<TermAndDefinition> getDisplayedStudySetData() {
        return mainMenu.getDisplayedStudySetData();
    }

    void loadStudySet(StudySet studySet) {
        mainMenu.loadStudySet(studySet);
    }

    TreeView<String> getFolderTree() {
        return mainMenu.getFolderTree();
    }

    void setupStudySections() {
        studyFlashcard = new StudyFlashcard(this);
        studyLearn = new StudyLearn(this);
        studyTest = new StudyTest(this);
    }

    void showStudyFlashcard(StudySet studySet) {
        studyFlashcard.loadStudySet(studySet);
        primaryStage.setScene(studyFlashcard.getScene());
    }

    void showStudyLearn(StudySet studySet) {
        studyLearn.loadStudySet(studySet);
        primaryStage.setScene(studyLearn.getScene());
    }

    void showStudyTest(StudySet studySet) {
        studyTest.loadStudySet(studySet);
        primaryStage.setScene(studyTest.getScene());
    }

    void endStudy() { showMainMenu(); }
}
