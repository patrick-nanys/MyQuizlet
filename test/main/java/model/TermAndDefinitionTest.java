package main.java.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermAndDefinitionTest {

    @Test
    void testConstructor() {
        TermAndDefinition tad = new TermAndDefinition("term", "definition");
        Assert.assertEquals("term", tad.term);
        Assert.assertEquals("definition", tad.definition);
    }

}