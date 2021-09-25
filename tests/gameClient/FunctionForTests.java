package gameClient;

import api.*;
import gameClient.util.Point3D;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FunctionForTests {
    private Config con = new Config();
    public static HashMap<Integer, HashMap<Integer, graph_data>> _graphData = new HashMap<>();
    private String[] arrayOfScenariosPath = getArrayOfAllScenariosPath();

    //========================= CONSTRUCTORS ===========================
    /**
     * constructor function
     * */
    public FunctionForTests(){
        arrayOfScenariosPath = getArrayOfAllScenariosPath();
    }

    //=========================== GETTERS & SETTERS ================================

    public static HashMap<Integer, HashMap<Integer, graph_data>> get_graphData() {
        return _graphData;
    }
    /**
     * This function return the array of the paths
     * */
    public String[] getArrayOfScenariosPath() {
        return arrayOfScenariosPath;
    }

    //=========================== GETTERS & SETTERS ================================
    /**
     * This function receives a string of Json and creates a list of pokemon
     * */
    public ArrayList<Pokemon> json2Pokemons(String json) {
        ArrayList<Pokemon> ans = new ArrayList<>();
        try {
            JSONObject jsonOb = new JSONObject(json);
            JSONArray ags = jsonOb.getJSONArray("Pokemons");
            for(int i=0; i<ags.length(); i++) {
                JSONObject pp = ags.getJSONObject(i);
                JSONObject pk = pp.getJSONObject("Pokemon");
                int t = pk.getInt("type");
                double v = pk.getDouble("value");
                String p = pk.getString("pos");
                Pokemon f = new Pokemon(new Point3D(p), t, v, null);
                ans.add(f);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        ans.sort((o1, o2) -> {
            if(o1.getValue() > o2.getValue())
                return 1;
            else if(o1.getValue() < o2.getValue())
                return -1;
            else return 0;
        });
        return ans;
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
     * This function return the array of the paths of the levels
     * */
    public String getPathAllScenario(int scenario) {
        return System.getProperty("user.dir") + "/tests/gameClient/scenarios/"+ scenario;
    }
    /**
     * This function receives a string of Json and creates a list of pokemon
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
     * This function receives a string of Json and creates a list of Agent
     * */
    public List<Agent> getAgents(String json, directed_weighted_graph g){
        ArrayList<Agent> ans = new ArrayList<>();
        try {
            JSONObject ttt = new JSONObject(json);
            JSONArray ags = ttt.getJSONArray("Agents");
            for(int i=0;i<ags.length();i++) {
                Random r = new Random();
                r.nextInt(g.nodeSize());
                //System.out.println(r.nextInt(g.nodeSize()));
                Agent c = new Agent(g ,r.nextInt(g.nodeSize()));
                ans.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ans;
    }
    /**
     * This function receives a string of Json and creates a graph from it
     * */
    public directed_weighted_graph graphJsonToGraph(String json){
        dw_graph_algorithms ga = new DWGraph_Algo();
        ga.load("files/graph.json");
        return ga.getGraph();
    }

}