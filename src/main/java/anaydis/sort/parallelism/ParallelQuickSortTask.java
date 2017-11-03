package anaydis.sort.parallelism;

import anaydis.sort.HybridQuickSorter;
import anaydis.sort.Sorter;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class ParallelQuickSortTask<T> extends RecursiveAction{

    private final int M = 100;
    private final int low;
    private final int high;
    private final Comparator<T> comparator;
    private final List<T> list;
    private final HybridQuickSorter cutOff;

    public ParallelQuickSortTask(int low, int high, Comparator<T> comparator, List<T> list, HybridQuickSorter cutOff) {
        this.low = low;
        this.high = high;
        this.comparator = comparator;
        this.list = list;
        this.cutOff = cutOff;
    }


    @Override
    protected void compute() {
        if(high - low + 1 <= M) cutOff.sort(low, high, comparator, list);
        else {
            int i = partition(low, high, comparator, list);
            ForkJoinTask task1 = new ParallelQuickSortTask<T>(low, i-1, comparator, list, cutOff);
            ForkJoinTask task2 = new ParallelQuickSortTask<T>(i+1, high, comparator, list, cutOff);
            task2.fork();
            task1.invoke();
            task2.join();
        }
    }

    private <T> int partition(int low, int high, @NotNull Comparator<T> comparator, @NotNull List<T> list){
        {
            // si o si usa a high como pivot
            int i = low - 1;
            int j = high;

            while (true)
            {
                i++;
                while(!greater(i, high, list, comparator) && i < high){
                    i++;
                }

                j--;
                while(!greater(high, j, list, comparator) && j > low){
                    j--;
                }

                if(i >= j) break;
                swap(i,j, list);
            }
            swap(i, high, list);
            return i;
        }
    }

    private <T> boolean greater(int i, int j, @NotNull List<T> list, @NotNull Comparator<T> comp) {
        return comp.compare(list.get(i), list.get(j)) > 0;
    }
    private <T> void swap(int i, int j, @NotNull List<T> list) {

        T t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }
}
