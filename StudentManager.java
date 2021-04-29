package KodlamaioInheritance;

public class StudentManager extends UserManager {

    @Override
    public void homework(String homework) {
        System.out.println("Ödev teslim edildi " + homework);
    }

}
