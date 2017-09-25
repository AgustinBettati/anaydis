package anaydis.sort.practice;

import anaydis.sort.InsertionSorter;
import anaydis.sort.Sorter;
import anaydis.sort.practice.tp2.FirstNameComparator;
import anaydis.sort.practice.tp2.FullName;
import anaydis.sort.practice.tp2.LastNameComparator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class FullNameSortTest {

    @Test
    public void testSortOfFullNameWithInsertion() {
        List<FullName> list = new ArrayList<>();

        list.add(new FullName("Jorge", "Perez"));
        list.add(new FullName("Agustin", "Bettati"));
        list.add(new FullName("Andres", "Peralta"));
        list.add(new FullName("Bernardo","Bettati"));

        Sorter sorter = new InsertionSorter();
        sorter.sort(new FirstNameComparator(), list);
        sorter.sort(new LastNameComparator(), list);

        assertThat(list.get(0)).isEqualToComparingFieldByField(new FullName("Agustin","Bettati"));
        assertThat(list.get(1)).isEqualToComparingFieldByField(new FullName("Bernardo","Bettati"));
        assertThat(list.get(2)).isEqualToComparingFieldByField(new FullName("Andres","Peralta"));
        assertThat(list.get(3)).isEqualToComparingFieldByField(new FullName("Jorge", "Perez"));
    }
}
