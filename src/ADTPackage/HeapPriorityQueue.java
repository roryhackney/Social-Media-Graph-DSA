package ADTPackage;
/**
   A class that implements the ADT priority queue by using a maxheap.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.01
   @param <T> data type for the objects stored in the priority queue. Must be Comparable.
*/
public final class HeapPriorityQueue<T extends Comparable<? super T>>
                   implements PriorityQueueInterface<T>
{
	/** The priorityQueue to hold items */
	private MaxHeapInterface<T> pq;

	/** Constructs a new empty PriorityQueue */
	public HeapPriorityQueue()
	{
		pq = new MaxHeap<>();
	} // end default constructor
	
	public void add(T newEntry)
	{ 
		pq.add(newEntry);
	} // end add

	public T remove()
	{
		return pq.removeMax();
	} // end remove
		
	public T peek()
	{
		return pq.getMax();
	} // end peek

	public boolean isEmpty()
	{
		return pq.isEmpty();
	} // end isEmpty
	
	public int getSize()
	{
		return pq.getSize();
	} // end getSize

	public void clear()
	{
		pq.clear();
	} // end clear
} // end HeapPriorityQueue
