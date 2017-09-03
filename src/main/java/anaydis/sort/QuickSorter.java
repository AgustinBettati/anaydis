package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class QuickSorter extends AbstractQuickSorter {

    public QuickSorter(){
        super(SorterType.QUICK);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(0, list.size() -1,comparator, list );
    }

    private <T> void sort(int low, int high,@NotNull Comparator<T> comparator, @NotNull List<T> list){
        if(high <= low) return;
        int i = partition(low, high,comparator, list);
        sort(low, i-1, comparator, list);
        sort(i+1, high, comparator, list);
    }

}
