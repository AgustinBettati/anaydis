package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class ArrayMap<K extends Comparable<K>,V> implements Map<K,V>{

    private final List<K> keys;
    private final List<V> values;
    private int size;


    public ArrayMap(int maxSize) {
        this.size = size;
        keys = new ArrayList<>(size);
        values = new ArrayList<>(size);
    }

    public ArrayMap(){
        this(10);
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
            if(keys.get(i).equals(key))
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
