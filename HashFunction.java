import java.util.*;
import java.io.*;

public class HashFunction {
    String[] array;
    int arraySize;
    int itemsInArray = 0;

    public static void main(String[] args){
        HashFunction func = new HashFunction(200); // 3 spaces
        
        try {
            BufferedReader in = new BufferedReader(new FileReader("Words200D16.txt"));

            String str;

            List<String> list = new ArrayList<String>();
            while((str = in.readLine()) != null){
                list.add(str);
            }

            String[] stringArr = list.toArray(new String[0]);
            func.hashAddress(stringArr, func.array);
        } catch (Exception er) { System.out.println(er);}

        func.display();
    }

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

    public void hashAddress(String[] stringsForArray, String[] array){

		for(int i = 0; i < stringsForArray.length; i++){
			String newElementValue = stringsForArray[i];
			char secondCharacter = newElementValue.charAt(1);
			char fifteenthCharacter = newElementValue.charAt(14);
			long twelveAndThirteen = stringToLongConverter(newElementValue);

			long product = (twelveAndThirteen) * 10;
			long address = (secondCharacter + fifteenthCharacter + twelveAndThirteen) % 128;
			System.out.println("Modulus Index = " + address + " for value " + newElementValue);

			while(array[(int)address] != "-1"){

				++address;
				System.out.println("Collision. Trying " + address + " instead");

				address %= arraySize;
			}
			array[(int)address] = newElementValue;
		}

	}
/*
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
*/
    public long stringToLongConverter(String str){
		long newLong = Long.parseLong(str.substring(11, 13));
		return newLong;
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
