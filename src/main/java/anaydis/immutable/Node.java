package anaydis.immutable;

import org.jetbrains.annotations.NotNull;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class Node<T> implements List<T>{

    private final T head;
    private final List<T> tail;

    public Node(T head, List<T> tail){
        this.head = head;
        this.tail = tail;
    }

    static final List<Object> NIL = new List<Object>() {
        @Override
        public Object head() {
            throw new RuntimeException("NIL does not have head");
        }

        @NotNull
        @Override
        public List<Object> tail() {
            throw new RuntimeException("NIL does not have tail");
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @NotNull
        @Override
        public List<Object> reverse() {
            return this;
        }
    };

    @Override
    public T head() {
        return head;
    }

    @NotNull
    @Override
    public List<T> tail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @NotNull
    @Override
    public List<T> reverse() {
        List<T> newList = List.cons(head, List.nil());
        List<T> currentTail = tail;
        while(!currentTail.isEmpty()){
            newList = List.cons(currentTail.head(), newList);
            currentTail = currentTail.tail();
        }
        return newList;
    }
}
