package ADTPackage;
/**
   A class of runtime exceptions thrown by methods to
   indicate that a queue is empty.
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class EmptyQueueException extends RuntimeException
{
   /** Constructs an EmptyQueueException with no message */
   public EmptyQueueException()
   {
      this(null);
   } // end default constructor

   /**
    * Constructs an EmptyQueueException with the given message
    * @param message Message to be displayed when the exception is thrown
    */
   public EmptyQueueException(String message)
   {
      super(message);
   } // end constructor
} // end EmptyQueueException
