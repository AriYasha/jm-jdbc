package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao,AutoCloseable {

    private Connection connection;
    private PreparedStatement getAll = null;
    private PreparedStatement addUser = null;
    private PreparedStatement deleteById = null;
    private PreparedStatement deleteAllRecords = null;
    private PreparedStatement createTable = null;
    private PreparedStatement deleteTable = null;

    public UserDaoJDBCImpl() {
        try {
            connection = Util.getMySQLConnection();
            getAll = connection.prepareStatement("SELECT * FROM user");
            addUser = connection.prepareStatement("INSERT INTO user (Name, LastName, Age) VALUES (?, ?, ?)");
            deleteById = connection.prepareStatement("DELETE FROM user WHERE Id = ?");
            deleteAllRecords = connection.prepareStatement("DELETE FROM user ");
            createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS User(\n" +
                    "  Id   BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  Name VARCHAR (20)     NOT NULL,\n" +
                    "  LastName VARCHAR (20) NOT NULL,\n" +
                    "  Age  TINYINT              NOT NULL,\n" +
                    "  PRIMARY KEY (Id)\n" +
                    ");");
            deleteTable = connection.prepareStatement("DROP TABLE user");
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void createUsersTable() {
        try {
            if (createTable.executeUpdate() < 0) {
                createTable.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            if (createTable.executeUpdate() >= 0) {
                deleteTable.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            addUser.setString(1, name);
            addUser.setString(2, lastName);
            addUser.setByte(3, age);
            addUser.execute();
            System.out.println("User с именем - "+name+" добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            deleteById.setLong(1, id);
            deleteById.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try (ResultSet resultSet = getAll.executeQuery()){
            while (resultSet.next()){
                User user = new User();
                Long id = resultSet.getLong("Id");
                if (!resultSet.wasNull())  {
                    user.setId(id);
                }
                String name = resultSet.getString("Name");
                if (!resultSet.wasNull()) {
                    user.setName(name);
                }
                String lastName = resultSet.getString("LastName");
                if (!resultSet.wasNull()) {
                    user.setLastName(lastName);
                }
                byte age = resultSet.getByte("Age");
                if (!resultSet.wasNull()) {
                    user.setAge(age);
                }
                listUsers.add(user);
            }
            return listUsers;
        } catch (SQLException e) {
            e.printStackTrace();        }
        return listUsers;
    }

    public void cleanUsersTable() {
        try {
            deleteAllRecords.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws Exception {
        if(getAll != null){
            try {
                getAll.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(deleteTable != null){
            try {
                deleteTable.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (addUser != null){
            try {
                addUser.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(deleteAllRecords != null){
            try {
                deleteAllRecords.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(deleteById != null){
            try {
                deleteById.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(createTable != null){
            try {
                createTable.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
