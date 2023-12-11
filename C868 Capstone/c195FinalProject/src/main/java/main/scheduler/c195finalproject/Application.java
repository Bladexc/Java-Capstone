package main.scheduler.c195finalproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.scheduler.c195finalproject.data.JDBC;
import main.scheduler.c195finalproject.data.UserQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This Application Class defines a program that serves as a customer and appointment management System that handles several types of data verification.
 *
 * JavaDoc Directory:  Attached as Separate ZIP File.
 * Put path here 
 *
 * The Application class is the main entry point for the JavaFX application.
 * It initializes the user interface and sets up the initial stage, which is a LOGIN screen, which contains fields that can translate from English to French.
 *
 * @author Blake Heller
 *
 */
public class Application extends javafx.application.Application {

    /**
     * The start method is called when the JavaFX application is launched.
     * It sets up the initial stage and loads the LoginMenu.fxml file.
     *
     * @param stage the primary stage for this application.
     * @throws IOException if an I/O error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("LoginMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);

        //translate the welcome message on the Login screen based on customer's language.
        ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("main/scheduler/c195finalproject/Nat", Locale.getDefault());
        stage.setTitle(defaultResourceBundle.getString("welcome"));

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method is the entry point of the Java application.
     * It opens the connection to the database, retrieves the UserList in preparation for Login Attempts, launches the JavaFX application, and closes the database connection.
     *
     * @param args the command-line arguments.
     * @throws SQLException if a database access error occurs.
     */
    public static void main(String[] args) throws SQLException {
       // Locale.setDefault(new Locale("fr"));

        //open connection to database
        JDBC.openConnection();

        //Load the UserList in preparation for our login attempt.
        UserQuery.selectAll();

        launch();

        JDBC.closeConnection();
    }
}