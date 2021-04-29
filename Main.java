package KodlamaioInheritance;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        InstructorManager instructorManager = new InstructorManager();
        CourseManager courseManager = new CourseManager();
        UserManager userManager = new UserManager();

        Instructor instructor1 = new Instructor(1, "Eğitmen Ad", "Eğitmen Soyad", "email@email.com", userManager);
        Instructor instructor2 = new Instructor(2, "Eğitmen Ad2", "Eğitmen Soyad2", "email2@email.com", userManager);

        Contact contact = new Contact("Başlık", "Mesaj");
        instructor1.setContact(contact);

        Student student1 = new Student(3, "Öğrenci Ad", "Öğrenci Soyad", "ogrmail@mail.com", userManager);
        Student student2 = new Student(4, "Öğrenci Ad2", "Öğrenci Soyad2", "ogrmail2@mail.com", userManager);
        Student student3 = new Student(5, "Öğrenci Ad3", "Öğrenci Soyad3", "ogrmail3@mail.com", userManager);

        courseManager.addCourse(1, "Kurs Resmi1", "Kurs Başlığı1", "Kurs açıklaması1", instructor1, 0);
        courseManager.addCourse(2, "Kurs Resmi2", "Kurs Başlığı2", "Kurs açıklaması2", instructor2, 0);

        courseManager.addUser(courseManager.getCourse(1), student1);
        courseManager.addUser(courseManager.getCourse(1), student2);
        courseManager.addUser(courseManager.getCourse(2), student1);
        courseManager.addUser(courseManager.getCourse(2), student2);
        courseManager.addUser(courseManager.getCourse(2), student3);

        List<User> course1users = courseManager.listUser(1);

        System.out.println("Kurs 1 Öğrenci listesi");
        for (User user : course1users) {
            System.out.println(user.getName());
        }

        List<User> course2users = courseManager.listUser(2);

        System.out.println("Kurs 2 Öğrenci listesi");
        for (User user : course2users) {
            System.out.println(user.getName());
        }

        System.out.println("Eğitmen ad iletişim bilgileri");
        System.out.println(instructor1.getContact().getSubject() + "\n" + instructor1.getContact().getMessage());

        instructorManager.homework("Verilen ödev");
        studentManager.homework("Verilen ödev");

        System.out.println("Tüm kullanıcılar");
        List<User> allUsers = userManager.getUsers();
        for (User user : allUsers) {
            System.out.println(user.getName());
        }

    }

}
