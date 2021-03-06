package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class BubbleSorter extends AbstractSorter {

    public BubbleSorter() {
        super(SorterType.BUBBLE);
    }


    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        final int n = list.size();

        for (int i = 0; i < (n-1); i++) {
            box((n - 1) - i, n - 1);
            boolean swapWasMade = false;
            for (int j = 0; j < (n - 1) - i; j++) {
                if(greater(j, j + 1, list, comparator)){
                    swap(j, j + 1, list);
                    swapWasMade = true;
                }
            }
            if(!swapWasMade) return;
        }

    }
}
