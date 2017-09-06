package anaydis.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class ThreeWayQuickSorterTest extends SorterTest{

    @Test
    public void testThreeWayQuickSort(){

        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_THREE_PARTITION, 25);
    }

}