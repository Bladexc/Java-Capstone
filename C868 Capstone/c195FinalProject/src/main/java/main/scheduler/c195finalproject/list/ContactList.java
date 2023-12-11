package main.scheduler.c195finalproject.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.scheduler.c195finalproject.model.Appointment;
import main.scheduler.c195finalproject.model.Contact;
import main.scheduler.c195finalproject.model.Division;

/**
 * The ContactList class represents a list of contacts in the application.
 * It provides methods to add, update, and delete contacts from the list.
 */
public class ContactList {
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    private static ObservableList<String> allContactNames = FXCollections.observableArrayList();

    /**
     * Adds a new contact to the list.
     *
     * @param newContact the contact to add
     */
    public static void add(Contact newContact) {
        allContacts.add(newContact);
    }

    /**
     * Adds a new contact name to the list of contact names
     *
     * @param name the contact name to add
     */
    public static void addContactName(String name) {
        allContactNames.add(name);
    }

    /**
     * Updates an existing contact in the list.
     *
     * @param index           the index of the contact to update
     * @param selectedContact the updated contact
     */
    public static void update(int index, Contact selectedContact) {
        int count  = -1;

        for (Contact allContacts : ContactList.getAllContacts() ) {
            count++;

            if (allContacts.getId() == index ) { // for every single update, I may need to add index + 1 as the condition
                ContactList.getAllContacts().set(count, selectedContact);
            }
        }
    }

    /**
     * Deletes a contact from the list.
     *
     * @param selectedContact the contact to delete
     * @return {@code true} if the contact was successfully deleted, {@code false} otherwise
     */
    public static boolean delete(Contact selectedContact) {
        for (Contact allContacts : ContactList.getAllContacts()) {
            if (selectedContact.equals(allContacts)) {
                ContactList.getAllContacts().remove(selectedContact);
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the list of all contacts.
     *
     * @return the list of all contacts
     */
    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }

    /**
     * Returns the list of all contact names.
     *
     * @return the list of all contact names
     */
    public static ObservableList<String> getAllContactNames() {
        return allContactNames;
    }

    /**
     * Builds the list of contact names in the ContactList.
     * This method retrieves all contacts from the ContactList,
     * extracts their names, and adds them to the list of contact names.
     */
    public static void buildContactNameList() {
        ContactList.getAllContactNames().clear();

        for (int count = 0; count < ContactList.getAllContacts().size(); count++) {
            String name = ContactList.getAllContacts().get(count).getName();

            ContactList.addContactName(name);
        }

    }

    /**
     * Looks up the contact ID based on the contact name.
     *
     * @param name the name of the contact
     * @return the ID of the contact if found, or 0 if not found
     */
    public static int lookupContactId(String name) {
        for (Contact allContacts : ContactList.getAllContacts()) {
            if (allContacts.getName() == name) {
                return allContacts.getId();
            }
        }
        return 0;
    }

    /**
     * Looks up the contact name based on the contact ID.
     *
     * @param id the ID of the contact
     * @return the name of the contact if found, or null if not found
     */
    public static String lookupContactName(int id) {
        for (Contact allContacts : ContactList.getAllContacts()) {
            if (allContacts.getId() == id) {
                return allContacts.getName();
            }
        }
        return null;
    }

}
