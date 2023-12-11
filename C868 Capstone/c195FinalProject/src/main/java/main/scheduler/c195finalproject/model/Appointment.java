package main.scheduler.c195finalproject.model;

import main.scheduler.c195finalproject.utility.TimeConvert;
import java.time.LocalDateTime;

/**
 * Represents an appointment in the application.
 */
public class Appointment {

    private int id;
    private String title;
    private String description;
    private String location;
    private int contactId;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int customerId;
    private int userId;

    /**
     * Constructs a new Appointment object with the specified properties.
     *
     * @param id              the ID of the appointment
     * @param title           the title of the appointment
     * @param description     the description of the appointment
     * @param location        the location of the appointment
     * @param contactId       the ID of the contact associated with the appointment
     * @param type            the type of the appointment
     * @param startDateTime   the start date and time of the appointment in UTC
     * @param endDateTime     the end date and time of the appointment in UTC
     * @param customerId      the ID of the customer associated with the appointment
     * @param userId          the ID of the user associated with the appointment
     */
    public Appointment(int id, String title, String description, String location, int contactId, String type,
                       LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId, int userId)      {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactId = contactId;
        this.type = type;
        // Convert the startDateTime and endDateTime from UTC to local time zone
        this.startDateTime = TimeConvert.fromUTCToLocal(startDateTime).toLocalDateTime();
        this.endDateTime = TimeConvert.fromUTCToLocal(endDateTime).toLocalDateTime();

        this.customerId = customerId;
        this.userId = userId;
    }

    /**
     * Returns the ID of the appointment.
     *
     * @return the ID of the appointment
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the appointment.
     *
     * @param id the ID of the appointment
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the title of the appointment.
     *
     * @return the title of the appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the appointment.
     *
     * @param title the title of the appointment
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description of the appointment.
     *
     * @return the description of the appointment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the appointment.
     *
     * @param description the description of the appointment
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the location of the appointment.
     *
     * @return the location of the appointment
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the appointment.
     *
     * @param location the location of the appointment
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the ID of the contact associated with the appointment.
     *
     * @return the ID of the contact associated with the appointment
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the ID of the contact associated with the appointment.
     *
     * @param contactId the ID of the contact associated with the appointment
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Returns the type of the appointment.
     *
     * @return the type of the appointment
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the appointment.
     *
     * @param type the type of the appointment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the start date and time of the appointment.
     *
     * @return the start date and time of the appointment
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the start date and time of the appointment.
     *
     * @param startDateTime the start date and time of the appointment
     */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * Returns the end date and time of the appointment.
     *
     * @return the end date and time of the appointment
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Sets the end date and time of the appointment.
     *
     * @param endDateTime
    the end date and time of the appointment
     */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the ID of the customer associated with the appointment.
     *
     * @return the ID of the customer associated with the appointment
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the ID of the customer associated with the appointment.
     *
     * @param customerId the ID of the customer associated with the appointment
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns the ID of the user associated with the appointment.
     *
     * @return the ID of the user associated with the appointment
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user associated with the appointment.
     *
     * @param userId the ID of the user associated with the appointment
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
