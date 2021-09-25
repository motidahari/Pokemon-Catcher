package api;

import gameClient.Config;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the class of DWGraph_DS
 * */
class DWGraph_DSTest {
    private Config con = new Config();
    private FunctionForTests _func = new FunctionForTests();
    private directed_weighted_graph _graph;
    private String _pathFolder = con.PATH_FOLDER;
    private String _fileGraph = con.GameGraph;
    private String _jsonGraph;
    private String[] _array = _func.getArrayOfScenariosPath();
    private HashMap<Integer,HashMap<Integer,graph_data>> graphData = new HashMap<Integer, HashMap<Integer, graph_data>>();


    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the constructor function for the class DWGraph_DS
     * */
    @Test
    void constructor() {
        _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(_array[0] + _fileGraph);
        _graph = _func.graphJsonToGraph(_jsonGraph);
        assertNotNull(_graph);
        graphData =_func.getGraphData(_graph);
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getNode function for the class DWGraph_DS
     * */
    @Test
    void getNode() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            for (node_data n : _graph.getV()){
                assertNotNull(n);
                assertNotNull(_graph.getNode(n.getKey()));
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getEdge function for the class DWGraph_DS
     * */
    @Test
    void getEdge() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            _func.createNewTextFileFromString(_func.getToString(graphData),"graphData");
            for (node_data n : _graph.getV()) {
                for (node_data ni : _graph.getV()) {

                    edge_data e = _graph.getEdge(n.getKey(), ni.getKey());
                    if (e != null) {
                        assertEquals(e.getSrc(), n.getKey());
                        assertEquals(e.getDest(), ni.getKey());
                        assertNotEquals(e.getSrc(), ni.getKey());
                        assertNotEquals(e.getDest(), n.getKey());
                    }
                }
            }
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the addNode function for the class DWGraph_DS
     * */
    @Test
    void addNode() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            _func.createNewTextFileFromString(_func.getToString(graphData),"graphData");
            directed_weighted_graph newG = new DWGraph_DS();
            for (node_data n : _graph.getV()) {
                newG.addNode(n);
            }
            for (node_data n : _graph.getV()) {
                assertEquals(n.getKey(),newG.getNode(n.getKey()).getKey());
            }
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the connect function for the class DWGraph_DS
     * */
    @Test
    void connect() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            _func.createNewTextFileFromString(_func.getToString(graphData),"graphData");
            directed_weighted_graph newG = new DWGraph_DS();
            for (node_data n : _graph.getV()) {
                newG.addNode(n);
            }
            for (node_data n : _graph.getV()) {
                for (node_data ni : _graph.getV()) {
                    if(_graph.getEdge(n.getKey(),ni.getKey()) != null){
                        newG.connect(n.getKey(),ni.getKey(),_graph.getEdge(n.getKey(),ni.getKey()).getWeight());
                    }

                }
            }
            for (node_data n : _graph.getV()) {
                for (node_data ni : _graph.getV()) {
                    if(_graph.getEdge(n.getKey(),ni.getKey()) != null){
                        assertEquals(newG.getE(n.getKey()),newG.getE(n.getKey()));
                    }

                }
            }
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getV function for the class DWGraph_DS
     * */
    @Test
    void getV() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            _func.createNewTextFileFromString(_func.getToString(graphData),"graphData");
            directed_weighted_graph newG = new DWGraph_DS();
            for (node_data n : _graph.getV()) {
                newG.addNode(n);
            }
            for (node_data n : newG.getV()) {
                assertEquals(newG.getV(), newG.getV());
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getE function for the class DWGraph_DS
     * */
    @Test
    void getE() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            _func.createNewTextFileFromString(_func.getToString(graphData),"graphData");
            directed_weighted_graph newG = new DWGraph_DS();
            for (node_data n : _graph.getV()) {
                newG.addNode(n);
            }
            for (node_data n : _graph.getV()) {
                for (node_data ni : _graph.getV()) {
                    if(_graph.getEdge(n.getKey(),ni.getKey()) != null){
                        newG.connect(n.getKey(),ni.getKey(),_graph.getEdge(n.getKey(),ni.getKey()).getWeight());
                    }
                }
            }
            for (node_data n : _graph.getV()) {
                for (node_data ni : _graph.getV()) {
                    if(_graph.getEdge(n.getKey(),ni.getKey()) != null){
                        assertEquals(newG.getE(n.getKey()),newG.getE(n.getKey()));
                    }
                }
            }
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the removeNode function for the class DWGraph_DS
     * */
    @Test
    void removeNode() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            _func.createNewTextFileFromString(_func.getToString(graphData),"graphData");
            directed_weighted_graph newG = new DWGraph_DS();
            for (node_data n : _graph.getV()) {
                newG.addNode(n);
            }
            for (node_data n : _graph.getV()) {
                assertNotNull(newG.removeNode(n.getKey()));

            }
            assertEquals(newG.getV().size(), 0);
        }

    }
    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the removeEdge function for the class DWGraph_DS
     * */
    @Test
    void removeEdge() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            _func.createNewTextFileFromString(_func.getToString(graphData),"graphData");
            directed_weighted_graph newG = new DWGraph_DS();
            for (node_data n : _graph.getV()) {
                newG.addNode(n);
            }
            for (node_data n : _graph.getV()) {
                for (node_data ni : _graph.getV()) {
                    if(_graph.getEdge(n.getKey(),ni.getKey()) != null){
                        newG.connect(n.getKey(),ni.getKey(),_graph.getEdge(n.getKey(),ni.getKey()).getWeight());
                    }
                }
            }
            for (node_data n : _graph.getV()) {
                for (node_data ni : _graph.getV()) {
                    if(_graph.getEdge(n.getKey(),ni.getKey()) != null){
                        newG.removeEdge(n.getKey(),ni.getKey());
                    }
                }
            }
            assertEquals(newG.edgeSize(), 0);
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the nodeSize function for the class DWGraph_DS
     * */
    @Test
    void nodeSize() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            _func.createNewTextFileFromString(_func.getToString(graphData),"graphData");
            directed_weighted_graph newG = new DWGraph_DS();
            for (node_data n : _graph.getV()) {
                newG.addNode(n);
            }
            assertEquals(newG.nodeSize(), _graph.getV().size());
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the edgeSize function for the class DWGraph_DS
     * */
    @Test
    void edgeSize() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            _func.createNewTextFileFromString(_func.getToString(graphData),"graphData");
            directed_weighted_graph newG = new DWGraph_DS();
            for (node_data n : _graph.getV()) {
                newG.addNode(n);
            }
            for (node_data n : _graph.getV()) {
                for (node_data ni : _graph.getV()) {
                    if(_graph.getEdge(n.getKey(),ni.getKey()) != null){
                        newG.connect(n.getKey(),ni.getKey(),_graph.getEdge(n.getKey(),ni.getKey()).getWeight());
                    }
                }
            }
            assertEquals(newG.edgeSize(), _graph.edgeSize());
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getMC function for the class DWGraph_DS
     * */
    @Test
    void getMC() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            _func.createNewTextFileFromString(_func.getToString(graphData),"graphData");
            directed_weighted_graph newG = new DWGraph_DS();
            for (node_data n : _graph.getV()) {
                newG.addNode(n);
            }
            for (node_data n : _graph.getV()) {
                for (node_data ni : _graph.getV()) {
                    if(_graph.getEdge(n.getKey(),ni.getKey()) != null){
                        newG.connect(n.getKey(),ni.getKey(),_graph.getEdge(n.getKey(),ni.getKey()).getWeight());
                    }
                }
            }
            assertEquals(newG.getMC(), _graph.getMC());
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the testToString function for the class DWGraph_DS
     * */
    @Test
    void testToString() {
        for (String str : _array) {
            _jsonGraph = FunctionForTests.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            graphData =_func.getGraphData(_graph);
            _func.createNewTextFileFromString(_func.getToString(graphData),"graphData");
            directed_weighted_graph newG = new DWGraph_DS();
            for (node_data n : _graph.getV()) {
                newG.addNode(n);
            }
            for (node_data n : _graph.getV()) {
                for (node_data ni : _graph.getV()) {
                    if(_graph.getEdge(n.getKey(),ni.getKey()) != null){
                        newG.connect(n.getKey(),ni.getKey(),_graph.getEdge(n.getKey(),ni.getKey()).getWeight());
                    }
                }
            }
            assertEquals(newG.toString(), _graph.toString());
        }

    }

}