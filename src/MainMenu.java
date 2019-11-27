import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.*;

class MainMenu {

    private Scene scene;
    private BorderPane layout;
    private Button createFolder;
    private Button createSet;
    private TreeView<String> tree;
    private Button delete;
    private Button study;
    private StudySetBox studySetBox;
    private Button createNewElement;

    MainMenu(Controller controller) {
        layout = new BorderPane();
        createButtons(controller);

        studySetBox = new StudySetBox();
        studySetBox.setPadding(new Insets(10, 10, 10, 10));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(studySetBox);
        scrollPane.setFitToWidth(true);
        layout.setCenter(scrollPane);

        scene = new Scene(layout, 800, 520);
        scene.getStylesheets().add("MainMenuStyles.css");
    }

    void setupLeftMenu() {

        VBox leftMenu = new VBox();
        leftMenu.setSpacing(5);
        leftMenu.setPadding(new Insets(10, 10, 10, 10));

        leftMenu.getChildren().addAll(createFolder, createSet, new Separator(), tree, delete, study);

        layout.setLeft(leftMenu);
    }

    Scene getScene() {
        return scene;
    }

    private void addSetElement() {
        studySetBox.getChildren().remove(createNewElement);
        SetElement setElement = new SetElement(new TermAndDefinition("", ""), studySetBox);
        studySetBox.getChildren().add(setElement);
        studySetBox.getChildren().add(createNewElement);
    }

    private void createButtons(Controller controller) {
        createFolder = new Button("Create folder");
        createFolder.setOnAction(controller);

        createSet = new Button("Create set");
        createSet.setOnAction(controller);

        delete = new Button("Delete");
        delete.setId("delete-button");
        delete.setOnAction(controller);

        study = new Button("Study");
        study.setOnAction(controller);

        createFolder.setMaxWidth(Double.MAX_VALUE);
        createSet.setMaxWidth(Double.MAX_VALUE);
        delete.setMaxWidth(Double.MAX_VALUE);
        study.setMaxWidth(Double.MAX_VALUE);

        createNewElement = new Button("Create new element");
        createNewElement.setOnAction(actionEvent -> addSetElement());
    }

    void loadFolderTree(ArrayList<Pair<String, String>> treeHierarchy, Controller controller) {

        TreeItem<String> root = new TreeItem<>("root");
        root.setExpanded(true);

        Set<String> distinctFolders = new TreeSet<>();

        for(Pair<String, String> entry : treeHierarchy) {
            if(!distinctFolders.contains(entry.getKey())) {
                distinctFolders.add(entry.getKey());
                TreeItem<String> newFolder = new TreeItem<>(entry.getKey());
                root.getChildren().add(newFolder);
            }
            if(!entry.getValue().equals("")) {
                TreeItem<String> newSet = new TreeItem<>(entry.getValue());
                for (TreeItem<String> ti : root.getChildren()) {
                    if (ti.getValue().equals(entry.getKey())) {
                        ti.getChildren().add(newSet);
                        break;
                    }
                }
            }
        }

        tree = new TreeView<>(root);
        tree.setShowRoot(false);

        tree.getSelectionModel().selectedItemProperty().addListener(controller);

    }

    void loadStudySet(StudySet studySet) {

        studySetBox.getChildren().clear();

        for(TermAndDefinition tad : studySet.getTermsAndDefinitions()) {
            SetElement setElement = new SetElement(tad, studySetBox);
            studySetBox.getChildren().add(setElement);
        }

        studySetBox.getChildren().add(createNewElement);
    }

    void addItemToTree(String treeItemValue, String folderName) throws NullPointerException {
        if(folderName.equals("root")) {
            tree.getRoot().getChildren().add(new TreeItem<>(treeItemValue));
        } else {
            TreeItem<String> item = getTreeItem(folderName, tree.getRoot());
            if(item != null) {
                item.getChildren().add(new TreeItem<>(treeItemValue));
            } else {
                throw new NullPointerException();
            }
        }
    }

    void removeItemFromTree(String treeItemValue, String folderName) throws NullPointerException {
        if(folderName.equals("root")) {
            TreeItem<String> item = getTreeItem(treeItemValue, tree.getRoot());
            tree.getRoot().getChildren().remove(item);
        } else {
            TreeItem<String> folder = getTreeItem(folderName, tree.getRoot());
            if(folder != null) {
                TreeItem<String> item = getTreeItem(treeItemValue, folder);
                if(item != null) folder.getChildren().remove(item);
                else             throw new NullPointerException();
            } else {
                throw new NullPointerException();
            }
        }
    }

    private TreeItem<String> getTreeItem(String treeItemValue, TreeItem<String> treeFolder) {
        for(TreeItem<String> ti : treeFolder.getChildren()) {
            if(ti.getValue().equals(treeItemValue))
                return ti;
        }
        return null;
    }

    ArrayList<TermAndDefinition> getDisplayedStudySetData() {
        studySetBox.getChildren().remove(createNewElement);

        ArrayList<TermAndDefinition> tads = new ArrayList<>();
        for(Node node : studySetBox.getChildren()) {
            SetElement element = (SetElement) node;
            tads.add(new TermAndDefinition(element.getTerm(), element.getDefinition()));
        }
        return tads;
    }

    TreeView<String> getFolderTree() {
        return tree;
    }
}