package kodlamaioop;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class KodlamaioOOP {

    public static void main(String[] args) {
        Educator enginDemirog = new Educator("Engin Demiroğ", "Educator Image");
        CourseManager courseManager = new CourseManager();
        courseManager.addCourse(1, "C# Image",
                "Yazılım Geliştirici Yetiştirme Kampı (C# + ANGULAR)",
                "2 ay sürecek ücretsiz ve profesyonel bir programla, sıfırdan yazılım geliştirme öğreniyoruz.",
                enginDemirog,
                120);
        courseManager.addCourse(2, "Java Image",
                "Yazılım Geliştirici Yetiştirme Kampı (JAVA + REACT)",
                "JAVA Course Descriptoin",
                enginDemirog,
                0);
        courseManager.addCourse(3, "Introduction Image",
                "Programlamaya Giriş İçin Temel Kurs",
                "PYTHON, JAVA, C# gibi tüm programlama dilleri için temel programlama mantığını anlaşılır örneklerle öğrenin.",
                enginDemirog,
                0);
        courseManager.setComplated(2, 25);
        List<Course> cources = new ArrayList<Course>();
        cources = courseManager.getListCources();
        for (Course course : cources) {
            System.out.println(course.getImage());
            System.out.println(course.getTitle());
            System.out.println(course.getDescription());
            System.out.print(course.getEducator().getImage() + " " + course.getEducator().getName() + " ");
            if (course.getComplated() == 0) {
                if (course.getPrice() == 0) {
                    System.out.print("ÜCRETSİZ");
                } else {
                    System.out.print(course.getPrice()+" ");
                }

            } else {
                System.out.print("%"+course.getComplated()+" TAMAMLANDI");
            }
            System.out.println("");
        }

    }

}
