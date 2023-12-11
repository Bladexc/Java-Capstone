package main.scheduler.c195finalproject.model;

/**
 * The Division class represents a division in the application.
 * It stores the ID, name, and country ID associated with a division.
 */
public class Division {
    private int id;
    private String name;
    private int countryId;

    /**
     * Constructs a new {@code Division} object with the specified details.
     *
     * @param id        the ID of the division
     * @param name      the name of the division
     * @param countryId the ID of the country associated with the division
     */
    public Division(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    /**
     * Returns the ID of the division.
     *
     * @return the ID of the division
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the division.
     *
     * @param id the ID of the division
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the division.
     *
     * @return the name of the division
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the division.
     *
     * @param name the name of the division
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the ID of the country associated with the division.
     *
     * @return the ID of the country associated with the division
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the ID of the country associated with the division.
     *
     * @param countryId the ID of the country associated with the division
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
