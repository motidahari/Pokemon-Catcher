package api;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class DWGraph_DS implements directed_weighted_graph , Serializable {

    private HashMap<Integer, node_data> V;
    private HashMap<Integer, HashMap<Integer, edge_data>> E;
    private HashMap<Integer, HashMap<Integer, node_data>> N;
    private int nodeSize;
    private int edgeSize;
    private int mc;

    /**
     * constructor for WGraph_DS to create new empty hashMap(Vertex) for the graph
     */
    public DWGraph_DS() {
        V = new HashMap<>();
        E = new HashMap<>();
        N = new HashMap<>();
        nodeSize = 0;
        edgeSize = 0;
        mc = 0;
    }
    /**
     * Compute a deep copy of this weighted graph.
     * we run first to add the Vertex from the graph
     * then we run by the the graph and the list of the Neighbors to connect the nodes
     * @param other - object of the graph
     */
    public DWGraph_DS(directed_weighted_graph other){
        for (node_data v : other.getV()){
            node_data newNode = new NodeData(v);
            this.addNode(newNode);
        }
        for (node_data v : other.getV()){
            for (edge_data ni : other.getE(v.getKey())){
                connect(v.getKey(), ni.getDest(), ni.getWeight());

            }
        }
        this.nodeSize = other.nodeSize();
        this.edgeSize = other.edgeSize();
    }

    /* ********************* CLASS METHODS ********************** */

    /**
     * returns the node_data by the node_id,
     * @param key - the node_id.
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        return V.get(key);
    }


    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method run in O(1) time.
     * check if has two Vertex in the graph by src && dest
     * check if has two Vertex in the edge by src && dest
     * if the two nodes are exist we check if to src has neighbor to dest && to dest has neighbor src
     * @param src key
     * @param dest key
     * @return true if are they neighbors from both sides, false if they don't have
     */
    public boolean hasEdge(int src, int dest) {
        return  V.containsKey(src) &&
                V.containsKey(dest) &&
                E.containsKey(src) &&
                E.containsKey(dest) &&
                E.get(src).containsKey(dest) &&
                E.get(dest).containsKey(src) &&
                N.containsKey(src) &&
                N.containsKey(dest) &&
                N.get(src).containsKey(dest) &&
                N.get(dest).containsKey(src) &&
                src != dest;
    }
    /**
     * returns the data of the edge (src,dest), null if none.
     * this method runs in O(1) time.
     * @param src - start node.
     * @param dest - end node.
     * @return e - return the edge between src to dest.
     */
    @Override
    public edge_data getEdge(int src, int dest) {
        return E.get(src).get(dest);
    }

    /**
     * adds a new node to the graph with the given node_data.
     * this method runs in O(1) time.
     * @param n - new node.
     */
    @Override
    public void addNode(node_data n) {
        int key = n.getKey();
        if(V.putIfAbsent(key,n) == null){
            E.put(key, new HashMap<>());
            N.put(key, new HashMap<>());
            nodeSize++;
            mc++;
        }
    }

    /**
     * Connects an edge with weight w between node src to node dest.
     * this method runs in O(1) time.
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if (src != dest) {
            E.get(src).putIfAbsent(dest ,new EdgeData(src,dest,w));
            N.get(src).putIfAbsent(V.get(dest).getKey() ,V.get(dest));
            edgeSize++;
            mc++;
        }
    }

    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * this method runs in O(1) time.
     * @return Collection<node_data> - all the vertex in the graph.
     */
    @Override
    public Collection<node_data> getV() {
        return V.values();
    }


    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the edges getting out of
     * the given node (all the edges starting (source) at the given node).
     * this method runs in O(k) time, k being the collection size.
     * @param node_id - id of the node.
     * @return Collection<edge_data> - all the Neighbors by node id.
     */
    @Override
    public Collection<edge_data> getE(int node_id) {
        return E.get(node_id).values();
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method run in O(k), V.degree=k, as all the edges be removed.
     * @param key - of the node.
     * @return node - the data of the removed node (null if none).
     */
    @Override
    public node_data removeNode(int key) {
        if (V.containsKey(key)) {
            Collection<edge_data> t = getE(key);
            while (!t.isEmpty())
                removeEdge(key, t.iterator().next().getDest());
            mc++;
            nodeSize--;
            N.remove(key);
            return V.remove(key);
        }
        return null;
    }

    /**
     * Deletes the edge from the graph,
     * Note: this method run in O(1) time.
     * @param src - id of src node.
     * @param dest - id of dest node.
     * @return the data of the removed edge (null if none).
     */
    @Override
    public edge_data removeEdge(int src, int dest) {
        edge_data t = E.get(src).remove(dest);
        N.get(src).remove(dest);
        if(t != null) {
            edgeSize--;
            mc++;
        }
        return t;
    }

    /**
     * Returns the number of vertices (nodes) in the graph.
     * Note: this method run in O(1) time.
     * @return nodeSize - the number of the vertex in the graph.
     */
    @Override
    public int nodeSize() {
        return nodeSize;
    }

    /**
     * Returns the number of edges (assume directional graph).
     * Note: this method run in O(1) time.
     * @return edgeSize - the number of the edges in the graph.
     */
    @Override
    public int edgeSize() {
        return edgeSize;
    }

    /**
     * Returns the Mode Count - for testing changes in the graph.s
     * @return mc - The amount of changes in the graph.
     */
    @Override
    public int getMC() {
        return mc;
    }

    /**
     * Returns a string representation of this graph as an adjacency list.
     * @return s - a string representation this graph
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Vertices: " + nodeSize() + " Edges: " + edgeSize() + " MC: " + getMC() + "\n");
        for (int key : V.keySet()) {
            s.append(key).append(": ");
            for (edge_data e : getE(key)) {
                s.append(e);
            }
            s.append("\n");
        }
        return s.toString();
    }
}