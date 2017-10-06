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


    @Parameterized.Parameter public anaydis.search.Map<String,Integer> map;

    @Test
    public void testPutMethod() {
        HashMap<String, Integer> hashMap = new HashMap<>();

        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            String key = rand.nextInt() + "";
            map.put(key, i);
            hashMap.put(key, i);
        }

        for(Map.Entry<String, Integer> element : hashMap.entrySet()) {
            assertEquals(element.getValue(), map.get(element.getKey()));
        }
    }

    @Parameterized.Parameters(name = "Map {0}")
    public static List<Object[]> maps() {
        final anaydis.search.Map<String,Integer> arrayMap = new ArrayMap<String, Integer>((o1, o2) -> o1.compareTo(o2));
        final anaydis.search.Map<String,Integer> treeMap = new RandomizedTreeMap<String, Integer>((o1, o2) -> o1.compareTo(o2));
        final anaydis.search.Map<String,Integer> rWayTrieMap = new RWayTrieMap<Integer>();
        final List<Object[]> maps = new ArrayList<>();
        maps.add(new Object[]{arrayMap});
        maps.add(new Object[]{treeMap});
        maps.add(new Object[]{rWayTrieMap});
        return maps;
    }
}