package main.scheduler.c195finalproject.data;

// make sure every import has java.sql, otherwise you will run into issues.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The JDBC class provides methods for connecting to a MySQL database and managing the database connection.
 *
 */
public class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    /**
     * Opens a connection to the MySQL database.
     * The connection should be opened only once, ideally during the program launch.
     *
     * To close the connection, call the {@link #closeConnection()} method.
     */
    public static void openConnection() {
        // the goal is to open the connect 1 time, in the launch. So go Open connection, launch program, then close connection in main method.
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch (Exception error) {
            // System.out.println("Error:" + e.getMessage());
            error.printStackTrace();
        }
    }

    /**
     * Closes the connection to the MySQL database.
     *
     * This method should be called when the application is closing.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch (Exception e) {
            //You want to catch this exception and then do nothing about it, because the only time you will run into the problem is when the application is closing.
        }
    }

    /**
     * Retrieves the current connection to the MySQL database.
     *
     * @return the current connection object
     */
    public static Connection getConnection() {
        //this method went unused in my application.
        return connection;
    }

}
