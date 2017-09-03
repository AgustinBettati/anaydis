package anaydis.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class NonRecursQuickSorterTest extends SorterTest{

    @Test
    public void testNonRecursiveQuickSort(){

        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 25);
    }

}