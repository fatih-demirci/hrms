package KodlamaioInheritance;

public class Student extends User {

    public Student(int id, String name, String surName, String email,UserManager userManager) {
        super(id, name, surName, email);
        userManager.addUser(this);
    }

    public Student(int id, String name, String surName, String email, String address, Contact contact, Course myCourses,UserManager userManager) {
        super(id, name, surName, email, address, contact, myCourses);
        userManager.addUser(this);
    }
}
