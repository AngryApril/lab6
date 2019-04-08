package calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexey.valiev on 4/4/19.
 */
public enum Operator {

    ADD("+") {
        @Override public double apply(double x1, double x2) {
            return x1 + x2;
        }
    },
    SUBTRACT("-") {
        @Override public double apply(double x1, double x2) {
            return x1 - x2;
        }
    },
    DIVIDE("/"){
        @Override public double apply(double x1, double x2) { return x1 / x2;}
    },
    MULTIPLY("*"){
        @Override public double apply(double x1, double x2) {
            return x1 * x2;
        }
    };


    private final String text;

    private Operator(String text) {
        this.text = text;
    }

    public abstract double apply(double x1, double x2);

    @Override public String toString() {
        return text;
    }

    private static ArrayList<Operator> operatorList = new ArrayList<>();

    public static void setOperatorList(){
        for (Operator operator : Operator.values()) {
            operatorList.add(operator);
            //System.out.print(operator.ordinal());
        }
    }

    public static ArrayList<Operator> getOperatorList() {
        return operatorList;
    }

    private static final Map<String, Operator> map;

    static {
        map = new HashMap<>();
        for (Operator operator : Operator.values()) {
            map.put(operator.text, operator);
        }
    }

    public static Operator find(String operator) {
        return map.get(operator);
    }
}
