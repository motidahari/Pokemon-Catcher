package gameClient;

import api.directed_weighted_graph;
import api.edge_data;
import api.geo_location;
import api.node_data;
import gameClient.util.Point3D;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * This Class represents Agent - a pokemon catcher.
 * it has the following properties:
 * position, id, speed, current edge, current pokemon, value.
 */
public class Agent {

	private int _id;
	private geo_location _pos;
	private double _speed;
	private edge_data _curr_edge;
	private node_data _curr_node;
	private directed_weighted_graph _gg;
	private Pokemon _curr_pokemon;
	private Queue<node_data> queue;
	private double _value;

	//========================= CONSTRUCTORS ===========================

	public Agent(directed_weighted_graph g, int start_node) {
		_gg = g;
		setValue(0);
		this._curr_node = _gg.getNode(start_node);
		_pos = _curr_node.getLocation();
		_id = -1;
		setSpeed(0);
		queue = new LinkedList<>();
	}

	//=========================== GETTERS & SETTERS ================================

	public int getSrcNode() {return this._curr_node.getKey();}
	public void setCurrNode(int src) {
		this._curr_node = _gg.getNode(src);
	}

	/**
	 * @return ID of this agent
	 */
	public int getID() {
		return this._id;
	}

	/**
	 * @param id to set to this agent
	 */
	public void setID(int id) {
		this._id = id;
	}

	/**
	 * @return the next node this agent is going to
	 */
	public int getNextNode() {
		int ans = -2;
		if(this._curr_edge==null) {
			ans = -1;}
		else {
			ans = this._curr_edge.getDest();
		}
		return ans;
	}

	/**
	 * @param dest the next node this agent goes to
	 * @return if succeeded
	 */
	public boolean setNextNode(int dest) {
		boolean ans = false;
		int src = this._curr_node.getKey();
		this._curr_edge = _gg.getEdge(src, dest);
		if(_curr_edge!=null) {
			ans=true;
		}
		else {_curr_edge = null;}
		return ans;
	}

	/**
	 * @return the speen of this agent
	 */
	public double getSpeed() { return this._speed; }

	/**
	 * @param s the speed to set to this agent
	 */
	public void setSpeed(double s) { this._speed = s; }

	/**
	 * @return this agent value
	 */
	public double getValue() { return this._value; }

	/**
	 * @param v the value to set to this agent
	 */
	public void setValue(double v) { _value = v; }

	/**
	 * @return the current pokemon targeted by this agent
	 */
	public Pokemon get_curr_pokemon() { return _curr_pokemon; }

	/**
	 * @param curr_pokemon set target pokemon to this agent
	 */
	public void set_curr_pokemon(Pokemon curr_pokemon) {this._curr_pokemon = curr_pokemon; }

	/**
	 * @return this agent's location
	 */
	public geo_location getLocation() {
		return _pos;
	}

	/**
	 * @param geo set this agent's location to
	 */
	public void setLocation(geo_location geo) {
		this._pos = geo;
	}

	/**
	 * @return on what edge this agent is
	 */
	public edge_data get_curr_edge() {
		return this._curr_edge;
	}

	/**
	 * @param _curr_edge set this pokemon's place on that edge
	 */
	public void set_curr_edge(edge_data _curr_edge) {
		this._curr_edge = _curr_edge;
	}

	public Queue<node_data> getQ(){return queue;}
	public void setQ(Queue<node_data> queue){ this.queue = queue;}

	/**
	 * updates this agent from a json string
	 * @param json a string holds an agent's information
	 */
	public void update(String json) {
		JSONObject line;
		try {
			line = new JSONObject(json);
			JSONObject ttt = line.getJSONObject("Agent");
			int id = ttt.getInt("id");
			if(id==this.getID() || this.getID() == -1) {
				if(this.getID() == -1) {_id = id;}
				double speed = ttt.getDouble("speed");
				String p = ttt.getString("pos");
				Point3D pp = new Point3D(p);
				int src = ttt.getInt("src");
				int dest = ttt.getInt("dest");
				double value = ttt.getDouble("value");
				this._pos = pp;
				this.setCurrNode(src);
				this.setSpeed(speed);
				this.setNextNode(dest);
				this.setValue(value);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @return a string representation of this agent
	 */
	//@Override
	public String toJSON() {
		int d = this.getNextNode();
		String ans = "{\"Agent\":{"
				+ "\"id\":"+this._id+","
				+ "\"value\":"+this._value+","
				+ "\"src\":"+this._curr_node.getKey()+","
				+ "\"dest\":"+d+","
				+ "\"speed\":"+this.getSpeed()+","
				+ "\"pos\":\""+_pos.toString()+"\""
				+ "}"
				+ "}";
		return ans;
	}

	/**
	 * @return true if this agent is moving, false otherwise
	 */
	public boolean isMoving() {
		return this._curr_edge!=null;
	}

	//======================== toString & equals methods =========================
	/**
	 * @return a string representation of this agent
	 */
	public String toString() {
		return toJSON();
	}

	/**
	 * checks if a given agent is equals to this agent
	 * @param o the other agent to compare to
	 * @return if the two agents are equals
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Agent agent = (Agent) o;
		return _id == agent._id &&
				Double.compare(agent._speed, _speed) == 0 &&
				Double.compare(agent._value, _value) == 0 &&
				Objects.equals(_pos, agent._pos) &&
				Objects.equals(_curr_edge, agent._curr_edge) &&
				Objects.equals(_curr_node, agent._curr_node) &&
				Objects.equals(_gg, agent._gg) &&
				Objects.equals(_curr_pokemon, agent._curr_pokemon) &&
				Objects.equals(queue, agent.queue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _pos, _speed, _curr_edge, _curr_node, _gg, _curr_pokemon, queue, _value);
	}
}
