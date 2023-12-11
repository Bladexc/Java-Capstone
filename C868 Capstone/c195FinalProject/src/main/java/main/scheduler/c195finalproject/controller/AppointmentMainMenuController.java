package main.scheduler.c195finalproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.scheduler.c195finalproject.builder.DialogBuilder;
import main.scheduler.c195finalproject.data.AppointmentQuery;
import main.scheduler.c195finalproject.data.CustomerQuery;
import main.scheduler.c195finalproject.list.AppointmentList;
import main.scheduler.c195finalproject.list.ContactList;
import main.scheduler.c195finalproject.list.CustomerList;
import main.scheduler.c195finalproject.list.UserList;
import main.scheduler.c195finalproject.model.Appointment;
import main.scheduler.c195finalproject.utility.SceneSetter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AppointmentMainMenuController implements Initializable {

    // Dont forget your @FXML tags!

    Stage stage;
    Parent scene;

    @FXML
    private TableView<Appointment> appointmentTableView;

    @FXML
    private TableColumn<Appointment, Integer> appointmentContactCol;

    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerIdCol;

    @FXML
    private TableColumn<Appointment, String> appointmentDescriptionCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> appointmentEndDateCol;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIdCol;

    @FXML
    private TableColumn<Appointment, String> appointmentLocationCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> appointmentStartDateCol;

    @FXML
    private TableColumn<Appointment, String> appointmentTitleCol;

    @FXML
    private TableColumn<Appointment, String> appointmentTypeCol;

    @FXML
    private TableColumn<Appointment, Integer> appointmentUserIdCol;


    @FXML
    private RadioButton filterMonthRBtn;

    @FXML
    private RadioButton filterWeekRBtn;

    @FXML
    private RadioButton filterAllRBtn;

    @FXML
    private ToggleGroup filterRBtn;

    /**
     * Filters the list of appointments based on the selected filter option (LAMBDA PRESENT).
     *
     * Lambda is in use using the built-in Java Functional Interface PREDICATE (Boolean-value function), which filters the list based on the defined condition,
     * without the need of creating another list object.
     *
     * 1st LAMBDA EXPRESSION is used to shorten the code to filter the list of appointments based on month.
     *
     * 2nd LAMBDA EXPRESSION is a much more complex filter using Locale API to first get the current week, then the current week based on each item
     * in the appointments list, then return any items in the list that match the condition.
     *
     * @param event the action event triggering the filter action
     */
    @FXML
    void onActionFilterAppointment(ActionEvent event) {
        //use of Lambda expressions are justified because this is the only place in the program where this functionality is implemented.
        LocalDate currentTime = LocalDate.now();

        if (filterAllRBtn.isSelected()) { // if selected, clear all filters and go back to the default list.
            appointmentTableView.setItems(AppointmentList.getAllAppointments());
        }
        if (filterMonthRBtn.isSelected()) {
            // using a LAMBDA EXPRESSION to shorten the code to filter the list of appointments based on month.
            //The interface used is the Predicate Java interface, which returns true or false based on a condition.
            //LAMBDA JUSTIFICATION: It is easier to create a filter on the list of appointments rather than attempting to create a new list each time.
            Predicate<Appointment> filter = Appointment -> Appointment.getStartDateTime().getMonth() == currentTime.getMonth();
            appointmentTableView.setItems(AppointmentList.getAllAppointments().filtered(filter));
        }
        if (filterWeekRBtn.isSelected()) { // using a LAMBDA EXPRESSION to shorten the code to filter the list of appointments based on week.
            Predicate<Appointment> filter = Appointment -> {
                //The interface used is the Predicate Java interface, which returns true or false based on a condition.
                //set a much more complex filter using Locale API to first get the current week, then the current week based on each item
                //in the appointments list, then return any items in the list that match the condition.
                //LAMBDA JUSTIFICATION: It is easier to create a filter on the list of appointments rather than attempting to create a new list each time.
                int currentWeek = currentTime.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
                int appointmentWeek = Appointment.getStartDateTime().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
                return appointmentWeek == currentWeek;
            };
            appointmentTableView.setItems(AppointmentList.getAllAppointments().filtered(filter));
        }
    }

    @FXML
    void onActionCreateAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/main/scheduler/c195finalproject/AddAppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        SceneSetter.prepareWindow(stage);
        stage.show();
    }

    @FXML
    void onActionModifyAppointment(ActionEvent event) throws IOException { // sends Appointment Information to ModifyAppointmentController
        try {
            FXMLLoader appointmentLoader = new FXMLLoader();
            appointmentLoader.setLocation(getClass().getResource("/main/scheduler/c195finalproject/ModifyAppointmentMenu.fxml"));
            appointmentLoader.load();

            ModifyAppointmentMenuController MPController = appointmentLoader.getController();
            MPController.sendAppointment(appointmentTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = appointmentLoader.getRoot();
            stage.setScene(new Scene(scene));
            SceneSetter.prepareWindow(stage);
            stage.show();

        }
        catch (NullPointerException error) { // catches the nullPointer error and will display a dialog box informing the user to make a selection
            DialogBuilder.displayErrorMessage("No Selection Made","Please select an appointment to modify.");
        }
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

        try {
            Appointment selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();

            if (selectedAppointment == null) { // if null selection, throw error.
                throw new NullPointerException();
            }

            int id = selectedAppointment.getId();
            String type = selectedAppointment.getType();

            if (DialogBuilder.displayConfirmationMessage("Deleting Appointment", "Are you sure you want to delete the selected Appointment?")) {

                //refresh appointment List to have most up-to-date information.
                AppointmentList.getAllAppointments().clear();
                AppointmentQuery.selectAll();

                int rowsAffected = AppointmentQuery.delete(id);

                if (rowsAffected == 1) {
                    AppointmentList.getAllAppointments().clear();
                    AppointmentQuery.selectAll();
                    appointmentTableView.refresh();

                    DialogBuilder.displayInformationMessage("Appointment Deleted",  "Appointment ID:     " + id + "\nAppointment Type: " + type, "Appointment deleted Successfully");

                }
                else { // if no rows were affected, display an error to the user.
                    throw new SQLException();
                }
            }
        }
        catch (NullPointerException error) {
            DialogBuilder.displayErrorMessage("No Selection Made","Please select an appointment to Delete");
        }
        catch (SQLException error) {
            DialogBuilder.displayErrorMessage("Database Error", "The database failed to respond.");
        }


    }

    @FXML
    void onActionDisplayCustomers(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/main/scheduler/c195finalproject/CustomerMenu.fxml"));
        stage.setScene(new Scene(scene));
        SceneSetter.prepareWindow(stage);
        stage.show();
    }

    @FXML
    void onActionDisplayReports(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/main/scheduler/c195finalproject/ReportMenu.fxml"));
        stage.setScene(new Scene(scene));
        SceneSetter.prepareWindow(stage);
        stage.show();
    }

    @FXML
    void onActionRefresh(ActionEvent event) {
        //I personally like having a refresh button on any screen I am working on based on previous work experience.
        //It is best to work with the most up-to-date information possible to avoid duplicating work.
        try {
            // perform full refresh, clear list, rebuild list, refresh tableview.
            // Because no data will be lost, we do not need to provide the customer a confirmation dialog box.
            AppointmentList.getAllAppointments().clear();
            AppointmentQuery.selectAll();
            appointmentTableView.refresh();

            //provide feedback to the user that the request was successful
            DialogBuilder.displayInformationMessage("Appointment Data Refreshed", "Notification", "The Appointment Table has been Refreshed");
        }
        catch (SQLException error) { // catch any other SQL errors, like if the database isn't reachable.
            DialogBuilder.displayErrorMessage("Database Error", "The database returned the following information:" + error.getMessage());
        }
    }

    @FXML
    void onActionExit(ActionEvent event) {
        if (DialogBuilder.displayConfirmationMessage("Terminating Program", "Are you sure you want to exit?")) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // move selectAll queries to this line to save space,

        appointmentTableView.setItems(AppointmentList.getAllAppointments());

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartDateCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        appointmentEndDateCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        appointmentCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        filterAllRBtn.setSelected(true);



    }
}
