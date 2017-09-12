package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class MergeSorter extends AbstractMergeSort {

    public MergeSorter() {
        super(SorterType.MERGE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    private <T> void sort(Comparator<T> comparator, List<T> list, int low, int high) {
        if(low < high) {
            int mid = (low + high) / 2;
            sort(comparator, list, low, mid);
            sort(comparator, list, mid + 1, high);
            merge(list, comparator, low, mid, high);
        }
    }
}
