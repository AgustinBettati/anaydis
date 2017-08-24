package anaydis.tasks;

import anaydis.sort.FullName;
import anaydis.sort.InsertionSorter;
import anaydis.sort.Sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class NameSorting {

    public static void main(String[] args) {
        List<FullName> list = new ArrayList<>();

        list.add(new FullName("Jorge", "Perez"));
        list.add(new FullName("Agustin", "Bettati"));
        list.add(new FullName("Andres", "Peralta"));
        list.add(new FullName("Bernardo","Bettati"));



        System.out.println("Initial list: ");
        list.forEach(fullName -> System.out.println(fullName));
        System.out.println();

        Sorter sorter = new InsertionSorter();
        sorter.sort(Comparator.naturalOrder(), list);

        System.out.println("Sorted with " + sorter.getType());
        list.forEach(fullName -> System.out.println(fullName));
    }
}
