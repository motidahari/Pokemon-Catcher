package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FunctionForTests {
    private Config con = new Config();
    public static HashMap<Integer, HashMap<Integer, graph_data>> _graphData = new HashMap<>();
    private directed_weighted_graph _graph;
    private final dw_graph_algorithms _algo = new DWGraph_Algo();
    private String[] arrayOfScenariosPath = getArrayOfAllScenariosPath();
    private HashMap<Integer,HashMap<Integer,graph_data>> graphData = new HashMap<Integer, HashMap<Integer, graph_data>>();

    //========================= CONSTRUCTORS ===========================
    /**
     * constructor function
     * */
    public FunctionForTests(){
        arrayOfScenariosPath = getArrayOfAllScenariosPath();
    }

    //=========================== GETTERS & SETTERS ================================
    /**
     * This function return the array of the paths
     * */
    public String[] getArrayOfScenariosPath() {
        return arrayOfScenariosPath;
    }

    //=========================== GETTERS & SETTERS ================================
    /**
     * This function receives a string that represents the content of the file that we want to export,
     * and it receives the name of the file.
     * The function creates a new file with the string
     * */
    public boolean createNewTextFileFromString(String str, String nameFile) {
        try {
            FileWriter myWriter = new FileWriter(nameFile + ".txt");
            myWriter.write(str);
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * This function receives a path of Json file and creates a string of the Json
     * */
    public static String readJsonFromFileAndGetAsString(String path) {
        String str = "";
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file, "UTF-8");
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                str += data;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return str;
    }
    /**
     * This function return path of a level
     * */
    public String getPathAllScenario(int scenario) {
        return System.getProperty("user.dir") + "/tests/gameClient/scenarios/"+ scenario;
    }
    /**
     * This function return the array of the paths of the levels
     * */
    public String[] getArrayOfAllScenariosPath() {
        String [] array = new String[0];
        for (int i = 0; i < 24; i++) {
            array = Arrays.copyOf(array, array.length + 1);
            array[i] = getPathAllScenario(i);
        }
        return array;
    }
    /**
     * This function receives a string of Json and creates a graph from it
     * */
    public directed_weighted_graph graphJsonToGraph(String json){
        dw_graph_algorithms ga = new DWGraph_Algo();
        ga.load("files/graph.json");
        return ga.getGraph();
    }
    /**
     * This function receives a graph and it returns all the information regarding the paths on the graph in HashMap
     * */
    public HashMap<Integer,HashMap<Integer,graph_data>> getGraphData(directed_weighted_graph g){
        this._graph = g;
        for (node_data v : g.getV()){
            node_data newNode = new NodeData(v);
            this.graphData.put(newNode.getKey(),new HashMap<>());
        }
        for (node_data v : g.getV()){
            for (node_data ni : g.getV()){
                graph_data GD = new graph_data(g,v.getKey(),ni.getKey());
                graphData.get(v.getKey()).put(ni.getKey(),GD);
            }
        }
        this.graphData = graphData;
        return graphData;
    }
    /**
     * This function receives a graphData and print all the data
     * */
    public void PrintToString(HashMap<Integer,HashMap<Integer,graph_data>> graphData){
        for (node_data v : _graph.getV()){
            for (node_data ni : _graph.getV()){
                System.out.println( graphData.get(v.getKey()).get(ni.getKey()).toString());
            }
        }
    }
    /**
     * This function receives a graphData and returns string str the represent all the data by the graph
     * */
    public String getToString(HashMap<Integer,HashMap<Integer,graph_data>> graphData){
        String str = "";
        for (node_data v : _graph.getV()){
            for (node_data ni : _graph.getV()){
                str +=  graphData.get(v.getKey()).get(ni.getKey()).toString();
            }
        }
        return str;
    }


}