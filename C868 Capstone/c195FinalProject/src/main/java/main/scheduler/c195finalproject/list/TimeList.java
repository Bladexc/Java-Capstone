package main.scheduler.c195finalproject.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;

/**
 * The {@code TimeList} class represents a list of timeframes used in scheduling appointments.
 * It provides methods to add timeframes in 15-minute increments and retrieve the list of all timeframes.
 */
public class TimeList {

    private static ObservableList<LocalTime> allAppointmentTimes = FXCollections.observableArrayList();


    /**
     * Adds a new timeframe to the list of times in 15 minute increments.
     *
     * @param newTime the timeStamp to add
     */
    public static void addTime(LocalTime newTime) {
        allAppointmentTimes.add(newTime);
    }

    /**
     * Builds the list of appointment timeframes in 15-minute increments from 00:00 to 23:45.
     * Each timeframe is added to the list.
     */
    public static void buildAppointmentTimes() {
        LocalTime startTime = LocalTime.of(0,0);
        LocalTime endTime = LocalTime.of(23,45);

        while (!startTime.equals(endTime)) {

            TimeList.addTime(startTime);
            startTime = startTime.plusMinutes(15);
        }
        //add final timeframe to the list.
        TimeList.addTime(startTime);

    }

    /**
     * Returns the list of all appointment timeframes used in the scheduling ComboBoxes.
     *
     * @return the list of all appointment timeframes.
     */
    public static ObservableList<LocalTime> getAllAppointmentTimes() {
        return allAppointmentTimes;
    }
}
