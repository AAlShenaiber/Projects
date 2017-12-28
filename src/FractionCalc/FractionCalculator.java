package FractionCalc;

/**
 * Created by Abdullah A on 2017-08-12.
 */
import java.util.*;

public class FractionCalculator {
    public static void main(String[] args) {
        System.out.println("-Fraction Calculator-");
        System.out.println("This program will add, subtract, multiply, and divide fractions and whole numbers");
        System.out.println("Please enter fractions in the form of a/b where a and b are integers. Enter Q to quit.");

        String operation = "Initializing this string";
        String FirstFrac;
        String SecondFrac;

        int numerator1;
        int denominator1;
        int numerator2;
        int denominator2;

        Scanner sc = new Scanner(System.in);

        while (operation != "Q") {
            System.out.println("---------------------------------------------------------");

            System.out.print("Please enter an operation (+, -, *, /, = or Q to quit): ");
            operation = sc.next();

            //Making sure it's correct input
            while (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/") && !operation.equals("=") && !operation.equals("Q")){
                System.out.print("Please enter a correct operation (+, -, *, /, =, or Q to quit): ");
                operation = sc.next();
            }

            if(operation.equals("Q")) throw new IllegalArgumentException("Quitting program...");

            System.out.print("Please enter a fraction (a/b) or integer (a): ");
            FirstFrac = sc.next();

            //Making sure it's correct input
            while(isNumber(FirstFrac) == false) {
                System.out.print("Invalid fraction. Please enter a fraction (a/b) or integer (a) where b is not 0: ");
                FirstFrac = sc.next();
            }

            //Splitting up numerator and denominator into separate integers
            if (FirstFrac.contains("/")) {
                int index = FirstFrac.indexOf("/");
                int length = FirstFrac.length();
                numerator1 = Integer.parseInt(FirstFrac.substring(0, index));
                denominator1 = Integer.parseInt(FirstFrac.substring(index + 1));
            } else {
                numerator1 = Integer.parseInt(FirstFrac);
                denominator1 = 1;
            }

            //REPEAT PROCESS with second fraction

            System.out.print("Please enter a fraction (a/b) or integer (a): ");
            SecondFrac = sc.next();

            while(isNumber(SecondFrac) == false) {
                System.out.print("Invalid fraction. Please enter a fraction (a/b) or integer (a) where b is not 0: ");
                SecondFrac = sc.next();
            }

            if (SecondFrac.contains("/")) {
                int index = SecondFrac.indexOf("/");
                int length = SecondFrac.length();
                numerator2 = Integer.parseInt(SecondFrac.substring(0, index));
                denominator2 = Integer.parseInt(SecondFrac.substring(index + 1));
            } else {
                numerator2 = Integer.parseInt(SecondFrac);
                denominator2 = 1;
            }

            //Converting to fraction objects
            Fraction Fraction1 = new Fraction(numerator1, denominator1);
            Fraction Fraction2 = new Fraction(numerator2, denominator2);

            //Setting and printing result of users operation
            String result = " ";

            if(operation.equals("+")) result = Fraction1.add(Fraction2).toString();
            if(operation.equals("-")) result = Fraction1.subtract(Fraction2).toString();
            if(operation.equals("*")) result = Fraction1.multiply(Fraction2).toString();
            if(operation.equals("/")) result = Fraction1.divide(Fraction2).toString();
            if(operation.equals("=")) {
                if(Fraction1.equals(Fraction2) == true)
                    result = "true";
                else
                    result = "false";
            }

            //If result is whole number or 0 (Special Cases)
            int index = result.indexOf("/");
            String resultDenominator = result.substring(index+1);

            if(resultDenominator.equals("1"))                        //if denominator is 1, it's a whole number
                result = result.substring(0, index);
            if(result.charAt(0) == '0')                               //if numerator zero, result is 0
                result = "0";


            //Printing answer
            System.out.print(Fraction1.toString() + " " + operation + " " + Fraction2.toString() + " = " + result + "\n");
        }
    }


    public static boolean isNumber(String input) {

        boolean isItANumber = true;

        //Checking if it's a fraction with denominator 0:
        if(input.contains("/")) {
            int index = input.indexOf("/");
            if (input.substring(index).equals("0"))          //if denominator is 0
                isItANumber = false;
        }

        //Checking if everything is a number (besides "/" and "-")

        input = input.replace("/", "");
        input = input.replace("-", "");

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '0' && input.charAt(i) != '1' && input.charAt(i) != '2' && input.charAt(i) != '3' && input.charAt(i) != '4' && input.charAt(i) != '5' && input.charAt(i) != '6' && input.charAt(i) != '7' && input.charAt(i) != '8' && input.charAt(i) != '9')
                isItANumber = false;
        }

        return isItANumber;
    }
}
