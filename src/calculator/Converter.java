package calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.valiev on 4/8/19.
 */
public class Converter {

    public List<Integer> convertToList(String input) {

        List<Integer> array = new ArrayList<>();
        String[] numbers = input.split(" ");

        for (int i = 0; i < numbers.length; i++) {

            try {
                array.add(Integer.parseInt(numbers[i]));
            } catch (NumberFormatException nfe) {
                System.out.println("'" + numbers[i] +"'" + " NOT A VALID NUMBER");
            }
        }

        return array;
    }

    /*public int[] convertToArray(ArrayList<Integer> arrayList) {

        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {

            try {
                array[i] = arrayList.get(i);
            } catch (NumberFormatException nfe) {
                System.out.println(arrayList.get(i) + " NOT A VALID NUMBER");
            }
        }

        return array;
    }*/
}
