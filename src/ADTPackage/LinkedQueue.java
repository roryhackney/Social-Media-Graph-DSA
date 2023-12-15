package ADTPackage;
/**
 A class that implements a queue of objects by using
 a chain of linked nodes that has both head and tail references.
 @param <T> data type of objects to be stored in the LinkedQueue
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
 */
public final class LinkedQueue<T> implements QueueInterface<T>
{
   /** References node at front of queue */
   private Node firstNode;
   /** References node at back of queue */
   private Node lastNode;

   /** Constructs a new empty LinkedQueue */
   public LinkedQueue()
   {
      firstNode = null;
      lastNode = null;
   } // end default constructor
	
   public void enqueue(T newEntry)
   {
      Node newNode = new Node(newEntry, null);
      
      if (isEmpty())
         firstNode = newNode;
      else
         lastNode.setNextNode(newNode);
      
      lastNode = newNode;
   } // end enqueue

   public T getFront()
   {
      if (isEmpty())
         throw new EmptyQueueException();
      else
         return firstNode.getData();
   } // end getFront
   
   public T dequeue()
   {
      T front = getFront();  // Might throw EmptyQueueException
                             // Assertion: firstNode != null
      firstNode.setData(null);
      firstNode = firstNode.getNextNode();
      
      if (firstNode == null)
         lastNode = null;
      
      return front;
   } // end dequeue
		
   public boolean isEmpty()
   {
      return (firstNode == null) && (lastNode == null);
   } // end isEmpty
   
   public void clear()
   {
      firstNode = null;
      lastNode = null;
   } // end clear

   /** Node represents a container for data which also refers to the next Node in the LinkedQueue */
   private class Node
   {
      /** Entry in queue */
      private T    data;
      /** Link to next node */
      private Node next;

      /**
       * Constructs a new node with the given dataPortion and null next
       * @param dataPortion the data the node should contain
       */
      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;
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
} // end LinkedQueue
