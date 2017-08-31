package anaydis.sort.practice.tp2;

import java.util.Comparator;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
class FirstNameComparator implements Comparator<FullName> {

    @Override
    public int compare(FullName o1, FullName o2) {
        return o1.getFirstname().compareTo(o2.getFirstname());
    }
}
