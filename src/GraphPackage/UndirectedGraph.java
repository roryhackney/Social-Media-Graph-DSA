package GraphPackage;

import ADTPackage.StackInterface;

/**
 * Represents an Undirected Graph which stores the vertices and the undirected edges connecting them to their neighbors
 * @param <T> data type of the objects held in the graph
 */
public class UndirectedGraph<T> extends DirectedGraph<T> implements GraphInterface<T>
{
    /** Constructor */
    public UndirectedGraph()
    {
        super();
    } // end default constructor

    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        return super.addEdge(begin, end, edgeWeight) &&
                super.addEdge(end, begin, edgeWeight);
        // Assertion: edge count is twice its correct value due to calling addEdge twice
    } // end addEdge

    public boolean removeEdge(T begin, T end) {
        //TODO: implement removeEdge in Graph class
        return true;
    }

    public boolean addEdge(T begin, T end)
    {
        return this.addEdge(begin, end, 0);
    } // end addEdge

    public int getNumberOfEdges()
    {
        return super.getNumberOfEdges() / 2;
    } // end getNumberOfEdges

    public StackInterface<T> getTopologicalOrder()
    {
        throw new UnsupportedOperationException("Topological sort is illegal in an undirected graph.");
    } // end getTopologicalOrder
} // end UndirectedGraph
