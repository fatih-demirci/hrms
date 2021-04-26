package kodlamaioop;

public class Course {

    private int id;
    private String image;
    private String title;
    private String description;
    private Educator educator;
    private double price;
    private int complated;

    public Course(int id, String image, String title, String description, Educator educator, double price) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.educator = educator;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Educator getEducator() {
        return educator;
    }

    public void setEducator(Educator educator) {
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
