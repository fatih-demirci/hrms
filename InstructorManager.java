package KodlamaioInheritance;

public class InstructorManager extends UserManager {

    @Override
    public void homework(String homework){
        System.out.println("Ödev verildi "+ homework);
    }

}
