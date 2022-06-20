package app.model.dao.exeption;

/**
 * An exception that provides information about a database access error.
 *
 * @author Ihor Berezovskyi
 */
public class DAOException extends Exception {

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message   the detail message (which is saved for later retrieval
     *                  by the {@link #getMessage()} method).
     * @param throwable the cause (which is saved for later retrieval by the
     *                  {@link #getCause()} method).  (A <tt>null</tt> value is
     *                  permitted, and indicates that the cause is nonexistent or
     *                  unknown.)
     * @since 1.4
     */
    public DAOException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
