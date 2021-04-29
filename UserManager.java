package KodlamaioInheritance;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserManager() {
        users = new ArrayList<>();
    }

    public void homework(String homework) {

    }

    public void addUser(User user) {
        users.add(user);
        System.out.println(user.getId() + " Id'li kullanıcının kaydı yapıldı.");
    }

    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.remove(i);
                users.add(user);
                System.out.println("Kullanıcı verileri güncellendi");
                return;
            }
        }
        System.out.println("Kullanıcı Yok");
    }

    public void deleteUser(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                System.out.println("Kullanıcı Silindi");
                return;
            }
        }
    }

}
