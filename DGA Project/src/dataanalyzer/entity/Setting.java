package dataanalyzer.entity;

/**
 * Represents a setting which has a name, a value and a data type.
 * @author David Neilsen
 * @param <T> the type of data the setting stores
 */
public class Setting <T> {

    /** The name of the setting */
    public String name;
    /** The data stored by the setting */
    public T value;
    /** The type of the setting represented as a String */
    public String type;

    /**
     * Convenience constructor.
     */
    public Setting() {
    }

    /**
     * Initializes the setting.
     * @param name the name of the setting
     * @param value the data stored by the setting
     * @param type the type of the setting represented as a String
     */
    public Setting(String name, T value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String toString() {
        return "Setting[" + name + "," + value.toString() + "," + type + "]";
    }
}