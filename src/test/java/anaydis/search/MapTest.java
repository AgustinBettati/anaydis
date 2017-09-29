package anaydis.search;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
@RunWith(Parameterized.class)
public class MapTest {


    @Parameterized.Parameter public anaydis.search.Map<Integer,Integer> map;

    @Test
    public void testPutMethod() {
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

    @Parameterized.Parameters(name = "Map {0}")
    public static List<Object[]> maps() {
        final anaydis.search.Map<Integer,Integer> arrayMap = new ArrayMap<Integer, Integer>((o1, o2) -> o1.compareTo(o2));
        final anaydis.search.Map<Integer,Integer> treeMap = new RandomizedTreeMap<Integer, Integer>((o1, o2) -> o1.compareTo(o2));
        final List<Object[]> maps = new ArrayList<>();
        maps.add(new Object[]{arrayMap});
        maps.add(new Object[]{treeMap});
        return maps;
    }
}