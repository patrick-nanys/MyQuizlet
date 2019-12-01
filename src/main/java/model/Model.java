package main.java.model;

import javafx.util.Pair;
import main.java.view.View;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Model {
    private TreeMap<String, Folder> folders;
    private transient View view;

    /**
     * Beallitja az osztaly valtozoit.
     */
    public Model() {
        folders = new TreeMap<>();
    }

    /**
     * Beallitja a nezet referenciat.
     * @param view nezet
     */
    public void linkView(View view) {
        this.view = view;
    }

    /**
     * Fajlba menti a szetteket es a mappakat.
     */
    public void saveSets() {
        try {
            FileOutputStream f = new FileOutputStream("data");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(folders);
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Kiolvassa fajlbol az elmentett szettekeet es mappakat.
     */
    public void loadSavedSets() {
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

    /**
     * Letrehoz egy magadott nevu mappat a strukturaban.
     * @param folderName mappa neve
     */
    public void createFolder(String folderName) {
        Folder folder = new Folder();
        folders.put(folderName, folder);
        try {
            view.addItemToMainTree(folderName, "root");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Letrehoz egy megadott nevu szettet a megadott nevu mappaban.
     * @param setName szett neve
     * @param folderName mappa neve
     */
    public void createStudySetInFolder(String setName, String folderName) {
        if(folders.containsKey(folderName)) {
            folders.get(folderName).addStudySet(setName);
            try {
                view.addItemToMainTree(setName, folderName);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Letrehoz egy hierarchia listat a mappakbol es szettekbol.
     * @return hiererhia lista
     */
    public ArrayList<Pair<String, String>> getHierarchyList() {
        ArrayList<Pair<String, String>> hierarchy = new ArrayList<>();
        for(String folderName : folders.keySet()) {
            for(String setName : folders.get(folderName).getSetNames())
                hierarchy.add(new Pair<>(folderName, setName));
            if(folders.get(folderName).getSetNames().size() == 0)
                hierarchy.add(new Pair<>(folderName, ""));
        }
        return hierarchy;
    }

    /**
     * Visszater a megadott nevu szettel a megadott nevu mappabol.
     * @param setName szett neve
     * @param folderName mappa neve
     * @return szett
     */
    public StudySet getStudySetFromFolder(String setName, String folderName) {
        return folders.get(folderName).getStudySet(setName);
    }

    /**
     * Eltavolitja a megadott nevu mappat es annak az osszes elemet a modelbol.
     * @param folderName mappa neve
     */
    public void removeFolder(String folderName) {
        folders.remove(folderName);
        try {
            view.removeItemFromMainTree(folderName, "root");
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Eltavolitja a megadott nevu szettet a megadott nevu mappabol a modelbol
     * @param setName szett neve
     * @param folderName mappa neve
     */
    public void removeStudySet(String setName, String folderName) {
        if(folders.containsKey(folderName)) {
            folders.get(folderName).removeStudySet(setName);
            try {
                view.removeItemFromMainTree(setName, folderName);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Megadja, hogy a megadott nevu elem letezik-e a megadott nevu mappaban.
     * @param name elem neve
     * @param folderName mappa neve
     * @return letezik-e
     */
    public boolean nameDoesNotExist(String name, String folderName) {
        if(folderName.equals("root")) {
            return !folders.containsKey(name);
        } else {
            return !folders.containsKey(folderName) || !folders.get(folderName).containsStudySet(name);
        }
    }

    /**
     * Elmenti a modellben az eppen kijelzett szettet a megadott nevvel a megadott mappaba.
     * @param setName szett neve
     * @param folderName mappa neve
     */
    public void saveDisplayedStudySet(String setName, String folderName) {
        ArrayList<TermAndDefinition> tads = view.getDisplayedStudySetData();
        StudySet studySet = new StudySet(setName);
        for(TermAndDefinition tad : tads)
            studySet.add(tad);
        folders.get(folderName).setStudySet(setName, studySet);
    }

    /**
     * Betolti a nezetbe a megadott nevu szettet a megadott mappabol.
     * @param setName szett neve
     * @param folderName mappa neve
     */
    public void loadStudySet(String setName, String folderName) {
        StudySet studySet = folders.get(folderName).getStudySet(setName);
        view.loadStudySet(studySet);
    }
}
