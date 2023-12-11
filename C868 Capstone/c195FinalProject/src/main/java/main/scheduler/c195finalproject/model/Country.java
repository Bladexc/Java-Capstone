package main.scheduler.c195finalproject.model;

/**
 * The Country class represents a country entity in the application.
 * It stores the ID and name of a country.
 */
public class Country {

    private int id;
    private String name;

    /**
     * Constructs a new {@code Country} object with the specified ID and name.
     *
     * @param id   the ID of the country
     * @param name the name of the country
     */
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of the country.
     *
     * @return the ID of the country
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the country.
     *
     * @param id the ID of the country
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the country.
     *
     * @return the name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the country.
     *
     * @param name the name of the country
     */
    public void setName(String name) {
        this.name = name;
    }
}
