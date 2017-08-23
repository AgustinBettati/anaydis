package anaydis.sort;

import org.jetbrains.annotations.NotNull;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class FullName implements Comparable<FullName>{

    private String firstname;
    private String lastname;

    public FullName(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }

    public String toString(){
        return lastname + ", " + firstname;
    }

    @Override
    public int compareTo(@NotNull FullName o) {
        return firstname.compareTo(o.firstname) != 0 ?
                firstname.compareTo(o.firstname) : lastname.compareTo(o.lastname) != 0 ?
                                                    lastname.compareTo(o.lastname) : 0;
    }
}
