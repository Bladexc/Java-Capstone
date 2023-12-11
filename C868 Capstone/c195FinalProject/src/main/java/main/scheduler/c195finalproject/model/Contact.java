package main.scheduler.c195finalproject.model;

/**
 * The Contact class represents a contact entity.
 * It stores the ID and name of a contact.
 */
public class Contact {

    private int id;
    private String name;

    /**
     * Constructs a new {@code Contact} object with the specified ID and name.
     *
     * @param id   the ID of the contact
     * @param name the name of the contact
     */
    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of the contact.
     *
     * @return the ID of the contact
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the contact.
     *
     * @param id the ID of the contact
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the contact.
     *
     * @return the name of the contact
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the contact.
     *
     * @param name the name of the contact
     */
    public void setName(String name) {
        this.name = name;
    }

}
