package ku.cs.shop.services;

import com.opencsv.CSVReader;
import ku.cs.shop.models.User;
import ku.cs.shop.models.UserList;

import java.io.*;

public class UserDataSource implements DataSource<UserList> {
    private String filename ;

    public UserDataSource(String filename) {
        this.filename = filename ;
    }

    @Override
    public UserList readData() {

        UserList userList = new UserList() ;

        try {
            FileReader fileReader = new FileReader(filename) ;
            CSVReader reader = new CSVReader(fileReader) ;
            String[] data = null ;

            while ((data = reader.readNext()) != null) {

                String userName = data[0].trim() ;
                String firstName = data[1].trim() ;
                String lastName = data[2].trim() ;
                String password = data[3].trim() ;
                String birthDay = data[4].trim() ;
                String birthMonth = data[5].trim() ;
                String birthYear = data[6].trim() ;
                String imageName = data[7].trim() ;
                String phone = data[8].trim() ;
                String sex = data[9].trim() ;
                String shopName = data[10].trim() ;

                User userInformation = new User(firstName, lastName, userName, password,
                        birthDay, birthMonth, birthYear, imageName, phone, sex, shopName) ;
                userList.addUser(userInformation);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Cannot read information in file " + filename);
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }

        return userList;
    }

    @Override
    public void writeData(UserList userList) {
        String path = filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            buffer.write(userList.toCsv());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                buffer.close();
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
