package main.scheduler.c195finalproject.builder;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The ErrorBuilder class provides methods for validating and managing error information.
 */
public abstract class ErrorBuilder {

    private static StringBuilder errorInfo = new StringBuilder();

    /**
     * Retrieves the current error information stored in a StringBuilder object.
     *
     * @return the StringBuilder object containing the error information.
     */
    public static StringBuilder getErrorInfo() {
        return errorInfo;
    }

    /**
     * Sets the error information in the StringBuilder object.
     *
     * @param errorInfo the StringBuilder object containing the error information.
     */
    public static void setErrorInfo(StringBuilder errorInfo) {
        ErrorBuilder.errorInfo = errorInfo;
    }

    /**
     * Clears the error information stored in the StringBuilder object.
     */
    public static void clearErrorInfo() {
        errorInfo.setLength(0);
    }

    /**
     * Checks the fields of a customer and appends error messages to the error information if any fields are empty or null.
     *
     * @param name     The name of the customer.
     * @param phone    The phone number of the customer.
     * @param address  The address of the customer.
     * @param postal   The postal code of the customer's address.
     * @param country  The country of the customer.
     * @param division The division of the customer.
     */
    public static void checkCustomerFields(String name, String phone, String address, String postal, String country, String division, String type) {

        if (type == null) {
            errorInfo.append(" - Customer Type Dropdown: -- (Please make a selection for customer type selector)\n\n");
        }
        if (name.isEmpty()) {
            errorInfo.append(" - Name Field: -- (Requires a value for First and Last Name)\n\n");
        }
        if (phone.isEmpty()) {
            errorInfo.append(" - Phone Number Field: -- (Requires a value for Phone Number)\n\n");
        }
        if (address.isEmpty()) {
            errorInfo.append(" - Address Field: -- (Requires a value for Customer Address)\n\n");
        }
        if (postal.isEmpty()) {
            errorInfo.append(" - Postal Code Field: -- (Requires a value for Address Postal Code)\n\n");
        }
        if (country == null) {
            errorInfo.append(" - Country Dropdown -- (Please make a selection for both boxes)\n\n");
        }
        if (division == null) {
            errorInfo.append(" - Division Dropdown: -- (Please make a selection for the state/province)\n\n");
        }

    }

    /**
     * Checks the fields of an appointment and appends error messages to the error information if any fields are empty or null.
     *
     * @param title       The title of the appointment.
     * @param description The description of the appointment.
     * @param location    The location of the appointment.
     * @param type        The type of the appointment.
     * @param contact     The contact of the appointment.
     * @param customer    The customer of the appointment.
     * @param user        The user entering the appointment.
     * @param startDate   The start date of the appointment.
     * @param startTime   The start time of the appointment.
     * @param endDate     The end date of the appointment.
     * @param endTime     The end time of the appointment.
     */
    public static void checkAppointmentFields(String title, String description, String location, String type, String contact, String customer, String user,
                                              LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {

        if (title.isEmpty()) {
            errorInfo.append(" - Title Field: -- (Requires a value for Title of Appointment)\n\n");
        }
        if (description.isEmpty()) {
            errorInfo.append(" - Description Field: -- (Requires a value for Description of Appointment)\n\n");
        }
        if (location.isEmpty()) {
            errorInfo.append(" - location Field: -- (Requires a value for Location of Appointment)\n\n");
        }
        if (type.isEmpty()) {
            errorInfo.append(" - Type Field: -- (Requires a value for Type of Appointment)\n\n");
        }
        if (contact == null) {
            errorInfo.append(" - Contact Dropdown -- (Please make a selection for Contact Name)\n\n");
        }
        if (customer == null) {
            errorInfo.append(" - Customer Dropdown: -- (Please make a selection for Customer Name)\n\n");
        }
        if (user == null) {
            errorInfo.append(" - User Dropdown: -- (Please make a selection for User Entering the Appointment)\n\n");
        }
        if (startDate == null) {
            errorInfo.append(" - Start Date Selector: -- (Please make a selection for Start Date)\n\n");
        }
        if (startTime == null) {
            errorInfo.append(" - Start Time Dropdown: -- (Please make a selection for Start Time)\n\n");
        }
        if (endDate == null) {
            errorInfo.append(" - End Date Selector: --(Please make a selection for End Date)\n\n");
        }
        if (endTime == null) {
            errorInfo.append(" - End Time Dropdown: -- (Please make a selection for Start Time)\n\n");
        }
    }

    /**
     * Checks the start and end dates of an appointment and appends error messages to the error information if any of them are null.
     *
     * @param startDate The start date of the appointment.
     * @param endDate   The end date of the appointment.
     */
    public static void checkAppointmentDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            errorInfo.append(" - Start Date Selector: -- (Please make a selection for both Start Date and Time)\n\n");
        }
        if (endDate == null) {
            errorInfo.append(" - End Date Selector: -- (Please make a selection for both End Date and Time)\n\n");
        }
    }

}
