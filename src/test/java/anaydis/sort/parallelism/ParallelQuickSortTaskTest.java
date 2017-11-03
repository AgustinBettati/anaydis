package anaydis.sort.parallelism;

import anaydis.sort.HybridQuickSorter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RunnableFuture;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class ParallelQuickSortTaskTest {

    @Test
    public void parallelSortTest() {
        List<Integer> list = new ArrayList<>();
        Random rand = new Random();

        int n = 100000;

        for (int i = 0; i < n; i++) {
            list.add(rand.nextInt(100001));
        }

        final int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);
        ForkJoinPool pool = new ForkJoinPool(processors);
        ForkJoinTask task = new ParallelQuickSortTask<Integer>(0, n-1,Comparator.naturalOrder(), list,  new HybridQuickSorter());
        pool.invoke(task);



    }

}