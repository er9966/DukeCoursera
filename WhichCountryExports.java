
/**
 * Write a description of ParseData1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ParseData1 {
    public void tester (){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // String result = countryInfo(parser,"Germany");
        // System.out.println(result);
        // listExportersTwoProducts(parser, "gold","diamonds");
        //int howMany = numberOfExporters(parser, "gold");
        //System.out.println("There are "+ howMany + " countries export gold.");
        //int result = stringToNumber("$999,999,999");
        //System.out.println("result number is " + result);
        bigExporters(parser, "$999,999,999");
        System.out.println("---------------da end------------");
    }
    
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser) {
           String found = record.get("Country");
           if (found.contains(country)) {
               String exports = record.get("Exports");
               String dollars = record.get("Value (dollars)");
               return country + ": " + exports + ": " + dollars;
           }
        }
        return "Country Not Found Brooo";
    }
    
    public void listExportersTwoProducts(CSVParser parser, 
                 String exportItem1, String exportItem2) {
                     
         for (CSVRecord record : parser) {
             // get all exports
             String export = record.get("Exports");
             boolean containItem1 = false;
             boolean containItem2 = false;
             // does it contain item 1?
             if(export.contains(exportItem1)){
                containItem1 = true; 
             }
             // contain item 2?
              if(export.contains(exportItem2)){
                containItem2 = true; 
             }
             // print if both are true
             if(containItem1 == true && containItem2 == true){
                 String country = record.get("Country");
                 System.out.println(country);
             }
         }
    }
    
    public int numberOfExporters(CSVParser parser, 
                                  String exportItem){
        int result = 0;
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){
                result++;
            }
        }
        return result;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        int amountLen = amount.length();
        for (CSVRecord record : parser) {
            String moneyStr = record.get("Value (dollars)");
            int moneyLen = moneyStr.length();
            if(moneyLen > amountLen) {
                String country = record.get("Country");
                System.out.println(country + " " + moneyStr);
            }
        }
    }
    
    /*
    public int stringToNumber(String str){
        String resultStr = "";
        for(int i = 1; i < str.length();i++){
            if(str.charAt(i)!=',') {
              resultStr = resultStr + str.charAt(i);  
            }
        }
        int result = Integer.parseInt(resultStr);
        return result;
    }
    */

}
