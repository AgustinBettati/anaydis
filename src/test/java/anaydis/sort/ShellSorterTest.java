package anaydis.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class ShellSorterTest {

    @Test
    public void testShellSortWithSpecificSequence(){
        IntegerDataSetGenerator generator = new IntegerDataSetGenerator();
        List<Integer> list = generator.createRandom(50);

        ShellSorter sorter = new ShellSorter();

        final int[] sequence = {1, 8, 23, 77, 281, 1073, 4193, 16577};

        final List<Integer> copy = new ArrayList<>(list);
        sorter.sort(generator.getComparator(), list, sequence);

        copy.sort(generator.getComparator());

        assertThat(list).usingElementComparator(generator.getComparator()).containsExactlyElementsOf(copy);
    }




}