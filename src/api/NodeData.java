package api;

import gameClient.util.Point3D;

import java.io.Serializable;

public class NodeData implements node_data, Serializable {

    private int key;
    private int tag;
    private String info;
    private geo_location location;
    private double weight;

    /**
     * constructor - create new node
     * */
    public NodeData() {
        key = -1;
        tag = 0;
        info = "";
        location = new Point3D(0,0,0);
        weight = 0;
    }
    /**
     * constructor - create new node
     * @param key - the key of the node
     * */
    public NodeData(int key) {
        this.key = key;
        tag = 0;
        info = "";
        location = new Point3D(0,0,0);
        weight = 0;
    }
    /**
     * constructor - create new node
     * @param key - the key of the node
     * @param tag - the tag of the node
     * @param info - the info of the node
     * @param location - the location of the node
     * @param weight - the weight of the node
     * */
    public NodeData(int key, int tag, String info, geo_location location, double weight) {
        this.key = key;
        this.tag = tag;
        this.info = info;
        this.location = location;
        this.weight = weight;
    }
    /**
     * constructor - create new node
     * @param key - the key of the node
     * @param location - the location of the node
     * */
    public NodeData(int key, geo_location location) {
        this.key = key;
        this.tag = 0;
        this.info = "";
        this.location = location;
        this.weight = 0;
    }
    /**
     * constructor - create new node
     * @param o - the object data of the node
     * */
    public NodeData(node_data o) {
        this(o.getKey(), o.getTag(), o.getInfo(), o.getLocation(), o.getWeight());
    }

    /**
     * Returns the key (id) associated with this node.
     * @return key - the Location of the node
     */
    @Override
    public int getKey() {
        return key;
    }

    /**
     * Returns the location of this node, if
     * @return location - the location of the node
     */
    @Override
    public geo_location getLocation() {
        return location;
    }

    /**
     * Allows changing this node's location.
     * @param p - new location (position) of this node.
     */
    @Override
    public void setLocation(geo_location p) {
        location = p;
    }

    /**
     * Returns the weight associated with this node.
     * @return weight - the Weight of the node
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * Allows changing this node's weight.
     * @param w - the new weight
     */
    @Override
    public void setWeight(double w) {
        weight = w;
    }

    /**
     * Returns the remark (meta data) associated with this node.
     * @return info - of the node.
     */
    @Override
    public String getInfo() {
        return info;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     * @param s - the info of the node
     */
    @Override
    public void setInfo(String s) {
        info = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     * @return tag - the tag of the node
     */
    @Override
    public int getTag() {
        return tag;
    }

    /**
     * Allows setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        tag = t;
    }

    /**
     * return String of the data by the node
     * @return s - string of the node.
     */
    @Override
    public String toString() {
        return "#"+key;
    }
}
