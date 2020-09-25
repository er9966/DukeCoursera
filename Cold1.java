
/**
 * Write a description of Cold1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Cold1 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow : parser){
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if(coldestSoFar == null && currentTemp != -9999) {
                coldestSoFar = currentRow;
            } else if (currentTemp != -9999) {
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp < coldestTemp){
                    coldestSoFar = currentRow;
                }
            }
            
        }
        return coldestSoFar;
    }
    
    public String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        String fileName = null;
        // select multiple files
        DirectoryResource dr = new DirectoryResource();
        // iterate thru all files
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null){
                coldestSoFar = current;
            } else {
               double currentTemp = Double.parseDouble(current.get("TemperatureF"));
               double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(coldestTemp > currentTemp){
                    coldestSoFar = current;
                    fileName = f.getName();
                }
            }
        }
        return fileName;
    }
    
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for(CSVRecord currentRow : parser){
            String currentHumStr = currentRow.get("Humidity");
            if(currentHumStr.equals("N/A")){
                currentHumStr = "-9999";
            }
            double currentHum = Double.parseDouble(currentHumStr);
            if(lowestSoFar == null && currentHum !=-9999) {
                lowestSoFar = currentRow;
            } else if (currentHum != -9999){
                double lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
                if(currentHum < lowestHum){
                    lowestSoFar = currentRow;
                }
            }
            
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord lowestHum = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("lowest humidity was " 
                            + lowestHum.get("Humidity") + " at "
                            + lowestHum.get("DateUTC"));
        System.out.println("------done finding lowest hum from one file--------");
    }
    
    
   
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        // select multiple files
        DirectoryResource dr = new DirectoryResource();
        // iterate thru all files
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if (lowestSoFar == null){
                lowestSoFar = current;
            } else {
               double currentHum = Double.parseDouble(current.get("Humidity"));
               double lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
                if(lowestHum > currentHum){
                    lowestSoFar = current;
                }
            }
        }
        return lowestSoFar;
        
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumF = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " 
                + lowestHumF.get("Humidity") + " at " 
                + lowestHumF.get("DateUTC"));
        System.out.println("------done finding lowest hum from many files--------");              
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0.0;
        double count = 0.0;
        for(CSVRecord currRow : parser) {
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            if(currTemp != -9999){
                totalTemp = totalTemp + currTemp;
                count++;
            }
            
        }
        double avg = totalTemp / count;
        
        return avg;
        
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + avgTemp);
    }
    

    public double avgTempWithHighHumd(CSVParser parser, int value) {
        double totalTemp = 0.0;
        double count = 0.0;
        int timesItsGreaterThanValue = 0;
           for(CSVRecord currRow : parser) {
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            double currentHum = Double.parseDouble(currRow.get("Humidity")); 
            if(currTemp != -9999){
                totalTemp = totalTemp + currTemp;
                count++;
            }
            if(currentHum >= value){
                timesItsGreaterThanValue++;
            }
        }
        
        if(timesItsGreaterThanValue >0 ){
            return totalTemp / count;
        } else {
            return -99999.999;
        }
    }
    
    public void testavgTempWithHighHumd() {
        FileResource fr = new FileResource();
        double avgTemp = avgTempWithHighHumd(fr.getCSVParser(), 80);
        if (avgTemp == -99999.999){
            System.out.println("No temperatures with that humidity.");
        } else {
            System.out.println("Average temperature with high humidity was: " + avgTemp);
        }
    }

    
    
    
   
   
   public void testFileWithColdestTemperature() {
        String coldestFileName = fileWithColdestTemperature();
        System.out.println("coldest day was in file " + coldestFileName);
        
        FileResource fr = new FileResource(coldestFileName);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        
        System.out.println("coldest temperature on that day was " 
                            + coldest.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were: ");
        for(CSVRecord record : fr.getCSVParser()) {
            System.out.println(record.get("DateUTC") + ": " +
                                record.get("TemperatureF"));
        }
        System.out.println("------done finding coldest file name-------");
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + 
                            coldest.get("TemperatureF") + " at " 
                            + coldest.get("DateUTC"));
        System.out.println("------done finding lowest temperature from one file------");
    }

}
