package com.huifer.data.lx;

import java.util.Stack;

/**
 * <p>Title : P1175 </p>
 * <p>Description : 数学表达式转换</p>
 *
 * @author huifer
 * @date 2019-04-19
 */
public class P1175 {

    public static float evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        Stack<Float> number = new Stack<>();

        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i] == ' ') {
                continue;
            }

            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer sbuf = new StringBuffer();
                // 拼接完整的数字两位数以上
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
                    sbuf.append(tokens[i++]);
                }
                i--;
                number.push(Float.parseFloat(sbuf.toString()));
            } else if (tokens[i] == '(') {
                ops.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (ops.peek() != '(') {
                    number.push(caculate(ops.pop(), number.pop(), number.pop()));
                }
                ops.pop();
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*'
                    || tokens[i] == '/') {

                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    number.push(caculate(ops.pop(), number.pop(), number.pop()));
                }

                ops.push(tokens[i]);
            }
        }

        while (!ops.empty()) {
            number.push(caculate(ops.pop(), number.pop(), number.pop()));
        }

        return number.pop();
    }

    /**
     * 运算优先级
     *
     * @param op1 运算符1
     * @param op2 运算符2
     */
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    /**
     * 进行数学运算
     *
     * @param op 运算符
     * @param b  数b
     * @param a  数a
     * @return 结果值
     */
    public static float caculate(char op, float b, float a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
            default:
                break;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(P1175.evaluate("1+(3-1)*4/2"));
        System.out.println(P1175.evaluate("100 * 2 + 12"));
        System.out.println(P1175.evaluate("100 * ( 2 + 12 )"));
        System.out.println(P1175.evaluate("100 * ( 2 + 12 ) / 14"));
    }

}
