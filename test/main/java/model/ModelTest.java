package main.java.model;

import javafx.stage.Stage;
import main.java.view.View;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class ModelTest {

    Model model;

    @BeforeEach
    void setUp() {
        model = new Model();
    }

    @Test
    public void testNameDoesNotExist() {
        Assert.assertTrue("nameDoesNotExist does not work",
                model.nameDoesNotExist("random name", "random folder name"));
    }

    @Test
    public void testCreateFolder() {
        model.createFolder("second folder");
        Assert.assertFalse("createFolder() didn't create a folder",
                model.nameDoesNotExist("second folder", "root"));
    }

    @Test
    public void testCreateStudySet() {
        model.createFolder("third folder");
        model.createStudySetInFolder("third set", "third folder");
        Assert.assertFalse("createStudySetInFolder() did not create a set",
                model.nameDoesNotExist("third set", "third folder"));
    }

    @Test
    public void testRemoveStudySet() {
        model.createFolder("fourth folder");
        model.createStudySetInFolder("fourth set", "fourth folder");
        model.removeStudySet("fourth set", "fourth folder");
        Assert.assertTrue("removeStudySet() did not remove the set",
                model.nameDoesNotExist("fourth set", "fourth folder"));
    }

    @Test
    public void testRemoveFolder() {
        model.createFolder("fifth folder");
        model.removeFolder("fifth folder");
        Assert.assertTrue("removeFolder() did not remove the folder",
                model.nameDoesNotExist("fifth folder", "root"));
    }
}