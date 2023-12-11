package main.scheduler.c195finalproject.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.scheduler.c195finalproject.builder.DialogBuilder;
import main.scheduler.c195finalproject.data.AppointmentQuery;
import main.scheduler.c195finalproject.list.AppointmentList;
import main.scheduler.c195finalproject.list.AppointmentCountList;
import main.scheduler.c195finalproject.list.ContactList;
import main.scheduler.c195finalproject.list.CustomerList;
import main.scheduler.c195finalproject.model.Appointment;
import main.scheduler.c195finalproject.model.AppointmentCount;
import main.scheduler.c195finalproject.utility.SceneSetter;

public class ReportMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableView<Appointment> customerReportTableView;

    @FXML
    private TableColumn<Appointment, Integer> customerCustomerIdCol;

    @FXML
    private TableColumn<Appointment, String> customerTitleCol;

    @FXML
    private TableColumn<Appointment, String> customerTypeCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> customerStartCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> customerEndCol;



    @FXML
    private TableView<AppointmentCount> typeReportTableView;

    @FXML
    private TableColumn<AppointmentCount, String> typeMonthCol;

    @FXML
    private TableColumn<AppointmentCount, String> typeTypeCol;

    @FXML
    private TableColumn<AppointmentCount, Integer> typeCountCol;



    @FXML
    private TableView<Appointment> contactReportTableView;

    @FXML
    private TableColumn<Appointment, Integer> contactAppointmentIdCol;

    @FXML
    private TableColumn<Appointment, String> contactTitleCol;

    @FXML
    private TableColumn<Appointment, String> contactTypeCol;

    @FXML
    private TableColumn<Appointment, String> contactDescriptionCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> contactStartCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> contactEndCol;

    @FXML
    private TableColumn<Appointment, Integer> contactCustomerIdCol;



    @FXML
    private ComboBox<String> customerComboPicker;

    @FXML
    private ComboBox<String> contactComboPicker;

    /**
     * Sets the customer report table view with appointments associated with the selected customer (LAMBDA PRESENT).
     *
     * Lambda is in use using the built-in Java Functional Interface PREDICATE (Boolean-value function), which filters the list based on the defined condition,
     * without the need of creating another list object.
     *
     * LAMBDA EXPRESSION is a more simple, modified version of the Lambda in use in the main menu, which only filters the list based on appointments with the matching Customer ID.
     *
     * @param event the action event triggering the set customer action
     */
    @FXML
    void onActionSetCustomer(ActionEvent event) {
        //custom label is set for the Tableview if no appointments are found for the selected customer to provide feedback to the user that the function worked.
        Label noAppointments = new Label("No Appointments Found for Customer");

        int customerId = CustomerList.lookupCustomerId(customerComboPicker.getSelectionModel().getSelectedItem());

        //A more simple, modified version of the Lambda in use in the main menu, which only filters the list based on appointments with the matching Customer ID.
        //LAMBDA JUSTIFICATION: It is easier to create a filter on the list of appointments rather than attempting to create a new list each time.
        Predicate<Appointment> filter = Appointment -> Appointment.getCustomerId() == customerId;

        customerReportTableView.setItems(AppointmentList.getAllAppointments().filtered(filter));

        if (AppointmentList.getAllAppointments().filtered(filter).isEmpty()) {
            customerReportTableView.setPlaceholder(noAppointments);
        }
    }

    /**
     * Filters the list of appointments based on the selected filter option (LAMBDA PRESENT).
     *
     * Lambda is in use using the built-in Java Functional Interface PREDICATE (Boolean-value function), which filters the list based on the defined condition,
     * without the need of creating another list object.
     *
     * LAMBDA EXPRESSION is a more simple, modified version of the Lambda in use in the main menu, which only filters the list based on appointments with the matching Contact ID.
     *
     * @param event the action event triggering the filter action
     */
    @FXML
    void onActionSetContact(ActionEvent event) {
        //custom label is set for the Tableview if no appointments are found for the selected customer to provide feedback to the user that the function worked.
        Label noAppointments = new Label("No Appointments Found for Contact");
        //get contactId using method,
        int contactId = ContactList.lookupContactId(contactComboPicker.getSelectionModel().getSelectedItem());

        //A more simple, modified version of the Lambda in use in the main menu, which only filters the list based on appointments with the matching Contact ID.
        //LAMBDA JUSTIFICATION: It is easier to create a filter on the list of appointments rather than attempting to create a new list each time.
        Predicate<Appointment> filter = Appointment -> Appointment.getContactId() == contactId;

        contactReportTableView.setItems(AppointmentList.getAllAppointments().filtered(filter));

        if (AppointmentList.getAllAppointments().filtered(filter).isEmpty()) {
            //if the list is empty, display the above label so the user understands that the search worked.
            contactReportTableView.setPlaceholder(noAppointments);
        }
    }


    @FXML
    void onActionRefresh(ActionEvent event) throws SQLException {
        //I personally like having a refresh button on any screen I am working on based on previous work experience.
        //It is best to work with the most up-to-date information possible to avoid duplicating work.
        try {
            AppointmentList.getAllAppointments().clear();
            AppointmentQuery.selectAll();

            customerComboPicker.setValue(null);
            contactComboPicker.setValue(null);

            AppointmentCountList.getAllAppointmentTypeTotals().clear();
            AppointmentQuery.selectCount();
            typeReportTableView.setItems(AppointmentCountList.getAllAppointmentTypeTotals());

            DialogBuilder.displayInformationMessage("Data Refreshed", "The Appointment Table has been refreshed");;
        }
        catch (SQLException error) {
            DialogBuilder.displayErrorMessage("Database Error", "The database returned the following information:" + error.getMessage());
        }

    }

    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        //first ask the user if they wish to exit, then exit if OK is selected.
        if (DialogBuilder.displayConfirmationMessage("Exiting Screen", "Returning to main window, continue?")) {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/main/scheduler/c195finalproject/AppointmentMainMenu.fxml"));
            stage.setScene(new Scene(scene));
            SceneSetter.prepareWindow(stage);
            stage.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize my custom report TableView, but don't set any items until a selection is made in the comboBox.
        customerCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        customerTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        customerStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        customerEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));


        try {
            //next initialize and set up the report for appointments by month/type.
            AppointmentCountList.getAllAppointmentTypeTotals().clear();
            AppointmentQuery.selectCount();
        }
        catch (SQLException error) {
            DialogBuilder.displayErrorMessage("Database Error", "The database returned the following information:" + error.getMessage());
        }

        typeReportTableView.setItems(AppointmentCountList.getAllAppointmentTypeTotals());

        typeMonthCol.setCellValueFactory(new PropertyValueFactory<>("monthName"));
        typeTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        typeCountCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        //initialize the Contact Report tableview, but don't set any items until a selection is made in the comboBox.
        contactAppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        contactTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        contactTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        contactStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        contactEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        contactCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        //initialize the comboBoxes.
        contactComboPicker.setItems(ContactList.getAllContactNames());
        customerComboPicker.setItems(CustomerList.getAllCustomerNames());

    }

}
