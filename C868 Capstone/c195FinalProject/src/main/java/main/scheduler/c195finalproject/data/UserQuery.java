package main.scheduler.c195finalproject.data;

import main.scheduler.c195finalproject.list.UserList;
import main.scheduler.c195finalproject.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * UserQuery is an abstract class that provides utility methods for querying user data from the database.
 *
 * The database used is SQL Workbench 8.0.26.
 */
public abstract class UserQuery {

    /**
     * Retrieves all user records from the USERS table in the database and uses the data to populate the UserList.
     *
     * @throws SQLException if a database access error occurs.
     */
    public static void selectAll() throws SQLException {
        String sql = "SELECT * FROM USERS";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("User_ID");
            String username = resultSet.getString("User_Name");
            String password = resultSet.getString("Password");

            User user = new User(id, username, password);
            UserList.add(user);
        }
    }
}
