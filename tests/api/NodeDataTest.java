package api;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
/**
 * This class tests the class of NodeData
 * */
class NodeDataTest {
    node_data[] arrayNodes;

    /**
     * We are running 100 times, and creating a new Node(vertex) each run
     * This functions tests the constructor function for the class NodeData
     * */
    @Test
    void constructor() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
        }
        assertTrue(arrayNodes.length == 100 && arrayNodes[99] != null);
    }

    /**
     * We are running 100 times, and creating a new Node(vertex) each run
     * This functions tests the getKey function for the class NodeData
     * */
    @Test
    void getKey() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            assertTrue(arrayNodes[i].getKey() == i);
        }
    }
    /**
     * We are running 100 times, and creating a new Node(vertex) each run
     * This functions tests the getLocation function for the class NodeData
     * */
    @Test
    void getLocation() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            geo_location g = new GeoLocation(i,i+1,i+2);
            arrayNodes[i].setLocation(g);
            assertTrue(arrayNodes[i].getLocation() == g);
        }
    }
    /**
     * We are running 100 times, and creating a new Node(vertex) each run
     * This functions tests the setLocation function for the class NodeData
     * */
    @Test
    void setLocation() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            geo_location g = arrayNodes[i].getLocation();
            geo_location g2 = new GeoLocation(i,i+1,i+2);
            arrayNodes[i].setLocation(g2);
            assertEquals(arrayNodes[i].getLocation(), g2);
            assertNotEquals(arrayNodes[i].getLocation(),g);
        }
    }
    /**
     * We are running 100 times, and creating a new Node(vertex) each run
     * This functions tests the getWeight function for the class NodeData
     * */
    @Test
    void getWeight() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            double weight = Double.MAX_VALUE;
            double getW = arrayNodes[i].getWeight();
            arrayNodes[i].setWeight(weight);

            assertTrue(arrayNodes[i].getWeight() == Double.MAX_VALUE);
            assertTrue(arrayNodes[i].getWeight() != getW);
        }
    }
    /**
     * We are running 100 times, and creating a new Node(vertex) each run
     * This functions tests the setWeight function for the class NodeData
     * */
    @Test
    void setWeight() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            double getW = arrayNodes[i].getWeight();
            double weight = Double.MAX_VALUE;
            arrayNodes[i].setWeight(weight);
            assertTrue(arrayNodes[i].getWeight() == Double.MAX_VALUE);
            assertTrue(arrayNodes[i].getWeight() != getW);
        }
    }
    /**
     * We are running 100 times, and creating a new Node(vertex) each run
     * This functions tests the getInfo function for the class NodeData
     * */
    @Test
    void getInfo() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            String str = "Boaz the egg and the brain fuck";
            arrayNodes[i].setInfo(str);
            assertFalse(arrayNodes[i].getInfo().equals(""));
            assertTrue(arrayNodes[i].getInfo().equals(str));

        }
    }
    /**
     * We are running 100 times, and creating a new Node(vertex) each run
     * This functions tests the setInfo function for the class NodeData
     * */
    @Test
    void setInfo() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            String str = "Boaz the egg and the brain fuck";
            arrayNodes[i].setInfo(str);
            assertFalse(arrayNodes[i].getInfo().equals(""));
            assertTrue(arrayNodes[i].getInfo().equals(str));
        }
    }
    /**
     * We are running 100 times, and creating a new Node(vertex) each run
     * This functions tests the getTag function for the class NodeData
     * */
    @Test
    void getTag() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            int num = Integer.MAX_VALUE;
            arrayNodes[i].setTag(num);
            assertFalse(arrayNodes[i].getTag() == 0);
            assertTrue(arrayNodes[i].getTag() == num );
        }
    }
    /**
     * We are running 100 times, and creating a new Node(vertex) each run
     * This functions tests the setTag function for the class NodeData
     * */
    @Test
    void setTag() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            int num = Integer.MAX_VALUE;
            arrayNodes[i].setTag(num);
            assertFalse(arrayNodes[i].getTag() == 0);
            assertTrue(arrayNodes[i].getTag() == num );
        }
    }
    /**
     * We are running 100 times, and creating a new Node(vertex) each run
     * This functions tests the testToString function for the class NodeData
     * */
    @Test
    void testToString() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            assertTrue(arrayNodes[i].toString().equals("#"+i));
            assertFalse(arrayNodes[i].toString().equals("#"+(i+1)));
        }
    }
}