package gameClient;

import api.*;
import gameClient.util.Point3D;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * This class tests the class of pokemon
 * */
class PokemonTest {

    private Config con = new Config();
    private FunctionForTests _func = new FunctionForTests();
    private String[] _array = _func.getArrayOfScenariosPath();
    private List<Pokemon> _pokemonsList;
    private String _fileName = con.Pokemons;
    private Pokemon _pokemon ;


    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the constructor function for the class pokemon
     * */
    @Test
    void constructor() {
        for (String str : _array){
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for(Pokemon p : _pokemonsList){
                Pokemon _pokemon = new Pokemon(p.getLocation(),p.getType(),p.getValue() , p.get_edge());
                assertEquals(_pokemon.getType() , p.getType());
                assertEquals(_pokemon.getValue() , p.getValue());
                assertEquals(_pokemon.get_edge() , p.get_edge());
                assertEquals(_pokemon.getLocation() , p.getLocation());
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the get_edge function for the class pokemon
     * */
    @Test
    void get_edge() {
        for (String str : _array){
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for(Pokemon p : _pokemonsList){
                Pokemon _pokemon = new Pokemon(p.getLocation(),p.getType(),p.getValue(), p.get_edge());
                assertEquals(_pokemon.get_edge() , p.get_edge());
                EdgeData e = new EdgeData(Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE);
                assertNotEquals(p.get_edge(), e);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the set_edge function for the class pokemon
     * */
    @Test
    void set_edge() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                edge_data _edge = new EdgeData(Integer.MAX_VALUE, Integer.MAX_VALUE,Integer.MAX_VALUE);
                p.set_edge(_edge);
                assertEquals(p.get_edge(), _edge);
                _edge = new EdgeData(Integer.MIN_VALUE, Integer.MIN_VALUE,Integer.MIN_VALUE);
                assertNotEquals(p.get_edge(), _edge);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getLocation function for the class pokemon
     * */
    @Test
    void getLocation() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                Point3D point = new Point3D(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
                p.setLocation(point);
                assertEquals(p.getLocation(), point);
                point = new Point3D(Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE);
                assertNotEquals(p.getLocation(), point);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getType function for the class pokemon
     * */
    @Test
    void getType() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setType(Integer.MAX_VALUE);
                assertEquals(p.getType(), Integer.MAX_VALUE);
                assertNotEquals(p.getType(), Integer.MIN_VALUE);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getValue function for the class pokemon
     * */
    @Test
    void getValue() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setValue(Integer.MAX_VALUE);
                assertEquals(p.getValue(), Integer.MAX_VALUE);
                assertNotEquals(p.getValue(), Integer.MIN_VALUE);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getMin_dist function for the class pokemon
     * */
    @Test
    void getMin_dist() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.set_min_dist(Integer.MAX_VALUE);
                assertEquals(p.get_min_dist(), Integer.MAX_VALUE);
                assertNotEquals(p.get_min_dist(), Integer.MIN_VALUE);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the setMin_dist function for the class pokemon
     * */
    @Test
    void setMin_dist() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                double Min_dist = p.get_min_dist();
                p.set_min_dist(Integer.MIN_VALUE);
                assertEquals(p.get_min_dist(), Integer.MIN_VALUE);
                assertNotEquals(p.get_min_dist(), Min_dist);

            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getFrom function for the class pokemon
     * */
    @Test
    void getFrom() {
     for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.set_from(Integer.MAX_VALUE);
                assertEquals(p.get_from(), Integer.MAX_VALUE);
                assertNotEquals(p.get_from(), Integer.MIN_VALUE);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the setFrom function for the class pokemon
     * */
    @Test
    void setFrom() {
            for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                int from = p.get_from();
                p.setType(Integer.MAX_VALUE);
                assertNotEquals(p.getType(), from);
                assertEquals(p.getType(), Integer.MAX_VALUE);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getTo function for the class pokemon
     * */
    @Test
    void getTo() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.set_to(Integer.MAX_VALUE);
                assertEquals(p.get_to(), Integer.MAX_VALUE);
                assertNotEquals(p.get_to(), Integer.MIN_VALUE);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the setTo function for the class pokemon
     * */
    @Test
    void setTo() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                int to = p.get_to();
                p.set_to(Integer.MAX_VALUE);
                assertEquals(p.get_to(), Integer.MAX_VALUE);
                assertNotEquals(p.get_to(), to);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the getWorth function for the class pokemon
     * */
    @Test
    void getWorth() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.set_worth(Integer.MAX_VALUE);
                assertEquals(p.get_worth(), Integer.MAX_VALUE);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the setWorth function for the class pokemon
     * */
    @Test
    void setWorth() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                double worth = p.get_worth();
                p.set_worth(Integer.MAX_VALUE);
                assertNotEquals(p.get_worth(), worth);
                assertEquals(p.get_worth(), Integer.MAX_VALUE);
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the testToString function for the class pokemon
     * */
    @Test
    void testToString() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                str = "Pokemon{" +
                        "_edge=" + p.get_edge() +
                        ", _value=" + p.getValue() +
                        ", _type=" + p.getType() +
                        ", _pos=" + p.getLocation() +
                        ", min_dist=" + p.get_min_dist() +
                        ", min_ro=" + p.get_from() +
                        '}';
                ;
                assertEquals(p.toString(), str);
                assertNotEquals(p.toString(), str+ "+");
            }
        }
    }

    /**
     * We are running by array, this array contains paths for all levels.
     * This functions tests the testEquals function for the class pokemon
     * */
    @Test
    void testEquals() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            Point3D point = new Point3D(Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE);
            _pokemon = new Pokemon(point,100,Integer.MIN_VALUE,new EdgeData(-10,-10,0.0));
            for (Pokemon p : _pokemonsList) {
                Pokemon p2 = new Pokemon(p.getLocation(),p.getType(),p.getValue(),p.get_edge());
                assertTrue(p.equals(p2));
                assertFalse(p.equals(_pokemon));
            }
        }
    }
}