
public class Part3 {
    public void main(){
        String dna1 = "ATGTAAGATGCCCTAGT"; 
        int count = countAllGenes(dna1);
        System.out.println(count);
        
    }
    
    public int countAllGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while(true) {
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty()) {
                break;
            }
            count++;
            startIndex = dna.indexOf(currentGene, startIndex)
                         + currentGene.length();
        }
        return count;
    }
    
    public String findGene(String dna, int startIndex){
        String resultStr = "";
        //find start codon
        startIndex = dna.indexOf("ATG", startIndex);
        
        if (startIndex == -1) {
            return "No Start Index Found";
        }
        
        //find end codon
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        //find whichever one appears first;
        int minIndex = Math.min(taaIndex, 
                                Math.min(tagIndex,tgaIndex));
        // if dna.length() happend to be the smallest:((
        if(minIndex == dna.length()){
            return "no end index found";
        }
        
        //cut out the desired substring
        resultStr = dna.substring(startIndex, minIndex + 3);
        return resultStr;
    }
    
    public int findStopCodon(String dnaStr, int startIndex, 
                             String endStr) {
        int currIndex = dnaStr.indexOf(endStr);
        while(currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){
                return currIndex;
            }
            currIndex = dnaStr.indexOf(endStr, currIndex + 1);
        }
        return dnaStr.length(); //if end index was not found
    }
    
    
}
