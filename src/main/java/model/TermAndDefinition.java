package main.java.model;

import java.io.Serializable;

public class TermAndDefinition implements Serializable {
    public String term;
    public String definition;

    /**
     * Beallitja az osztalyban tarolt kifejezest es definiciot.
     * @param term kifejezes
     * @param definition definicio
     */
    public TermAndDefinition(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }
}
