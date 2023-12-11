package main.scheduler.c195finalproject.model;

public class Type {

    private int id;
    private String name;
    private String relationship;

    /**
     * Constructs a new type object with the specified ID, name, and relationship.
     *
     * @param id            the ID of the type
     * @param name          the name of the type
     * @param relationship  the relationship of the type
     */
    public Type(int id, String name, String relationship) {
        this.id = id;
        this.name = name;
        this.relationship = relationship;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }


}
