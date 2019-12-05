package main.java.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TermAndDefinitionTest {

    /**
     * Teszteli a konstruktor megfelelo mukodeset.
     */
    @Test
    void testConstructor() {
        TermAndDefinition tad = new TermAndDefinition("term", "definition");
        Assert.assertEquals("term", tad.term);
        Assert.assertEquals("definition", tad.definition);
    }

}