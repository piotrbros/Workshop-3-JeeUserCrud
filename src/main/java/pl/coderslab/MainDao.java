package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.SQLException;

public class MainDao {
    public static void main(String[] args) {

        //Test create
//        UserDao userDao = new UserDao();
//        User user = new User();
//        user.setUserName("TEST UPDATE");
//        user.setEmail("TEST UPDATE3");
//        user.setPassword("testpass");
//        try {
//            userDao.create(user);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return;
//        }

        //Test read
//        try {
//            User user2 = userDao.read(1);
//            if(user == null) {
//                return;
//            } else {
//                System.out.println(user2);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //Test update
//        UserDao userDaoUpdate = new UserDao();
//        try {
//            User user3 = userDaoUpdate.read(4);
//            user3.setUserName("UPDATED NAME");
//            user3.setEmail("UPDATED EMAIL");
//            user3.setPassword("TEST");
//            userDaoUpdate.update(user3);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //Test delete
//        UserDao userDaoDelete = new UserDao();
//        try {
//            userDaoDelete.delete(5);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //Test findall
        UserDao userDaoFindAll = new UserDao();
        try {
            User[] allUsers = userDaoFindAll.findAll();
            for (User user : allUsers) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
