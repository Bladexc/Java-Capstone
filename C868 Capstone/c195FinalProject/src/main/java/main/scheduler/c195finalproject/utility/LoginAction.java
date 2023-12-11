package main.scheduler.c195finalproject.utility;

import main.scheduler.c195finalproject.builder.DialogBuilder;
import main.scheduler.c195finalproject.list.AppointmentList;
import main.scheduler.c195finalproject.list.UserList;
import main.scheduler.c195finalproject.model.Appointment;
import main.scheduler.c195finalproject.model.User;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The LoginAction class provides utility methods for login-related actions.
 * It includes a login method for validating user credentials and an appointment login check method
 * for checking upcoming appointments for a logged-in user.
 */
public abstract class LoginAction {

    /**
     * Validates the provided username and password against the user list.
     *
     * @param username the username to check
     * @param password the password to check
     * @return {@code true} if the username and password match a user in the list, {@code false} otherwise
     */
    public static boolean login(String username, String password) {
        //used boolean format method for use in an If statement.
        for (User allUsers : UserList.getAllUsers()) {
            if (Objects.equals(allUsers.getUsername(), username) && Objects.equals(allUsers.getPassword(), password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for upcoming appointments within a 15-minute range of a login.
     * If an upcoming appointment is found, a notification message is displayed.
     *
     */
    public static void appointmentLoginCheck() {
        //after attempting to use a Lambda expression to filter the list of Appointments and try to perform time checks,
        //I settled with this method that makes more sense to me in terms of comparing times and dates.
        //this felt more simple than using the ChronoUnit.MINUTES.between method.



        //declare times we will be using to perform comparisons against the list of appointments.
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlusRange = LocalDateTime.now().plusMinutes(15);
        Appointment upcomingAppointment = null;

        for (Appointment allAppointments : AppointmentList.getAllAppointments()) {
            //first iterate through every single appointment in the list.


            //if (allAppointments.getStartDateTime().isAfter(now) && allAppointments.getStartDateTime().isBefore(nowPlusRange))
            if (allAppointments.getStartDateTime().isAfter(now) && allAppointments.getStartDateTime().isBefore(nowPlusRange)) {

                //if appointment falls within our range, set the appointment = our upcomingAppointment object.
                if (upcomingAppointment == null || allAppointments.getStartDateTime().isBefore(upcomingAppointment.getStartDateTime())) {

                    //this final statement verifies that we only locate the most recent appointment
                    upcomingAppointment = allAppointments;
                }
            }

        }

        if (upcomingAppointment != null) {

            //if our appointment is not its initial value of null, display message.
            DialogBuilder.displayInformationMessage("Upcoming Appointment Notification", "Notification", "You have an upcoming appointment: \n\n" +
                    "Appointment ID:      " + upcomingAppointment.getId() + "\nAppointment Time:  " + upcomingAppointment.getStartDateTime());
        }
        else {

            //display no upcoming appointments.
            DialogBuilder.displayInformationMessage("Upcoming Appointment Notification", "Notification", "You have no upcoming appointments in the next 15 minutes");
        }

    }

}
