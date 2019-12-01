package main.java.view;

import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import javafx.util.Pair;
import main.java.controller.Controller;
import main.java.model.StudySet;
import main.java.model.TermAndDefinition;

import java.util.ArrayList;

public class View {

    private Stage primaryStage;
    private MainMenu mainMenu;
    private StudyFlashcard studyFlashcard;
    private StudyLearn studyLearn;
    private StudyTest studyTest;

    /**
     * Beallitja a nezetben a szintert es beallitja az ablak cimet.
     * @param primaryStage szinter.
     */
    public View(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MyQuizlet");
    }

    /**
     * Kijelzi a nezetet.
     */
    public void showView() {
        primaryStage.show();
    }

    /**
     * Megjelenit egy felugro ablakot a megadott szoveggel.
     * @param text megjelenitett szoveg
     */
    public void displayAlertBox(String text) {
        AlertBox.display(text);
    }

    /**
     * Megjelenit egy felugro ablakot a megadott szoveggel és egy bemeneti mezovel.
     * @param text megjelenitett szoveg
     */
    public void displayPopUpBox(String text) {
        PopUpBox.display(text);
    }

    /**
     * Visszater a megjelenitett ablakban beirt szoveggel.
     * @return beirt szoveg
     */
    public String getEnteredTextPopUpBox() { return PopUpBox.getEnteredText(); }

    /**
     * Visszater, hogy meg lett-e nyomva a felugro ablakban az ok gomb.
     * @return meg lett-e nyomva
     */
    public boolean didPressOkPopUpBox() { return PopUpBox.didPressOk(); }

    /**
     * Beallitja a fomenut a megadott hierarchia lista alapjan es megadja a fomenunek a kontrollert a gomb nyomasok
     * lekezelesehez.
     * @param controller kontroller
     * @param hierarchyList hierarchia lista
     */
    public void setupMainMenu(Controller controller, ArrayList<Pair<String, String>> hierarchyList) {
        mainMenu = new MainMenu(controller);
        mainMenu.loadFolderTree(hierarchyList, controller);
        mainMenu.setupLeftMenu();
    }

    /**
     * Beallitja a szinterre a fomennut.
     */
    public void showMainMenu() {
        primaryStage.setScene(mainMenu.getScene());
    }

    /**
     * Hozzzaadja a megadott nevu elemet a megadott nevu mappaba
     * @param treeItemValue elem neve
     * @param folderName mappa neve
     * @throws NullPointerException ha egy szettet probalunk letrehozni ugy, hogy egyik elem sincsen kivalasztva
     */
    public void addItemToMainTree(String treeItemValue, String folderName) throws NullPointerException {
        mainMenu.addItemToTree(treeItemValue, folderName);
    }

    /**
     * Eltavolitja a megadott nevu elemet a megadott nevu mappabol
     * @param treeItemValue elem neve
     * @param folderName mappa neve
     * @throws NullPointerException ha egy szettet probalunk eltavolitani ugy, hogy egyik elem sincsen kivalasztva
     */
    public void removeItemFromMainTree(String treeItemValue, String folderName) throws NullPointerException {
        mainMenu.removeItemFromTree(treeItemValue, folderName);
    }

    /**
     * Visszater a fomenuben aktualisan megjelenitett szettnek a kifeljezes-definicio listajaval.
     * @return kifejezes-definicio lista
     */
    public ArrayList<TermAndDefinition> getDisplayedStudySetData() {
        return mainMenu.getDisplayedStudySetData();
    }

    /**
     * Betolti a fomenube a megadott szettet.
     * @param studySet szett
     */
    public void loadStudySet(StudySet studySet) {
        mainMenu.loadStudySet(studySet);
    }

    /**
     * Visszater a fomenuben megjelenitett fa mappa strukturaval.
     * @return fa mappa struktura
     */
    public TreeView<String> getFolderTree() {
        return mainMenu.getFolderTree();
    }

    /**
     * Letrehozza az egyes tanulo ablakokat.
     */
    public void setupStudySections() {
        studyFlashcard = new StudyFlashcard(this);
        studyLearn = new StudyLearn(this);
        studyTest = new StudyTest(this);
    }

    /**
     * Az ablakban a megadott szettel a main.java.view.StudyFlashcard reszt jeleniti meg.
     * @param studySet szett
     */
    public void showStudyFlashcard(StudySet studySet) {
        studyFlashcard.loadStudySet(studySet);
        primaryStage.setScene(studyFlashcard.getScene());
    }

    /**
     * Az ablakban a megadott szettel a main.java.view.StudyLearn reszt jeleniti meg.
     * @param studySet szett
     */
    public void showStudyLearn(StudySet studySet) {
        studyLearn.loadStudySet(studySet);
        primaryStage.setScene(studyLearn.getScene());
    }

    /**
     * Az ablakban a megadott szettel a main.java.view.StudyTest reszt jeleniti meg.
     * @param studySet szett
     */
    public void showStudyTest(StudySet studySet) {
        studyTest.loadStudySet(studySet);
        primaryStage.setScene(studyTest.getScene());
    }

    /**
     * A tanulas befejezese es ujra a fomenu megjelenitese.
     */
    void endStudy() { showMainMenu(); }
}
