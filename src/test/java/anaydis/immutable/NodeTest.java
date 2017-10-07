package anaydis.immutable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class NodeTest {

    @Test
    public void reverseTest() {
        List<Integer> list = List.cons(1, List.cons(2, List.cons(3, List.cons(4, List.nil()))));

        List<Integer> reverse = list.reverse();

        java.util.List<Integer> elementsOfReverse = new ArrayList<>();

        elementsOfReverse.add(reverse.head());
        List<Integer> currentTail = reverse.tail();
        while(!currentTail.isEmpty()){
            elementsOfReverse.add(currentTail.head());
            currentTail = currentTail.tail();
        }
        assertThat(elementsOfReverse).usingElementComparator(Integer::compareTo).
                containsExactlyElementsOf(Arrays.asList(4,3,2,1));
    }

    @Test
    public void nillEmptyTest() {
        List nill = List.nil();
        assert(nill.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void nillHeadTest() {
        List nill = List.nil();
        nill.head();
    }

    @Test(expected = RuntimeException.class)
    public void nillTailTest() {
        List nill = List.nil();
        nill.tail();
    }


}