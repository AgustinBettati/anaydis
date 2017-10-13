package anaydis.compression;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class TestBits {

    public static void main(String[] args) {
        Bits test = new Bits();
        test.addBit(0);
        test.addBit(0);
        test.addBit(1);
        test.addBit(1);
        test.addBit(0);
        test.addBit(0);
        test.addBit(1);
        System.out.println(test.byteIsFull());
        test.addBit(1);
        System.out.println(test.byteIsFull());
        System.out.println(test.bitAt(0));
        System.out.println(test.bitAt(2));
        System.out.println(test.bitAt(6));

        System.out.println(test);
    }
}
