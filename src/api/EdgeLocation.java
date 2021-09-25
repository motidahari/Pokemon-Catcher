package api;

/**
 * This class represents a position on the graph (a relative position
 * on an edge - between two consecutive nodes).
 */
public class EdgeLocation implements edge_location {

    private edge_data edge;
    private double ratio;
    /**
     * Returns the edge on which the location is.
     * @return e - return the edge.
     */
    @Override
    public edge_data getEdge() {
        return edge;
    }

    /**
     * Returns the relative ration [0,1] of the location between src and dest.
     * @return d - return the ratio of the edge.
     */
    @Override
    public double getRatio() {
        return ratio;
    }
}
