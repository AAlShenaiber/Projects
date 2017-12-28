/**
 * Created by Abdullah A on 2017-05-29
 */

import java.util.Scanner;

public class Crypto {
    public static void main(String[] args) {

        System.out.println("Welcome to Crypto, please input the message to be encrypted (type any numbers as words): ");
        Scanner messageScan = new Scanner(System.in);
        String messageEncrypt = messageScan.nextLine();

        System.out.println("Please input the \"shift\" number you would like: ");
        Scanner shiftScan = new Scanner(System.in);
        int shiftNumber = shiftScan.nextInt();

        System.out.println("Please input the number of characters you would like your message to be grouped in: ");
        Scanner groupingScan = new Scanner(System.in);
        int groupNumber = groupingScan.nextInt();

        System.out.println("Great, processing your encrypted message...\n");

        String encryptedMessage = encryptString(messageEncrypt, shiftNumber, groupNumber);
        System.out.print(encryptedMessage + "\n");


    }

    public static String normalizeText(String normalize) {

        normalize = normalize.replaceAll("[ .,?!()\":;']", ""); //Removes all spaces and symbols, each individual character in the [] gets replaced with "", basically removed
        normalize = normalize.toUpperCase();

        return normalize;
    }

    public static String obify(String obfuscate) {
        //placing OB in front of all vowels

        obfuscate = obfuscate.replace("O", "OBO"); //this is first so "OB"s don't get the "O" replaced
        obfuscate = obfuscate.replace("A", "OBA");
        obfuscate = obfuscate.replace("E", "OBE");
        obfuscate = obfuscate.replace("I", "OBI");
        obfuscate = obfuscate.replace("U", "OBU");
        obfuscate = obfuscate.replace("Y", "OBY");

        return obfuscate;

    }


    public static String unobify(String unobfuscation) {

        unobfuscation = unobfuscation.replace("OB", "");

        return unobfuscation;
    }


    public static String caesariphy(String caesarString, int shiftAmount) { //"ADE"

        String z = shiftAlphabet(shiftAmount);
        String Result="";

        for(int i = 0; i < caesarString.length(); i++) {

            Result = Result + String.valueOf(z.charAt( (int) caesarString.charAt(i)-65)); //String.valueOf makes it a string

        }

        return Result;
    }


    //Given method that lists the alphabet STARTING from the shifted amount (e.g a shift of 2 gives CDEFGHIJKLMNOP...YZA)

    public static String shiftAlphabet(int shift) { //CD
        int start = 0;

        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }

        String result = "";
        char currChar = (char) start;

        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }

        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }

        return result;
    }

    public static String groupify(String code, int groupSize) {

        double noOfGroups = Math.ceil((code.length() / (double) groupSize));
        int Pad=0;

        if((code.length() % groupSize) != 0 ) {
            Pad = groupSize - (code.length() % groupSize);
        }

        String Result = "";
        String x;

        for(int i=0; i < (int)noOfGroups; i++) {

            if(i==noOfGroups-1)
                x = code.substring(i*groupSize);
            else
                x = code.substring(i*groupSize, (i*groupSize) + (groupSize));


            if (i==0)
                Result += x;
            else
                Result += " " + x;


        }

        for(int i=0; i < Pad; i++) {
            Result += "x";
        }

        return Result;

    }

    public static String encryptString(String inputString, int shift, int groupSize) {

        inputString = normalizeText(inputString);
        inputString = obify(inputString);
        inputString = caesariphy(inputString, shift);
        inputString = groupify(inputString, groupSize);

        return inputString;
    }
}
