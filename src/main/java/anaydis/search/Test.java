package anaydis.search;

import java.util.Comparator;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class Test {

    public static void main(String[] args) {
        ArrayMap<Integer,String> map = new ArrayMap<>((o1, o2) -> o1.compareTo(o2));

        map.put(5, "Hola");
        map.put(4, "Andres");
        map.put(2, "Jorge");
        System.out.println(map.put(5, "Agustin"));
        System.out.println(map.get(5));



    }
}
