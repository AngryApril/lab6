package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by alexey.valiev on 4/4/19.
 */
public class Calculator {

    public static final int endValue = 24;

    public ArrayList<Integer> calculate(List<List<Integer>> arrayList, List<List<Operator>> operatorList) {
        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<String> inputs = new ArrayList<>();
        int size = arrayList.size()*operatorList.size();
        double tmpValue;

        //Create all possible combinations of given numbers and operators
        for(int i = 0; i < arrayList.size(); i++){
            for(int k = 0; k < operatorList.size(); k++) {
                String[] strings = new String[size];

                for(int j = 0, n = 0; j < arrayList.get(i).size(); j++) {
                    if(j>0){
                        strings[k]+=arrayList.get(i).get(j);
                        strings[k]+=" ";
                        strings[k]+= operatorList.get(k).get(n);
                        if(j < arrayList.get(i).size() - 1){
                            strings[k]+=" ";
                        }
                        n++;
                    }
                    else{
                        strings[k] = "";
                        strings[k]+=arrayList.get(i).get(j);
                        strings[k]+=" ";
                    }
                }
                //Print all strings
                //System.out.println(strings[k]);
                inputs.add(strings[k]);
            }
        }

        for (int i = 0; i < inputs.size(); i++){
            tmpValue = calc(inputs.get(i));
            //System.out.println(inputs.get(i));
            results.add((int) tmpValue);

        }

        return results;


    }

    //check if result equals 24

    public boolean canBeEqualTo24(int[] arr){

        ArrayList<Operator> operators;
        List<Integer> arrayList;
        List<List<Operator>> operatorCombinations = new ArrayList<>();
        List<List<Operator>> operatorPermutations = new ArrayList<>();
        List<List<Integer>> arrayPermutations = new ArrayList<>();
        List<Integer> results = new ArrayList<>();

        Operator.setOperatorList();
        operators = Operator.getOperatorList();

        Converter converter = new Converter();
        arrayList = converter.convertArrayToList(arr);
        if (arrayList.size()<1) return false;

        //get all combinations with repeating values, e.g. '+ + +','+ / *','- / -'
        Combinator combinator = new Combinator();

        //arrayCombinations = combinator.combineHelp(arrayList,arrayList.size());
        operatorCombinations = combinator.combineHelp(operators, arrayList.size()-1,true);

        //get all permutations & combinations
        arrayPermutations.addAll(Combinator.permute(arrayList,arrayList.size()));
        operatorPermutations = combinator.permuteHelp(operatorCombinations,arrayList.size()-1);


        //calculate if we can reach 24
        Calculator calculator = new Calculator();
        results = calculator.calculate(arrayPermutations,operatorPermutations);

        if(results.contains(24)) return true;
        else return false;

    }


    public boolean canBeEqualToEndResult(Integer result) {

        if(result.equals(endValue)){
            return true;
        }
        else return false;
    }

    //calculate value using Reverse Polish notation, returns Double for getting correct values when divide
    private static Double calc(String input) {
        try {
            Stack<Double> numbers = new Stack<>();
            for (String number : input.split("\\s+")) {
                Operator operator = Operator.find(number);
                if (operator != null) {
                    calcSign(numbers, operator);
                } else {
                    numbers.push(Double.parseDouble(number));
                }
            }
            return numbers.pop();
        }
        catch (NumberFormatException nfe){
            System.out.println(input);
        }
        return 0.0;
    }

    //operate with 2 numbers from Stack
    private static Stack<Double> calcSign(Stack<Double> numbers, Operator operator) {
        numbers.push(operator.apply(numbers.pop(), numbers.pop()));
        return numbers;
    }
}
