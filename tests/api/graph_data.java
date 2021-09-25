package api;

import api.*;

import java.util.List;
/**
 * This class presents the distance between every two vertex
 **/
public class graph_data {
    private double _path;
    private List<node_data> _list;
    private int _length;
    private dw_graph_algorithms _algo = new DWGraph_Algo();
    private directed_weighted_graph g = new DWGraph_DS();
    int src,dest;

    /**
     * This constructor receives a graph and two vertex.
     * It provides the distance between src to test and the sum of the weights of the edges
     * */
    public graph_data(directed_weighted_graph g, int src, int dest){
        this.src = src;
        this.dest = dest;
        _algo.init(g);
        _path = _algo.shortestPathDist(src,dest);
        _list = _algo.shortestPath(src,dest);
        _length = _algo.shortestPath(src,dest).size();
    }

    /**
     * return the path from src to dest
     * */
    public double get_path() {
        return _path;
    }

    /**
     * return the path from src to dest
     **/
    public List<node_data> get_list() {
        return _list;
    }
    /**
     * return the String of the data
     **/
    @Override
    public String toString() {
         String str = "\n(" + src + " -> " + dest + ")\n";
         str += "_path => " + _path +"\n";
         str += "_list => " + _list +"\n";
        return str;
    }
}
