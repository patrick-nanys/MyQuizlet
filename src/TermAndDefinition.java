import java.io.Serializable;

public class TermAndDefinition implements Serializable {
    String term;
    String definition;

    public TermAndDefinition(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }
}
