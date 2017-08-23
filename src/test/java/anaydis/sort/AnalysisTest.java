package anaydis.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class AnalysisTest {


    public static void main(String[] args){

        IntegerDataSetGenerator generator = new IntegerDataSetGenerator();

        ArrayList<List<Integer>> lists = new ArrayList<>();
        lists.add(generator.createAscending(10000));
        lists.add(generator.createDescending(10000));
        lists.add(generator.createRandom(10000));

        AbstractSorter[] sorters = {new SelectionSorter(), new InsertionSorter(), new BubbleSorter()};

        for (List<Integer> listToSort : lists) {
            for (int j = 0; j < sorters.length; j++){
                AbstractSorter sorter = sorters[j];
                obtainValueOfSorting(sorter, new ArrayList<Integer>(listToSort), generator.getComparator());
            }
            System.out.println("----------------------");
        }


    }

    private static void obtainValueOfSorting(AbstractSorter sorter, List<Integer> listToSort, Comparator<Integer> comparator) {
        CounterListener listener = new CounterListener();
        sorter.addSorterListener(listener);

        long startTime = System.nanoTime();
        sorter.sort(comparator, listToSort);
        long totalTime = (System.nanoTime() - startTime) / 1000000; //miliseconds

        System.out.println("Sorting algorithm -> " + sorter.getType());
        System.out.println("time: " + totalTime);
        System.out.println("Amount of swaps: "+ listener.getAmtOfSwaps());
        System.out.println("Amount of comparisons: "+ listener.getAmtOfComparisons());
    }

    private static void obtainValuesOfSortingWithRandom(int n, AbstractSorter sorter){
        CounterListener listener = new CounterListener();
        sorter.addSorterListener(listener);
        IntegerDataSetGenerator generator = new IntegerDataSetGenerator();

        long startTime = System.nanoTime();
        sorter.sort(generator.getComparator() ,generator.createRandom(n));
        long totalTime = (System.nanoTime() - startTime) / 1000000; //miliseconds

        System.out.print("Sorting algorithm -> " + sorter.getType());
        System.out.println("    n = " + n);
        System.out.println("time: " + totalTime);
        System.out.println("Amount of swaps: "+ listener.getAmtOfSwaps());
        System.out.println("Amount of comparisons: "+ listener.getAmtOfComparisons());
    }
}
