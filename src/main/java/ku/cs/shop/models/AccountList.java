package ku.cs.shop.models;

import java.util.ArrayList;

public class AccountList {

    private ArrayList<Account> accounts ;
    private Account currentAccount ;

    public AccountList() { accounts = new ArrayList<>(); }

    public Account getCurrentAccount() {
        return currentAccount ;
    }

    public void addAccount(Account account) {
        accounts.add(account) ;
    }

    //ตรวจสอบ userName ว่าซ้ำมั้ย
    public boolean checkUserNameHaveUsed(String userName) {
        for (Account account: this.accounts) {
            if (account.getUserName().equals(userName) ) {
                return true;
            }
        }
        return false;
    }

    //ตรวจสอบ shopName ว่าซ้ำมั้ย
    public boolean checkShopNameHaveUsed(String shopName) {
        for (Account account: this.accounts) {
            if (account.getShopName().equals(shopName) ) {
                return true;
            }
        }
        return false;
    }

    public Account login(String userName, String password) {
        for (Account account: this.accounts) {
            if (account.getUserName().equals(userName)) {
                if (account.getPassword().equals(password)) {
                    currentAccount = account ;
                    return account ;
                }
            }
        }
        return null ;
    }

//    public ArrayList<UserAccount> getAllUserAccount() {
//        ArrayList<UserAccount> userAccounts ;
//
//    }

    public String toCsv() {
        String result = "" ;
        for (Account account: this.accounts) {
            result += account.toCsv() + "\n";
        }
        return result ;
    }
}
