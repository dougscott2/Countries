import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
  //static Country  country;
    static final String fileName = "countries.txt";
    public static void main(String[] args) {
       //Country country = new Country();
        Scanner scanner = new Scanner(System.in);
        HashMap<String, ArrayList<Country>> countriesMap = new HashMap<>();
        String countryContent = readFile(fileName);
        String [] countryArray = countryContent.split("\n");

        for (String countryContents : countryArray){
               //for loop goes through countArray and puts data into new string countryContents one at a time
            String[] columns = countryContents.split("\\|");
               //each slot of countryArray goes into new array columns after being split at the | via countryContents
            String firstLetter = String.valueOf(countryContents.charAt(0));
               //the firstletter (charAt(0)) is taken from countryContents as well
            ArrayList<Country> countryList = countriesMap.get(firstLetter);
               //creating array list based off of each first letter and putting it in the HashMap
            String abbreviation = columns[0];
            //taking the first slot of columns (before the |) to receive the abbreviation
            String name = columns[1];
            //taking info from the second slot of columns to get the name
            Country country = new Country(abbreviation, name);
            //new country object is created with the abbreviation and name
            countriesMap.put(firstLetter, countryList);
            //hashmap slot added with the firstLetter as the key and the object from the countryList


            if (countryList == null){//inside for loop if there isn't a countryList a
                countryList = new ArrayList();   // new one will be created
                countryList.add(country);        //country object will be put into the new countryList
                countriesMap.put(firstLetter, countryList);// the countryList and first letter will be put into the hashmap
            } else{
                countryList.add(country); //otherwise the countryList already exists and a country object is given to it
            }

        }//end for
        System.out.println("Search by letter");
        String s = scanner.nextLine().toUpperCase();
        String countryInfo = String.format("%s_countries.txt", s); //uses users letter entry store *_countries.text into a string

        if(countriesMap.containsKey(s)) { //if the hashmap containsKey(s) (the user's input)
            String newLine = ""; //new empty string
            for(Country newCountry : countriesMap.get(s)) {//the user's input will be used as the key to search the hashmap and put the info ito a newCountry object

                newLine = String.format("%s %s\n", newCountry.abbreviation, newCountry.name); //the empty string is given the newCountry object's abbreviation and name

                writeFile(countryInfo, newLine);//the string with info newLine is written to the new file who's name is taken from countyInfo
            }//end of for loop
        }//end if

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
