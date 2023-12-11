package main.scheduler.c195finalproject.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.scheduler.c195finalproject.model.Customer;
import main.scheduler.c195finalproject.model.User;

import java.util.Objects;

/**
 * The {@code UserList} class represents a list of users in the application.
 * It provides methods to add, update, and delete users from the list.
 */
public class UserList {

    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    private static ObservableList<String> allUserNames = FXCollections.observableArrayList();

    /**
     * Adds a new user to the list.
     *
     * @param newUser the user to add
     */
    public static void add(User newUser) {
        allUsers.add(newUser);
    }

    /**
     * Adds a new user "name" to the list of Usernames.
     *
     * @param name the user "name" to add
     */
    public static void addUserName(String name) {
        allUserNames.add(name);
    }

    /**
     * Updates an existing user in the list.
     *
     * @param index         the index of the user to update
     * @param selectedUser  the updated user
     */
    public static void update(int index, User selectedUser) {
        int count = -1;

        for (User allUsers : UserList.getAllUsers()) {
            count++;

            if (allUsers.getId() == index) { // for every single update, I may need to add index + 1 as the condition
                UserList.getAllUsers().set(count, selectedUser);
            }
        }
    }

    /**
     * Deletes a user from the list.
     *
     * @param selectedUser  the user to delete
     * @return {@code true} if the user was successfully deleted, {@code false} otherwise
     */
    public static boolean delete(User selectedUser) {
        for (User allUsers : UserList.getAllUsers()) {
            if (selectedUser.equals(allUsers)) {
                UserList.getAllUsers().remove(selectedUser);
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the list of all users.
     *
     * @return the list of all users
     */
    public static ObservableList<User> getAllUsers() {
        return allUsers;
    }

    /**
     * Returns the list of all user "names" for ComboBox function.
     *
     * @return the list of all user "names"
     */
    public static ObservableList<String> getAllUserNames() {
        return allUserNames;
    }



    /**
     * Clears the list of usernames and rebuilds it using the user objects in the user list.
     */
    public static void buildUserNameList() {
        UserList.getAllUserNames().clear();

        for (int count = 0; count < UserList.allUsers.size(); count++) {
            String name = UserList.getAllUsers().get(count).getUsername();

            UserList.addUserName(name);
        }

    }

    /**
     * Looks up the user ID based on the provided username.
     *
     * @param name the username to lookup
     * @return the user ID if found, or 0 if not found
     */
    public static int lookupUserId(String name) {
        for (User allUsers : UserList.getAllUsers()) {
            if (Objects.equals(allUsers.getUsername(), name)) {
                return allUsers.getId();
            }
        }
        return 0;
    }

    /**
     * Looks up the username based on the provided user ID.
     *
     * @param id the user ID to lookup
     * @return the username if found, or null if not found
     */
    public static String lookupUserName(int id) {
        for (User allUsers : UserList.getAllUsers()) {
            if (allUsers.getId() == id) {
                return allUsers.getUsername();
            }
        }
        return null;
    }


}
