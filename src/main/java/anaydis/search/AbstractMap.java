package anaydis.search;

import anaydis.search.practice.MapListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
abstract class AbstractMap<K,V> implements Map<K,V> {

    private final List<MapListener> listeners;

    public AbstractMap() {
        this.listeners = new ArrayList<>();
    }


    public void addSorterListener(@NotNull MapListener listener) {
        listeners.add(listener);
    }


    public void removeSorterListener(@NotNull MapListener listener) {
        listeners.add(listener);
    }


    <T> boolean greater(int i, int j, @NotNull List<T> list, @NotNull Comparator<T> comp) {
        listeners.forEach(MapListener::greater);
        return comp.compare(list.get(i), list.get(j)) > 0;
    }

    <T> boolean greater(T i, T j, @NotNull Comparator<T> comp) {
        listeners.forEach(MapListener::greater);
        return comp.compare(i, j) > 0;
    }


}
