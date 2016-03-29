import java.util.Arrays;

public class HashFunction {
    String[] array;
    int arraySize;
    int itemsInArray = 0;
    
    public static void main(String[] args){
        HashFunction func = new HashFunction(30); // 30 spaces
        String[] elementsToAdd = {"1", "5"};
        
        func.hashFunction(elementsToAdd, func.Array);
        func.display();
    }
    
    public void hashFunction(String[] stringsForArray, String[] Array){
        for (int n = 0; n < stringsForArray.length; n++){
            String newElementVal = stringsForArray[n];
            array[Integer.parseInt(newElementVal)] = newElementVal;
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

				if (theArray[n].equals("-1"))
					System.out.print("|      ");

				else
					System.out
							.print(String.format("| %3s " + " ", theArray[n]));

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

		}
    }

    
}
