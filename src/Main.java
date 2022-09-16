import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String stringInput = scan.nextLine();
        calculate(stringInput);


    }

    static void calculate(String stringInput) {
        String stringEqualsArabian = stringInput.replaceAll("\\d+\\s(\\+|-|\\/|\\*)\\s\\d+", ""); //делаем пустую строку (убираем числа и знак)
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
            switch (znak) {
                case '+':
                    int sum = num1 + num2;
                    System.out.print("Output: " + sum);
                    break;
                case '-':
                    int difference = num1 - num2;
                    System.out.print("Output: " + difference);
                    break;
                case '*':
                    int multiplication = num1 * num2;
                    System.out.print("Output: " + multiplication);
                    break;
                case '/':
                    int division = num1 / num2;
                    System.out.print("Output: " + division);
                    break;
            }
        }


        String stringEqualsRoman = stringInput.replaceAll("[I,V,X,L,C]+\\s(\\+|-|\\/|\\*)\\s[I,V,X,L,C]+", ""); //убираем римские числа и знак.
        boolean flagRoman = stringEqualsRoman.isEmpty();//проверка математического выражения, на то, что оно состояло из римских чисел.
        if (flagRoman == true) {
            String[] arrStringInput = stringInput.split(" ");
            String x1 = "";
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
            int num1 = Roman.valueOf(x1).toInt();//Возвращаем из Enum константу, соответствующую переменной, применяя метод, 'toInt' возвращаем int-представление константы.
            int num2 = Roman.valueOf(x2).toInt();
            int result = 0;
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
                System.out.println("Output: " + romanNum[result]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("В римской системе счистления нет отрицательных чисел");
            }
        }
        //Обработка исключений
        String mathOperation = stringInput.replaceAll("\\d+|\\w+|", "");
        boolean mathOperationFalse = mathOperation.isEmpty();
        if (mathOperationFalse == true) {
            try {
                throw new ArithmeticException();
            } catch (ArithmeticException e) {
                System.out.println("Строка  не является математической операцией.");
            }
        }

        String mathNotation = stringInput.replaceAll("\\d+\\s(\\+|-|\\*|\\/)\\s[I,V,X,L,C]+|[I,V,X,L,C]+\\s(\\+|-|\\*|\\/)\\s\\d+|", "");
        boolean mathNotationFalse = mathNotation.isEmpty();
        if (mathNotationFalse == true) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Используются одновременно разные системы счисления");
            }
        }

        String mathOperance = stringInput.replaceAll("[\\d+\\s(\\+|-|\\/|\\*)\\s\\d+]{5,}|[[I,V,X,L,C]+\\s(\\+|-|\\/|\\*)\\s[I,V,X,L,C]+]{5,}|", "");
        boolean mathOperanceFalse = mathOperance.isEmpty();
        if (mathOperanceFalse == true) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+,-,/,*)");
            }
        }

    }
}


