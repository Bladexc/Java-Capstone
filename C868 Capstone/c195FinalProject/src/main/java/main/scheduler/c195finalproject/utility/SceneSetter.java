package main.scheduler.c195finalproject.utility;

import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * The SceneSetter class provides utility methods for setting up and positioning the application window.
 */
public abstract class SceneSetter {

    /**
     * Method used to center the window in the middle of the screen
     *
     * @param stage the stage representing the application window to be centered.
     */
    public static void prepareWindow(Stage stage) {
        //Get the center of the window
        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2;
        double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2;

        //Get the desired center coordinates using our center value
        double windowX = centerX - stage.getWidth() / 2;
        double windowY = centerY - stage.getHeight() / 2;

        stage.setX(windowX);
        stage.setY(windowY);
    }
}