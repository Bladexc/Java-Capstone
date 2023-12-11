package main.scheduler.c195finalproject.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.scheduler.c195finalproject.model.AppointmentCount;

/**
 * The AppointmentCountList class represents a list of appointment type totals.
 * It provides methods to add and retrieve appointment type totals from the list.
 */
public class AppointmentCountList {

    private static ObservableList<AppointmentCount> allAppointmentTypeTotals = FXCollections.observableArrayList();

    /**
     * Adds a new Appointment Total Type to the list of totaled appointments
     *
     * @param newAppointmentTypeTotal the appointment type to add
     */
    public static void addAppointmentTypeTotal(AppointmentCount newAppointmentTypeTotal) {
        allAppointmentTypeTotals.add(newAppointmentTypeTotal);
    }

    /**
     * Returns the list of all appointment totals used in our report of appointment Type Totals
     *
     * @return the list of all appointment totals.
     */
    public static ObservableList<AppointmentCount> getAllAppointmentTypeTotals() {
        return allAppointmentTypeTotals;
    }
}
