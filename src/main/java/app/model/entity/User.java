package app.model.entity;

import app.model.enums.LockStatus;

import java.util.Objects;
/**
 * Used to store information about User.
 *
 * @author Ihor Berezovskyi
 */
public class User {
    /**
     * id of the User.
     */
    private long id;
    /**
     * name of the User.
     */
    private String name;
    /**
     * email address of the User.
     */
    private String emailAddress;
    /**
     * password of the User.
     */
    private String password;
    /**
     * role of the User.
     */
    private Role role;
    /**
     * lock status of the User.
     * @see Role
     */
    private LockStatus lockStatus;

    public User() {
    }

    public User(long id, String name, String emailAddress, String password, Role role, LockStatus lockStatus) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
        this.lockStatus = lockStatus;
    }

    /**
     * Return lock status of the User.
     *
     * @return lock status of the User.
     */
    public LockStatus getLockStatus() {
        return lockStatus;
    }

    /**
     * Set lock status of the User.
     *
     * @param lockStatus new lock status.
     */
    public void setLockStatus(LockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }

    /**
     * Return role of the User.
     *
     * @return role of the User.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Set role of the User.
     *
     * @param role new role.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Return id of the User.
     *
     * @return id of the User.
     */
    public long getId() {
        return id;
    }

    /**
     * Set id of the User.
     *
     * @param id new id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Return name of the User.
     *
     * @return name of the User.
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of the User.
     *
     * @param name new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return email address of the User.
     *
     * @return mail address of the User.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Set email address of the User.
     *
     * @param emailAddress new emailAddress.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Return password of the User.
     *
     * @return password of the User.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password of the User.
     *
     * @param password new password.
     */
    public void setPassword(String password) {
        this.password = password;
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
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(emailAddress, user.emailAddress) && Objects.equals(password, user.password) && Objects.equals(role, user.role) && lockStatus == user.lockStatus;
    }

    /**
     * Override {@link Object#hashCode()}<br>
     *
     * @return integer hashCode of object/
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, emailAddress, password, role, lockStatus);
    }

    /**
     * Override {@link Object#toString()} ()}<br>
     *
     * @return a string representation of the object.
     */


    @Override
    public String toString() {
        return "User{" +
                " userName='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
