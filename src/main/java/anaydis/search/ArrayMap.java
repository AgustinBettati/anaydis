package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class ArrayMap<K,V> implements Map<K,V>{

    private final List<K> keys;
    private final List<V> values;
    private int size;
    private Comparator<K> comp;


    public ArrayMap(Comparator<K> comparator){
        keys = new ArrayList<>(10);
        values = new ArrayList<>(10);
        comp = comparator;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return indexOf(key) != -1;
    }

    private int indexOf(K key){
        for (int i = 0; i < keys.size(); i++) {
            if(comp.compare(key, keys.get(i)) == 0)
                return i;
        }

        return -1;
    }

    @Override
    public V get(@NotNull K key) {
        final int index = indexOf(key);
        if(index == -1) return null;
        return values.get(index);

    }

    @Override
    public V put(@NotNull K key, V value) {
        int index = indexOf(key);
        if(index == -1) {
            index = size;
            keys.add(index, key);
            values.add(index, value);
            size++;
            return null;
        }
        V prev = values.get(index);
        values.add(index, value);
        return prev;
    }

    @Override
    public void clear() {
        size = 0;

    }

    @Override
    public Iterator<K> keys() {
        return keys.iterator();
    }


}
