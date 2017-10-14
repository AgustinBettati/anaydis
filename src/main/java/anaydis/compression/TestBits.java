package anaydis.compression;


import java.util.HashMap;
import java.util.Map;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class TestBits {

    public static void main(String[] args) {
        System.out.println(-1+(2*128));
        Bits bits = new Bits(5, 3);
        Map<Bits, Integer> map = new HashMap<>();

        map.put(bits, 100);

        System.out.println(map.containsKey(new Bits(5,3)));
        System.out.println(map.containsKey(new Bits(4,3)));

        Bits test = new Bits();
        test.addBit(1);
        test.addBit(0);
        test.addBit(0);
        test.addBit(1);
        test.addBit(1);
        test.addBit(1);
        test.addBit(1);
        test.addBit(1);
        System.out.println(test.bitAt(4));

        System.out.println(test);
    }
}
