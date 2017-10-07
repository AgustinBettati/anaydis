package anaydis.immutable;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class BinaryTree<K,V> implements Map<K,V> {

    private final Node<K,V>  head;
    private final int size;
    private Comparator<K> comp;

    public BinaryTree(Comparator<K> comparator) {
        head = null;
        size = 0;
        comp = comparator;
    }

    private BinaryTree(Node<K, V> head, int size, Comparator<K> comp) {
        this.head = head;
        this.size = size;
        this.comp = comp;
    }

    protected class Node<Key,Val>{
        final Key key;
        final Val value;
        final Node<Key,Val> left;
        final Node<Key,Val> right;

        Node(Key key, Val value, Node<Key,Val> left, Node<Key,Val> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    @Override
    public Map<K, V> put(@NotNull K key, V value) {
        boolean keyWasPresent = containsKey(key);
        Node<K,V> newHead = put(head, key, value);

        if(keyWasPresent) return new BinaryTree<>(newHead,size,comp);
        else {
            return new BinaryTree<>(newHead, size + 1, comp);
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if(node == null) {
            return new Node<>(key, value, null,null);
        }
        else{
            final int comparison = comp.compare(key, node.key);
            Node<K,V> newNode;
            if(comparison < 0) {
                newNode = new Node<>(node.key, node.value, put(node.left, key, value), node.right);
            }
            else if(comparison > 0){
                newNode = new Node<>(node.key, node.value, node.left, put(node.right,key,value));
            }
            else {
                newNode = new Node<>(node.key, value,node.left, node.right);
            }
            return newNode;
        }
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return find(head, key) != null;
    }

    @Override
    public V get(@NotNull K key) {
        Node<K,V> node = find(head, key);
        if(node == null) return null;
        return node.value;
    }
    private Node<K,V> find(Node<K, V> node, K key) {
        if(node == null) return null;
        final int comparison = comp.compare(key, node.key);
        if(comparison == 0) return node;
        else if(comparison < 0) return find(node.left, key);
        else return find(node.right, key);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<K> keys() {
        java.util.List<K> list = new ArrayList<>();
        getTreeInOrder(head, list);
        return list.iterator();
    }

    private void getTreeInOrder(Node<K,V> node, java.util.List<K> list) {
        if (node != null) {
            getTreeInOrder(node.left, list);
            list.add(node.key);
            getTreeInOrder(node.right, list);
        }
    }
}
