package anaydis.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class HybridQuickSorterTest extends SorterTest{

    @Test
    public void testHybridQuickSort(){

        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_CUT, 25);
    }

}