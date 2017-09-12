package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class MergeSorterTest{


    @Test
    public void testMerging(){

        DataSetGenerator<Integer> generator = new IntegerDataSetGenerator();

        List<Integer> left = generator.createRandom(15);
        Collections.sort(left);
        List<Integer> right = generator.createRandom(15);
        Collections.sort(right);

        List<Integer> list = new ArrayList<>(left);
        left.addAll(15, right);

        List<Integer> copy = new ArrayList<>(list);
        copy.sort(generator.getComparator());
        assertThat(list).usingElementComparator(generator.getComparator()).containsExactlyElementsOf(copy);

    }



}