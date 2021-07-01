package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;


public class Main {
    public static void main(String[] args) {
//
//        try (UserServiceImpl userDaoJDBC = new UserServiceImpl()) {
//            userDaoJDBC.createUsersTable();
//            userDaoJDBC.saveUser("katya","polinaa",(byte) 6);
//            userDaoJDBC.saveUser("lina","marina",(byte) 89);
//            userDaoJDBC.saveUser("nik","luk",(byte) 64);
//            userDaoJDBC.saveUser("thebest","pit",(byte) 7);
//            List<User> allUsers = userDaoJDBC.getAllUsers();
//            userDaoJDBC.cleanUsersTable();
//            userDaoJDBC.dropUsersTable();
//        } catch (Exception e){
//            e.printStackTrace();
//        }


        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("karin","bubkin",(byte)56);
        userDaoHibernate.saveUser("bonya","pupkin",(byte)56);
        userDaoHibernate.saveUser("lesya","ivanova",(byte)45);
        userDaoHibernate.saveUser("luda","petrova",(byte)65);
        List<User> userList = userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
        //userDaoHibernate.removeUserById(3);
        //userDaoHibernate.cleanUsersTable();
       // List<User> userList = userDaoHibernate.getAllUsers();
        //System.out.println(userList.toString());



    }
}
