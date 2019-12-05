package main.java.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class StudySetTest {

    StudySet studySet;

    /**
     * Felallitja a teszteloi kornyezetet.
     */
    @BeforeEach
    void setUp() {
        studySet = new StudySet("name");
    }

    /**
     * Teszteli az add() fuggveny megfelelo mukodeset.
     */
    @Test
    void testAdd() {
        studySet.add(new TermAndDefinition("term", "definition"));
        Assert.assertEquals(studySet.getTermsAndDefinitions().size(), 1);
        Assert.assertEquals(studySet.getTermsAndDefinitions().get(0).term, "term");
        Assert.assertEquals(studySet.getTermsAndDefinitions().get(0).definition, "definition");
    }

    /**
     * Teszteli a getName() fuggveny megfelelo mukodeset.
     */
    @Test
    void testGetName() {
        Assert.assertEquals("name", studySet.getName());
    }

    /**
     * Teszteli a getTermsAndDefinitions() fuggveny megfelelo mukodeset.
     */
    @Test
    void testGetTermsAndDefinitions() {
        ArrayList<TermAndDefinition> tads = new ArrayList<>();
        StudySet ss = new StudySet("name");
        for (int i = 0; i < 10; i++) {
            TermAndDefinition tad = new TermAndDefinition("term", "definition");
            tads.add(tad);
            ss.add(tad);
        }
        Assert.assertEquals(tads, ss.getTermsAndDefinitions());
    }
}