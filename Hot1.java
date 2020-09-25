
/**
 * Write a description of Hot1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Hot1 {
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for(CSVRecord currentRow : parser){
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    
    // find the hottest day over many files
    public CSVRecord hottestInManyDays() {
        // overall largest file
        CSVRecord largestSoFar = null;
        // select multiple files
        DirectoryResource dr = new DirectoryResource();
        // iterate thru all files
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(current, largestSoFar);
        }
        return largestSoFar;
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord current, CSVRecord largest){
            if(largest == null){
                largest = current;
            } else {
                double currentTemp = Double.parseDouble(current.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largest.get("TemperatureF"));
                if(currentTemp > largestTemp){
                    largest = current;
                }
            }
            return largest;
    }
    
    public void testRow() {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + 
                            largest.get("TemperatureF") + " at " 
                            + largest.get("TimesEST"));
    }
    
    public void testMultipleFiles() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature over many files was "
                            + largest.get("TemperatureF") + " at " 
                            + largest.get("DateUTC"));
    }
   

}
