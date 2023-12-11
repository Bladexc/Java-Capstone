package main.scheduler.c195finalproject.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.scheduler.c195finalproject.model.Appointment;

/**
 * The AppointmentList class represents a list of appointments in the application.
 * It provides methods to add, update, and delete appointments from the list.
 */
public class AppointmentList {

    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    /**
     * Adds a new appointment to the list.
     *
     * @param newAppointment the appointment to add
     */
    public static void addAppointment(Appointment newAppointment) {
        allAppointments.add(newAppointment);
    }



    /**
     * Updates an existing appointment in the list.
     *
     * @param index              the index of the appointment to update
     * @param selectedAppointment the updated appointment
     */
    public static void update(int index, Appointment selectedAppointment) {
        int count  = -1;

        for (Appointment allAppointments : AppointmentList.getAllAppointments() ) {
            count++;

            if (allAppointments.getId() == index ) { // for every single update, I may need to add index + 1 as the condition
                AppointmentList.getAllAppointments().set(count, selectedAppointment);
            }
        }
    }

    /**
     * Deletes an appointment from the list.
     *
     * @param selectedAppointment the appointment to delete
     * @return {@code true} if the appointment was successfully deleted, {@code false} otherwise
     */
    public static boolean delete(Appointment selectedAppointment) {
        for (Appointment allAppointments : AppointmentList.getAllAppointments()) {
            if (selectedAppointment.equals(allAppointments)) {
                AppointmentList.getAllAppointments().remove(selectedAppointment);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a customer has any appointments associated with them.
     *
     * @param customerId the ID of the customer to check
     * @return {@code true} if the customer has appointments, {@code false} otherwise
     */
    public static boolean customerAppointmentCheck(int customerId) {
        for (Appointment allAppointments : AppointmentList.getAllAppointments()) {
            if (customerId == allAppointments.getCustomerId()){
                return true;
            }
        }
        return false;
    }


    /**
     * Returns the list of all appointments.
     *
     * @return the list of all appointments
     */
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

}
