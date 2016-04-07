import java.util.Scanner;
import java.io.*;

public class HashFunction2 {
    
    String[] theArray;
    int[] probesArray = new int[200];
    int arraySize;
    int itemsInArray = 0;
    
    public static String[] elementsToAdd = new String[200];
    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        try {
            File file = new File("Word200D16.txt");
            Scanner scanner = new Scanner(file);
            
            while(scanner.hasNextLine()){
                for(int i = 0; i < 200; i++){
                    String line = scanner.nextLine();
                    elementsToAdd[i] = line;
                }
            }
            
        } catch(Exception er) {
            System.out.println("Error: " + er);
        }
        
        HashFunction2 halfHashTable = new HashFunction2(200, "half", "linear");
        HashFunction2 eightyFiveHashTable = new HashFunction2(200, "85", "linear");
        HashFunction2 halfFullRandom = new HashFunction2(200, "half", "random");
        HashFunction2 eightFiveHashRand = new HashFunction2(200, "85", "random");
        
        // Display every item in the array with
        // the index they are associated with
        
        halfHashTable.displayTheArray("linear"); // C Option Part A
        eightyFiveHashTable.displayTheArray("linear"); // C Option Part B
        halfFullRandom.displayTheArray("random"); // C Option Part C
        eightFiveHashRand.displayTheArray("random"); // C Option Part C
    }
    
    public int stringHashFunction(String wordToHash) {
        
        int hashKeyValue = 0;
        
        int secondChar = wordToHash.charAt(1);
        int fifChar = wordToHash.charAt(14);
        int tweChar = wordToHash.charAt(11);
        int thiChar = wordToHash.charAt(12);
        
        hashKeyValue = (secondChar + fifChar + (tweChar * thiChar)) % 128;
        return hashKeyValue;
        
    }
    
    HashFunction2(int size, String method, String type) {
        
        arraySize = size;
        
        theArray = new String[size];
        
        // Fill the array with empty Strings
        
        for (int i = 0; i < 200; i++) {
            
            theArray[i] = "                    ";
            
        }
        
        if(method.equalsIgnoreCase("half")){
            if(type.equalsIgnoreCase("linear")){
                halfFull(elementsToAdd, "linear");
            }
            else{
                halfFull(elementsToAdd, "random");
            }
        }
        else if(method.equalsIgnoreCase("85")){
            if(type.equalsIgnoreCase("linear")){
                eightyFivePercentFull(elementsToAdd, "linear");
            }
            else {
                eightyFivePercentFull(elementsToAdd, "random");
            }
        }
        else{
            if(type.equalsIgnoreCase("linear")){
                addTheArray(elementsToAdd, "linear");
            }
            else{
                addTheArray(elementsToAdd, "random");
            }
        }
        
        
    }
    
    public void insertRandom(String newWord) {
        
        String wordToHash = ensure16(newWord);
        int numberOfProbes = 0;
        int r = 1;
        
        // Calculate the hashkey for the Word
        
        int hashKey = stringHashFunction(wordToHash);
        // Add the new word to the array and set
        // the key for the word
        
        while(!theArray[hashKey].equals("                    ")){
            hashKey = generateRandom(r) + numberOfProbes;
            numberOfProbes += 1;
        }
        
        theArray[hashKey] = wordToHash;
        probesArray[hashKey] = numberOfProbes;
    }
    
    public int generateRandom(int r){
        int q = 0;
        int p = 0;
        while (p != 1){
            r = r * 5;
            r = r % (2^200);
            q = r / 4;
            p = r;
        }
        return r;
    }
    
    public void insertLinear(String newWord) {
        
        String wordToHash = ensure16(newWord);
        int numberOfProbes = 0;
        
        // Calculate the hashkey for the Word
        
        int hashKey = stringHashFunction(wordToHash);
        // Add the new word to the array and set
        // the key for the word
        
        while(!theArray[hashKey].equals("                    ")){
            if(hashKey > 199){
                hashKey = 0;
            }
            hashKey += 1;
            numberOfProbes += 1;
        }
        
        theArray[hashKey] = wordToHash;
        probesArray[hashKey] = numberOfProbes;
    }
    
    public String ensure16(String newWord){
        if (newWord.length() < 20){
            int remainder = 20 - newWord.length();
            for(int i = 0; i < remainder; i++){
                newWord += " ";
            }
        }
        else {
            newWord = newWord;
        }
        return newWord;
    }
    
    
    public void addTheArray(String[] elementsToAdd, String method) {
        
        for (int i = 0; i < elementsToAdd.length; i++) {
            
            String newWord = elementsToAdd[i];
            // Add the Word to theArray
            if(method.equalsIgnoreCase("linear")){
                insertLinear(newWord);
            } else {
                insertRandom(newWord);
            }
            
            
        }
        
    }
    
    public void halfFull(String[] elementsToAdd, String method) {
        for (int i = 0; i < 100; i++) {
            String newWord = elementsToAdd[i];
            // Add the Word to theArray
            if(method.equalsIgnoreCase("linear")){
                insertLinear(newWord);
            } else {
                insertRandom(newWord);
            }
        }
    }
    
    public void eightyFivePercentFull(String[] elementsToAdd, String method){
        for (int j = 0; j < 170; j++){
            String newWord = elementsToAdd[j];
            //Add the word to theArray
            if(method.equalsIgnoreCase("linear")){
                insertLinear(newWord);
            } else {
                insertRandom(newWord);
            }
        }
    }
    
    public void displayTheArray(String type) {
        
        for (int i = 0; i < 7; i +=3){
            System.out.println("--------------------------------------------------------------------");
            System.out.println("|         " + i + "            |         " + (i + 1) +  "           |       " + (i + 2) +  "            |");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("| " + theArray[i] + probesArray[i] + "|" + theArray[i+1] + probesArray[i + 1] + "|" + theArray[i+2]+ probesArray[i + 2] + "|");
        }
        
        for(int i = 10; i < 97; i+=3){
            System.out.println("--------------------------------------------------------------------");
            System.out.println("|        " + i + "            |         " + (i + 1) +  "          |       " + (i + 2) +  "            |");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("| " + theArray[i] + probesArray[i] + "|" + theArray[i+1] + probesArray[i + 1] + "|" + theArray[i+2]+ probesArray[i + 2] + "|");
        }
        
        
        for (int i = 100; i < 197; i+=3) {
            
            System.out.println("--------------------------------------------------------------------");
            System.out.println("|        " + i + "           |        " + (i + 1) +  "          |       " + (i + 2) +  "           |");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("| " + theArray[i] + probesArray[i] + "|" + theArray[i+1] + probesArray[i + 1] + "|" + theArray[i+2]+ probesArray[i + 2] + "|");
        }
        
        System.out.println("First 30 entries Stats:");
        displayArrayStats(probesArray, 0, 29);
        System.out.println("");
        System.out.println("Last 30 entries Stats:");
        displayArrayStats(probesArray, 169, 199);
        System.out.println("");
        
        if(type.equalsIgnoreCase("linear")){
        double linearTheoreticalNumOfProbes = calculateTheoreticalNum(30, 200, "linear");
        
        System.out.println("Theoretical expected number of probes using linear probing: " + linearTheoreticalNumOfProbes);
        
        System.out.println("This value differs from our empirical value because the theoretical value \n" +
                           "assumes the hashing function is perfect, which ours is not.\n The theoretical " +
                           "value assumes a very low value of collisions,\n where our hash function contains" +
                           " many.");
        }
        else {
            double theoreticalNumOfProbes = calculateTheoreticalNum(30, 200, "random");
            
            System.out.println("Theoretical expected number of probes using random probing: " + theoreticalNumOfProbes);
            System.out.println("This value differs from our empirical value because the theoretical value \n" +
                               "assumes the hashing function is perfect, which ours is not.\n The theoretical " +
                               "value assumes a very low value of collisions,\n where our hash function contains" +
                               " many.");
        }
    }
        
    
    public double avgNumberProbes(int[] probesArray, int startEntry, int FinalEntry){
        int sum = 0;
        int ofHowManyRecords = FinalEntry - startEntry;
        for(int d=0; d <= ofHowManyRecords; d++) {
            sum = sum + probesArray[startEntry + d];
        }
        
        double average = 1.0d * sum / ofHowManyRecords;
        return average;
        
    }
    
    public void displayArrayStats(int[] probesArray, int min, int max){
        System.out.println("Average number of Probes: " + avgNumberProbes(probesArray, min, max));
        System.out.println("Minimum number of Probes: " + minProbes(probesArray, min, max));
        System.out.println("Maximum number of Probes: " + maxProbes(probesArray, min, max));
        
    }
    
    public double calculateTheoreticalNum(double keys, double tableSize, String type){
        if(type.equalsIgnoreCase("linear")){
            double loadFactor = (keys / tableSize);
            double theoreticalNumOfProbes = ((1 - loadFactor)/2)/(1 - loadFactor);
            return theoreticalNumOfProbes;
        }
        else{
            double loadFactor = (keys / tableSize);
            double theoreticalNumOfProbes = -(1 / loadFactor)*Math.log(1 - loadFactor);
            return theoreticalNumOfProbes;
        }
    }
    
    public int minProbes(int[] array, int startEntry, int FinalEntry){
        int minValue = array[startEntry];
        int d = FinalEntry - startEntry;
        for(int i=0;i <= d; i++){
            if(array[startEntry + i] < minValue){
                minValue = array[startEntry + i];
            }
        }
        return minValue;
    }
    
    public static int maxProbes(int[] array, int startEntry, int FinalEntry){
        int maxValue = array[startEntry];
        int d = FinalEntry - startEntry;
        for(int i=0; i <= d; i++){
            if(array[startEntry + i] > maxValue){
                maxValue = array[startEntry + i];
                
            }
        }
        return maxValue;
    }
    
    
}
