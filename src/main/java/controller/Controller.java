package main.java.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import main.java.model.Model;
import main.java.view.View;

public class Controller implements EventHandler<ActionEvent>, ChangeListener<TreeItem<String>> {
    private Model model;
    private View view;

    private TreeItem<String> displayedSet = null;

    /**
     * Beallitja a modell referenciat a kesobbi kommunikaciohoz.
     * @param model modell
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * Beallitja a nezet referenciat.
     * @param view nezet
     */
    public void linkView(View view) {
        this.view = view;
    }

    /**
     * Kezeli az egyes gombnyomasokat.
     * @param actionEvent esemeny
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        String name = ((Button) actionEvent.getSource()).getText();
        switch (name) {
            case "Create folder":
                view.displayPopUpBox("Create folder");
                createFolder();
                break;
            case "Create set":
                view.displayPopUpBox("Create set");
                createStudySet(view.getFolderTree());
                break;
            case "Delete":
                deleteFromTree(view.getFolderTree());
                break;
            case "Flashcards":
                studyFlashcards(view.getFolderTree());
                break;
            case "Learn":
                studyLearn(view.getFolderTree());
                break;
            case "Test":
                studyTest(view.getFolderTree());
                break;
        }
    }

    /**
     * Kezeli az egyes valtozasokat amik a kijelzett mappa fan tortennek.
     * @param observableValue megfigyelheto ertek
     * @param old_val regi ertek
     * @param new_val uj ertek
     */
    @Override
    public void changed(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> old_val, TreeItem<String> new_val) {
        differentTreeItemSelected(view.getFolderTree());
    }

    /**
     * Az aktualisan kijelzett szettet elmenti a modellben.
     */
    public void saveDisplayedSet() {
        if (displayedSet != null) {
            String setName = displayedSet.getValue();
            String folderName = displayedSet.getParent().getValue();
            model.saveDisplayedStudySet(setName, folderName);
        }
    }

    /**
     * Letrehoz egy uj mappat a modellben, ami aztan frissiti a kijelzett fat is.
     */
    private void createFolder() {
        if(view.didPressOkPopUpBox()) {
            String folderName = view.getEnteredTextPopUpBox();
            if (!folderName.equals("")) {
                if (model.nameDoesNotExist(folderName, "root")) {
                    model.createFolder(folderName);
                } else {
                    view.displayAlertBox("Folder already exists!");
                }
            } else {
                view.displayAlertBox("Give a valid folder name!");
            }
        }
    }

    /**
     * Letrehoz egy uj szettet a modellben, ami aztan frissiti a kijelzett fat is.
     * @param tree kijelzett fa struktura a mappakrol es szettekrol
     */
    private void createStudySet(TreeView<String> tree) {
        if(view.didPressOkPopUpBox()) {
            TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
            String setName = view.getEnteredTextPopUpBox();
            if (!setName.equals("")) {
                if (selectedItem != null) {
                    String folderName;
                    if (selectedItem.getParent() == tree.getRoot()) folderName = selectedItem.getValue();
                    else folderName = selectedItem.getParent().getValue();
                    if (model.nameDoesNotExist(setName, folderName)) {
                        model.createStudySetInFolder(setName, folderName);
                    } else {
                        view.displayAlertBox("Set already exists in folder!");
                    }
                } else {
                    view.displayAlertBox("Select to which folder you want to add the new set!");
                }
            } else {
                view.displayAlertBox("Give a valid set name!");
            }
        }
    }

    /**
     *
     * @param tree kijelzett fa struktura a mappakrol es szettekrol
     */
    private void deleteFromTree(TreeView<String> tree) {
        TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
        displayedSet = null;
        if(selectedItem != null) {
            if (selectedItem.getParent() == tree.getRoot()) {
                String folderName = selectedItem.getValue();
                model.removeFolder(folderName);
            } else {
                String folderName = selectedItem.getParent().getValue();
                String fileName   = selectedItem.getValue();
                model.removeStudySet(fileName, folderName);
            }
        }
    }

    /**
     *
     * @param tree kijelzett fa struktura a mappakrol es szettekrol
     */
    private void differentTreeItemSelected(TreeView<String> tree) {
        TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
        if(!isFolder(selectedItem, tree)) {
            saveDisplayedSet();

            displayedSet = selectedItem;
            String setName = selectedItem.getValue();
            String folderName = selectedItem.getParent().getValue();
            model.loadStudySet(setName, folderName);
        }
    }

    /**
     * Visszater azzal, hogy igaz-e, hogy a megadott elem egy mappa.
     * @param item a kerdeses elem
     * @param tree kijelzett fa struktura a mappakrol es szettekrol
     * @return igaz-e, hogy mappa a fenti elem
     */
    private boolean isFolder(TreeItem<String> item, TreeView<String> tree) {
        return item.getParent() == tree.getRoot();
    }
/*
    private void deleteFile(String path) throws FileSystemException {
        File file = new File(FOLDER_DIR + File.separator + path);
        if(file.exists()) {
            if(!file.delete()) {
                File[] files = file.listFiles();
                for(File f : files) {
                    if(!f.delete())
                        throw new FileSystemException(file.getAbsolutePath());
                }
            }
        }
    }
*/

    /**
     * Elmenti az aktualisan kijelzett szettet, ezutan megjeleniti a main.java.view.StudyFlashcard reszt.
     * @param tree kijelzett fa struktura a mappakrol es szettekrol
     */
    private void studyFlashcards(TreeView<String> tree) {
        if(displayedSet != null) {
            saveDisplayedSet();
            view.showStudyFlashcard(model.getStudySetFromFolder(displayedSet.getValue(), displayedSet.getParent().getValue()));
        } else {
            view.displayAlertBox("Select a set before trying to study!");
        }
    }

    /**
     * Elmenti az aktualisan kijelzett szettet, ezutan megjeleniti a main.java.view.StudyLearn reszt.
     * @param tree kijelzett fa struktura a mappakrol es szettekrol
     */
    private void studyLearn(TreeView<String> tree) {
        if(displayedSet != null) {
            saveDisplayedSet();
            if(model.getStudySetFromFolder(displayedSet.getValue(), displayedSet.getParent().getValue()).getTermsAndDefinitions().size() >= 4) {
                view.showStudyLearn(model.getStudySetFromFolder(displayedSet.getValue(), displayedSet.getParent().getValue()));
            } else {
                view.displayAlertBox("You need to have at least four terms and definitions to study this way!");
            }
        } else {
            view.displayAlertBox("Select a set before trying to study!");
        }
    }

    /**
     * Elmenti az aktualisan kijelzett szettet, ezutan megjeleniti a main.java.view.StudyTest reszt.
     * @param tree kijelzett fa struktura a mappakrol es szettekrol
     */
    private void studyTest(TreeView<String> tree) {
        if(displayedSet != null) {
            saveDisplayedSet();
            if(model.getStudySetFromFolder(displayedSet.getValue(), displayedSet.getParent().getValue()).getTermsAndDefinitions().size() >= 15) {
                view.showStudyTest(model.getStudySetFromFolder(displayedSet.getValue(), displayedSet.getParent().getValue()));
            } else {
                view.displayAlertBox("You need to have at least fifteen terms and definitions to study this way!");
            }
        } else {
            view.displayAlertBox("Select a set before trying to study!");
        }
    }
}
