package main.scheduler.c195finalproject.builder;

import main.scheduler.c195finalproject.utility.TimeConvert;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The LogFileBuilder class provides methods for logging login activities to a log file.
 */
public abstract class LogFileBuilder  {

    private static String logFile = "login_activity.txt";

    /**
     * Retrieves the path of the log file.
     *
     * @return the path of the log file.
     */
    public String getLogFile() {
        return logFile;
    }

    /**
     * Logs a successful login attempt to the log file.
     *
     * @param username The username of the user who successfully logged in.
     * @throws IOException if an I/O error occurs while writing to the log file.
     */
    public static void loginSucceeded(String username) throws IOException {

        FileWriter fileWriter = new FileWriter(logFile, true);

        LocalDateTime currentTime = LocalDateTime.now();

        fileWriter.append("User: " + username + " successfully logged in at: " + currentTime.format(TimeConvert.getFormatter()) + "\n");

        fileWriter.close();
    }

    /**
     * Logs a failed login attempt to the log file.
     *
     * @param username The username of the user who made the failed login attempt.
     * @throws IOException if an I/O error occurs while writing to the log file.
     */
    public static void loginFailed(String username) throws IOException {

        FileWriter fileWriter = new FileWriter(logFile, true);

        LocalDateTime currentTime = LocalDateTime.now();

        fileWriter.append("User: " + username + " failed login attempt at: " + currentTime.format(TimeConvert.getFormatter()) + "\n");

        fileWriter.close();
    }

}
