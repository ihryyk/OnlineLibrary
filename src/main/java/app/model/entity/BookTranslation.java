package app.model.entity;

import java.util.Objects;

/**
 * Used to store some information about the Book, which is in different languages.
 *
 * @author Ihor Berezovskyi
 */

public class BookTranslation {
    /**
     * title of the Book.
     */
    private String title;
    /**
     * author of the Book.
     */
    private String author;
    /**
     * the language in which information about the book is preserved.
     */
    private Language language;


    public BookTranslation() {
    }

    public BookTranslation(String title, Language language, String author) {
        this.title = title;
        this.language = language;
        this.author = author;
    }

    /**
     * Return author of the Book.
     *
     * @return id of the Book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set author of the Book.
     *
     * @param author new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Return title of the Book.
     *
     * @return title of the Book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title of the Book.
     *
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Return the language in which information about book is preserved.
     *
     * @return the language in which information about book is preserved.
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Set the language in which information about book is preserved.
     *
     * @param language new language
     */
    public void setLanguage(Language language) {
        this.language = language;
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
        BookTranslation that = (BookTranslation) o;
        return Objects.equals(title, that.title) && Objects.equals(language, that.language) && Objects.equals(author, that.author);
    }

    /**
     * Override {@link Object#hashCode()}<br>
     *
     * @return integer hashCode of object/
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, language, author);
    }

    /**
     * Override {@link Object#toString()} ()}<br>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "BookTranslation{" +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
