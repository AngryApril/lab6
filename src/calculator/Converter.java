package calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexey.valiev on 4/8/19.
 */
public class Converter {

    public List<Integer> convertToList(String input) {

        List<Integer> arrayList = new ArrayList<>();
        String[] numbers = input.split(" ");
        int tmpNum;

        for (int i = 0; i < numbers.length; i++) {

            try {
                tmpNum = Integer.parseInt(numbers[i]);
                arrayList.add(tmpNum);
                if(tmpNum < 1 || tmpNum > 9){
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("'" + numbers[i] +"'" + " NOT A VALID NUMBER");
                return Collections.emptyList();
            }
        }

        return arrayList;
    }

    public List<Integer> convertArrayToList(int[] array) {

        List<Integer> arrayList = new ArrayList<>();

        for(int i = 0; i < array.length; i++){
            arrayList.add(array[i]);
        }

        return arrayList;
    }


}
