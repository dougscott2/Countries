/**
 * Created by DrScott on 10/15/15.
 */
public class Country extends Countries{
    String name;
    String abbreviation;

    public Country(String name, String abrreviation) {
        this.name = name;
        this.abbreviation = abrreviation;
    }

    public void test(String input) {

    }

    public String getName() {  //getters for JSON
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
