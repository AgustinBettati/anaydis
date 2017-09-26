package anaydis.search;


import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class TreeMapTest {


    @org.junit.Test
    public void testPutMethod() {
        RandomizedTreeMap<Integer, String> map = new RandomizedTreeMap<>((o1, o2) -> o1.compareTo(o2));

        for (int i = 0; i < 100; i++) {
            map.put(i, "" + i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals("" + i, map.get(i));
        }
    }
}