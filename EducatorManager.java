package kodlamaioop;

import java.util.ArrayList;
import java.util.List;


public class EducatorManager {
    List<Educator> educators;
    public EducatorManager(){
    educators=new ArrayList<>();
    }
    
    public void addEducator(String name, String image){
    Educator educator = new Educator(name, image);
    educators.add(educator);
    }
    
    public List getEducators(){
    return educators;
    }
    
}
