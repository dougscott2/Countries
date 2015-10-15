import jodd.json.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
  static Country  country;
    static final String fileName = "countries.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, ArrayList<Country>> countriesMap = new HashMap();
        String allCountries = readFile(fileName);
        String[] countryArray = allCountries.split("\n"); //  \n means new lines. will split postContent on the new lines for the array list


        for (String line : countryArray) {

            String[] columns = line.split("\\|");
            String abbreviation = columns[1];
            String name = columns[0];
            Country country = new Country(name, abbreviation);
            if (countriesMap.containsKey(name.substring(0, 1))) {
                countriesMap.get(name.substring(0, 1)).add(country);
            } else
            {
                ArrayList<Country> countryList = new ArrayList();
                countryList.add(country);
                countriesMap.put(name.valueOf(name.charAt(0)), countryList);
            }
        }

        String countryInfo = "";
        String s;
        while (true) {

            System.out.println("Enter a letter");
            s = scanner.nextLine();

            if (countriesMap.containsKey(s)) {
                for (Country country : countriesMap.get(s)) {
                    countryInfo = String.format("%s %s\n ", country.abrreviation, country.name);
                }
            }
            writeFile(String.format("%s_countries.txt", s), countryInfo);
            break;
        }



    }//end main

    static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            return null;
        }
    }

    static void writeFile(String fileName, String fileContent){
        File f = new File(fileName);
        try{
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (Exception e){

        }
    }
}
