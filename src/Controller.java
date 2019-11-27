import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class Controller implements EventHandler<ActionEvent>, ChangeListener<TreeItem<String>> {
    private Model model;
    private View view;

    private TreeItem<String> displayedSet = null;

    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String name = ((Button) actionEvent.getSource()).getText();
        switch (name) {
            case "Create folder":
                PopUpBox.display("Create folder");
                createFolder();
                break;
            case "Create set":
                PopUpBox.display("Create set");
                createStudySet(view.getFolderTree());
                break;
            case "delete":
                deleteFromTree(view.getFolderTree());
                break;
        }
    }

    @Override
    public void changed(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> old_val, TreeItem<String> new_val) {
        differentTreeItemSelected(view.getFolderTree());
    }

    void saveDisplayedSet() {
        if (displayedSet != null) {
            String setName = displayedSet.getValue();
            String folderName = displayedSet.getParent().getValue();
            model.saveDisplayedStudySet(setName, folderName);
        }
    }

    private void createFolder() {
        if(PopUpBox.didPressOk()) {
            String folderName = PopUpBox.getEnteredText();
            if (!folderName.equals("")) {
                if (model.nameDoesNotExist(folderName, "root")) {
                    model.createFolder(folderName);
                } else {
                    AlertBox.display("Folder already exists!");
                }
            } else {
                AlertBox.display("Give a valid folder name!");
            }
        }
    }

    private void createStudySet(TreeView<String> tree) {
        if(PopUpBox.didPressOk()) {
            TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
            String setName = PopUpBox.getEnteredText();
            if (!setName.equals("")) {
                if (selectedItem != null) {
                    if (model.nameDoesNotExist(setName, selectedItem.getParent().getValue())) {
                        String folderName;
                        if (selectedItem.getParent() == tree.getRoot()) folderName = selectedItem.getValue();
                        else folderName = selectedItem.getParent().getValue();
                        model.createStudySetInFolder(setName, folderName);
                    } else {
                        AlertBox.display("Set already exists in folder!");
                    }
                } else {
                    AlertBox.display("Select to which folder you want to add the new set!");
                }
            } else {
                AlertBox.display("Give a valid set name!");
            }
        }
    }

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
}
