package anaydis.search;


import java.util.*;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class TreeMapTest {


    @org.junit.Test
    public void testPutMethod() {
        RandomizedTreeMap<Integer, Integer> map = new RandomizedTreeMap<>((o1, o2) -> o1.compareTo(o2));
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            int number = rand.nextInt();
            map.put(number, i);
        }

        for(Map.Entry<Integer, Integer> element : hashMap.entrySet()) {
            assertEquals(element.getValue(), map.get(element.getKey()));
        }

    }
}