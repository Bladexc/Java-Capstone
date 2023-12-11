package main.scheduler.c195finalproject.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.scheduler.c195finalproject.model.Country;
import main.scheduler.c195finalproject.model.Customer;
import main.scheduler.c195finalproject.model.Division;

import java.util.Comparator;
import java.util.Objects;

/**
 * The {@code DivisionList} class represents a list of divisions in the application.
 * It provides methods to add, update, and delete divisions from the list.
 */
public class DivisionList {

    private static ObservableList<Division> allDivisions = FXCollections.observableArrayList();

    private static ObservableList<String> allDivisionNames = FXCollections.observableArrayList();

    /**
     * Adds a new division to the list.
     *
     * @param newDivision the division to add
     */
    public static void add(Division newDivision) {
        allDivisions.add(newDivision);
    }

    /**
     * Adds a new division name to the list of divisions.
     *
     * @param name the division name to add
     */
    public static void addName(String name) {
        allDivisionNames.add(name);
        DivisionList.getAllDivisionNames().sort(Comparator.comparing(String::toString));
    }

    /**
     * Updates an existing division in the list.
     *
     * @param index            the index of the division to update
     * @param selectedDivision the updated division
     */
    public static void update(int index, Division selectedDivision) {
        int count = -1;

        for (Division allDivisions : DivisionList.getAllDivisions()) {
            count++;

            if (allDivisions.getId() == index) {
                DivisionList.getAllDivisions().set(count, selectedDivision);
            }
        }
    }

    /**
     * Deletes a division from the list.
     *
     * @param selectedDivision the division to delete
     * @return {@code true} if the division was successfully deleted, {@code false} otherwise
     */
    public static boolean delete(Division selectedDivision) {
        for (Division allDivisions : DivisionList.getAllDivisions()) {
            if (selectedDivision.equals(allDivisions)) {
                DivisionList.getAllDivisions().remove(selectedDivision);
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the list of all divisions.
     *
     * @return the list of all divisions
     */
    public static ObservableList<Division> getAllDivisions() {
        return allDivisions;
    }

    /**
     * Returns the list of all division names.
     *
     * @return the list of all division names.
     */
    public static ObservableList<String> getAllDivisionNames() {
        return allDivisionNames;
    }

    /**
     * Builds the list of division names in the DivisionList based on the selected country.
     * This method retrieves all divisions from the DivisionList that match the provided country,
     * and adds their names to the list of division names.
     *
     * @param countryName the name of the country to filter the divisions
     */
    public static void buildDivisionNameList(String countryName) {
        //design is to go through the list of divisions and only add a name to the name list when divisions.country_id == country.country_id

        DivisionList.getAllDivisionNames().clear(); // reset the list on new selection, which will happen when the user selects a country
        Country selectedCountry = null;

        // Find the Country object that matches the provided countryName
        for (Country country : CountryList.getAllCountries()) {
            if (country.getName().equals(countryName)) {
                selectedCountry = country;
                //stop execution once a match is found.
                break;
            }
        }

        // If a matching Country is found, build the Division name list
        if (selectedCountry != null) {
            for (Division division : DivisionList.getAllDivisions()) {
                if (division.getCountryId() == selectedCountry.getId()) {
                    DivisionList.addName(division.getName());
                }
            }
        }

    }

    /**
     * Looks up the division ID based on the division name.
     *
     * @param name the name of the division
     * @return the ID of the division if found, or 0 if not found
     */
    public static int lookupDivisionId(String name) {
        for (Division allDivisions : DivisionList.getAllDivisions()) {
            if (allDivisions.getName().equals(name)) {
                return allDivisions.getId();
            }
        }
        return 0;
    }

}
