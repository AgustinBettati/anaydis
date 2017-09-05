package anaydis.sort.practice.tp2;

import java.util.Comparator;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class LastNameComparator implements Comparator<FullName> {

    @Override
    public int compare(FullName o1, FullName o2) {
        return o1.getLastname().compareTo(o2.getLastname());
    }
}
