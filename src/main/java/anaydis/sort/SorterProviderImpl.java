package anaydis.sort;

import anaydis.sort.provider.SorterProvider;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class SorterProviderImpl implements SorterProvider {

    private final Map<SorterType, Sorter> sorters;

    public SorterProviderImpl() {
        this.sorters = new EnumMap<>(SorterType.class);
        sorters.put(SorterType.SELECTION, new SelectionSorter());
        sorters.put(SorterType.INSERTION, new InsertionSorter());
        sorters.put(SorterType.BUBBLE, new BubbleSorter());
        sorters.put(SorterType.H, new HSorter());
        sorters.put(SorterType.SHELL, new ShellSorter());
        sorters.put(SorterType.QUICK, new QuickSorter());
    }

    @NotNull
    @Override
    public Iterable<Sorter> getAllSorters() {
        return sorters.values();
    }

    @NotNull
    @Override
    public Sorter getSorterForType(@NotNull SorterType type) {
        if( !sorters.containsKey(type) ){
            throw new IllegalArgumentException("Sorter type: " + type + " was not found!");
        }
        return sorters.get(type);
    }
}
