package anaydis.sort;

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

        list.add(new FullName("Agustin", "Bettati"));
        list.add(new FullName("Juan", "Perez"));
        list.add(new FullName("Bernardo", "Bettati"));
        list.add(new FullName("Carlos", "Rodriguez"));

        Sorter sorter = new SelectionSorter();
        sorter.sort(Comparator.naturalOrder(), list);

        list.forEach(fullName -> System.out.println(fullName));
    }
}
