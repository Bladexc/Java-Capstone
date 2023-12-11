package main.scheduler.c195finalproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.scheduler.c195finalproject.builder.DialogBuilder;
import main.scheduler.c195finalproject.builder.ErrorBuilder;
import main.scheduler.c195finalproject.data.AppointmentQuery;
import main.scheduler.c195finalproject.list.*;
import main.scheduler.c195finalproject.model.Appointment;
import main.scheduler.c195finalproject.utility.SceneSetter;
import main.scheduler.c195finalproject.utility.TimeConvert;
import main.scheduler.c195finalproject.utility.Validator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;


public class AddAppointmentMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField appointmentIdText;

    @FXML
    private TextField titleText;

    @FXML
    private TextField descriptionText;

    @FXML
    private TextField locationText;

    @FXML
    private TextField typeText;


    @FXML
    private ComboBox<String> contactNameComboPicker;

    @FXML
    private ComboBox<String> customerNameComboPicker;

    @FXML
    private ComboBox<String> userNameComboPicker;



    @FXML
    private ComboBox<LocalTime> startTimeComboPicker;

    @FXML
    private ComboBox<LocalTime> endTimeComboPicker;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;


    /**
     * Creates a new appointment using input from the user after performing some validation checks (LAMBDA PRESENT).
     *
     * Lambda is in use using the built-in Java Functional Interface PREDICATE (Boolean-value function), which filters the list based on the defined condition,
     * without the need of creating another list object.
     *
     * Another Lambda Expression is used to build a filter on the list of appointments using multiple checks,
     * first we get our matching customerId, then verify any appointments fall within the date range of the appointment we are trying to create.
     * LAMBDA JUSTIFICATION: It is easier to create a filter on the list of appointments rather than attempting to create a new list each time.
     * I did research and found the PREDICATE built in Java Interface along with its usage on the FILTER object and utilized it accordingly.
     *
     * @param event the action event triggering the save action.
     */
    @FXML
    void onActionSave(ActionEvent event) {
        //Primary save appointment method.  First variables are declared so all necessary information is in scope for try/catch blocks.
        String title = titleText.getText();
        String description = descriptionText.getText();
        String location = locationText.getText();
        String type = typeText.getText();

        String contact = contactNameComboPicker.getSelectionModel().getSelectedItem();
        String customer = customerNameComboPicker.getSelectionModel().getSelectedItem();
        String user = userNameComboPicker.getSelectionModel().getSelectedItem();

        LocalDate startDate = startDatePicker.getValue();
        LocalTime startTime = startTimeComboPicker.getSelectionModel().getSelectedItem();

        LocalDate endDate = endDatePicker.getValue();
        LocalTime endTime = endTimeComboPicker.getSelectionModel().getSelectedItem();

        try { //  first check is to verify all fields have information, if they do not, a custom error message is built based on which fields are blank.
            if (title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty() || contact == null || customer == null || user == null) {
                throw new NullPointerException();
            }
            //Declare our start/end Date and Time objects so we can run time checks or conversions for each.
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

            // simple check to make sure end time is after start time
            if (endDateTime.isBefore(startDateTime) || startTime.equals(endTime)) {
                throw new RuntimeException("End date is before start date!");
            }

            // simple check to make sure we cannot schedule appointments in the past.
            if (startDateTime.isBefore(LocalDateTime.now()) || endDateTime.isBefore(LocalDateTime.now())) {
                throw new RuntimeException("You must make appointments for future times and dates!");
            }

            //After verifying all fields are complete and correct, we begin by refreshing our appointment data.
            //refresh data in our program to make sure we are checking for overlap with the most up-to-date information possible.
            AppointmentList.getAllAppointments().clear();
            AppointmentQuery.selectAll();

            //declare ID fields here, so we also have their information to run Lambda expression checks for Appointment Overlap.
            //For ease of reading, the Contact, customer, and user fields display names, then these custom methods search for the ID field using the String names.
            int contactId = ContactList.lookupContactId(contact);
            int customerId = CustomerList.lookupCustomerId(customer);
            int userId = UserList.lookupUserId(user);

            //using a custom method, the start/end times are converted into ZonedDateTime objects, then converted to Eastern Time, then we extract
            //only the hour values to perform business hours checks.
            if (Validator.checkBusinessHours(startDateTime, endDateTime)) {
                throw new RuntimeException("Appointment is outside company business hours!");
            }
            //Another Lambda Expression is used to build a filter on the list of appointments using multiple checks,
            //first we get our matching customerId, then verify any appointments fall within the date range of the appointment we are trying to create.
            //LAMBDA JUSTIFICATION: It is easier to create a filter on the list of appointments rather than attempting to create a new list each time.
            //I did research and found the PREDICATE built in Java Interface along with its usage on the FILTER object and utilized it accordingly.
            Predicate<Appointment> filter = Appointment ->
                    Appointment.getCustomerId() == customerId && Appointment.getStartDateTime().isBefore(endDateTime) && Appointment.getEndDateTime().isAfter(startDateTime);

            //If our list has any results matching our Lambda, aka, if our list "IS NOT EMPTY", throw error.
            if(!AppointmentList.getAllAppointments().filtered(filter).isEmpty()) {
                throw new RuntimeException("Customer has another Overlapping Appointment at this time!");
            }

            //finally, we convert the time to UTC time, then pass the data to the query for database processing.
            int rowsAffected = AppointmentQuery.insert(title, description, location, type,
                    TimeConvert.fromLocalToUTC(startDateTime).toLocalDateTime(), TimeConvert.fromLocalToUTC(endDateTime).toLocalDateTime(), contactId, customerId, userId);

            // TimeConvert.fromLocalToUTC(startDateTime).toLocalDateTime()  TimeConvert.fromLocalToUTC(endDateTime).toLocalDateTime()
            //as long as a row was affected, we will reset our AppointmentList again, rebuild our appointment list, and then display Main screen.
            if (rowsAffected == 1) {
                DialogBuilder.displayConfirmationMessage("Appointment Added Successfully", "Appointment added, returning to main menu");

                AppointmentList.getAllAppointments().clear();
                AppointmentQuery.selectAll();

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/main/scheduler/c195finalproject/AppointmentMainMenu.fxml"));
                stage.setScene(new Scene(scene));
                SceneSetter.prepareWindow(stage);
                stage.show();
            }
            else {
                //if the appointment wasn't added for an unknown SQL reason, display some sort of error.
                DialogBuilder.displayErrorMessage("Appointment Not Added", "Appointment was not added, potential Database Communication Error");
            }

        }
        catch (NullPointerException error)
        {
            //catches our null or blank field errors, then builds a custom message for the user.
            ErrorBuilder.checkAppointmentFields(title, description, location, type, contact, customer, user, startDate, startTime, endDate, endTime);
            DialogBuilder.displayErrorMessage("Required Information not Complete", "Blank Fields Detected",
                    "Please fill out the following fields: \n\n" + ErrorBuilder.getErrorInfo().toString());
            ErrorBuilder.clearErrorInfo();

        }
        catch (RuntimeException error) {
            //Custom error message builder that displays the error message based on which exception was caught. Above code will simply throw a custom message
            //based on the type of error caught.
            DialogBuilder.displayErrorMessage("Date Range Error", error.getMessage());
        }
        catch (SQLException error) {
            DialogBuilder.displayErrorMessage("Database Error", error.getMessage());
        }
        catch (IOException error) {
            DialogBuilder.displayErrorMessage("IO Exception Error", error.getMessage());
        }

    }

    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        //prompts the user verifying they want to return to the main screen.
        if (DialogBuilder.displayConfirmationMessage("Exiting Screen", "You will lose unsaved data, continue?")) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/main/scheduler/c195finalproject/AppointmentMainMenu.fxml"));
            stage.setScene(new Scene(scene));
            SceneSetter.prepareWindow(stage);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Using a custom list of appointment times, populate both combo boxes with the appointment times.
        startTimeComboPicker.setItems(TimeList.getAllAppointmentTimes());
        endTimeComboPicker.setItems(TimeList.getAllAppointmentTimes());
        //Set the Combo Boxes to another custom List that displays information in NAME format instead of ID NUMBER format.
        contactNameComboPicker.setItems(ContactList.getAllContactNames());
        customerNameComboPicker.setItems(CustomerList.getAllCustomerNames());
        userNameComboPicker.setItems(UserList.getAllUserNames());

    }
}
