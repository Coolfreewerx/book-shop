package ku.cs.shop.models;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users ;

    public UserList() { users = new ArrayList<>(); }

    public void addUser(User user) {
        users.add(user) ;
    }

    public String toCsv() {
        String result = "" ;
        for (User user: this.users) {
            result += user.toCsv() + "\n";
        }
        return result ;
    }
}
