package ADTPackage;
import java.util.Iterator;
/**
   An interface for the ADT list that has an iterator.
   @param <T> data type of the objects to be held in the List
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public interface ListWithIteratorInterface<T> extends ListInterface<T>, Iterable<T>
{
   /**
    * Returns an Iterator over the elements in the list
    * @return an Iterator over the elements in the list
    */
   public Iterator<T> getIterator();
} // end ListWithIteratorInterface
