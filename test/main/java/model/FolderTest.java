package main.java.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FolderTest {

    Folder folder;

    @BeforeEach
    void setUp() {
        folder = new Folder();
    }

    @Test
    void getStudySet() {
        Assert.assertNull(folder.getStudySet("random set"));
    }

    @Test
    void testAddStudySet() {
        folder.addStudySet("study set");
        Assert.assertEquals("study set",
                folder.getStudySet("study set").getName());
    }

    @Test
    void testContainsStudySet() {
        Assert.assertFalse(folder.containsStudySet("never existed"));
        folder.addStudySet("exists");
        Assert.assertTrue(folder.containsStudySet("exists"));
    }

    @Test
    void removeStudySet() {
        folder.addStudySet("some name");
        folder.removeStudySet("some name");
        Assert.assertFalse(folder.containsStudySet("some name"));
    }

    @Test
    void testGetSetNames() {
        ArrayList<String> setNames = new ArrayList<>();
        Folder f = new Folder();
        for (int i = 0; i < 10; i++) {
            String name = "set name";
            setNames.add(name);
            folder.addStudySet(name);
        }
        Assert.assertEquals(setNames, folder.getSetNames());
    }

    @Test
    void testSetStudySet() {
        folder.addStudySet("original set");
        folder.setStudySet("original set", new StudySet("modified set"));
        Assert.assertTrue(folder.containsStudySet("modified set"));
    }
}