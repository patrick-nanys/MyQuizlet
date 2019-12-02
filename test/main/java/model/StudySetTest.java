package main.java.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudySetTest {

    StudySet studySet;

    @BeforeEach
    void setUp() {
        studySet = new StudySet("name");
    }

    @Test
    void testAdd() {
        studySet.add(new TermAndDefinition("term", "definition"));
        Assert.assertEquals(studySet.getTermsAndDefinitions().size(), 1);
        Assert.assertEquals(studySet.getTermsAndDefinitions().get(0).term, "term");
        Assert.assertEquals(studySet.getTermsAndDefinitions().get(0).definition, "definition");
    }

    @Test
    void testGetName() {
        Assert.assertEquals("name", studySet.getName());
    }

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