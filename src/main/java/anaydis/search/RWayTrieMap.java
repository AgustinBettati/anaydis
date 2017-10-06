package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class RWayTrieMap<V> implements Map<String,V> {

    private Node<V> head;
    private int size;
    private List<String> keys;

    private class Node<T>{
        T value;
        List<Node<T>> next;
        
        Node(T value){
            this();
            this.value = value;
        }

        Node(){
            next = new ArrayList<>();
            for (int i = 0; i < 256; i++) {
                next.add(null);
            }
        }
    }

    public RWayTrieMap(){
        head = null;
        size = 0;
        keys = new ArrayList<>();
    }

    @Override
    public boolean containsKey(@NotNull String key) {
        return find(head, key, 0) != null;
    }

    @Override
    public V get(@NotNull String key) {
        Node<V> node = find(head, key, 0);
        if(node == null) return null;
        return node.value;
    }

    private Node<V> find(Node<V> node, String key, int level) {
        if (node == null) return null;
        if(level == key.length()) return node;
        int indexOfNext = (int) key.charAt(level);
        return find(node.next.get(indexOfNext), key, level+1);

    }


    @Override
    public V put(@NotNull String key, V value) {
        //TODO manejar lo de keys
        V prev = get(key);
        if(prev == null) keys.add(key);
        head = put(head, key, value, 0);
        return prev;
    }

    private Node<V> put(Node<V> node, String key, V value, int level) {
        if(node == null){
            if(level < key.length()){
                Node<V> result = new Node<>();
                int indexOfNext = (int) key.charAt(level);
                result.next.set(indexOfNext, put(result.next.get(indexOfNext), key, value, level+1) );
                return result;
            }
            else{
                Node<V> result = new Node<>(value);
                size++;
                return result;
            }


        }
        else if(level == key.length()){
            node.value = value;
            return node;
        }
        else{
            int indexOfNext = (int) key.charAt(level);
            node.next.set(indexOfNext, put(node.next.get(indexOfNext), key, value, level+1) );
            return node;

        }
    }


    @Override
    public void clear() {
        size = 0;
        head = null;
        keys.clear();
    }

    @Override
    public Iterator<String> keys() {
        return keys.iterator();
    }


    @Override
    public int size() {
        return size;
    }
}
