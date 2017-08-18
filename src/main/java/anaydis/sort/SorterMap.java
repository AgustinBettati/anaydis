package anaydis.sort;

import anaydis.sort.provider.SorterProvider;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class SorterMap implements SorterProvider {

    private final Map<SorterType, Sorter> sorters;

    public SorterMap() {
        this.sorters = new EnumMap<SorterType, Sorter>(SorterType.class);
        sorters.put(SorterType.SELECTION, new SelectionSorter());
        sorters.put(SorterType.INSERTION, new InsertionSorter());
        sorters.put(SorterType.BUBBLE, new BubbleSorter());
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
