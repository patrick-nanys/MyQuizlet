import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class StudySet implements Serializable {
    private String name;
    private ArrayList<TermAndDefinition> termsAndDefinitions = new ArrayList<>();

    StudySet(String name) {
        this.name = name;
    }

    void add(TermAndDefinition tad) {
        termsAndDefinitions.add(tad);
    }

    String getName() {
        return name;
    }

    ArrayList<TermAndDefinition> getTermsAndDefinitions() {
        return termsAndDefinitions;
    }
}
