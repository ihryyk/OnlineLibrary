package app.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Used to store information about Book.
 *
 * @author Ihor Berezovskyi
 */
public class Book {
    /**
     * variable to define deleted books
     */
    boolean deleted;
    /**
     * id of the Book.
     */
    private long id;
    /**
     * year of publication of the Book.
     */
    private int year;
    /**
     * publishing house of the Book.
     */
    private String publishingHouse;
    /**
     * list of fields that are implemented in different languages.
     *
     * @see BookTranslation
     */
    private List<BookTranslation> bookTranslations;
    /**
     *number of copies of the Book.
     */
    private int number;

    public Book() {
        this.bookTranslations = new ArrayList<>();
    }

    public Book(long id, int year, String publishingHouse, int number, List<BookTranslation> bookTranslations) {
        this.id = id;
        this.year = year;
        this.publishingHouse = publishingHouse;
        this.bookTranslations = bookTranslations;
        this.number = number;
    }
    /**
     * Return true if book is deleted.
     *
     * @return id of the Book.
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Set deleted boolean
     * @param deleted new deleted value
     *
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Return id of the Book.
     *
     * @return id of the Book.
     */
    public long getId() {
        return id;
    }

    /**
     * Set id of the Book.
     *
     * @param id new id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Return year of publication of the Book.
     *
     * @return year of publication of the Book.
     */
    public int getYear() {
        return year;
    }

    /**
     * Set year of publication of the Book.
     *
     * @param year new year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Return  publishing house of the Book.
     *
     * @return publishing house of the Book.
     */
    public String getPublishingHouse() {
        return publishingHouse;
    }

    /**
     * Set  publishing house of the Book.
     *
     * @param publishingHouse new publishing house.
     */
    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    /**
     * Return list of fields that are implemented in different languages.
     *
     * @return list of fields that are implemented in different languages.
     */
    public List<BookTranslation> getBookTranslations() {
        return bookTranslations;
    }

    /**
     * Set list of fields that are implemented in different languages.
     *
     * @param bookTranslations new list of bookTranslations
     */
    public void setBookTranslations(List<BookTranslation> bookTranslations) {
        this.bookTranslations = bookTranslations;
    }

    /**
     * Return number of copies of the Book.
     *
     * @return number of copies of the Book.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set number of copies of the Book.
     *
     * @param number new number
     */
    public void setNumber(int number) {
        this.number = number;
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
        Book book = (Book) o;
        return id == book.id && year == book.year && number == book.number && Objects.equals(publishingHouse, book.publishingHouse) && Objects.equals(bookTranslations, book.bookTranslations);
    }

    /**
     * Override {@link Object#hashCode()}<br>
     *
     * @return integer hashCode of object/
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, year, publishingHouse, bookTranslations, number);
    }

    /**
     * Override {@link Object#toString()} ()}<br>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Book{" +
                "year=" + year +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", bookTranslations=" + bookTranslations +
                ", number=" + number +
                '}';
    }
}
