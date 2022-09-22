import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String stringInput = scan.nextLine();
        System.out.print(calculate(stringInput));
    }






    static String calculate(String stringInput) {
        Integer result = 0; // Для записи результата вычислений
        String exception=""; // возвращает обработанное исключение
        String arabianResult="";

        String stringEqualsArabian = stringInput.replaceAll("(-?[0-9]+\\s(\\+|-|\\/|\\*)\\s-?[0-9]+){1}", ""); //делаем пустую строку (убираем числа и знак)
        boolean flagArabian = stringEqualsArabian.isEmpty();//проверка математического выражения, подтверждающая, что были введены арабские числа.

        if (flagArabian == true) {
            String[] arrStringInput = stringInput.split(" ");
            String stringNum1 = "";//принимает число в строковом представлении.
            String stringNum2 = "";//принимает число в строковом представлении.
            char znak = '0';//принимает знак в строковом представлении.
            for (int i = 0; i < arrStringInput.length; i++) {
                if (i == 0) {
                    stringNum1 = arrStringInput[0];
                } else if (i == 2) {
                    stringNum2 = arrStringInput[2];
                } else if (i == 1) {
                    znak = arrStringInput[1].charAt(0);
                }
            }
            int num1 = Integer.parseInt(stringNum1);//Парсим числа из строки.
            int num2 = Integer.parseInt(stringNum2);//


            if((num1>10 || num2>10)||(num1<0 || num2<0)){//контроль работы калькулятора в диапазоне 0-10
                try{
                    throw new IllegalArgumentException();
                } catch (IllegalArgumentException e){
                    exception = "Введенное число не входит в диапазон работы калькулятора.";
                    return exception;
                }
            }

            switch (znak) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    try {
                        result = num1 / num2;
                    } catch (ArithmeticException e) {
                        exception = "Нельзя делить на ноль.";
                        return exception;
                    }
            }
            arabianResult = result.toString(); //получаем строку
            return arabianResult;
        }


        String stringEqualsRoman = stringInput.replaceAll("-?[I,V,X,L,C]{1,8}\\s(\\+|-|\\/|\\*)\\s-?[I,V,X,L,C]{1,8}", ""); //убираем римские числа и знак.
        boolean flagRoman = stringEqualsRoman.isEmpty();//проверка математического выражения, на то, что оно состояло из римских чисел.

        if (flagRoman == true) {
            String[] arrStringInput = stringInput.split(" ");
            String x1 = ""; //записываем в эти переменные строковое представление римского числа
            String x2 = "";
            char znak = '0';
            for (int i = 0; i < arrStringInput.length; i++) {
                if (i == 0) {
                    x1 = arrStringInput[0];
                } else if (i == 1) {
                    znak = arrStringInput[1].charAt(0);
                } else if (i == 2) {
                    x2 = arrStringInput[2];
                }
            }


            try { //контроль работы калькулятора в диапазоне римских цифр 1-10
                int num1Compare = Roman.valueOf(x1).toInt();
                int num2Compare = Roman.valueOf(x2).toInt();
            } catch (IllegalArgumentException e){
                exception = "Введенное число не входит в диапазон работы калькулятора.";
                return exception;
            }


            int num1 = Roman.valueOf(x1).toInt();//Возвращаем из Enum константу, соответствующую переменной, применяя метод, 'toInt' возвращаем int-представление константы.
            int num2 = Roman.valueOf(x2).toInt();
            switch (znak) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            String[] romanNum = {
                    "0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII",
                    "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XVI", "XVII", "XVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV",
                    "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                    "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV",
                    "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII",
                    "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII",
                    "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

                try {
                    return arabianResult=romanNum[result].toString();
                } catch (ArrayIndexOutOfBoundsException e) {
                    exception ="В римской системе счистления нет отрицательных чисел";
                }
        }
//Обработка исключений
        String mathOperation = stringInput.replaceAll("\\d+|\\w+|", "");
        boolean mathOperationFalse = mathOperation.isEmpty();
        if (mathOperationFalse == true) {
            try {
                throw new ArithmeticException();
            } catch (ArithmeticException e) {
                exception ="Строка  не является математической операцией.";
            }
        }

        String mathNotation = stringInput.replaceAll("\\d+\\s(\\+|-|\\*|\\/)\\s[I,V,X,L,C]+|[I,V,X,L,C]+\\s(\\+|-|\\*|\\/)\\s\\d+|", "");
        boolean mathNotationFalse = mathNotation.isEmpty();
        if (mathNotationFalse == true) {
            try {
                throw new IOException();
            } catch (IOException e) {
                exception ="Используются одновременно разные системы счисления";
            }
        }


        String mathOperance = stringInput.replaceAll("[0-9]+\\s(\\+|-|\\/|\\*)\\s[0-9]+(\\s(\\+|-|\\/|\\*)\\s[0-9]+){1,}", "");
        boolean mathOperanceFalse = mathOperance.isEmpty();
        if (stringInput.length() > 5 && mathOperanceFalse == true) {
            try {
                throw new IOException();
            } catch (IOException e) {
                exception ="Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+,-,/,*)";
            }
        }


    String mathOperance2 = stringInput.replaceAll("[I,V,X,L,C]+\\s(\\+|-|\\/|\\*)\\s[I,V,X,L,C]+(\\s(\\+|-|\\/|\\*)\\s[I,V,X,L,C]+){1,}", "");
    boolean mathOperanceFalse2 = mathOperance2.isEmpty();
        if (stringInput.length() > 8 && mathOperanceFalse2 == true) {
        try {
            throw new IOException();
        } catch (IOException e) {
            exception ="Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+,-,/,*)";
        }
    }
        return exception;
}

}

