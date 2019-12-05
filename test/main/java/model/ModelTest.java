package main.java.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class ModelTest {

    Model model;

    /**
     * Felallitja a teszteloi kornyezetet.
     */
    @BeforeEach
    void setUp() {
        model = new Model();
    }

    /**
     * Teszteli a nameDoesNotExist() fuggveny megfelelo mukodeset.
     */
    @Test
    public void testNameDoesNotExist() {
        Assert.assertTrue("nameDoesNotExist does not work",
                model.nameDoesNotExist("random name", "random folder name"));
    }

    /**
     * Teszteli a createFolder() fuggveny megfelelo mukodeset.
     */
    @Test
    public void testCreateFolder() {
        model.createFolder("second folder");
        Assert.assertFalse("createFolder() didn't create a folder",
                model.nameDoesNotExist("second folder", "root"));
    }

    /**
     * Teszteli a createStudySet() fuggveny megfelelo mukodeset.
     */
    @Test
    public void testCreateStudySet() {
        model.createFolder("third folder");
        model.createStudySetInFolder("third set", "third folder");
        Assert.assertFalse("createStudySetInFolder() did not create a set",
                model.nameDoesNotExist("third set", "third folder"));
    }

    /**
     * Teszteli a removeStudySet() fuggveny megfelelo mukodeset.
     */
    @Test
    public void testRemoveStudySet() {
        model.createFolder("fourth folder");
        model.createStudySetInFolder("fourth set", "fourth folder");
        model.removeStudySet("fourth set", "fourth folder");
        Assert.assertTrue("removeStudySet() did not remove the set",
                model.nameDoesNotExist("fourth set", "fourth folder"));
    }

    /**
     * Teszteli a removeFolder() fuggveny megfelelo mukodeset.
     */
    @Test
    public void testRemoveFolder() {
        model.createFolder("fifth folder");
        model.removeFolder("fifth folder");
        Assert.assertTrue("removeFolder() did not remove the folder",
                model.nameDoesNotExist("fifth folder", "root"));
    }
}