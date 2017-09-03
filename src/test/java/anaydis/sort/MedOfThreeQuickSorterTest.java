package anaydis.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class MedOfThreeQuickSorterTest extends SorterTest {

    @Test
    public void testMedOfThreeQuickSort(){

        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_MED_OF_THREE, 25);
    }


}