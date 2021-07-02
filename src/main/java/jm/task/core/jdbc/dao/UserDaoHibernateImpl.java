package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
<<<<<<< HEAD
import org.hibernate.SessionFactory;
=======
>>>>>>> 94fb0aaa75591dc62a409f98582c871913a8fa7a
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
<<<<<<< HEAD
        String createTable = "CREATE TABLE IF NOT EXISTS User(\n" +
=======
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS User(\n" +
>>>>>>> 94fb0aaa75591dc62a409f98582c871913a8fa7a
                "  Id   BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  Name VARCHAR (20)     NOT NULL,\n" +
                "  LastName VARCHAR (20) NOT NULL,\n" +
                "  Age  TINYINT              NOT NULL,\n" +
                "  PRIMARY KEY (Id)\n" +
<<<<<<< HEAD
                ");";
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createSQLQuery(createTable).addEntity(User.class);
        query.executeUpdate();
        tx1.commit();
        session.close();
=======
                ");").executeUpdate();
>>>>>>> 94fb0aaa75591dc62a409f98582c871913a8fa7a

    }

    @Override
    public void dropUsersTable() {
<<<<<<< HEAD
        String drop = "DROP TABLE IF EXISTS User;";
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createSQLQuery(drop).addEntity(User.class);
        query.executeUpdate();
=======
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
>>>>>>> 94fb0aaa75591dc62a409f98582c871913a8fa7a
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
