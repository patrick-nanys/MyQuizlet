import java.io.Serializable;
import java.util.ArrayList;

class Folder implements Serializable {
    private ArrayList<StudySet> studySets;

    /**
     * Letrehozza az osztaly valtozoit.
     */
    Folder() {
        studySets = new ArrayList<>();
    }

    /**
     * Hozzaad egy szettet a mappahoz.
     * @param nameOfSet szett neve
     */
    void addStudySet(String nameOfSet) {
        StudySet studySet = new StudySet(nameOfSet);
        studySets.add(studySet);
    }

    /**
     * Visszater a megadott nevu szettel, ha van olyan a mappaban.
     * @param setName szett neve
     * @return szett, ha ne letezik akkor null
     */
    StudySet getStudySet(String setName) {
        for(StudySet ss : studySets) {
            if(ss.getName().equals(setName)) {
                return ss;
            }
        }
        return null;
    }

    /**
     * Eltavolitja a megadott nevu szettet a mappabol.
     * @param setName szett neve
     */
    void removeStudySet(String setName) {
        for(StudySet ss : studySets) {
            if(ss.getName().equals(setName)) {
                studySets.remove(ss);
                break;
            }
        }
    }

    /**
     * Visszater, hogy letezik-e a megadott szett a mappaban, vagy sem.
     * @param setName szett neve
     * @return igaz, ha benne van, kulonben hamis
     */
    boolean containsStudySet(String setName) {
        for(StudySet ss : studySets) {
            if(ss.getName().equals(setName))
                return true;
        }
        return false;
    }

    /**
     * Visszater egy listaval a mappaban levo szettek neveirol
     * @return lista
     */
    ArrayList<String> getSetNames() {
        ArrayList<String> setNames = new ArrayList<>();
        for(StudySet ss : studySets) {
            setNames.add(ss.getName());
        }
        return setNames;
    }

    /**
     * A megadott szettnevvel beteszi a megadott szettet a mappaba.
     * @param setName szett neve
     * @param studySet szett
     */
    void setStudySet(String setName, StudySet studySet) {
        int i = 0;
        for(StudySet ss : studySets) {
            if(ss.getName().equals(setName)) {
                studySets.set(i, studySet);
                break;
            }
            ++i;
        }
    }
}
