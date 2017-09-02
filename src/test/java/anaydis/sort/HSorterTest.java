package anaydis.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class HSorterTest{

    @Test
    public void testWithRandomList() {
        IntegerDataSetGenerator generator = new IntegerDataSetGenerator();
        List<Integer> randomList = generator.createRandom(25);

        HSorter sorter = new HSorter();
        final int h = 7;
        sorter.sort(generator.getComparator(),randomList, h);

        for (int i = 0; i < h; i++) {
            List<Integer> subList = new ArrayList<>();
            for (int j = i; j < randomList.size(); j += h) {
                subList.add(randomList.get(j));
            }
            List<Integer> copy = new ArrayList<>(subList);
            copy.sort(generator.getComparator());
            assertThat(subList).usingElementComparator(generator.getComparator()).containsExactlyElementsOf(copy);
        }
    }


}