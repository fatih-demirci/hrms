package KodlamaioInheritance;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private final int id;
    private String image;
    private String title;
    private String description;
    private Instructor educator;
    private double price;
    private int complated;
    private List<User> users;

    public Course(int id, String image, String title, String description, Instructor educator, double price) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.educator = educator;
        this.price = price;
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instructor getEducator() {
        return educator;
    }

    public void setEducator(Instructor educator) {
        this.educator = educator;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getComplated() {
        return complated;
    }

    public void setComplated(int complated) {
        this.complated = complated;
    }

}
