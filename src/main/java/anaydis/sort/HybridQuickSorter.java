package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class HybridQuickSorter extends AbstractQuickSorter {

    private final int M;

    public HybridQuickSorter() {
        this(9);
    }

    public HybridQuickSorter(int cutOffValue) {
        super(SorterType.QUICK_CUT);
        this.M = cutOffValue;
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(0, list.size() -1,comparator, list );
    }

    private <T> void sort(int low, int high,@NotNull Comparator<T> comparator, @NotNull List<T> list){
        if(high - low + 1 <= M) insertion(low, high, comparator, list);
        else {
            int i = partition(low, high, comparator, list);
            sort(low, i - 1, comparator, list);
            sort(i + 1, high, comparator, list);
        }
    }

    private <T> void insertion(int low,int high,@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        final int n = list.size();

        for (int i = low + 1; i <= high; i++) {
            box(0,i);
            int j = i;
            while(j > low && greater(j-1,j,list,comparator) ){
                swap(j-1,j,list);
                j--;
            }
        }
    }
}
