package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class HybridQuickSorter extends AbstractQuickSorter {

    private final int M;
    private InsertionSorter cut;

    public HybridQuickSorter() {
        this(9);
    }

    public HybridQuickSorter(int cutOffValue) {
        super(SorterType.QUICK_CUT);
        this.M = cutOffValue;
        cut = new InsertionSorter();
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(0, list.size() -1,comparator, list );
    }

    public <T> void sort(int low, int high,@NotNull Comparator<T> comparator, @NotNull List<T> list){
        if(high - low + 1 <= M) cut.sort(low, high, comparator, list);
        else {
            int i = partition(low, high, comparator, list);
            sort(low, i - 1, comparator, list);
            sort(i + 1, high, comparator, list);
        }
    }

    @Override
    public void addSorterListener(@NotNull SorterListener listener) {
        super.addSorterListener(listener);
        cut.addSorterListener(listener);
    }

    @Override
    public void removeSorterListener(@NotNull SorterListener listener) {
        super.removeSorterListener(listener);
        cut.addSorterListener(listener);
    }
}
