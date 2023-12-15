package GraphPackage;

/**
 * Graph interface which combines the requirements of BasicGraph and GraphAlgorithms
 * @param <T> data type to be held in the vertices of the Graph
 */
public interface GraphInterface<T> extends BasicGraphInterface<T>,
        GraphAlgorithmsInterface<T>
{
} // end GraphInterface
