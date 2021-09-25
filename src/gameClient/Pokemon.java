package gameClient;
import api.edge_data;
import gameClient.util.Point3D;

import java.util.Objects;

/**
 * This class represents a pokemon.
 * Every pokemon has the following properties:
 * edge (on which the pokemon on),
 * value,
 * type (if the type is -1 so the pokemon lies in a slope),
 * position (3d point),
 * minimum distance (every agent set this to mark the distances to all pokemons),
 * from (the node on this pokemon's edge that "behind" the pokemon),
 * to (the node on this pokemon's edge that "in front" of the pokemon),
 * worth (based on the minimum distance + value)
 */
public class Pokemon {

	private edge_data _edge;
	private double _value;
	private int _type;
	private Point3D _pos;
	private double _min_dist;
	private int _from;
	private int _to;
	private double _worth;

	//============================ CONSTRUCTOR ===============================
	public Pokemon(Point3D p, int t, double v, edge_data e) {
		_type = t;
		_value = v;
		_edge = e;
		_pos = p;
		_min_dist = -1;
		_from = -1;
	}

	//=========================== GETTERS & SETTERS ===========================

	/**
	 * @return this Edge that this pokemon is on
	 */
	public edge_data get_edge() {return _edge;}

	/**
	 * set this pokemon's edge to a given one
	 * @param _edge the Edge that this pokemon is on
	 */
	public void set_edge(edge_data _edge) {this._edge = _edge;}

	/**
	 * @return this pokemon's location (3d point)
	 */
	public Point3D getLocation() { return _pos;}

	/**
	 * set this pokemon's location to a given one
	 * @param pos the given 3d point to set this pokemon's location
	 */
	public void setLocation(Point3D pos) { this._pos = pos;}

	/**
	 * @return this pokemon's type (if the type is -1 so the pokemon lies in a slope)
	 */
	public int getType() {return _type;}

	/**
	 * set this pokemon's type
	 * @param type the given type
	 */
	public void setType(int type) {this._type = type;}

	/**
	 * @return this pokemon's value
	 */
	public double getValue() {return _value;}

	/**
	 * set this pokemon's value to a given one
	 * @param _value the given value
	 */
	public void setValue(int _value) {this._value = _value; }

	/**
	 * @return the minimum distance from the agent that runs this method
	 */
	public double get_min_dist() {return _min_dist;}

	/**
	 * set the minimum distance from the agent that runs this method
	 * @param mid_dist the minimum distance
	 */
	public void set_min_dist(double mid_dist) {this._min_dist = mid_dist;}

	/**
	 * @return get this pokemon's edge source
	 */
	public int get_from() {return _from;}

	/**
	 * set this pokemon's edge source
	 * @param from the key of the node
	 */
	public void set_from(int from) {this._from = from;}

	/**
	 * @return get this pokemon's edge destination
	 */
	public int get_to() {return _to;}
	/**
	 * set this pokemon's edge source
	 * @param to the key of the node
	 */
	public void set_to(int to) {this._to = to;}

	/*
	 * @return the worth of this pokemon (based on the minimum distance + value)
	 */
	public double get_worth() {return _worth; }

	/**
	 * set this pokemon worth
	 * @param _worth the worth
	 */
	public void set_worth(double _worth) {this._worth = _worth; }

	//======================== toString & equals methods =========================
	/**
	 * @return a string representation of this pokemon
	 */
	@Override
	public String toString() {
		return "Pokemon{" +
				"_edge=" + _edge +
				", _value=" + _value +
				", _type=" + _type +
				", _pos=" + _pos +
				", min_dist=" + _min_dist +
				", min_ro=" + _from +
				'}';
	}

	/**
	 * checks if a given pokemon is equals to this pokemon
	 * @param o the other pokemon to compare to
	 * @return if the two pokemons are equals
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pokemon pokemon = (Pokemon) o;
		return _type == pokemon._type && Objects.equals(_pos, pokemon._pos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(_type, _pos);
	}
}
