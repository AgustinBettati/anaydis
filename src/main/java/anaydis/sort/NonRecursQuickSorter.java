package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class NonRecursQuickSorter extends AbstractQuickSorter {

    public NonRecursQuickSorter() {
        super(SorterType.QUICK_NON_RECURSIVE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(list.size() - 1);

        int r;
        int l;

        while (!stack.empty()) {
            r = stack.pop();
            l = stack.pop();
            if (r <= l) {
                continue;
            }
            int i = partition(l, r, comparator, list);
            if (i - l > r - i) {
                stack.push(l);
                stack.push(i - 1);
            }
            stack.push(i + 1);
            stack.push(r);

            if(r - i >= i - l){
                stack.push(l);
                stack.push(i - 1);
            }

        }

    }




}
