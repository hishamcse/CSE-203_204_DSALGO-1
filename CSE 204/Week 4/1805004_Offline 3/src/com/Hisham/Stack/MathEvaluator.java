package com.Hisham.Stack;

/*
 * Problem 1: Mathematical Expression Validator and evaluator
 */

import java.util.Scanner;

public class MathEvaluator {

    private boolean operator(char c) {              // checks if the character is an operator or not
        return c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int get_precedence(String s) {          // returns precedence as integer value
        return switch (s) {
            case "(", ")" -> 0;
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "~" -> 3;         // unary minus
            default -> -1;         // not an operator. actually a value
        };
    }

    public static double getValue_By_Operation(String operator, double value1, double value2) {
        switch (operator) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                if (value2 == 0) {
                    return Double.NaN;
                }
                return value1 / value2;
            default:
                return Double.NaN;
        }
    }

    private boolean populate_Value_stack(String operator, CustomStack<Double> vals) {
        if (operator.equals("~")) {
            if (vals.length() < 1) return false;
            double val = vals.pop();
            vals.push(-val);
        } else {
            if (vals.length() < 2) return false;
            Double val2 = vals.pop();
            Double val1 = vals.pop();
            vals.push(getValue_By_Operation(operator, val1, val2));
        }
        return true;
    }

    private boolean unaryValidity(String[] all) {
        int n = all.length;
        for (int i = 0; i < n; i++) {
            if (all[i].equals("~")) {
                if (i == 0 || i == n - 1 || !all[i - 1].equals("(")) return false;
                if (operator(all[i + 1].charAt(0)) && !all[i + 1].equals("(")) return false;
                if (i < n - 2 && !operator(all[i + 1].charAt(0)) && all[i + 2].equals(")")) continue;
                if (operator(all[i + 1].charAt(0))) {
                    if (!(all[i - 1].charAt(0) == '(')) return false;
                    int leftParen = 0, rightParen = 0, j = i - 1;
                    while (j != n) {
                        if (all[j].charAt(0) == '(') {
                            leftParen++;
                        } else if (all[j].charAt(0) == ')') {
                            rightParen++;
                            if (leftParen == rightParen || j == n - 1 || (operator(all[j + 1].charAt(0)) && !all[j + 1].equals(")") && !all[j + 1].equals("("))) {
                                break;
                            }
                        }
                        j++;
                    }
                    if (leftParen != rightParen) return false;
                }
            }
        }
        return true;
    }

    private boolean negativeValidity(int index, String[] all) {
        return index > 0 && index < all.length - 1 && all[index - 1].equals("~") && all[index + 1].equals(")");
    }

    private String modifiedExpression(String expression) {         // method which returns properly spaced expression
        StringBuilder sb = new StringBuilder();
        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (operator(c) && i > 0 && i < chars.length - 1) {
                if (c == '-' && chars[i - 1] == '(') {
                    sb.append("~").append(" ");
                } else {
                    sb.append(c).append(" ");
                }
            } else if (operator(c)) {
                sb.append(c).append(" ");
            } else {
                sb.append(c);
                if (i < chars.length - 1) {
                    if (operator(chars[i + 1])) {
                        sb.append(" ");
                    }
                }
            }
        }
        return sb.toString().trim();
    }

    public double evaluate(String expression) {                   // check validation and evaluate simultaneously
        expression = modifiedExpression(expression.replaceAll("\\s", ""));
        CustomStack<String> operators = new CustomStack<>();
        CustomStack<Double> values = new CustomStack<>();

        String[] all = expression.split(" ");
        int n = all.length;

        if (!unaryValidity(all)) return Double.NaN;

        for (int i = 0; i < n; i++) {
            String s = all[i];

            if (get_precedence(s) == -1) {             // value handler
                try {
                    double d = Double.parseDouble(s);
                    if (i > 0 && all[i - 1].equals("~")) {
                        if (!negativeValidity(i, all)) {
                            return Double.NaN;
                        }
                    }
                    values.push(d);
                } catch (NumberFormatException e) {
                    return Double.NaN;
                }
                continue;
            }

            while (true) {                             // operator handler
                if (operators.length() == 0 || s.equals("(") || (get_precedence(s) > get_precedence(operators.peek()))) {
                    operators.push(s);
                    break;
                }

                String op = operators.pop();
                if (op.equals("(")) {
                    if (!s.equals(")")) return Double.NaN;
                    break;
                } else {
                    if (!populate_Value_stack(op, values)) return Double.NaN;
                }
            }
        }

        while (!(operators.length() == 0)) {
            if (values.length() < 2) return Double.NaN;
            String op = operators.pop();
            double val2 = values.pop();
            double val1 = values.pop();
            values.push(getValue_By_Operation(op, val1, val2));
        }

        if (values.length() != 1 || operators.length() != 0) return Double.NaN;

        return values.pop();
    }

    public void answer(String expression) {             // prints properly formatted answer
        double ans = evaluate(expression);
        if (Double.isNaN(ans)) {
            System.out.println("Not valid");
        } else {
            StringBuilder sb = new StringBuilder("Valid expression, Computed value: ");
            int parsed_Int = (int) Math.round(ans);
            if (ans - parsed_Int == 0) {
                sb.append(parsed_Int);
            } else {
                sb.append(ans);
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        MathEvaluator evaluator = new MathEvaluator();
        evaluator.answer("(9*3-(7*8+((-4/2)))");
        evaluator.answer("12.5-8.6/(12-(18-(2*3)))");
        evaluator.answer("(-((-(-(-4)))))");
        evaluator.answer("((-((-(-(-7)))))-7)-((((-((-4)))*(-8))))");

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        evaluator.answer(s);
    }
}
