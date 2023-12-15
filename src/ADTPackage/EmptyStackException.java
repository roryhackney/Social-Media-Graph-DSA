package ADTPackage;

/**
 A class of runtime exceptions thrown by methods to
 indicate that a stack is empty.
 @author Frank M. Carrano
 @author Rory Hackney
 @version 5.0
 */
public class EmptyStackException extends RuntimeException
{
    /** Constructs an EmptyStackException with no message */
    public EmptyStackException()
    {
        this(null);
    } // end default constructor

    /**
     * Constructs an EmptyStackException with the given message
     * @param message Message to be displayed when the exception is thrown
     */
    public EmptyStackException(String message)
    {
        super(message);
    } // end constructor
} // end EmptyStackException