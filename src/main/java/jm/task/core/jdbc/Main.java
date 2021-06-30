package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        try (UserServiceImpl userDaoJDBC = new UserServiceImpl()) {
            userDaoJDBC.createUsersTable();
            userDaoJDBC.saveUser("katya","polinaa",(byte) 6);
            userDaoJDBC.saveUser("lina","marina",(byte) 89);
            userDaoJDBC.saveUser("nik","luk",(byte) 64);
            userDaoJDBC.saveUser("thebest","pit",(byte) 7);
            List<User> allUsers = userDaoJDBC.getAllUsers();
            userDaoJDBC.cleanUsersTable();
            userDaoJDBC.dropUsersTable();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
