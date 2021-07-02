package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        UserService userHibernate = new UserServiceImpl();
        userHibernate.createUsersTable();
        userHibernate.saveUser("karin","bubkin",(byte)56);
        userHibernate.saveUser("bonya","pupkin",(byte)56);
        userHibernate.saveUser("lesya","ivanova",(byte)45);
        userHibernate.saveUser("luda","petrova",(byte)65);
        userHibernate.getAllUsers();
        userHibernate.cleanUsersTable();
        userHibernate.dropUsersTable();
    }
}
