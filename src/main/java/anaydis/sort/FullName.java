package anaydis.sort;

import org.jetbrains.annotations.NotNull;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class FullName{



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


}
