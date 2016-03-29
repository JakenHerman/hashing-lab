import java.util.Arrays;

public class HashFunction {
    String[] array;
    int arraySize;
    int itemsInArray = 0;
    
    public static void main(String[] args){
        HashFunction func = new HashFunction(30); // 3 spaces
  
        String[] elementsToAdd = {"1", "5", "51"};
        String[] elementsToAdd2 = {"1", "5", "1", "51"};
      //  func.hashFunction(elementsToAdd, func.array);
        func.modHash(elementsToAdd2, func.array);
        
        func.findKey("51");
        func.findKey("1");

        func.display();
    }
    
   /* public void hashFunction(String[] stringsForArray, String[] Array){
        for (int n = 0; n < stringsForArray.length; n++){
            String newElementVal = stringsForArray[n];
            array[Integer.parseInt(newElementVal)] = newElementVal;
        }
    } */
    
    public String findKey(String key){
        
        int arrayIndexHash = Integer.parseInt(key) % 29;
        
        while(array[arrayIndexHash] != "-1"){
            if (array[arrayIndexHash] == key){
                System.out.println(key + " was found at index " + arrayIndexHash);
                return array[arrayIndexHash];
            }
            
            ++arrayIndexHash;
            arrayIndexHash %= arraySize;
        }
        return null;
    }
    
    public void modHash(String[] stringsForArray, String[] array){
        for(int i = 0; i < stringsForArray.length; i++){
            String newElementVal = stringsForArray[i];
            int arrayIndex = Integer.parseInt(newElementVal) % 29;
            
            System.out.println("Modulus Index = " + arrayIndex + " for value " + newElementVal);
            
            while(array[arrayIndex] != "-1"){
                
                ++arrayIndex;
                System.out.println("Collision. Trying " + arrayIndex + " instead");
                
                arrayIndex %= arraySize;
            }
            array[arrayIndex] = newElementVal;
        }
    }
    HashFunction(int size){
        arraySize = size;
        array = new String[size];
        Arrays.fill(array, "-1");
    }
    public void display() {

		int increment = 0;

		for (int m = 0; m < 3; m++) {

			increment += 10;

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				System.out.format("| %3s " + " ", n);

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				if (array[n].equals("-1"))
					System.out.print("|      ");

				else
					System.out
							.print(String.format("| %3s " + " ", array[n]));

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

		}
    }

    
}
