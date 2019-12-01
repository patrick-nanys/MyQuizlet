import java.io.Serializable;

class TermAndDefinition implements Serializable {
    String term;
    String definition;

    /**
     * Beallitja az osztalyban tarolt kifejezest es definiciot.
     * @param term kifejezes
     * @param definition definicio
     */
    TermAndDefinition(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }
}
