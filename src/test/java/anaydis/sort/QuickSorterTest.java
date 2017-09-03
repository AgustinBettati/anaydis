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
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(8);
        list.add(6);
        list.add(5);
        list.add(7);
        list.add(4);

        Sorter sorter = new QuickSorter();
        sorter.sort(Comparator.naturalOrder(), list);

        list.forEach(integer -> System.out.println(integer));

        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK, 200);

    }

}