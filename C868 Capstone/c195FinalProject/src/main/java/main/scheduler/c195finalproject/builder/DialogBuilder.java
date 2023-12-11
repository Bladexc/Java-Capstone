package main.scheduler.c195finalproject.builder;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * The DialogBuilder class provides methods for displaying different types of dialogs, such as error messages, confirmation messages, and information messages.
 */
public abstract class DialogBuilder {

    /**
     * Displays an error message dialog that includes input for setting the header text in an alert dialog box.
     *
     * @param errorTitle   The title of the error message.

     * @param errorContext The content of the error message.
     */
    public static void displayErrorMessage(String errorTitle, String errorContext ) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(errorTitle);
        alert.setContentText(errorContext);
        alert.showAndWait();
    }

    /**
     * Overloaded version of DisplayErrorMessage that includes input for setting the header text in an alert dialog box.
     *
     * @param errorTitle   The title of the error message.
     * @param errorHeader  The header of the error message.
     * @param errorContext The content of the error message.
     */
    public static void displayErrorMessage(String errorTitle, String errorHeader, String errorContext) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(errorTitle);
        alert.setHeaderText(errorHeader);
        alert.setContentText(errorContext);
        alert.showAndWait();
    }

    /**
     * Displays an information message dialog that includes input for setting the header text in an alert dialog box.
     *
     * @param infoTitle   The title of the information message.
     * @param infoContext The content of the information message.
     */
    public static void displayInformationMessage(String infoTitle, String infoContext ) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(infoTitle);
        alert.setContentText(infoContext);
        alert.showAndWait();
    }

    /**
     * Overloaded version of displayInformationMessage that includes input for setting the header text in an alert dialog box.
     *
     * @param infoTitle   The title of the information message.
     * @param infoHeader  The header of the information message.
     * @param infoContext The content of the information message.
     */
    public static void displayInformationMessage(String infoTitle, String infoHeader, String infoContext ) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(infoTitle);
        alert.setHeaderText(infoHeader);
        alert.setContentText(infoContext);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation message dialog with the specified title and content.
     *
     * @param errorTitle    The title of the confirmation message dialog.
     * @param errorContent  The content of the confirmation message dialog.
     * @return              True if the user confirms the message by clicking the OK button, false otherwise.
     */
    public static boolean displayConfirmationMessage(String errorTitle, String errorContent ) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(errorTitle);
        alert.setContentText(errorContent);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            return true;
        }
        else {
            return false;
        }
    }
}
