import java.util.*;
import java.io.*;

public class test {
    
    public static void main(String[] args){
         try {
            BufferedReader in = new BufferedReader(new FileReader("Words200D16.txt"));
            String str;
            List<String> list = new ArrayList<String>();
            while((str = in.readLine()) != null){
                list.add(str);
            }
            String[] stringArr = list.toArray(new String[200]);
            for(int i = 0; i < stringArr.length; i++){
            System.out.println(stringArr[i]);}
        } catch (Exception er) { System.out.println(er);}
    }
}