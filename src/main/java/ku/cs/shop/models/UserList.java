package ku.cs.shop.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users ;
    private User currentUser ;

    public UserList() { users = new ArrayList<>(); }

    public User getCurrentUser() {
        return currentUser;
    }

    public void addUser(User user) {
        users.add(user) ;
    }

    //ตรวจสอบ username ว่าซ้ำมั้ย
    public boolean checkUserNameHaveUsed(String userName) {
        for (User user: this.users) {
            if (user.getUserName().equals(userName) ) {
                return true;
            }
        }
        return false;
    }

    public User login(String userName, String password) {
        for (User user: this.users) {
            if (user.getUserName().equals(userName)) {
                if (user.getPassword().equals(password)) {
                    currentUser = user ;
                    return user ;
                }
            }
        }
        return null ;
    }

    public String toCsv() {
        String result = "" ;
        for (User user: this.users) {
            result += user.toCsv() + "\n";
        }
        return result ;
    }
}
