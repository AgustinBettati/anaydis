package anaydis.immutable;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class BankersQueueTest {

    @Test
    public void testDequeue() {
        List<Integer> in = List.cons(1, List.cons(2, List.cons(3, List.nil())));
        List<Integer> out = List.cons(6, List.cons(5, List.cons(4, List.nil())));

        Queue<Integer> queue = new BankersQueue<>(in, out);

        java.util.List<Integer> dequeueResults = new ArrayList<Integer>();
        while (!queue.isEmpty()){
            Queue.Result<Integer> result = queue.dequeue();
            dequeueResults.add(result.value);
            queue = result.queue;
        }

        assertThat(dequeueResults).usingElementComparator(Integer::compareTo).
                containsExactlyElementsOf(Arrays.asList(6,5,4,3,2,1));

    }

}