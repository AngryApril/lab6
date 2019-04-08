package calculator;

import java.util.*;


/**
 * Created by alexey.valiev on 4/4/19.
 */
public class Combinator {

    //
    public <T> List<List<T>> permuteHelp(List<List<T>> list, int size){

        List<List<T>> listRes = new ArrayList<>();
        listRes.addAll(list);

        for(int i = 0; i < list.size(); i++){
            int j = 0;
            if(j<list.get(i).size()-1 && list.get(i).get(j).equals(list.get(i).get(j+1))){
                continue;
            }
            List<T> array = list.get(i);
            listRes.addAll(permute( array, size));
        }
        //System.out.println("listRes : " + listRes.size());
        return listRes;
    }

    public static <T> ArrayList<ArrayList<T>> permute(List<T> num, int size) {
        ArrayList<ArrayList<T>> result = new ArrayList<ArrayList<T>>();

        //start from an empty list
        result.add(new ArrayList<T>());

        for (int i = 0; i < num.size(); i++) {
            //list of list in current iteration of the array num
            ArrayList<ArrayList<T>> current = new ArrayList<ArrayList<T>>();

            for (List<T> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size()+1; j++) {
                    // + add num[i] to different locations
                    l.add(j, num.get(i));

                    ArrayList<T> temp = new ArrayList<T>(l);
                    current.add(temp);

                    //System.out.println(temp);

                    // - remove num[i] add
                    l.remove(j);
                }
            }

            result = new ArrayList<ArrayList<T>>(current);
        }

        return result;
    }

    //
    public <T> List<List<T>> combineHelp(List<T> values, int size, boolean repetitions) {

        List<List<T>> fullRes = combination(values, size);
        List<T> tmpList1 = new ArrayList<T>();
        List<T> tmpList2 = new ArrayList<T>();
        List<T> tmpList3 = new ArrayList<T>();
        T tmp1;
        List<List<T>> combRes = new ArrayList<>();


        for (int i = 0; i < fullRes.size(); i++){
            if(fullRes.get(i).size() == size){
                combRes.add(fullRes.get(i));
            }
        }

        //if repeated elements are allowed
        if(repetitions){
            for (int j = 0; j < values.size(); j++) {
                combRes.add(Collections.nCopies(size, values.get(j)));
            }
            for (int i = 0; i < values.size(); i++) {

                tmp1 = values.get(values.size()-i-1);
                tmpList1 = new ArrayList<T>();
                tmpList2 = new ArrayList<T>();
                tmpList3 = new ArrayList<T>();

                for (int j = 0; j < values.size()-1; j++) {

                    if(j == 0){
                        tmpList1.add(tmp1);
                    }
                    else{
                        tmpList1.add(values.get(i));
                    }

                    if(j == 1 ){
                        tmpList2.add(tmp1);
                    }
                    else{
                        tmpList2.add(values.get(i));
                    }

                    if(j == 2 ){
                        tmpList3.add(tmp1);
                    }
                    else{
                        tmpList3.add(values.get(i));
                    }

                }
                if(tmpList1.size() == values.size()-1) combRes.add(tmpList1);
                if(tmpList2.size() == values.size()-1) combRes.add(tmpList2);
                if(tmpList3.size() == values.size()-1) combRes.add(tmpList3);
            }
        }

        return combRes;

    }

    //get unique combinations of given size recursively
    private static <T> List<List<T>> combination(List<T> values, int size) {

        if ( size == 0) {
            return Collections.singletonList(Collections.<T> emptyList());
        }

        if (values.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<T>> combination = new LinkedList<List<T>>();

        T actual = values.iterator().next();

        List<T> subSet = new LinkedList<T>(values);
        subSet.remove(actual);

        List<List<T>> subSetCombination = combination(subSet, size - 1);

        for (List<T> set : subSetCombination) {
            List<T> newSet = new LinkedList<T>(set);
            newSet.add(0, actual);
            combination.add(newSet);
        }

        combination.addAll(combination(subSet, size));

        return combination;
    }

}


