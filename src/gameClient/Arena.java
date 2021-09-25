package gameClient;

import api.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.util.Range2Range;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * This class represents a multi Agents Arena which move on a graph
 * grabs Pokemons and earn points.
 * This is where all the game is happening:
 * 		the communication with the server,
 * 		the agent and pokemons updating,
 * 		the algorithms performs to manipulate the agents.
 * 		etc.
 */
public class Arena {

	public static final double EPS1 = 0.001, EPS2=EPS1*EPS1;
	private game_service _game;
	private directed_weighted_graph _graph;
	private List<Agent> _agents = new ArrayList<>();
	private List<Pokemon> _pokemons;
	private List<String> _info;
	private dw_graph_algorithms _algo;
	public static HashMap<Integer, ArrayList<node_data>> paths = new HashMap<>();
	HashMap<Integer, Pokemon> map = new HashMap<>();

	//========================= CONSTRUCTORS ===========================

	/**
	 * Construct an arena from a given game_service's information
	 * @param game the game that holds information of the graph, pokemons etc.
	 */
	public Arena(game_service game) {
		if(game != null){
			_info = new ArrayList<>();
			_game = game;
			_graph = graphJsonToGraph(game.getGraph());
			_algo = new DWGraph_Algo();
			_algo.init(_graph);
			_pokemons = json2Pokemons(game.getPokemons());
			initAgents();

			exportJsonToFile("GameJSON", game.toString());
			exportJsonToFile("GameGraph", game.getGraph());
			exportJsonToFile("GameAgents", game.getAgents());
			exportJsonToFile("GamePokemons", game.getPokemons());
		}
	}

	//========================== JSON CONVERTING ==============================

	/**
	 * Takes information about the agents from the game service,
	 * builds each agent, and initialize their places on the edges that
	 * have the most valuable pokemons on the graph.
	 * updating the server where we choose to place them.
	 */
	public void initAgents(){
		JSONObject line;
		try {
			line = new JSONObject(_game.toString());
			JSONObject ttt = line.getJSONObject("GameServer");
			int numberOfAgents = ttt.getInt("agents");
			ArrayList<Pokemon> pokemons = json2Pokemons(_game.getPokemons());
			pokemons.sort((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()));

            for (Pokemon p : pokemons) {
                Arena.updateEdge(p, _graph);
            }
			for(int i = 0; i < numberOfAgents ; i++) {
				Pokemon c = pokemons.get(i);
				int t;
				edge_data e = c.get_edge();
				if(c.getType() == -1){
					t = Math.max(e.getSrc(), e.getDest());
				}
				else{
					t = Math.min(e.getSrc(), e.getDest());
				}
				_game.addAgent(t);
			}
		}
		catch (JSONException e) {e.printStackTrace();}
	}

