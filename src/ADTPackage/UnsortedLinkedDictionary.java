package ADTPackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
   A class that implements the ADT dictionary by using a chain of nodes.
   The dictionary is unsorted and has distinct search keys.
   @param <K> data type for the search keys in the dictionary
   @param <V> data type for the associated values in the dictionary
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class UnsortedLinkedDictionary<K, V> implements DictionaryInterface<K, V>
{
	/** Reference to first node of chain */
	private Node firstNode;
	/** Current number of key/value pairs in the dictionary */
	private int  numberOfEntries; 

	/** Constructs a new empty UnsortedLinkedDictionary */
	public UnsortedLinkedDictionary()
	{
      initializeDataFields();
	} // end default constructor
	
   public V add(K key, V value)
   {
      if ((key == null) || (value == null))
         throw new IllegalArgumentException("Cannot add null to a dictionary.");
      else
      {
         V result = null;

         // Search chain for a node containing key
         Node currentNode = firstNode;
         while ( (currentNode != null) && !key.equals(currentNode.getKey()) )
         {
            currentNode = currentNode.getNextNode();
         } // end while

         if (currentNode == null)
         {
            // Key not in dictionary; add new node at beginning of chain
            Node newNode = new Node(key, value);
            newNode.setNextNode(firstNode);
            firstNode = newNode;
            numberOfEntries++;
         }
         else
         {
            // Key in dictionary; replace corresponding value
            result = currentNode.getValue();
            currentNode.setValue(value); // Replace value
         } // end if
      
         return result;
      } // end if
   } // end add

   public V remove(K key)
	{
   	V result = null;  // Return value
   	
		if (!isEmpty())
		{	
         // Search chain for a node containing key;
         // save reference to preceding node
			Node currentNode = firstNode;
			Node nodeBefore = null;
         
         while ( (currentNode != null) && !key.equals(currentNode.getKey()) )
			{
				nodeBefore = currentNode;
				currentNode = currentNode.getNextNode();
			} // end while
			
			if (currentNode != null)
			{
				// node found; remove it
				Node nodeAfter = currentNode.getNextNode();  // Node after the one to be removed

				if (nodeBefore == null)
					firstNode = nodeAfter;
				else
					nodeBefore.setNextNode(nodeAfter);        // Disconnect the node to be removed

				result = currentNode.getValue();             // Get ready to return removed entry
			  numberOfEntries--;                            // Decrease length for both cases
			} // end if
		} // end if
			
      return result;  
   } // end remove

   public V getValue(K key)
   {
      V result = null;

      // Search for a node that contains key
      Node currentNode = firstNode;

      while ( (currentNode != null) && !key.equals(currentNode.getKey()) )
      {
         currentNode = currentNode.getNextNode();
      } // end while

      if (currentNode != null)
      {
         // Search key found
         result = currentNode.getValue();
      } // end if

      return result;
   } // end getValue

	public boolean contains(K key)
   {
   	return getValue(key) != null; 
   } // end contains

   public boolean isEmpty()
   {
      return numberOfEntries == 0;
   } // end isEmpty
	
   public int getSize()
   {
      return numberOfEntries;
   } // end getSize

	public final void clear()
	{ 
      initializeDataFields();
   } // end clear

	public Iterator<K> getKeyIterator()
	{
		return new KeyIterator();
	} // end getKeyIterator

	public Iterator<V> getValueIterator()
	{
		return new ValueIterator();
	} // end getValueIterator

   /** Initializes the class's data fields to indicate an empty list. */
   private void initializeDataFields()
   {
		firstNode = null;
		numberOfEntries = 0;
   } // end initializeDataFields

	// Same as in SortedLinkedDictionary.
	// Since iterators implement Iterator, methods must be public.
	/**
	 * Iterator class over the search keys in the dictionary
	 */
	private class KeyIterator implements Iterator<K>
	{
		/** The next node in the chain of key/value pairs */
		private Node nextNode;

		/** Constructs a new KeyIterator which will start at the firstNode in the chain of pairs */
		private KeyIterator()
		{
			nextNode = firstNode;
		} // end default constructor

		public boolean hasNext()
		{
			return nextNode != null;
		} // end hasNext

		public K next()
		{
			K result;

			if (hasNext())
			{
				result = nextNode.getKey();
				nextNode = nextNode.getNextNode();
			}
			else
			{
				throw new NoSuchElementException();
			} // end if

			return result;
		} // end next

		/** Remove is unsupported for UnsortedLinkedDictionary
		 * @throws  UnsupportedOperationException when called
		 */
		public void remove()
		{
			throw new UnsupportedOperationException();
		} // end remove
	} // end KeyIterator

	/** Iterator class over the associated values in the dictionary */
	private class ValueIterator implements Iterator<V>
	{

		/** The next node in the chain of key/value pairs */
		private Node nextNode;

		/** Constructs a new ValueIterator starting at the firstNode in the chain */
		private ValueIterator()
		{
			nextNode = firstNode;
		} // end default constructor
		
		public boolean hasNext() 
		{
			return nextNode != null;
		} // end hasNext
		
		public V next()
		{
			V result;
			
			if (hasNext())
			{
				result = nextNode.getValue();
				nextNode = nextNode.getNextNode();
			}
			else
			{
				throw new NoSuchElementException();
			} // end if
		
			return result;
		} // end next

		/**
		 * Unsupported for UnsortedLinkedDictionary
		 * @throws UnsupportedOperationException when called
		 */
		public void remove()
		{
			throw new UnsupportedOperationException();
		} // end remove
	} // end getValueIterator

	/** Container class for key value pairs, also refers to the next node in the linked chain */
	private class Node
	{
		/** the search key */
		private K key;
		/** the associated value */
		private V value;
		/** the next node in the linked chain */
		private Node next;

		/**
		 * Constructs a new node with the given key and value and null next
		 * @param searchKey the search key
		 * @param dataValue the associated value
		 */
		private Node(K searchKey, V dataValue)
		{
			key = searchKey;
			value = dataValue;
			next = null;	
		} // end constructor

		/**
		 * Constructs a new node with the given key, value, and nextNode
		 * @param searchKey the search key
		 * @param dataValue the associated value
		 * @param nextNode the next node in the linked chain
		 */
		private Node(K searchKey, V dataValue, Node nextNode)
		{
			key = searchKey;
			value = dataValue;
			next = nextNode;	
		} // end constructor

		/**
		 * Returns the search key stored in this node
		 * @return the search key
		 */
		private K getKey()
		{
			return key;
		} // end getKey

		/**
		 * Returns the associated value stored in this node
		 * @return the associated value
		 */
		private V getValue()
		{
			return value;
		} // end getValue

		/**
		 * Sets the associated value stored in this node
		 * @param newValue the new associated value to be stored in this node
		 */
		private void setValue(V newValue)
		{
			value = newValue;
		} // end setValue

		/**
		 * Returns the next node in the linked chain that this node refers to
		 * @return the next node in the linked chain
		 */
		private Node getNextNode()
		{
			return next;
		} // end getNextNode

		/**
		 * Sets the next node in the linked chain for this node to refer to
		 * @param nextNode the desired next node in the linked chain following this one
		 */
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		} // end setNextNode
	} // end Node
} // end UnsortedLinkedDictionary