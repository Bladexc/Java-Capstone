package main.scheduler.c195finalproject.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.scheduler.c195finalproject.model.Country;

/**
 * The {@code CountryList} class represents a list of countries in the application.
 * It provides methods to add, update, and delete countries from the list.
 */
public class CountryList {

    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    private static ObservableList<String> allCountryNames = FXCollections.observableArrayList();


    /**
     * Adds a new country to the list.
     *
     * @param newCountry the country to add to the allCountries object list
     */
    public static void add(Country newCountry) {
        allCountries.add(newCountry);
    }


    /**
     * Adds a new country name to the list.
     *
     * @param name the country name to add to the country name list
     */
    public static void addCountryName(String name) {
        allCountryNames.add(name);
    }

    /**
     * Updates an existing country in the list.
     *
     * @param index           the index of the country to update
     * @param selectedCountry the updated country
     */
    public static void update(int index, Country selectedCountry) {
        int count = -1;

        for (Country allCountries : CountryList.getAllCountries()) {
            count++;

            if (allCountries.getId() == index) { // for every single update, I may need to add index + 1 as the condition
                CountryList.getAllCountries().set(count, selectedCountry);
            }
        }
    }

    /**
     * Deletes a country from the list.
     *
     * @param selectedCountry the country to delete
     * @return {@code true} if the country was successfully deleted, {@code false} otherwise
     */
    public static boolean delete(Country selectedCountry) {
        for (Country allCountries : CountryList.getAllCountries()) {
            if (selectedCountry.equals(allCountries)) {
                CountryList.getAllCountries().remove(selectedCountry);
            }
        }

        return false;
    }

    /**
     * Returns the list of all countries.
     *
     * @return the list of all countries
     */
    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }


    /**
     * Returns the list of all country names.
     *
     * @return the list of all country names.
     */
    public static ObservableList<String> getCountryNames() {
        return allCountryNames;
    }


    /**
     * Builds the list of country names in the CountryList.
     * This method retrieves all countries from the CountryList,
     * extracts their names, and adds them to the list of country names.
     */
    public static void buildCountryNameList() {
        CountryList.getCountryNames().clear();

        for (int count = 0; count < CountryList.getAllCountries().size(); count++) {
            String name = CountryList.getAllCountries().get(count).getName();

            CountryList.addCountryName(name);
        }
    }

}
