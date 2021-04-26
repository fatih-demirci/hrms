package kodlamaioop;

import java.util.ArrayList;
import java.util.List;

public class EducatorManager {

    List<Educator> educators;

    public EducatorManager() {
        educators = new ArrayList<>();
    }

    public void addEducator(int id, String name, String image) {
        Educator educator = new Educator(id, name, image);
        educators.add(educator);
    }

    public List getEducators() {
        return educators;
    }
    
        public Educator getEducator(int id) {
        for (int i = 0; i < educators.size(); i++) {
            if (educators.get(i).getId() == id) {
                return educators.get(i);
            }
        }
        return null;
    }

}
