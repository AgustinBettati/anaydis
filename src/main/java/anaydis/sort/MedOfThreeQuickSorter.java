package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class MedOfThreeQuickSorter extends AbstractQuickSorter {

    private final int M = 9;
    private InsertionSorter cut;


    public MedOfThreeQuickSorter() {

        super(SorterType.QUICK_MED_OF_THREE);
        cut = new InsertionSorter();
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(0, list.size() -1,comparator, list );
    }

    private <T> void sort(int low, int high,@NotNull Comparator<T> comparator, @NotNull List<T> list){
        if(high - low + 1 <= M) cut.sort(low, high, comparator, list);
        else {
            swap((low+high)/2, high-1, list);
            if(greater(low, high - 1, list, comparator))
                swap(low, high - 1, list);

            if(greater(low, high, list, comparator))
                swap(low, high, list);

            if(greater(high - 1, high, list, comparator))
                swap(high - 1, high, list);

            int i = partition(low + 1, high - 1, comparator, list);
            sort(low, i - 1, comparator, list);
            sort(i + 1, high, comparator, list);
        }
    }

}
