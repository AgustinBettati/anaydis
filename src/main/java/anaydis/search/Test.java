package anaydis.search;

import java.util.Comparator;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class Test {

    public static void main(String[] args) {
        ArrayMap<Integer, String> map = new ArrayMap<Integer, String>((o1, o2) -> o1.compareTo(o2));

        map.put(44,"Apu");
        map.put(14,"Argrgr");
        map.put(24,"ANdo");
        map.put(33,"Jorge");
        map.put(1,"Carlos");
        map.put(4,"Peres");

        System.out.println(map.put(24, "Nuevo"));
        System.out.println(map.get(24));
    }
}
