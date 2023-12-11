package main.scheduler.c195finalproject.data;

import main.scheduler.c195finalproject.list.AppointmentList;
import main.scheduler.c195finalproject.list.AppointmentCountList;
import main.scheduler.c195finalproject.model.Appointment;
import main.scheduler.c195finalproject.model.AppointmentCount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The AppointmentQuery class provides methods to interact with the Appointments table in the database.
 *
 * The database used is SQL Workbench 8.0.26.
 */
public abstract class  AppointmentQuery {

    /**
     * Retrieves all appointments from the Appointments table and populates the AppointmentList.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void selectAll() throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            int contactId = resultSet.getInt("Contact_ID");
            String type = resultSet.getString("Type");
            Timestamp startDateTime = resultSet.getTimestamp("Start");
            Timestamp endDateTime = resultSet.getTimestamp("End");
            int customerId = resultSet.getInt("Customer_ID");
            int userId = resultSet.getInt("User_ID");

            //startDateTime.format(TimeConvert.getFormatter());
            //TimeConvert.format(endDateTime);

            Appointment appointment = new Appointment(id, title, description, location, contactId, type,
                    startDateTime.toLocalDateTime(), endDateTime.toLocalDateTime(), customerId, userId);
            AppointmentList.addAppointment(appointment);
        }
    }


    /**
     * Inserts a new appointment into the Appointments table.
     *
     * @param title       the title of the appointment
     * @param description the description of the appointment
     * @param location    the location of the appointment
     * @param type        the type of the appointment
     * @param start       the start date and time of the appointment
     * @param end         the end date and time of the appointment
     * @param contactId   the ID of the contact associated with the appointment
     * @param customerId  the ID of the customer associated with the appointment
     * @param userId      the ID of the user associated with the appointment
     *
     * @return the number of rows affected by the insert operation
     *
     * @throws SQLException if a database access error occurs
     */
    public static int insert(String title, String description, String location, String type,
                             LocalDateTime start, LocalDateTime end, int contactId, int customerId, int userId) throws SQLException {
        //order of arguments matches the data organization of the Add Appointment FROM, not the query
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);

        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));

        preparedStatement.setInt(7,customerId);
        preparedStatement.setInt(8,userId);
        preparedStatement.setInt(9, contactId);

       int rowsAffected = preparedStatement.executeUpdate();

       return rowsAffected;
    }

    /**
     * Updates an existing appointment in the Appointments table.
     *
     * @param id          the ID of the appointment to update
     * @param title       the new title of the appointment
     * @param description the new description of the appointment
     * @param location    the new location of the appointment
     * @param type        the new type of the appointment
     * @param start       the new start date and time of the appointment
     * @param end         the new end date and time of the appointment
     * @param contactId   the new ID of the contact associated with the appointment
     * @param customerId  the new ID of the customer associated with the appointment
     * @param userId      the new ID of the user associated with the appointment
     *
     * @return the number of rows affected by the update operation
     *
     * @throws SQLException if a database access error occurs
     */
    public static int update(int id, String title, String description, String location, String type,
                             LocalDateTime start, LocalDateTime end, int contactId, int customerId, int userId) throws SQLException {

        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?," +
                "Customer_ID = ?, User_ID = ?, Contact_ID = ?  WHERE Appointment_ID = ?";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);

        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));

        preparedStatement.setInt(7,customerId);
        preparedStatement.setInt(8,userId);
        preparedStatement.setInt(9, contactId);
        preparedStatement.setInt(10, id);

        int rowsAffected = preparedStatement.executeUpdate();

        return rowsAffected;
    }

    /**
     * Deletes an appointment from the Appointments table.
     *
     * @param appointmentId the ID of the appointment to delete
     *
     * @return the number of rows affected by the delete operation
     *
     * @throws SQLException if a database access error occurs
     */
    public static int delete(int appointmentId) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, appointmentId);

        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;

    }

    /**
     * Retrieves the count of appointments by month and type from the Appointments table.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void selectCount() throws SQLException {
        String sql = "SELECT monthname(Start) AS Month, Type, COUNT(Type) FROM appointments GROUP BY monthname(Start), Type";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String month = resultSet.getString("Month");
            String type = resultSet.getString("Type");
            int count = resultSet.getInt("COUNT(Type)");

            AppointmentCount appointmentCount = new AppointmentCount(month, type, count);
            AppointmentCountList.addAppointmentTypeTotal(appointmentCount);
        }

    }
}
