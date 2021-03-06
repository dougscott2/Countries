import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
  static Country  country;
    static final String FILE_NAME = "countries.txt";
    public static JsonSerializer serializer = new JsonSerializer();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, ArrayList<Country>> countriesMap = new HashMap<>();
        String countryContent = readFile(FILE_NAME);
        String[] countryArray = countryContent.split("\n");
        System.out.println("Enter a letter or type exit to stop");
        String input = scanner.nextLine();

         if (input.length() == 1) {
           countryMethod(input.toUpperCase(), countryArray, countriesMap);
         } else if (input.equalsIgnoreCase("exit")){
             System.out.println("Check those files");
             System.exit(0);
         }
         else {
           System.out.println("Invalid entry, please try again");
         }
        System.out.println(countriesMap.get("A"));

 }//end main
    public static void countryMethod(String s, String[] array, HashMap<String, ArrayList<Country>> map) {
        for (String countryContents : array) {
            //for loop goes through countArray and puts data into new string countryContents one at a time

            String[] columns = countryContents.split("\\|");
            //each slot of countryArray goes into new array columns after being split at the | via countryContents

            String firstLetter = String.valueOf(countryContents.charAt(0));
            //the firstletter (charAt(0)) is taken from countryContents as well

            ArrayList<Country> countryList = map.get(firstLetter);
            //creating array list based off of each first letter and putting it in the HashMap

            String abbreviation = columns[0];
            //taking the first slot of columns (before the |) to receive the abbreviation

            String name = columns[1];
            //taking info from the second slot of columns to get the name

            Country country = new Country(name, abbreviation);
            //new country object is created with the abbreviation and name

            map.put(firstLetter, countryList);
            //hashmap slot added with the firstLetter as the key and the object from the countryList


            if (countryList == null) {//inside for loop if there isn't a countryList a
                countryList = new ArrayList();   // new one will be created
                countryList.add(country);        //country object will be put into the new countryList
                map.put(firstLetter, countryList);// the countryList and first letter will be put into the hashmap
            } else {
                countryList.add(country); //otherwise the countryList already exists and a country object is given to it
            }

        }

        String fileName = String.format("%s_countries.txt", s);
        if (map.containsKey(s)) {
            //String newLine = "";
            String contentToSave = "";
            for (Country tempCountry : map.get(s)) {
                //newLine = newLine + String.format("%s %s\n", newCountry.abbreviation, newCountry.name); //the empty string is given the newCountry object's abbreviation and name
                contentToSave += serializer.serialize(tempCountry)+"\n";
                writeFile(fileName, contentToSave+"\n");
            }
        }
    }//end countryMethod

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
            System.out.println("Something went wrong with writeFile()");
        }
    }
}