	/**
	 * Gets a json string contains information about the agents and
	 * creates all the agents from it.
	 * @param json the json string
	 * @return a list of agents
	 */
	public List<Agent> getAgents(String json) {
		ArrayList<Agent> ans = new ArrayList<>();
		try {
			JSONObject ttt = new JSONObject(json);
			JSONArray ags = ttt.getJSONArray("Agents");
			for(int i=0; i<ags.length(); i++) {
				Agent c = new Agent(_graph ,0);
				c.update(ags.get(i).toString());
				ans.add(c);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ans;
	}

	/**
	 * Gets a json string contains information about the pokemons and
	 * creates all the pokemons from it.
	 * @param json the json string
	 * @return a list of pokemons
	 */
	private ArrayList<Pokemon> json2Pokemons(String json) {
		ArrayList<Pokemon> ans = new ArrayList<>();
		try {
			JSONObject ttt = new JSONObject(json);
			JSONArray ags = ttt.getJSONArray("Pokemons");
			for(int i=0; i<ags.length(); i++) {
				JSONObject pp = ags.getJSONObject(i);
				JSONObject pk = pp.getJSONObject("Pokemon");
				int t = pk.getInt("type");
				double v = pk.getDouble("value");
				String p = pk.getString("pos");
				Pokemon f = new Pokemon(new Point3D(p), t, v, null);
				updateEdge(f, _graph);
				ans.add(f);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		ans.sort(Comparator.comparingDouble(Pokemon::getValue));
		return ans;
	}

	/**
	 * Gets a json string contains information about the graph and
	 * creates all the graph's nodes and edges from it.
	 * @param json the json string
	 * @return a directed_weighted_graph object
	 */
	private directed_weighted_graph graphJsonToGraph(String json){
		dw_graph_algorithms ga = new DWGraph_Algo();
		exportJsonToFile("graph", json);
		ga.load("files/graph.json");
		return ga.getGraph();
	}

	/**
	 * Converts json string to reformatted json file
	 * @param path path were to export the json file
	 * @param json the json string to be converted
	 */
	private void exportJsonToFile(String path, String json) {
		try {
			String j = (new JSONObject(json)).toString(4);
			File file = new File("files/" + path + ".json");
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(j);
			myWriter.close();
		}catch ( IOException | JSONException e) {
			e.printStackTrace();
		}
	}

	//================================ EDGES ====================================

	/**
	 * Gets pokemon p holds 3d point as location, finds on what edge this location is
	 * and updates the pokemon's edge to the found one.
	 * @param p the pokemon
	 * @param g the graph
	 */
	public static void updateEdge(Pokemon p, directed_weighted_graph g) {
		for (node_data v : g.getV())
			for (edge_data e : g.getE(v.getKey()))
				if (isOnEdge(p.getLocation(), e, p.getType(), g))
					p.set_edge(e);
	}

	/**
	 * Gets a pokemon ,location source and location destination.
     * returns true iff the pokemon location is between the given destinations.
	 * @param p the pokemon
	 * @param src the source location
	 * @param dest the destination location
     * @return true iff the pokemon location is between the given locations.
     */
	private static boolean isOnEdge(geo_location p, geo_location src, geo_location dest) {
		double dist = src.distance(dest);
		double d1 = src.distance(p) + p.distance(dest);
        return (dist > d1-EPS2);
    }
    /**
     * Gets a pokemon ,source node key and destination node key.
     * returns true iff the pokemon location is between the given nodes locations.
     * @param p the pokemon
     * @param s the key of the source node
     * @param d the key of the destination node
     * @return true iff the pokemon location is between the given nodes locations.
     */
	private static boolean isOnEdge(geo_location p, int s, int d, directed_weighted_graph g) {
		geo_location src = g.getNode(s).getLocation();
		geo_location dest = g.getNode(d).getLocation();
		return isOnEdge(p,src,dest);
	}
    /**
     * Gets a pokemon and an edge.
     * returns true iff the pokemon location is on the given edge.
     * @param p the pokemon
     * @param e the edge
     * @param type the type
     * @return true iff the pokemon location is on the given edge.
     */
	private static boolean isOnEdge(geo_location p, edge_data e, int type, directed_weighted_graph g) {
		int src = g.getNode(e.getSrc()).getKey();
		int dest = g.getNode(e.getDest()).getKey();
		if(type<0 && dest>src) {return false;}
		if(type>0 && src>dest) {return false;}
		return isOnEdge(p,src, dest, g);
	}

	//============================= GRAPH RANGE =================================

	private static Range2D GraphRange(directed_weighted_graph g) {
		Iterator<node_data> itr = g.getV().iterator();
		double x0=0,x1=0,y0=0,y1=0;
		boolean first = true;
		while(itr.hasNext()) {
			geo_location p = itr.next().getLocation();
			if(first) {
				x0=p.x(); x1=x0;
				y0=p.y(); y1=y0;
				first = false;
			}
			else {
				if(p.x()<x0) {x0=p.x();}
				if(p.x()>x1) {x1=p.x();}
				if(p.y()<y0) {y0=p.y();}
				if(p.y()>y1) {y1=p.y();}
			}
		}
		Range xr = new Range(x0,x1);
		Range yr = new Range(y0,y1);
		return new Range2D(xr,yr);
	}
	public static Range2Range w2f(directed_weighted_graph g, Range2D frame) {
		Range2D world = GraphRange(g);
		return new Range2Range(world, frame);
	}

	//=========================== GETTERS & SETTERS ================================

	public List<Agent> JsonToAgents() {return _agents; }
	public directed_weighted_graph getGraph() {return _graph;}
	public void setGraph(directed_weighted_graph g) {this._graph =g;}
	public List<Pokemon> getPokemons() {return _pokemons;}
	public void setPokemons(List<Pokemon> f) {this._pokemons = f;}
	public List<String> get_info() { return _info;}
	public void set_info(List<String> info) { this._info = info;}

    /**
     * Updates the agents and move each one to its chosen node.
     * the choosing process is in the method nextNode() below.
     */
	public void moveAgents() {
		_agents = getAgents(_game.move());

		this.setPokemons(json2Pokemons(_game.getPokemons()));

		for(Agent ag : _agents) {
			int src = ag.getSrcNode();
			if(!ag.isMoving()) {
				int dest = nextNode(ag, src);
				System.out.println("Agent " + ag.getID() + " next node: "+ dest);
				_game.chooseNextEdge(ag.getID(), dest);
				ag.setNextNode(dest);
			}
		}
	}

    /**
     * Gets an agent and assign it to a new destination.
	 * 	1. import an updated list of the pokemons
	 * 	2. find the total worth of each pokemon - see findWorthOfAllPokemons() method.
	 *	3. for this current agent choose the pokemon that has the maximum worth.
	 *	4. if we didnt find any appropriate pokemon to assign to this agent:
	 *		send this agent to his area on the graph.
	 *	5. find the shortest path to the chosen pokemon as a list.
	 *	6. add the node in the edge that the pokemon is on, that is not in the path.
	 *	7. return the second node in the path (the first is where the agent is currently on).
	 *
     * @param ag the agent that needs next node.
     * @param src where this agent is coming from.
     * @return the next node to move.
     */
	private int nextNode(Agent ag, int src) {
		int id = ag.getID();

		_pokemons = json2Pokemons(_game.getPokemons());
		findWorthOfAllPokemons(ag);

		Pokemon chosen = null;
		double max = 0;
		for(Pokemon p : _pokemons){
			if(available(p, ag)){
				double w = p.get_worth();
				if (w > max) {
					max = w;
					chosen = p;
				}
			}
		}
		map.put(id, chosen);
		if(chosen == null){
			int base = _graph.nodeSize()/ _agents.size()/2*(id+1);
			List<node_data> path = _algo.shortestPath(src, base);
			path.add(_graph.getNode(base+1));
			paths.put(id, new ArrayList<>(path));
			return paths.get(id).get(1).getKey();
		}
		List<node_data> path = _algo.shortestPath(src, chosen.get_from());
		path.add(_graph.getNode(chosen.get_to()));

		paths.put(id, new ArrayList<>(path));
		return paths.get(id).get(1).getKey();
	}

	/**
	 * Finds the total worth of all the pokemon regards to a given agent.
	 * The total worth of a pokemon regard to an agent is based on:
	 * 		1. the minimum distance between the agent and the pokemon.
	 * 		2. the pokemon's value.
	 *
	 * 1. iterate the pokemons and find the maximum and the minimum value.
	 * 	  and the maximum and the minimum min_dist.
	 * 2. create range of values 0-100 based of the minimum and the maximum we found.
	 * 3. create range of min_dist 0-100 based of the minimum and the maximum we found.
	 * 4. set each pokemon's worth based on their min_dist and values.
	 * @param ag the agent
	 */
	private void findWorthOfAllPokemons(Agent ag) {
		double min_val = 100, min_dist = 100, max_val = -100, max_dist = -100;
		for(Pokemon p : _pokemons){
			int type = p.getType();
			edge_data e = p.get_edge();
			if(type == -1){
				p.set_from(Math.max(e.getSrc(), e.getDest()));
				p.set_to(Math.min(e.getSrc(), e.getDest()));
			}
			else{
				p.set_from(Math.min(e.getSrc(), e.getDest()));
				p.set_to(Math.max(e.getSrc(), e.getDest()));
			}
			double dis2pokemon = totalDistanceToPokemon(ag, p);
			p.set_min_dist(dis2pokemon);

			if(dis2pokemon > max_dist) max_dist = dis2pokemon;
			if(dis2pokemon < min_dist) min_dist = dis2pokemon;
			if(p.getValue() > max_val) max_val = p.getValue();
			if(p.getValue() < min_val) min_val = p.getValue();
		}

		Range valuesRange = new Range(min_val, max_val);
		Range distRange = new Range(min_dist, max_dist);

		for(Pokemon p : _pokemons){
			double val = valuesRange.getPortion(p.getValue()) * 100;
			double dist = 100 - (distRange.getPortion(p.get_min_dist()) * 100);
			p.set_worth(1*val + 1*dist);
		}
	}

	/**
	 * @param p a pokemon
	 * @param a an agent
	 * @return true if a given pokemon is "available" for a given agent.
	 * A pokemon is consider "available" to an agent if their is no other agent assign to this
	 * pokemon, or not around the pokemon.
	 */
	private boolean available(Pokemon p, Agent a) {
		int eps = 4;
		for(Agent ag : _agents){
			if(a.getID()!=ag.getID() && paths.containsKey(ag.getID())) {
				for(node_data n : paths.get(ag.getID())){
					if(nodeToPokemonDistance(n, p) < totalDistanceToPokemon(a, p)-eps)
						return false;
				}
			}
		}
		return true;
	}

	/**
	 * @param ag an agent
	 * @param p a pokemon
	 * @return the total distance from a given pokemon to a given agent.
	 */
	private double totalDistanceToPokemon(Agent ag, Pokemon p) {
		double extra1 = ag.getLocation().distance(_graph.getNode(ag.getSrcNode()).getLocation());
		double extra2 = p.getLocation().distance(_graph.getNode(p.get_from()).getLocation());
		return _algo.shortestPathDist(ag.getSrcNode(), p.get_from()) + extra2 - extra1;
	}
	/**
	 * @param n some node
	 * @param p a pokemon
	 * @return the total distance from a given node to a given agent.
	 */
	private double nodeToPokemonDistance(node_data n, Pokemon p) {
		double extra = p.getLocation().distance(_graph.getNode(p.get_from()).getLocation());
		return _algo.shortestPathDist(n.getKey(), p.get_from()) + extra;
	}


}
