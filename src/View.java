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

    /**
     * Beallitja a nezetben a szintert es beallitja az ablak cimet.
     * @param primaryStage szinter.
     */
    View(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MyQuizlet");
    }

    /**
     * Kijelzi a nezetet.
     */
    void showView() {
        primaryStage.show();
    }

    /**
     * Beallitja a fomenut a megadott hierarchia lista alapjan es megadja a fomenunek a kontrollert a gomb nyomasok
     * lekezelesehez.
     * @param controller kontroller
     * @param hierarchyList hierarchia lista
     */
    void setupMainMenu(Controller controller, ArrayList<Pair<String, String>> hierarchyList) {
        mainMenu = new MainMenu(controller);
        mainMenu.loadFolderTree(hierarchyList, controller);
        mainMenu.setupLeftMenu();
    }

    /**
     * Beallitja a szinterre a fomennut.
     */
    void showMainMenu() {
        primaryStage.setScene(mainMenu.getScene());
    }

    /**
     * Hozzzaadja a megadott nevu elemet a megadott nevu mappaba
     * @param treeItemValue elem neve
     * @param folderName mappa neve
     * @throws NullPointerException ha egy szettet probalunk letrehozni ugy, hogy egyik elem sincsen kivalasztva
     */
    void addItemToMainTree(String treeItemValue, String folderName) throws NullPointerException {
        mainMenu.addItemToTree(treeItemValue, folderName);
    }

    /**
     * Eltavolitja a megadott nevu elemet a megadott nevu mappabol
     * @param treeItemValue elem neve
     * @param folderName mappa neve
     * @throws NullPointerException ha egy szettet probalunk eltavolitani ugy, hogy egyik elem sincsen kivalasztva
     */
    void removeItemFromMainTree(String treeItemValue, String folderName) throws NullPointerException {
        mainMenu.removeItemFromTree(treeItemValue, folderName);
    }

    /**
     * Visszater a fomenuben aktualisan megjelenitett szettnek a kifeljezes-definicio listajaval.
     * @return kifejezes-definicio lista
     */
    ArrayList<TermAndDefinition> getDisplayedStudySetData() {
        return mainMenu.getDisplayedStudySetData();
    }

    /**
     * Betolti a fomenube a megadott szettet.
     * @param studySet szett
     */
    void loadStudySet(StudySet studySet) {
        mainMenu.loadStudySet(studySet);
    }

    /**
     * Visszater a fomenuben megjelenitett fa mappa strukturaval.
     * @return fa mappa struktura
     */
    TreeView<String> getFolderTree() {
        return mainMenu.getFolderTree();
    }

    /**
     * Letrehozza az egyes tanulo ablakokat.
     */
    void setupStudySections() {
        studyFlashcard = new StudyFlashcard(this);
        studyLearn = new StudyLearn(this);
        studyTest = new StudyTest(this);
    }

    /**
     * Az ablakban a megadott szettel a StudyFlashcard reszt jeleniti meg.
     * @param studySet szett
     */
    void showStudyFlashcard(StudySet studySet) {
        studyFlashcard.loadStudySet(studySet);
        primaryStage.setScene(studyFlashcard.getScene());
    }

    /**
     * Az ablakban a megadott szettel a StudyLearn reszt jeleniti meg.
     * @param studySet szett
     */
    void showStudyLearn(StudySet studySet) {
        studyLearn.loadStudySet(studySet);
        primaryStage.setScene(studyLearn.getScene());
    }

    /**
     * Az ablakban a megadott szettel a StudyTest reszt jeleniti meg.
     * @param studySet szett
     */
    void showStudyTest(StudySet studySet) {
        studyTest.loadStudySet(studySet);
        primaryStage.setScene(studyTest.getScene());
    }

    /**
     * A tanulas befejezese es ujra a fomenu megjelenitese.
     */
    void endStudy() { showMainMenu(); }
}
