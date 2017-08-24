package anaydis.sort;

import org.junit.Test;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class SelectionSorterTest extends SorterTest {

    @Test
    public void testSelectionSort(){

        testSorter(createIntegerDataSetGenerator(), SorterType.SELECTION, 50);
    }
}
