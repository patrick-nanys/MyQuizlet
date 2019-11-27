import java.io.Serializable;
import java.util.ArrayList;

public class Folder implements Serializable {
    private ArrayList<StudySet> studySets;

    Folder() {
        studySets = new ArrayList<>();
    }

    void addStudySet(String nameOfSet) {
        StudySet studySet = new StudySet(nameOfSet);
        studySets.add(studySet);
    }

    StudySet getStudySet(String setName) {
        for(StudySet ss : studySets) {
            if(ss.getName().equals(setName)) {
                return ss;
            }
        }
        return null;
    }

    void removeStudySet(String setName) {
        for(StudySet ss : studySets) {
            if(ss.getName().equals(setName)) {
                studySets.remove(ss);
                break;
            }
        }
    }

    boolean containsStudySet(String setName) {
        for(StudySet ss : studySets) {
            if(ss.getName().equals(setName))
                return true;
        }
        return false;
    }

    ArrayList<String> getSetNames() {
        ArrayList<String> setNames = new ArrayList<>();
        for(StudySet ss : studySets) {
            setNames.add(ss.getName());
        }
        return setNames;
    }

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
