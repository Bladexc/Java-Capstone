package main.scheduler.c195finalproject.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.scheduler.c195finalproject.builder.DialogBuilder;
import main.scheduler.c195finalproject.builder.ErrorBuilder;
import main.scheduler.c195finalproject.data.AppointmentQuery;
import main.scheduler.c195finalproject.data.CustomerQuery;
import main.scheduler.c195finalproject.list.*;
import main.scheduler.c195finalproject.model.*;
import main.scheduler.c195finalproject.utility.SceneSetter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CustomerMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField customerIdText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField postalText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField customerSearchText;



    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, Integer> customerIdCol;

    @FXML
    private TableColumn<Customer, String> customerNameCol;

    @FXML
    private TableColumn<Customer, String> customerAddressCol;

    @FXML
    private TableColumn<Customer, String> customerPostalCol;

    @FXML
    private TableColumn<Customer, String> customerCountryCol;

    @FXML
    private TableColumn<Customer, String> customerDivisionCol;

    @FXML
    private TableColumn<Customer, String> customerPhoneCol;

    @FXML
    private TableColumn<Customer, String> customerTypeCol;



    @FXML
    private ComboBox<String> countryComboPicker;

    @FXML
    private ComboBox<String> divisionComboPicker;

    @FXML
    private ComboBox<String> typeComboPicker;

    @FXML
    void onEmptyReset(KeyEvent event) {

        String searchCustomer = customerSearchText.getText();

        if (searchCustomer.isEmpty()) {
            customerTableView.setItems(CustomerList.getAllCustomers());
        }

    }

    //Completed search code to search through the customer object and return any field that matches the search term.
    //I experimented with a different coding style by putting my braces on new fields
    @FXML
    void onKeyPressEnterSearch(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER)
        {

            String search = customerSearchText.getText();

            Predicate<Customer> filter = Customer ->
            {
                String name = Customer.getName();
                String address = Customer.getAddress();
                String postalCode = Customer.getPostalCode();
                String phone = Customer.getPhone();
                String country = Customer.getCountry();
                String division = Customer.getDivision();

                if (Customer instanceof Client)
                {
                    String type = ((Client) Customer).getType();

                    return name.contains(search) || address.contains(search) || postalCode.contains(search) || phone.contains(search) ||
                            country.contains(search) || division.contains(search) || type.contains(search);

                } else if (Customer instanceof Vendor)
                {
                    String type = ((Vendor) Customer).getType();
                    return name.contains(search) || address.contains(search) || postalCode.contains(search) || phone.contains(search) ||
                            country.contains(search) || division.contains(search) || type.contains(search);
                }
                else
                {
                    return false;
                }
            };

            if (CustomerList.getAllCustomers().filtered(filter).isEmpty())
            {
                DialogBuilder.displayErrorMessage("Search Completed", "No search results found");
            }
            else
            {
                customerTableView.setItems(CustomerList.getAllCustomers().filtered(filter));
            }
        }
    }

    @FXML
    void onActionSetDivision(ActionEvent event) {
        //Method that handles setting the division combobox.
        //First get the selected country name,
        String country = countryComboPicker.getSelectionModel().getSelectedItem();
        //then use a custom method that will use the country name as a filter to only display divisions that match the selected country.
        DivisionList.buildDivisionNameList(country);
        divisionComboPicker.setItems(DivisionList.getAllDivisionNames());
    }


    @FXML
    void onClickSelectCustomer(MouseEvent event) {
        try {
            //set a new selectedCustomer object with data pulled by the selected item in the tableview
            Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

            //set fields on click
            customerIdText.setText(String.valueOf(selectedCustomer.getId()));
            nameText.setText(selectedCustomer.getName());
            phoneText.setText(selectedCustomer.getPhone());
            addressText.setText(selectedCustomer.getAddress());
            postalText.setText(selectedCustomer.getPostalCode());

            countryComboPicker.setValue(selectedCustomer.getCountry());
            divisionComboPicker.setValue(selectedCustomer.getDivision());

            if (selectedCustomer instanceof Client) {
                typeComboPicker.setValue((((Client) selectedCustomer).getType()));
            }
            else if (selectedCustomer instanceof Vendor) {
                typeComboPicker.setValue((((Vendor) selectedCustomer).getType()));
            }

        }
        catch (NullPointerException error) {
         //do nothing with error to handle a bug with the tableview, where if you clicked in an area of the tableview without selecting a customer,
         // the log would throw an unhandled exception.
        }
    }

    @FXML
    void onActionClearSelection(ActionEvent event) {
        //clear each field and the selection in the tableview to prepare to add a new customer.
        customerIdText.clear();
        nameText.clear();
        phoneText.clear();
        addressText.clear();
        postalText.clear();

        countryComboPicker.setValue(null);
        divisionComboPicker.setValue(null);
        typeComboPicker.setValue(null);

        customerTableView.getSelectionModel().clearSelection();
    }

    @FXML
    void onActionAddNewCustomer(ActionEvent event) {
/*      When clicked, as long as the customer has previously cleared their selection, will take new data from the textfields and comboboxes
        and will send a query to the database to create a new customer.  If any of the fields are blank or if there is a value in the
        CustomerId textfield, a dialog box will be shown to the user.
        First the If/else statement handles the dialog box informing the user to clear their selection, then the program hits the try/catch block
        to handle any exceptions that may come up from attempting operations.
        */
        if (customerIdText.getText().isEmpty()) { //verify that the customer has cleared their selection.

            //declare fields.
            String name = nameText.getText();
            String address = addressText.getText();
            String postalCode = postalText.getText();
            String phone = phoneText.getText();

            String country = countryComboPicker.getSelectionModel().getSelectedItem();
            String division = divisionComboPicker.getSelectionModel().getSelectedItem();
            String type= typeComboPicker.getSelectionModel().getSelectedItem();

            try {
                //once fields are declared, check to see if any are empty.
                //add new checker to make sure type selection is made.
                if (name.isEmpty() || address.isEmpty() || postalCode.isEmpty() || phone.isEmpty() || country == null || division == null || type == null) {
                    throw new NumberFormatException();
                }

                //locate the divisionId based on user selection.
                int divisionId = DivisionList.lookupDivisionId(division);
                int typeId = TypeList.lookupTypeId(type);


                //push a query to the database, if successful, we will then clear the customerList, repopulate data from customer table, then refresh the tableview.
                int rowsAffected = CustomerQuery.insert(name, address, postalCode ,phone, divisionId, typeId);

                if (rowsAffected == 1) {
                    CustomerList.getAllCustomers().clear();
                    CustomerQuery.selectAll();

                    CustomerList.getAllCustomerNames().clear();
                    CustomerList.buildCustomerNameList();
                    customerTableView.refresh();

                    customerIdText.clear();
                    nameText.clear();
                    phoneText.clear();
                    addressText.clear();
                    postalText.clear();
                    countryComboPicker.setValue(null);
                    divisionComboPicker.setValue(null);

                }
            }
            catch (NumberFormatException error) {
                // if fields are empty, immediately build our error message, then display it to the user in a dialog box.
                ErrorBuilder.checkCustomerFields(name, phone, address, postalCode, country, division, type);
                DialogBuilder.displayErrorMessage("Required Information not Complete", "Blank Fields Detected",
                        "Please fill out the following fields: \n\n" + ErrorBuilder.getErrorInfo().toString());

                ErrorBuilder.clearErrorInfo(); // resets the ErrorInfo stringbuilder.
            }
            catch (SQLException error) {
                // in case the database is not reachable, the user can gain information using SQL's informative error message.
                DialogBuilder.displayErrorMessage("Database Error", "The database returned the following information:" + error.getMessage());
            }

        }
        else {
            // display error if the customerId field isn't empty.
            DialogBuilder.displayErrorMessage("Error", "To add a new customer, please select \n" +
                    "CLEAR SELECTION to clear fields,\n then enter new customer information.");
        }

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {
        //first verify that a selection is made in the tableview, then execute delete operation on the database if the user
        //confirms they want to delete the user from a dialog box.
        try {
            Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

            if (selectedCustomer == null) { // if null selection, throw error.
                throw new NullPointerException();
            }
            //declare customerID so that the variable is accessible in the try block
            int customerId = selectedCustomer.getId();

            if (DialogBuilder.displayConfirmationMessage("Deleting Customer", "Are you sure you want to delete selected customer?")) {
                //if the customer selects OK, delete the customer.

                //refresh appointment List to have most up-to-date information.
                AppointmentList.getAllAppointments().clear();
                AppointmentQuery.selectAll();

                if (AppointmentList.customerAppointmentCheck(customerId)) {
                    // if the customer has appointments, display an error instead of performing the delete action.
                    DialogBuilder.displayErrorMessage("Unable to Delete Customer", "Customer has associated appointments.\n\n" +
                            "Please delete appointments associated with customer to perform DELETE.");
                }
                else {
                    // if no associated appointments, complete delete operation.
                    int rowsAffected = CustomerQuery.delete(customerId);

                    if (rowsAffected == 1) {
                        // if the database processes the request, then perform the refresh on the customer table.
                        CustomerList.getAllCustomers().clear();
                        CustomerQuery.selectAll();
                        customerTableView.refresh();

                        CustomerList.getAllCustomerNames().clear();
                        CustomerList.buildCustomerNameList();

                        customerIdText.clear();
                        nameText.clear();
                        phoneText.clear();
                        addressText.clear();
                        postalText.clear();

                        countryComboPicker.setValue(null);
                        divisionComboPicker.setValue(null);

                        DialogBuilder.displayInformationMessage("Customer Deleted", "Notification", "Customer has been successfully deleted.");
                    }
                    else {
                        // if no rows were affected, display an error to the user.
                        throw new SQLException();
                    }
                }
            }
        }
        catch (NullPointerException error) {
            // catch nullPointer
            DialogBuilder.displayErrorMessage("No Selection Made","Please select a Customer to Delete");
        }
        catch (SQLException error) {
            // catch any other SQL errors, like if the database isn't reachable.
            DialogBuilder.displayErrorMessage("Database Error", "The database failed to respond, please refresh the table.");
        }

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) {
        //first declare fields, then verify the user has made a selection, then check if any of the fields are empty, then perform the
        //update operation on the database.

        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

        String name = nameText.getText();
        String address = addressText.getText();
        String postalCode = postalText.getText();
        String phone = phoneText.getText();

        String country = countryComboPicker.getSelectionModel().getSelectedItem();
        String division = divisionComboPicker.getSelectionModel().getSelectedItem();
        String type = typeComboPicker.getSelectionModel().getSelectedItem();

        try { // if no selection, throw NullPointer
            if (selectedCustomer == null) { // if null selection, throw error.
                throw new NullPointerException();
            }
            //check if any of the names are empty, then proceed with building an error if needed.
            if (name.isEmpty() || address.isEmpty() || postalCode.isEmpty() || phone.isEmpty() || country == null || division == null || type == null) {
                throw new NumberFormatException();
            }

            if (DialogBuilder.displayConfirmationMessage("Updating Customer", "Are you sure you want to update the selected Customer's Information?")) {
                //if the customer selects OK, perform the update customer operation.

                //declare variables needed to complete the query.
                int customerId = selectedCustomer.getId();
                int divisionId = DivisionList.lookupDivisionId(division);
                int typeId = TypeList.lookupTypeId(type);

                //complete query and check if any rows are affected.
                int rowsAffected = CustomerQuery.update(customerId, name, phone, address, postalCode, divisionId, typeId);

                if (rowsAffected == 1) { // if the database processes the request, then perform the refresh on the customer table.
                    CustomerList.getAllCustomers().clear();
                    CustomerQuery.selectAll();
                    customerTableView.refresh();
                    // replace focus back to the tableview so the user does not get an error if they click update again.
                    customerTableView.requestFocus();
                    customerTableView.getSelectionModel().select(selectedCustomer);

                }
                else { // throw an exception if the record does not exist anymore
                    throw new SQLException();
                }
            }
        }
        catch (NullPointerException error) {
            // catch nullPointer
            DialogBuilder.displayErrorMessage("No Selection Made","Please select a Customer to Update");
        }
        catch (NumberFormatException error) {
            // if fields are empty, immediately build our error message, then display it to the user in a dialog box.
            ErrorBuilder.checkCustomerFields(name, phone, address, postalCode, country, division, type);
            DialogBuilder.displayErrorMessage("Required Information not Complete", "Blank Fields Detected",
                    "Please complete the following fields: \n\n" + ErrorBuilder.getErrorInfo().toString());

            // resets the ErrorInfo string builder.
            ErrorBuilder.clearErrorInfo();
        }
        catch (SQLException error) {
            // catch any other SQL errors, like if the database isn't reachable.
            DialogBuilder.displayErrorMessage("Database Error", "The database failed to respond, please refresh the table.");
        }

    }

    @FXML
    void onActionRefresh(ActionEvent event) {
        //performs a function with feedback that will refresh the data in the customer table in case another logged-in user adds a customer to the list

        if (DialogBuilder.displayConfirmationMessage("Refreshing Fields", "Refreshing will clear the text fields, continue?")) {
            try { // perform full refresh, clear list, rebuild list, refresh tableview.
                customerSearchText.clear();
                CustomerList.getAllCustomers().clear();
                CustomerQuery.selectAll();

                customerTableView.setItems(CustomerList.getAllCustomers());
                customerTableView.refresh();

                //clear fields so that if a customer is removed, the old data is still not present in the application.
                customerIdText.clear();
                nameText.clear();
                phoneText.clear();
                addressText.clear();
                postalText.clear();
                countryComboPicker.setValue(null);
                divisionComboPicker.setValue(null);
                typeComboPicker.setValue(null);

                customerTableView.getSelectionModel().clearSelection();

                //provide feedback to the user that the request was successful
                DialogBuilder.displayInformationMessage("Data Refreshed", "Notification", "The Customer Table has been Refreshed");
            }
            catch (SQLException error) { // catch any other SQL errors, like if the database isn't reachable.
                DialogBuilder.displayErrorMessage("Database Error", "The database returned the following information:" + error.getMessage());
            }
        }
    }

    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        //first ask the user if they wish to exit, then exit if OK is selected.
        if (DialogBuilder.displayConfirmationMessage("Exiting Screen", "You will lose unsaved data, continue?")) {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/main/scheduler/c195finalproject/AppointmentMainMenu.fxml"));
            stage.setScene(new Scene(scene));
            SceneSetter.prepareWindow(stage);
            stage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize data and set up tableview data columns.
        customerTableView.setItems(CustomerList.getAllCustomers());

        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        customerTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));

        //initialize the countryList Combo using the list of countries.
        CountryList.buildCountryNameList();
        countryComboPicker.setItems(CountryList.getCountryNames());

        typeComboPicker.setItems(TypeList.getAllTypeNames());

    }
}
