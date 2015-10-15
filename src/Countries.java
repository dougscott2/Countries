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
       //Country country = new Country();
        Scanner scanner = new Scanner(System.in);
        HashMap<String, ArrayList<Country>> countriesMap = new HashMap<>();
        String countryContent = readFile(fileName);
        String [] lines = countryContent.split("\n");

        for (String line : lines){

            String [] columns = line.split("\\|");
            String firstLetter = String.valueOf(line.charAt(0));
            ArrayList<Country> countryList = countriesMap.get(firstLetter);
            String abrreviation = columns[0];
            String name = columns[1];
            Country country = new Country(abrreviation, name);
            countriesMap.put(firstLetter, countryList);


            if (countryList == null){
                countryList = new ArrayList();
                countryList.add(country);
                countriesMap.put(firstLetter, countryList);
            } else{
                countryList.add(country);
            }

        }
        System.out.println("enter a letter");
        String s = scanner.nextLine().toUpperCase();
        String xFile = String.format("%s_countries.txt", s);

        if(countriesMap.containsKey(s)) {
            String newLine = "";
            for(Country newCountry : countriesMap.get(s)) {

                newLine += String.format("%s %s\n", newCountry.abrreviation, newCountry.name);

                writeFile(xFile, newLine);
            }
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
