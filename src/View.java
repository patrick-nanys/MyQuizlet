import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;

public class View {

    private Stage primaryStage;
    private MainMenu mainMenu;

    View(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MyQuizlet");
    }

    void setupMainMenu(Controller controller, ArrayList<Pair<String, String>> hierarchyMap) {
        mainMenu = new MainMenu(controller);
        mainMenu.loadFolderTree(hierarchyMap, controller);
        mainMenu.setupLeftMenu();
    }

    void showMainMenu() {
        primaryStage.setScene(new Scene(mainMenu, 800, 520));
        primaryStage.show();
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
}
