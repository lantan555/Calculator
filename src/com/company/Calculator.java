package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculator {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static String msgError = ANSI_RED + "Неверная команда" + ANSI_RESET;
    int decimalPlace = 0;
    static double sum;

    public void start() throws IOException {
        greeting();

        System.out.println("Укажите сколько оставлять знаков после запятой:");
        try{
            decimalPlace = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e){
            System.out.println(msgError);
            return;
        }

        getOperation();
        System.out.println("==============================================");
        System.out.format(ANSI_GREEN + "Результат: %." + decimalPlace + "f\n", sum);
        System.out.println(ANSI_RED + "Программа завершена" + ANSI_RESET );
    }

    private void greeting(){
        System.out.println("==============================================");
        System.out.println( ANSI_GREEN +  " Приветствуем Вас в программе \"Калькулятор\" " + ANSI_RESET);
        System.out.println("==============================================");
        System.out.println("Доступные команды:");
        System.out.println( ANSI_GREEN + "ADD *число* - Прибавление числа");
        System.out.println("SUBTRACT *число* - Вычитание числа");
        System.out.println("MULTIPLY *число* - Умножение числа");
        System.out.println("DIVIDE *число* - Деление числа");
        System.out.println("POW *число* - Возведение в степень n");
        System.out.println("SQRT - Вычисление квадратного корня");
        System.out.println("SQRTN *число* - Вычисление корня n" + ANSI_RESET);
        System.out.println("Для получения результата введите: " + ANSI_GREEN + "RESULT" + ANSI_RESET);
        System.out.println("==============================================" );
    }

    private double getOperation() throws IOException {
        String[] answer;
        sum = 0;
        do {
            System.out.format("Текущее значение: " + ANSI_GREEN + "%." + decimalPlace + "f\n"  + ANSI_RESET, sum);
            System.out.println("Введите команду:");
            answer = reader.readLine().split(" ");
            boolean valid = false;

            if( (answer.length == 2) && (isDoubleNum(answer[1])) ){
                double num = Double.parseDouble(answer[1]);

                switch (answer[0].toUpperCase()){
                    case "ADD": {
                        sum += num;
                        break;
                    }
                    case "SUBTRACT":{
                        sum -= num;
                        break;
                    }
                    case "MULTIPLY": {
                        sum *= num;
                        break;
                    }
                    case "DIVIDE": {
                        if (num != 0) {
                            sum /= num;
                        } else {
                            System.out.println(ANSI_RED + "На ноль делить нельзя" + ANSI_RESET);
                        }
                        break;
                    }
                    case "POW": {
                        sum = Math.pow(sum, num);
                        break;
                    }
                    case "SQRTN": {
                        sum = Math.pow(sum, 1 / num);
                        break;
                    }
                    default:{
                        System.out.println(msgError);
                    }
                }
            } else if(  answer.length == 1 && answer[0].equalsIgnoreCase("SQRT") ){
                sum = Math.sqrt(sum);
            }
            else if(  answer.length == 1 && answer[0].equalsIgnoreCase("Result") ) break;
            else System.out.println(msgError);

        }while( !( answer.length == 1 && answer[0].equalsIgnoreCase("Result") ) );
        return sum;
    }

    private boolean isDoubleNum(String num){
        try{
           double n = Double.parseDouble(num);
           return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
