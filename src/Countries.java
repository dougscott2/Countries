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
        ArrayList<Country> countriesList = new ArrayList();
        HashMap<String, ArrayList<Country>> countriesMap = new HashMap();
        String allCountries = readFile(fileName);
        String[] countryArray = allCountries.split("\n"); //  \n means new lines. will split postContent on the new lines for the array list

        for (String line : countryArray) {
            String[] columns = line.split("\\|");
            int replyID = Integer.valueOf(columns[0]);
            country.abrreviation = columns[0];
            country.name = columns[1];
            Country test = new Country(country.name, country.abrreviation);
            countriesList.add(test);


            String firstLetter = String.valueOf(country.name.charAt(0));
            ArrayList<String> list = countriesList.get(firstLetter);
            if (list == null){
                list = new ArrayList();
                list.add(line);
                countriesMap.put(firstLetter, countriesList);
            } else {
                list.add(line);
            }

        }//end for loop
        System.out.println(countriesMap);

    }//end main

    static Country loadCountries() {
        try {
            File f = new File("save.json");
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] contents = new char[fileSize];
            fr.read(contents);
            String fileContents = new String(contents);
            JsonParser parser = new JsonParser();
            System.out.println(fileContents);
            return parser.parse(fileContents, Country.class);
        } catch (Exception e) {
            System.out.println("Something went wrong...sorry!");
            return null;
        }
    }


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
