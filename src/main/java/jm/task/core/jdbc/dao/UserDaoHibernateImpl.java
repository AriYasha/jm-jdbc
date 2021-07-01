package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS User(\n" +
                "  Id   BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  Name VARCHAR (20)     NOT NULL,\n" +
                "  LastName VARCHAR (20) NOT NULL,\n" +
                "  Age  TINYINT              NOT NULL,\n" +
                "  PRIMARY KEY (Id)\n" +
                ");").executeUpdate();

    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
        tx1.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        System.out.println("User с именем - "+name+" добавлен в базу данных");
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = session.get(User.class,id);
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>)  Util.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        //String stringQuery = "DELETE FROM sun";
        session.createQuery("DELETE User").executeUpdate();
        tx1.commit();
        session.close();
    }
}
