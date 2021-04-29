package KodlamaioInheritance;

public class Instructor extends User {

    public Instructor(int id, String name, String surName, String email,UserManager userManager) {
        super(id, name, surName, email);
        userManager.addUser(this);
    }

    public Instructor(int id, String name, String surName, String email, String address, Contact contact, Course myCourses,UserManager userManager) {
        super(id, name, surName, email, address, contact, myCourses);
        userManager.addUser(this);
    }

}
