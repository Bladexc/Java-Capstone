package main.scheduler.c195finalproject.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.scheduler.c195finalproject.Application;
import main.scheduler.c195finalproject.builder.DialogBuilder;
import main.scheduler.c195finalproject.builder.LogFileBuilder;
import main.scheduler.c195finalproject.data.*;
import main.scheduler.c195finalproject.list.*;
import main.scheduler.c195finalproject.model.Appointment;
import main.scheduler.c195finalproject.utility.LoginAction;
import main.scheduler.c195finalproject.utility.SceneSetter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class LoginController implements Initializable {

    Stage stage;
    Parent scene;

    ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("main/scheduler/c195finalproject/Nat", Locale.getDefault());

    @FXML
    private Label loginLabel;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Label zoneIdText;

    @FXML
    private Label zoneIdLabel;

    @FXML
    private Button loginButton;

    @FXML
    void onActionLogin(ActionEvent event) throws IOException, SQLException {
        // I will comment this out for ease of testing.
        try {
            String username = usernameText.getText();
            String password = passwordText.getText();

            if (LoginAction.login(username, password)) {
                //Log builder code that will log a successful login
                LogFileBuilder.loginSucceeded(username);

                //it seems this isn't working properly
                int userId = UserList.lookupUserId(username);

                //To protect data, we will only populate the rest of the lists once the user has successfully logged in.
                ContactQuery.selectAll();
                CountryQuery.selectAll();
                CustomerQuery.selectAll();
                DivisionQuery.selectAll();
                AppointmentQuery.selectAll();
                AppointmentQuery.selectCount();
                TypeQuery.selectAll();

                //build combo box lists
                ContactList.buildContactNameList();
                CustomerList.buildCustomerNameList();
                UserList.buildUserNameList();
                TimeList.buildAppointmentTimes();
                TypeList.buildTypeNameList();

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/main/scheduler/c195finalproject/AppointmentMainMenu.fxml"));
                stage.setScene(new Scene(scene));
                SceneSetter.prepareWindow(stage);
                stage.show();

                LoginAction.appointmentLoginCheck();
            }
            else {
                LogFileBuilder.loginFailed(username);
                DialogBuilder.displayErrorMessage(defaultResourceBundle.getString("login.failed"), defaultResourceBundle.getString("invalid.username.password"));

            }
        }
        catch (SQLException error) {
            DialogBuilder.displayErrorMessage("Database Error", "The database returned the following information:" + error.getMessage());
        }
        catch (Exception error) {

        }

/*
        NOTE TO EVALUATORS, I KEPT THIS CODE SNIPPET IN INTENTIONALLY, IN CASE I NEED TO TEST MY CODE LATER.

        IF NEEDED, COMMENT OUT THE ABOVE TRY CATCH BLOCK AND UNCOMMENT THIS CODE TO REMOVE THE LOGIN PROCESS.

        Simply click login to be brought to the main screen.


        ContactQuery.selectAll();

        CountryQuery.selectAll();

        CustomerQuery.selectAll();

        DivisionQuery.selectAll();

        AppointmentQuery.selectAll();

        AppointmentQuery.selectCount();

        //build combo box lists.
        TimeList.buildAppointmentTimes();
        ContactList.buildContactNameList();
        CustomerList.buildCustomerNameList();
        UserList.buildUserNameList();

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/main/scheduler/c195finalproject/AppointmentMainMenu.fxml"));
        stage.setScene(new Scene(scene));
        SceneSetter.prepareWindow(stage);
        stage.show();

        //INSERT OUR APPOINTMENT CHECKER ONCE WE KNOW WHAT USERID WE ARE WORKING WITH.
        DialogBuilder.displayInformationMessage("Sample Pop-up", "Sample appointment check pop-up");*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // get zoneID API here, also detect and set language here as well.

        //USED FOR TESTING FRENCH RESOURCE BUNDLE.

        ZoneId zone = ZoneId.systemDefault();

        zoneIdText.setText(zone.toString());

        try {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("main/scheduler/c195finalproject/Nat", Locale.getDefault());

                loginLabel.setText(defaultResourceBundle.getString("login"));
                loginButton.setText(defaultResourceBundle.getString("login"));
                zoneIdLabel.setText(defaultResourceBundle.getString("zone.id"));

                usernameText.setPromptText(defaultResourceBundle.getString("username"));
                passwordText.setPromptText(defaultResourceBundle.getString("password"));
            }
        }
        catch (MissingResourceException error) {
            //do nothing with this error so program execution can proceed.
        }
    }

}