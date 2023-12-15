package ADTPackage;
import java.util.EmptyStackException;
/**
   A class of stacks whose entries are stored in a chain of nodes.
   @param <T> data type of objects to be stored in the LinkedStack
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public final class LinkedStack<T> implements StackInterface<T>
{
	/** References the first node in the chain */
	private Node topNode;

	/** Constructs a new empty LinkedStack */
	public LinkedStack()
	{
		topNode = null;
	} // end default constructor
	
	public void push(T newEntry)
	{
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
//    topNode = new Node(newEntry, topNode); // Alternate code
	} // end push

	public T peek()
	{
		if (isEmpty())
			throw new EmptyStackException();
		else
         return topNode.getData();
	} // end peek

	public T pop()
	{
	   T top = peek();  // Might throw EmptyStackException
   // Assertion: topNode != null
      topNode = topNode.getNextNode();

	   return top;
	} // end pop

/*
// Question 1, Chapter 6: Does not call peek 
	public T pop()
	{
      if (isEmpty())
         throw new EmptyStackException();
      else
		{
         assert (topNode != null);
			top = topNode.getData();
			topNode = topNode.getNextNode();
		} // end if
		
		return top;
	} // end pop
*/		

	public boolean isEmpty()
	{
		return topNode == null;
	} // end isEmpty
	
	public void clear()
	{
		topNode = null;  // Causes deallocation of nodes in the chain
	} // end clear

	/** Node represents a container for data which also refers to the next Node in the LinkedStack */
	private class Node
	{
		/** Entry in stack */
      private T    data;
	  /** Link to next node */
      private Node next;

	  /**
	 	* Constructs a new node with the given dataPortion and null next
	  	* @param dataPortion the data the node should contain
	  */
      private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor

		/**
		 * Constructs a new node with the given dataPortion and which refers to linkPortion
		 * @param dataPortion the data the node should contain
		 * @param linkPortion the next node in the chain that this node should link to
		 */
      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;	
      } // end constructor

		/**
		 * Returns the data held in this node
		 * @return the data held in this node
		 */
      private T getData()
      {
         return data;
      } // end getData

		/**
		 * Sets the data in this node to newData
		 * @param newData the new data this node should hold
		 */
      private void setData(T newData)
      {
         data = newData;
      } // end setData

		/**
		 * Returns the next node this node refers to
		 * @return the next node this node refers to
		 */
      private Node getNextNode()
      {
         return next;
      } // end getNextNode

		/**
		 * Sets the next node this node should refer to
		 * @param nextNode the next node this node should refer to
		 */
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
} // end LinkedStack
