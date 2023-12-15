package GraphPackage;

import ADTPackage.LinkedListWithIterator;
import ADTPackage.ListWithIteratorInterface;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a node / vertex of a graph, which holds data and connects to other vertices
 * @param <T> data type of the data object held in the vertex
 */
class Vertex<T> implements VertexInterface<T>
{
    /** Object held in the vertex which identifies the vertex*/
    private T label;
    /** List of edges between this vertex and its neighbors */
    private ListWithIteratorInterface<Edge> edgeList;
    /** True if this vertex has been visited during the present traversal/path */
    private boolean visited;
    /** The previous vertex along the path from origin to this vertex */
    private VertexInterface<T> previousVertex;
    /** The cost of the path from origin to this vertex */
    private double cost;

    /**
     * Constructor
     * @param vertexLabel the data object used to identify the vertex
     */
    public Vertex(T vertexLabel)
    {
        label = vertexLabel;
        edgeList = new LinkedListWithIterator<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    } // end constructor

    // Ex 8 from here to ....
    public T getLabel()
    {
        return label;
    } // end getLabel

    public boolean hasPredecessor()
    {
        return previousVertex != null;
    } // end hasPredecessor

    public void setPredecessor(VertexInterface<T> predecessor)
    {
        previousVertex = predecessor;
    } // end setPredecessor

    public VertexInterface<T> getPredecessor()
    {
        return previousVertex;
    } // end getPredecessor

    public void visit()
    {
        visited = true;
    } // end visit

    public void unvisit()
    {
        visited = false;
    } // end unvisit

    public boolean isVisited()
    {
        return visited;
    } // end isVisited

    public double getCost()
    {
        return cost;
    } // end getCost

    public void setCost(double newCost)
    {
        cost = newCost;
    } // end setCost

    /**
     * Returns a String representation of this vertex's label
     * @return String representation of the label
     */
    public String toString()
    {
        return label.toString();
    } // end toString

    /** Represents an iterator over the weights of the edges between this vertex and its neighbors */
    private class WeightIterator implements Iterator<Double>
    {
        /** Iterator over the edges */
        private Iterator<Edge> edges;

        /** Constructor */
        private WeightIterator()
        {
            edges = edgeList.getIterator();
        } // end default constructor

        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        public Double next()
        {
            Double edgeWeight = 0.0;
            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                edgeWeight = edgeToNextNeighbor.getWeight();
            }
            else
                throw new NoSuchElementException();

            return edgeWeight;
        } // end next

        /** Remove not supported for WeightIterator */
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end WeightIterator

    public Iterator<Double> getWeightIterator()
    {
        return new WeightIterator();
    } // end getWeightIterator
// . . . to here Ex 8

    public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
    {
        boolean result = false;

        if (!this.equals(endVertex))
        {  // Vertices are distinct
            Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
            boolean duplicateEdge = false;

            while (!duplicateEdge && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    duplicateEdge = true;
            } // end while

            if (!duplicateEdge)
            {
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            } // end if
        } // end if

        return result;
    } // end connect

    public boolean connect(VertexInterface<T> endVertex)
    {
        return connect(endVertex, 0);
    } // end connect

    public Iterator<VertexInterface<T>> getNeighborIterator()
    {
        return new NeighborIterator();
    } // end getNeighborIterator

    public boolean hasNeighbor()
    {
        return !edgeList.isEmpty();
    } // end hasNeighbor

    public VertexInterface<T> getUnvisitedNeighbor()
    {
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while ( neighbors.hasNext() && (result == null) )
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        } // end while

        return result;
    } // end getUnvisitedNeighbor

    /**
     * Whether this vertex's label is equal to the other vertex's label
     * @param other the object to be compared, equal only if both are vertexes with the same label
     * @return whether this vertex's label is equal to the other's label
     */
    public boolean equals(Object other)
    {
        boolean result;

        if ((other == null) || (getClass() != other.getClass()))
            result = false;
        else
        {
            // The cast is safe within this else clause
            @SuppressWarnings("unchecked")
            Vertex<T> otherVertex = (Vertex<T>)other;
            result = label.equals(otherVertex.label);
        } // end if

        return result;
    } // end equals

    /** Iterator over the neighbors this vertex is connected to */
    private class NeighborIterator implements Iterator<VertexInterface<T>>
    {
        /**Iterator over the edges between this vertex and its neighbors */
        private Iterator<Edge> edges;

        /** Constructor */
        private NeighborIterator()
        {
            edges = edgeList.getIterator();
        } // end default constructor

        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        public VertexInterface<T> next()
        {
            VertexInterface<T> nextNeighbor = null;

            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
                throw new NoSuchElementException();

            return nextNeighbor;
        } // end next

        /**Not supported for this iterator */
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end NeighborIterator

    /** Edge between a vertex and its neighbors, may be weighted */
    protected class Edge
    {
        /**Vertex at end of edge*/
        private VertexInterface<T> vertex;
        /**Weight of the edge*/
        private double weight;

        /**
         * Constructor
         * @param endVertex Vertex at end of edge
         * @param edgeWeight weight of the edge
         */
        protected Edge(VertexInterface<T> endVertex, double edgeWeight)
        {
            vertex = endVertex;
            weight = edgeWeight;
        } // end constructor

        /**
         * Constructor
         * @param endVertex Vertex at end of edge
         */
        protected Edge(VertexInterface<T> endVertex)
        {
            vertex = endVertex;
            weight = 0;
        } // end constructor

        /** Returns the vertex at the end of the edge
         * @return the vertex at the end of the edge
         * */
        protected VertexInterface<T> getEndVertex()
        {
            return vertex;
        } // end getEndVertex

        /** Returns the weight of the edge
         * @return the weight of the edge
         * */
        protected double getWeight()
        {
            return weight;
        } // end getWeight
    } // end Edge

    /** For testing */
    public void display()
    {
        System.out.print(label + " " );
        Iterator<VertexInterface<T>> i = getNeighborIterator();
        Iterator<Double> w = getWeightIterator();

        while (i.hasNext())
        {
            Vertex<T> v = (Vertex<T>)i.next();
            System.out.print(v + " " + w.next() + " ");
        } // end while

        System.out.println();
    } // end display
} // end Vertex