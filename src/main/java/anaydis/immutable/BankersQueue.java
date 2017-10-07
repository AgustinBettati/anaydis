package anaydis.immutable;

import org.jetbrains.annotations.NotNull;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class BankersQueue<T> implements Queue<T> {
    private final List<T> in;
    private final List<T> out;

    public BankersQueue(@NotNull List<T> in,@NotNull List<T> out) {
        this.in = in;
        this.out = out;
    }

    @NotNull
    @Override
    public Queue<T> enqueue(@NotNull T value) {
        List<T> newIn = List.cons(value, in);
        return new BankersQueue<>(newIn, out);
    }

    @NotNull
    @Override
    public Result<T> dequeue() {
        List<T> newOut;
        T value;
        if(!out.isEmpty()){
            value = out.head();
            newOut = out.tail();
            return new Result<>(value, new BankersQueue<>(in, newOut));
        }
        else if(out.isEmpty() && !in.isEmpty()){
            List<T> aux = in.reverse();
            value = aux.head();
            newOut = aux.tail();
            return new Result<T>(value, new BankersQueue<>(List.nil(),newOut));
        }
        else{
            throw new RuntimeException("Queue is Empty");
        }

    }

    @Override
    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }
}
