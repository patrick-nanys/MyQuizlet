import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class StudySet implements Serializable {
    private String name;
    private ArrayList<TermAndDefinition> termsAndDefinitions = new ArrayList<>();

    /**
     * Letrehozza és beallitja a szett nevet.
     * @param name szett neve
     */
    StudySet(String name) {
        this.name = name;
    }

    /**
     * Hozzaad egy kifejezes-definicio part a szetthez.
     * @param tad kifejezes-definicio par
     */
    void add(TermAndDefinition tad) {
        termsAndDefinitions.add(tad);
    }

    /**
     * Gette a szett nevére.
     * @return szett neve
     */
    String getName() {
        return name;
    }

    /**
     * Visszater egy listaval amiben benne van a szettben levo osszes kifejezes-definicio par
     * @return lista a kifejezes-definicio parokkal
     */
    ArrayList<TermAndDefinition> getTermsAndDefinitions() {
        return termsAndDefinitions;
    }
}
