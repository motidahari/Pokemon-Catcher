package api;

/*********************************** EDGE DATA CLASS ***************************************/

public class EdgeData implements edge_data {

    private int src, dst;
    private double weight;
    private String info;
    private int tag;
    /**
     * constructor for EdgeData to create a new Edge
     * @param src - id of src node.
     * @param dst - id of dest node.
     * @param weight - double of the weight.
     */
    public EdgeData(int src, int dst, double weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
        this.info = "";
        this.tag = 0;
    }

    /**
     * return the id of the source node of this edge.
     * @return src - id of src node.
     */
    @Override
    public int getSrc() {
        return src;
    }

    /**
     * return the id of the destination node of this edge
     * @return dst - id of dest node.
     */
    @Override
    public int getDest() {
        return dst;
    }

    /**
     * return the weight of the edge
     * @return weight - the weight of this edge (positive value).
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the remark (meta data) associated with this edge.
     * @return info - string of the info by the edge.
     */
    @Override
    public String getInfo() {
        return info;
    }

    /**
     * Allows changing the remark (meta data) associated with this edge.
     * @param s - String of the new info
     */
    @Override
    public void setInfo(String s) {
        info = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     * @return tag - int of the tag by this edge.
     */
    @Override
    public int getTag() {
        return tag;
    }

    /**
     * This method allows setting the "tag" value for temporal marking an edge - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        tag = t;
    }
    /**
     * Returns a string representation of this edge
     * @return A string representation of this edge
     */
    @Override
    public String toString() {
        return "("+getSrc()+"->"+getDest()+")"+"w="+getWeight();
    }
}
