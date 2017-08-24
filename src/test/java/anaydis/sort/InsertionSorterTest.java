package anaydis.sort;

import org.junit.Test;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class InsertionSorterTest extends SorterTest{

    @Test
    public void testInsertionSort(){
        testSorter(createIntegerDataSetGenerator(), SorterType.INSERTION, 50);
    }

    @Test
    public void testInsertionSortString(){
        testSorter(createStringDataSetGenerator(), SorterType.INSERTION, 50);
    }
}
