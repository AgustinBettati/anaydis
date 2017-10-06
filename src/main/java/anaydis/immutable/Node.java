package anaydis.immutable;

import org.jetbrains.annotations.NotNull;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class Node<T> implements List<T>{
    static final List<Object> NIL = new List<Object>() {
        @Override
        public Object head() {
            throw new RuntimeException();
        }

        @NotNull
        @Override
        public List<Object> tail() {
            throw new RuntimeException();
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
        return null;
    }

    @NotNull
    @Override
    public List<T> tail() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @NotNull
    @Override
    public List<T> reverse() {
        return null;
    }
}
