package anaydis.sort.practice.tp2;

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

    String getFirstname() {
        return firstname;
    }
    String getLastname() {
        return lastname;
    }

    public String toString(){
        return lastname + ", " + firstname;
    }


}
