package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class ShellSorter extends AbstractSorter{
    private final HSorter hSorter;

    public ShellSorter() {
        super(SorterType.SHELL);
        hSorter = new HSorter();
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        int h = 1;
        while (h <= (list.size())/9)
            h = 3 * h + 1;

        for ( ; h > 0; h /= 3)
            hSorter.sort(comparator, list, h);

    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int[] sequence) {
        int i = sequence.length - 1;
        while (sequence[i] >= list.size())
            i--;

        for (; i >= 0; i--)
            hSorter.sort(comparator, list, sequence[i]);

    }
}
