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


    public String getName() {  //getters for JSON
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
