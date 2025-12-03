package Aufgaben2025;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.UnaryOperator;

public class Day_2_GiftShop {

    public static String invalidIds(String string) {

        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        String temp1;
        String temp2 = "";
        int choice;

        if ((string.length() % 2) == 0) {
            choice = 1;
        } else if ((string.length() % 3) == 0) {
            choice = 2;
        } else {
            choice = 3;
        }

        switch (choice) {
            case 1:
                for (int i = 0; i < 2; i++) {
                    stringBuilder1.append(string.charAt(i));
                }

                for (int i = 2; i < string.length(); i++) {
                    stringBuilder2.append(string.charAt(i));
                }
                temp1 = stringBuilder1.toString();
                temp2 = stringBuilder2.toString();

                if (temp1.equals(temp2)) {
                    return temp1;
                } else {
                    return "true";
                }
            case 2:
                for (int i = 0; i < 3; i++) {
                    stringBuilder1.append(string.charAt(i));
                }

                for (int i = 3; i < string.length(); i++) {
                    stringBuilder2.append(string.charAt(i));
                }
                temp1 = stringBuilder1.toString();
                temp2 = stringBuilder2.toString();

                if (temp1.equals(temp2)) {
                    return temp2;
                } else {
                    return "true";
                }
            case 3:
                return "true";
            default:
                return temp2;
        }
    }

    public static ArrayList<String> rangeCounter(String[] range) {

        int rangeBegin = Integer.parseInt(range[0]);
        int rangeEnd = Integer.parseInt(range[1]);
        int lengths = rangeEnd - rangeBegin;
        ArrayList<String> rangeOfNumbers = new ArrayList<>();

        for (int i = rangeBegin; i <= rangeEnd; i++) {
            rangeOfNumbers.add(String.valueOf(i));
        }

        return rangeOfNumbers;
    }

    public static String[] getNumbersFromList(String string) {

        String begin;
        String end;
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int temp = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '-') {
                temp = i;
            }
        }

        for (int i = 0; i < string.length(); i++) {
            if (i < temp) {
                stringBuilder1.append(string.charAt(i));
            } else if (i > temp) {
                stringBuilder2.append(string.charAt(i));
            }
        }

        begin = stringBuilder1.toString();
        end = stringBuilder2.toString();

        String[] range = new String[2];
        range[0] = begin;
        range[1] = end;

        return range;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Sebi\\Downloads\\AdventOfCode_InvalidIDs.csv"));
        String allIds = br.readLine().trim();

        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<String> listOfAllIds = new ArrayList<>();

        




//        int integer = 0;
//        for (int i = 0; i < allIds.length(); i++) {
//            if (allIds.charAt(i) != ',') {
//                integer = i;
//            }
//        }

//        StringBuilder newStringBuilder = new StringBuilder();
//        for (int i = 0; i < allIds.length(); i++) {
//            if (i < integer) {
//                stringBuilder.append(allIds.charAt(i));
//            } else if (i > integer) {
//                newStringBuilder.append(allIds.charAt(i));
//            }
//        }




        String[] temp = new String[2];
        ArrayList<String> temp1;
        int result = 0;

        for (int i = 0; i < listOfAllIds.size(); i += 2) {
            for (int j = 0; j < 2; j++) {
                temp[i] = Arrays.toString(getNumbersFromList(String.valueOf(listOfAllIds.get(i))));
            }

            temp1 = rangeCounter(temp);
            for (int j = 0; j < temp1.size(); j++) {
                if (!invalidIds(temp1.get(i)).equals("true")) {
                    result += Integer.parseInt(invalidIds(temp1.get(i)));
                }
            }
        }

        System.out.println(result);






















    }
}
