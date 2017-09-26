package anaydis.search;


import org.junit.Test;

import java.util.*;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class MapTest {


    @Test
    public void testArrayMap() {
        testPutMethod(new ArrayMap<>((o1, o2) -> o1.compareTo(o2)));
    }

    @Test
    public void testTreeMap() {
        testPutMethod(new RandomizedTreeMap<>((o1, o2) -> o1.compareTo(o2)));
    }



    public void testPutMethod(anaydis.search.Map<Integer,Integer> map) {
    HashMap<Integer, Integer> hashMap = new HashMap<>();

    Random rand = new Random();

    for (int i = 0; i < 100; i++) {
        int number = rand.nextInt();
        map.put(number, i);
        hashMap.put(number, i);
    }

    for(Map.Entry<Integer, Integer> element : hashMap.entrySet()) {
        assertEquals(element.getValue(), map.get(element.getKey()));
    }

    }
}