package api;
/**
 * This interface represents a geo location <x,y,z>, aka Point3D
 */
public interface geo_location {
    /**
     * return the value of x represent the location
     * @return x - aka Point3D
     * */
    public double x();
    /**
     * return the value of y represent the location
     * @return y - aka Point3D
     * */
    public double y();
    /**
     * return the value of z represent the location
     * @return z - aka Point3D
     * */
    public double z();
    /**
     * return the distance of between other geo_location
     * @param g - object data of geo location
     * @return d - double of the distance.
     * */
    public double distance(geo_location g);
}
