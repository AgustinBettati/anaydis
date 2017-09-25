package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class RandomizedTreeMap<K,V> implements Map<K,V> {

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return false;
    }

    @Override
    public V get(@NotNull K key) {
        return null;
    }

    @Override
    public V put(@NotNull K key, V value) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<K> keys() {
        return null;
    }
}
