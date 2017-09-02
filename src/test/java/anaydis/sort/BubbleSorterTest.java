package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.provider.SorterProvider;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class BubbleSorterTest extends SorterTest{

    @Test
    public void testBubbleSort(){
        testSorter(createIntegerDataSetGenerator(), SorterType.BUBBLE, 25);
    }
}