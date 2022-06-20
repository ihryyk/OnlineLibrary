package app.model.entity;

import java.util.Objects;

/**
 * Used to store information about Role.
 *
 * @author Ihor Berezovskyi
 */
public class Role {
    /**
     * id of the Role.
     */
    private long id;
    /**
     * name of the Role.
     */
    private String name;

    public Role(int id, String roleName) {
        this.id = id;
        this.name = roleName;
    }

    public Role() {
    }

    /**
     * Return id of Role.
     *
     * @return id of Book.
     */
    public long getId() {
        return id;
    }

    /**
     * Set id of Role.
     *
     * @param id new id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Return name of Role.
     *
     * @return name of Book.
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of Role.
     *
     * @param name new name.
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
        Role role = (Role) o;
        return id == role.id && Objects.equals(name, role.name);
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
        return "Role{" +
                "id=" + id +
                ", role='" + name + '\'' +
                '}';
    }

    ;
}
