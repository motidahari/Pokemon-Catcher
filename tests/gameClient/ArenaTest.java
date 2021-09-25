package gameClient;

import Server.Game_Server_Ex2;
import api.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class tests the class of Arena
 * */
class ArenaTest {
    private Config con = new Config();
    private FunctionForTests _func = new FunctionForTests();
    private game_service _game;
    private directed_weighted_graph _graph;

    private List<Agent> _agents = new ArrayList<>();
    private List<Pokemon> _pokemons = new ArrayList<Pokemon>();
    private List<String> _info;
    private static HashMap<Integer, ArrayList<node_data>> paths = new HashMap<>();
    private HashMap<Integer, Pokemon> map = new HashMap<>();

    private String _pathFolder = con.PATH_FOLDER;
    private String _fileAgents = con.Agents;
    private String _fileGraph = con.GameGraph;
    private String _fileGame = con.Game;
    private String _filePokemons = con.Pokemons;
    private String _jsonAgents;
    private String _jsonGraph;
    private String _jsonPokemons;
    private String[] _array = _func.getArrayOfScenariosPath();

    private final long id = 1111111111;
    /**
     * We are running by array, this array contains paths for all levels.
     * we import Json files and create objects from them, that contain the information regarding the rules of the game
     * This functions tests the constructor function for the class Arena
     * */
    @Test
    void constructor() {
        int i = 0;
        for (String str : _array){
             _jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
             _jsonGraph = _func.readJsonFromFileAndGetAsString(str + _fileGraph);
             _jsonPokemons = _func.readJsonFromFileAndGetAsString(str + _filePokemons);
            _game = Game_Server_Ex2.getServer(i++);
            _game.login(id);
            Arena a = new Arena(_game);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            _agents = _func.getAgents(_jsonAgents,_graph);
            _pokemons = _func.json2Pokemons(_game.getPokemons());
            _pokemons = _func.json2Pokemons(_jsonPokemons);

            assertEquals((_info = a.get_info()).toString(),"[]");
            assertEquals(a.getGraph().toString(),_graph.toString());
            assertEquals(a.getPokemons(),_pokemons);
            assertTrue(new File(_pathFolder + _fileAgents).isFile());
            assertTrue(new File(_pathFolder + _fileGraph).isFile());
            assertTrue(new File(_pathFolder + _fileGame).isFile());
            assertTrue(new File(_pathFolder + _filePokemons).isFile());
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * we import Json files and create objects from them, that contain the information regarding the rules of the game
     * This functions tests the getAgents function for the class Arena
     * */
    @Test
    void getAgents() {
        int i = 0;
        for (String str : _array){
            _jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            _jsonGraph = _func.readJsonFromFileAndGetAsString(str + _fileGraph);
            _jsonPokemons = _func.readJsonFromFileAndGetAsString(str + _filePokemons);

            _game = Game_Server_Ex2.getServer(i++);
            _game.login(id);

            Arena a = new Arena(_game);

            _graph = _func.graphJsonToGraph(_jsonGraph);
            _agents = _func.getAgents(_jsonAgents,_graph);
            _pokemons = _func.json2Pokemons(_game.getPokemons());
            _pokemons = _func.json2Pokemons(_jsonPokemons);
            assertTrue(a.getAgents(_jsonAgents).size() > 0);
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * we import Json files and create objects from them, that contain the information regarding the rules of the game
     * This functions tests the jsonToAgents function for the class Arena
     * */
    @Test
    void jsonToAgents() {
        int i = 0;
        for (String str : _array){
            _jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            _jsonGraph = _func.readJsonFromFileAndGetAsString(str + _fileGraph);
            _jsonPokemons = _func.readJsonFromFileAndGetAsString(str + _filePokemons);

            _game = Game_Server_Ex2.getServer(i++);
            _game.login(id);

            Arena a = new Arena(_game);
            List<Agent> a1 = a.getAgents(_jsonAgents);
            assertEquals(a1,a.getAgents(_jsonAgents));
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * we import Json files and create objects from them, that contain the information regarding the rules of the game
     * This functions tests the getPokemons function for the class Arena
     * */
    @Test
    void getPokemons() {
        int i = 0;
        for (String str : _array){
            _jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            _jsonGraph = _func.readJsonFromFileAndGetAsString(str + _fileGraph);
            _jsonPokemons = _func.readJsonFromFileAndGetAsString(str + _filePokemons);

            _game = Game_Server_Ex2.getServer(i++);
            _game.login(id);

            Arena a = new Arena(_game);

            _graph = _func.graphJsonToGraph(_jsonGraph);
            _agents = _func.getAgents(_jsonAgents,_graph);
            _pokemons = _func.json2Pokemons(_game.getPokemons());
            _pokemons = _func.json2Pokemons(_jsonPokemons);

            a.setPokemons(_pokemons);
            assertEquals(_pokemons,a.getPokemons());
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * we import Json files and create objects from them, that contain the information regarding the rules of the game
     * This functions tests the setPokemons function for the class Arena
     * */
    @Test
    void setPokemons() {
        int i = 0;
        for (String str : _array){
            _jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            _jsonGraph = _func.readJsonFromFileAndGetAsString(str + _fileGraph);
            _jsonPokemons = _func.readJsonFromFileAndGetAsString(str + _filePokemons);

            _game = Game_Server_Ex2.getServer(i++);
            _game.login(id);

            Arena a = new Arena(_game);

            _graph = _func.graphJsonToGraph(_jsonGraph);
            _agents = _func.getAgents(_jsonAgents,_graph);
            _pokemons = _func.json2Pokemons(_game.getPokemons());
            _pokemons = _func.json2Pokemons(_jsonPokemons);
             List<Pokemon> _pokemons2 = a.getPokemons();
            a.setPokemons(_pokemons);
            assertEquals(a.getPokemons(),_pokemons);
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * we import Json files and create objects from them, that contain the information regarding the rules of the game
     * This functions tests the getGraph function for the class Arena
     * */
    @Test
    void getGraph() {
        int i = 0;
        for (String str : _array){
            _jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            _jsonGraph = _func.readJsonFromFileAndGetAsString(str + _fileGraph);
            _jsonPokemons = _func.readJsonFromFileAndGetAsString(str + _filePokemons);

            _game = Game_Server_Ex2.getServer(i++);
            _game.login(id);

            Arena a = new Arena(_game);

            _graph = _func.graphJsonToGraph(_jsonGraph);
            _agents = _func.getAgents(_jsonAgents,_graph);
            _pokemons = _func.json2Pokemons(_game.getPokemons());
            _pokemons = _func.json2Pokemons(_jsonPokemons);
            directed_weighted_graph g = a.getGraph();
            a.setGraph(_graph);
            assertEquals(a.getGraph(),_graph);
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * we import Json files and create objects from them, that contain the information regarding the rules of the game
     * This functions tests the setGraph function for the class Arena
     * */
    @Test
    void setGraph() {
        int i = 0;
        for (String str : _array){
            _jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            _jsonGraph = _func.readJsonFromFileAndGetAsString(str + _fileGraph);
            _jsonPokemons = _func.readJsonFromFileAndGetAsString(str + _filePokemons);

            _game = Game_Server_Ex2.getServer(i++);
            _game.login(id);

            Arena a = new Arena(_game);

            _graph = _func.graphJsonToGraph(_jsonGraph);
            _agents = _func.getAgents(_jsonAgents,_graph);
            _pokemons = _func.json2Pokemons(_game.getPokemons());
            _pokemons = _func.json2Pokemons(_jsonPokemons);
            directed_weighted_graph g = a.getGraph();
            a.setGraph(_graph);
            assertEquals(a.getGraph(),_graph);
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * we import Json files and create objects from them, that contain the information regarding the rules of the game
     * This functions tests the get_info function for the class Arena
     * */
    @Test
    void get_info() {
        int i = 0;
        for (String str : _array){
            _jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            _jsonGraph = _func.readJsonFromFileAndGetAsString(str + _fileGraph);
            _jsonPokemons = _func.readJsonFromFileAndGetAsString(str + _filePokemons);

            _game = Game_Server_Ex2.getServer(i++);
            _game.login(id);

            Arena a = new Arena(_game);

            _graph = _func.graphJsonToGraph(_jsonGraph);
            _agents = _func.getAgents(_jsonAgents,_graph);
            _pokemons = _func.json2Pokemons(_game.getPokemons());
            _pokemons = _func.json2Pokemons(_jsonPokemons);
            directed_weighted_graph g = a.getGraph();
            List<String> list = a.get_info();
            List<String> list2 = new ArrayList<>();
            list2.add("1");
            list2.add("2");
            list2.add("3");
            list2.add("4");
            list2.add("5");
            list2.add("6");
            a.set_info(list2);
            assertEquals(a.get_info(),list2);
            assertNotEquals(a.get_info(),list);
        }
    }
    /**
     * We are running by array, this array contains paths for all levels.
     * we import Json files and create objects from them, that contain the information regarding the rules of the game
     * This functions tests the set_info function for the class Arena
     * */
    @Test
    void set_info() {
        int i = 0;
        for (String str : _array){
            _jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            _jsonGraph = _func.readJsonFromFileAndGetAsString(str + _fileGraph);
            _jsonPokemons = _func.readJsonFromFileAndGetAsString(str + _filePokemons);

            _game = Game_Server_Ex2.getServer(i++);
            _game.login(id);

            Arena a = new Arena(_game);

            _graph = _func.graphJsonToGraph(_jsonGraph);
            _agents = _func.getAgents(_jsonAgents,_graph);
            _pokemons = _func.json2Pokemons(_game.getPokemons());
            _pokemons = _func.json2Pokemons(_jsonPokemons);
            directed_weighted_graph g = a.getGraph();
            List<String> list = a.get_info();
            List<String> list2 = new ArrayList<>();
            list2.add("1");
            list2.add("2");
            list2.add("3");
            list2.add("4");
            list2.add("5");
            list2.add("6");
            a.set_info(list2);
            assertEquals(a.get_info(),list2);
            assertNotEquals(a.get_info(),list);
        }
    }

}