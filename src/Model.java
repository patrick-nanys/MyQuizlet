import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Model {
    private TreeMap<String, Folder> folders;
    private transient View view;

    Model(View view) {
        folders = new TreeMap<>();
        this.view = view;
    }

    void saveSets() {
        try {
            FileOutputStream f = new FileOutputStream("data");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(folders);
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    void loadSavedSets() {
        File file = new File("data");
        if(file.exists()) {
            try {
                FileInputStream f = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(f);
                folders = (TreeMap<String, Folder>) in.readObject();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    void createFolder(String folderName) {
        if (nameDoesNotExist(folderName, "root")) {
            //File file = new File(FOLDER_DIR + File.separator + folderName);
            //if (!file.exists())
                //file.mkdir();
            Folder folder = new Folder();
            folders.put(folderName, folder);
            try {
                view.addItemToMainTree(folderName, "root");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            AlertBox.display("Folder already exists!");
        }
    }

    void createStudySetInFolder(String setName, String folderName) {
        folders.get(folderName).addStudySet(setName);
        try {

            view.addItemToMainTree(setName, folderName);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Pair<String, String>> getHierarchyList() {
        ArrayList<Pair<String, String>> hierarchy = new ArrayList<>();
        for(String folderName : folders.keySet()) {
            for(String setName : folders.get(folderName).getSetNames())
                hierarchy.add(new Pair<>(folderName, setName));
            if(folders.get(folderName).getSetNames().size() == 0)
                hierarchy.add(new Pair<>(folderName, ""));
        }
        return hierarchy;
    }

    StudySet getStudySetFromFolder(String setName, String folderName) {
        return folders.get(folderName).getStudySet(setName);
    }

    void removeFolder(String folderName) {
        folders.remove(folderName);
        try {
            view.removeItemFromMainTree(folderName, "root");
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
    }

    void removeStudySet(String setName, String folderName) {
        folders.get(folderName).removeStudySet(setName);
        try {
            view.removeItemFromMainTree(setName, folderName);
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
    }

    boolean nameDoesNotExist(String name, String folderName) {
        if(folderName.equals("root")) {
            return !folders.containsKey(name);
        } else {
            return !folders.get(folderName).containsStudySet(name);
        }
    }

    void saveDisplayedStudySet(String setName, String folderName) {
        ArrayList<TermAndDefinition> tads = view.getDisplayedStudySetData();
        StudySet studySet = new StudySet(setName);
        for(TermAndDefinition tad : tads)
            studySet.add(tad);
        folders.get(folderName).setStudySet(setName, studySet);
    }

    void loadStudySet(String setName, String folderName) {
        StudySet studySet = folders.get(folderName).getStudySet(setName);
        view.loadStudySet(studySet);
    }
}
