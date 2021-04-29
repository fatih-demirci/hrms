package KodlamaioInheritance;

public class User {

    private final int id;
    private String name;
    private String surName;
    private String email;
    private String address;
    private Contact contact;
    private Course myCourses;

    public User(int id, String name, String surName, String email) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.email = email;
    }

    public User(int id, String name, String surName, String email, String address, Contact contact, Course myCourses) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.address = address;
        this.contact = contact;
        this.myCourses = myCourses;
    }

    public Course getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(Course myCourses) {
        this.myCourses = myCourses;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
