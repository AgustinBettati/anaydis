package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class BubbleSorter extends AbstractSorter{

    public BubbleSorter() {
        super(SorterType.BUBBLE);
    }


    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {


    }
}
