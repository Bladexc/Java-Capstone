package main.scheduler.c195finalproject.model;
/**
 * The AppointmentCount class represents the count of appointments based on month and appointment type.
 * It stores the month name, appointment type, and the count of appointments for that month and type.
 * This object is used in conjunction with a database query that handles the count method.
 */
public class AppointmentCount {

    private String monthName;
    private String appointmentType;
    private int count;

    /**
     * Constructs a new {@code AppointmentCount} object with the specified month, appointment type, and count.
     *
     * @param month      the name of the month
     * @param type       the appointment type
     * @param count      the count of appointments
     */
    public AppointmentCount(String month, String type, int count) {
        this.monthName = month;
        this.appointmentType = type;
        this.count = count;
    }

    /**
     * Returns the name of the month.
     *
     * @return the name of the month
     */
    public String getMonthName() {
        return monthName;
    }

    /**
     * Sets the name of the month.
     *
     * @param monthName the name of the month
     */
    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    /**
     * Returns the appointment type.
     *
     * @return the appointment type
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * Sets the appointment type.
     *
     * @param appointmentType the appointment type
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * Returns the count of appointments.
     *
     * @return the count of appointments
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count of appointments.
     *
     * @param count the count of appointments
     */
    public void setCount(int count) {
        this.count = count;
    }
}
