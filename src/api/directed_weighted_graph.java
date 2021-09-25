package api;
import java.util.Collection;
/**
 * This interface represents a directional weighted graph.
 * The interface has a road-system or communication network in mind - 
 * and support a large number of nodes (over 100,000).
 * The implementation be based on an efficient compact representation
 * (NOT be based on a n*n matrix).
 *
 */

public interface directed_weighted_graph {
	/**
	 * returns the node_data by the node_id,
	 * @param key - the node_id.
	 * @return the node_data by the node_id, null if none.
	 */
	public node_data getNode(int key);
	/**
	 * returns the data of the edge (src,dest), null if none.
	 * Note: this method run in O(1) time.
	 * @param src - start node.
	 * @param dest - end node.
	 * @return e - return the edge between src to dest.
	 */
	public edge_data getEdge(int src, int dest);
	/**
	 * adds a new node to the graph with the given node_data.
	 * Note: this method run in O(1) time.
	 * @param n - new node.
	 */
	public void addNode(node_data n);
	/**
	 * Connects an edge with weight w between node src to node dest.
	 * * Note: this method run in O(1) time.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 */
	public void connect(int src, int dest, double w);
	/**
	 * This method returns a pointer (shallow copy) for the
	 * collection representing all the nodes in the graph. 
	 * Note: this method run in O(1) time.
	 * @return Collection<node_data> - all the vertex in the graph.
	 */
	public Collection<node_data> getV();
	/**
	 * This method returns a pointer (shallow copy) for the
	 * collection representing all the edges getting out of 
	 * the given node (all the edges starting (source) at the given node). 
	 * Note: this method run in O(k) time, k being the collection size.
	 * @param node_id - id of the node.
	 * @return Collection<edge_data> - all the Neighbors by node id.
	 */
	public Collection<edge_data> getE(int node_id);
	/**
	 * Deletes the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method run in O(k), V.degree=k, as all the edges be removed.
	 * @param key - of the node.
	 * @return node - the data of the removed node (null if none).
	 */
	public node_data removeNode(int key);
	/**
	 * Deletes the edge from the graph,
	 * Note: this method run in O(1) time.
	 * @param src - id of src node.
	 * @param dest - id of dest node.
	 * @return the data of the removed edge (null if none).
	 */
	public edge_data removeEdge(int src, int dest);
	/** Returns the number of vertices (nodes) in the graph.
	 * Note: this method run in O(1) time.
	 * @return nodeSize - the number of the vertex in the graph.
	 */
	public int nodeSize();
	/** 
	 * Returns the number of edges (assume directional graph).
	 * Note: this method run in O(1) time.
	 * @return edgeSize - the number of the edges in the graph.
	 */
	public int edgeSize();
/**
 * Returns the Mode Count - for testing changes in the graph.
 * @return t - The amount of changes in the graph.
 */
	public int getMC();
}
