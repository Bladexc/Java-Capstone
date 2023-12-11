package main.scheduler.c195finalproject.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.scheduler.c195finalproject.model.Type;
import main.scheduler.c195finalproject.model.User;

import java.util.Objects;

public class TypeList {

    private static ObservableList<Type> allTypes = FXCollections.observableArrayList();

    private static ObservableList<String> allTypeNames = FXCollections.observableArrayList();

    /**
     * Adds a new type to the list.
     *
     * @param newType the type to add
     */
    public static void add(Type newType) {
        allTypes.add(newType);
    }

    /**
     * Adds a new type "name" to the list of Usernames.
     *
     * @param name the type "name" to add
     */
    public static void addTypeName(String name) {
        allTypeNames.add(name);
    }


    /**
     * Returns the list of all types.
     *
     * @return the list of all types
     */
    public static ObservableList<Type> getAllTypes() {
        return allTypes;
    }

    /**
     * Returns the list of all type "names" for ComboBox function.
     *
     * @return the list of all type "names"
     */
    public static ObservableList<String> getAllTypeNames() {
        return allTypeNames;
    }

    /**
     * Clears the list of type names and rebuilds it using the type objects in the type list.
     */
    public static void buildTypeNameList() {
        TypeList.getAllTypeNames().clear();

        for (int count = 0; count < TypeList.allTypes.size(); count++) {
            String name = TypeList.getAllTypes().get(count).getName();

            TypeList.addTypeName(name);
        }

    }

    /**
     * Looks up the type ID based on the provided username.
     *
     * @param name the type name to lookup
     * @return the type ID if found, or 0 if not found
     */
    public static int lookupTypeId(String name) {
        for (Type allTypes : TypeList.getAllTypes()) {
            if (Objects.equals(allTypes.getName(), name)) {
                return allTypes.getId();
            }
        }
        return 0;
    }

    /**
     * Looks up the type name based on the provided type ID.
     *
     * @param id the user ID to lookup
     * @return the type name if found, or null if not found
     */
    public static String lookupTypeName(int id) {
        for (Type allTypes : TypeList.getAllTypes()) {
            if (allTypes.getId() == id) {
                return allTypes.getName();
            }
        }
        return null;
    }
}
