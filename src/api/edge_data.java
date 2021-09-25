package api;
/**
 * This interface represents the set of operations applicable on a 
 * directional edge(src,dest) in a (directional) weighted graph.
 * @author boaz.benmoshe
 *
 */
public interface edge_data {
	/**
	 * The id of the source node of this edge.
	 * @return src - the id of the vertex.
	 */
	public int getSrc();
	/**
	 * The id of the destination node of this edge.
	 * @return dest - the id of the vertex.
	 */
	public int getDest();
	/**
	 * return the weight of this edge (positive value).
	 * @return w -the weight of the edge.
	 */
	public double getWeight();
	/**
	 * Returns the remark (meta data) associated with this edge.
	 * @return s - string of the info by the edge.
	 */
	public String getInfo();
	/**
	 * Allows changing the remark (meta data) associated with this edge.
	 * @param s - the new info.
	 */
	public void setInfo(String s);
	/**
	 * Temporal data (aka color: e,g, white, gray, black) 
	 * which can be used be algorithms .
	 * @return t - return the tag by the edge.
	 */
	public int getTag();
	/** 
	 * This method allows setting the "tag" value for temporal marking an edge - common
	 * practice for marking by algorithms.
	 * @param t - the new value of the tag.
	 */
	public void setTag(int t);
}
