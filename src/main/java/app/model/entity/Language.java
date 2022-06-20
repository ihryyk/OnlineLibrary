package app.model.entity;

import java.util.Objects;

/**
 * Used to store information about the language in which the book description is stored.
 *
 * @author Ihor Berezovskyi
 */
public class Language {

    /**
     * id of the Language.
     */
    private long id;
    /**
     * name of the Language.
     */
    private String name;

    public Language() {
    }

    public Language(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Return id of the Language.
     *
     * @return id of the Language.
     */
    public long getId() {
        return id;
    }

    /**
     * Set id of the Language.
     *
     * @param id new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Return name of the Language.
     *
     * @return name of the Language.
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of the Language.
     *
     * @param name new language name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Override {@link Object#equals(Object)}<br>
     * Check all fields of objects<br>
     *
     * @param o object to compare
     * @return Boolean equality of input and current objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return id == language.id && Objects.equals(name, language.name);
    }

    /**
     * Override {@link Object#hashCode()}<br>
     *
     * @return integer hashCode of object/
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * Override {@link Object#toString()} ()}<br>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Language{" + "name='" + name + '\'' + '}';
    }
}
