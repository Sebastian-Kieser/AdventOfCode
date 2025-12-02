package Aufgaben2025;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_2_GiftShop {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Sebi\\Downloads\\AdventOfCode_InvalidIDs.csv"));
        String allIds = br.readLine().trim();

        String[] listOfAllIds = new String[allIds.length()];
        ArrayList<Integer> safeIndex = new ArrayList<>();
        for (int i = 0; i < allIds.length(); i++) {
            if (!allIds.matches(",")) {
                safeIndex.add(i);
            } else {
                for (int j = safeIndex.getFirst(); i < safeIndex.getLast(); i++) {
                    listOfAllIds[0] = listOfAllIds[0] + allIds.charAt(j);
                }
            }
        }

















    }
}
