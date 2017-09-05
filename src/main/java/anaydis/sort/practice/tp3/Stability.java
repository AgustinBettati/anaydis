package anaydis.sort.practice.tp3;

import anaydis.sort.QuickSorter;
import anaydis.sort.ShellSorter;
import anaydis.sort.Sorter;
import anaydis.sort.practice.tp2.FirstNameComparator;
import anaydis.sort.practice.tp2.FullName;
import anaydis.sort.practice.tp2.LastNameComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class Stability {

    public static void main(String[] args) {
        List<FullName> list = new ArrayList<>();

        list.add(new FullName("Jorge", "Perez"));
        list.add(new FullName("Agustin", "Bettati"));
        list.add(new FullName("Santiago", "Peralta"));
        list.add(new FullName("Andres", "Peralta"));
        list.add(new FullName("Bernardo","Bettati"));
        list.add(new FullName("Carlos","Bettati"));
        list.add(new FullName("Pablo","Bettati"));
        list.add(new FullName("Aurelio","Perez"));
        System.out.println("Initial List: ");
        list.forEach(System.out::println);

        System.out.println();

        List<FullName> copy = new ArrayList<>(list);

        Sorter quickSorter = new QuickSorter();
        quickSorter.sort(new FirstNameComparator(), copy);
        quickSorter.sort(new LastNameComparator(), copy);
        System.out.println("Quick Sort: ");
        copy.forEach(System.out::println);

        System.out.println();

        System.out.println("Shell Sort:");
        Sorter shellSorter = new ShellSorter();
        shellSorter.sort(new FirstNameComparator(), list);
        shellSorter.sort(new LastNameComparator(), list);
        list.forEach(System.out::println);
    }
}
