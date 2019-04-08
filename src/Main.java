

import calculator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alexey.valiev on 4/4/19.
 */
public class Main {

    public static void main(String[] args){

        String input;
        ArrayList<Operator> operators;
        List<Integer> arrayList;
        List<List<Operator>> operatorCombinations = new ArrayList<>();
        List<List<Operator>> operatorPermutations = new ArrayList<>();
        //List<List<Integer>> arrayCombinations = new ArrayList<>();
        List<List<Integer>> arrayPermutations = new ArrayList<>();
        //List<Integer> results = new ArrayList<>();


        //initialize enum Operator
        Operator.setOperatorList();
        operators = Operator.getOperatorList();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Write numbers [1...9] divided by space:\ne.g. : 1 2 4 6");
            input = reader.readLine();
            Converter converter = new Converter();
            arrayList = converter.convertToList(input);
            if (arrayList.size()<1) return;

            //get all combinations with repeating values, e.g. '+ + +','+ / *','- / -'
            Combinator combinator = new Combinator();

            //arrayCombinations = combinator.combineHelp(arrayList,arrayList.size());
            operatorCombinations = combinator.combineHelp(operators, arrayList.size()-1,true);

            //get all permutations & combinations
            arrayPermutations.addAll(Combinator.permute(arrayList,arrayList.size()));
            operatorPermutations = combinator.permuteHelp(operatorCombinations,arrayList.size()-1);

            //Print all permutations & combinations
            System.out.println("arrayPermutations: " + arrayPermutations.toString());
            System.out.println("operatorPermutations" + operatorPermutations.toString());

            //calculate if we can reach 24
            Calculator calculator = new Calculator();
            calculator.calculate(arrayPermutations,operatorPermutations);


        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

    }

}
