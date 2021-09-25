package api;

import gameClient.util.Point3D;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


/**
 * This class tests the class of GeoLocation
 * */

class GeoLocationTest {
    private Point3D p;

    /**
     * We are running 100 times, and creating a new Point3D each run
     * This functions tests the constructor function for the class GeoLocation
     * */
    @Test
    void constructor() {
        for (int i = 0; i < 100; i++) {
            p = new Point3D(i,i+1,i+2);
            geo_location g = new GeoLocation(i,i+1,i+2);
            String check = "GeoLocation{p="+ ((double)i) +"," + ((double)i+1) +"," + ((double)i+2) +"}";
             assertEquals(check,g.toString());
        }

    }

    /**
     * We are running 100 times, and creating a new Point3D each run
     * This functions tests the distance between 2 points on a 3D platform
     * */
    @Test
    void distance() {
        for (int i = 0; i < 100; i++) {
            p = new Point3D(i,i+1,i+2);
            geo_location g = new GeoLocation(i,i+1,i+2);
            double check = Math.sqrt(Math.pow(p.y() - g.y(),2) + Math.pow(p.x() - g.x(),2) + Math.pow(p.z() - g.z(),2));
            assertTrue(g.distance(p) == check);
        }
    }
}