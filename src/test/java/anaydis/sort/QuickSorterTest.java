package anaydis.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class QuickSorterTest extends SorterTest{

    @Test
    public void testQuickSort(){
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK, 25);
    }

}