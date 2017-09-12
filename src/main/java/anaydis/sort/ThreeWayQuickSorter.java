package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class ThreeWayQuickSorter extends AbstractQuickSorter {

    public ThreeWayQuickSorter() {
        super(SorterType.QUICK_THREE_PARTITION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(0, list.size() -1, comparator, list);
    }

    private <T> void sort(int low, int high,@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        if (high <= low) return;

        int i = low - 1;
        int j = high;
        int p = low - 1;
        int q = high;
        int k;

        while(true) {
            while (greater(high, ++i, list,comparator)) ;
            while (greater(--j, high, list,comparator))
                if (j == low) break;
            if (i >= j) break;
            swap(i, j, list);
            if (equals(i, high,list, comparator)) { p++; swap(p,i,list); }
            if (equals(high,j,list, comparator)) { q--; swap(q,j,list); }
        }
        swap(i, high, list); j = i-1; i = i+1;
        for(k=low ;k<=p;k++,j--)
            swap(k,j,list);
        for (k = high-1; k >= q; k--,i++)
            swap(k,i, list);

        sort(low, j, comparator, list);
        sort(i, high, comparator, list);

    }
}
