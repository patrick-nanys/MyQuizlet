import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;

class View {

    private Stage primaryStage;
    private MainMenu mainMenu;
    private StudyFlashcard studyFlashcard;

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

    void setupStudyFlashcard() {
        studyFlashcard = new StudyFlashcard(this);
    }

    void loadStudyFlashcard(StudySet studySet) {
        studyFlashcard.loadStudySet(studySet);
    }

    void showStudyFlashcard() {
        primaryStage.setScene(studyFlashcard.getScene());
    }

    void endStudyFlashcard() {
        showMainMenu();
    }
}
