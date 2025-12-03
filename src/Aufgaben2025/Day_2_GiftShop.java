package Aufgaben2025;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day_2_GiftShop {

    /**
     * Entfernt alle Zeichen, die keine Ziffern sind (und optional ein führendes Minus).
     * Fängt z.B. ein BOM (﻿) oder andere Steuerzeichen ab.
     */
    public static String cleanNumberString(String s) {

        if (s == null) {
            return "";
        }

        s = s.trim();

        StringBuilder sb = new StringBuilder();
        boolean minusUsed = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Optionales Minus am Anfang
            if (c == '-' && !minusUsed && sb.length() == 0) {
                sb.append(c);
                minusUsed = true;
            } else if (c >= '0' && c <= '9') {
                sb.append(c);
            }
            // alles andere ignorieren
        }

        return sb.toString();
    }

    public static long parseLongClean(String s) {
        String cleaned = cleanNumberString(s);
        if (cleaned.isEmpty() || cleaned.equals("-")) {
            throw new NumberFormatException("Kann String nicht in Zahl umwandeln: '" + s + "'");
        }
        return Long.parseLong(cleaned);
    }

    /**
     * Part 1:
     * Ungültig, wenn die ID aus einem Block besteht,
     * der GENAU zweimal hintereinander vorkommt (z.B. 11, 6464, 123123).
     */
    public static boolean isInvalidIdPart1(String string) {

        if (string == null || string.length() == 0) {
            return false;
        }

        // Ungerade Länge → nie "XYXY"
        if ((string.length() % 2) != 0) {
            return false;
        }

        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();

        int half = string.length() / 2;

        // Erste Hälfte
        for (int i = 0; i < half; i++) {
            stringBuilder1.append(string.charAt(i));
        }

        // Zweite Hälfte
        for (int i = half; i < string.length(); i++) {
            stringBuilder2.append(string.charAt(i));
        }

        String temp1 = stringBuilder1.toString();
        String temp2 = stringBuilder2.toString();

        return temp1.equals(temp2);
    }

    /**
     * Part 2:
     * Ungültig, wenn die ID nur aus einem Block besteht,
     * der mindestens zweimal hintereinander vorkommt.
     *
     * Beispiele:
     *  - 12341234  → "1234" 2x
     *  - 123123123 → "123"  3x
     *  - 1212121212 → "12"  5x
     *  - 1111111   → "1"    7x
     */
    public static boolean isInvalidIdPart2(String string) {

        if (string == null || string.length() == 0) {
            return false;
        }

        int length = string.length();

        // Blocklänge kann maximal die Hälfte der Gesamtlänge sein
        for (int blockLen = 1; blockLen <= length / 2; blockLen++) {

            // Die Gesamtlänge muss ein Vielfaches der Blocklänge sein
            if (length % blockLen != 0) {
                continue;
            }

            int repeats = length / blockLen;
            if (repeats < 2) {
                continue; // zur Sicherheit, sollte eh nie passieren
            }

            // Block aus den ersten blockLen Zeichen aufbauen
            StringBuilder blockBuilder = new StringBuilder();
            for (int i = 0; i < blockLen; i++) {
                blockBuilder.append(string.charAt(i));
            }
            String block = blockBuilder.toString();

            // Prüfen, ob der gesamte String nur aus diesem Block besteht
            boolean allMatch = true;
            int pos = 0;

            for (int r = 0; r < repeats; r++) {
                for (int j = 0; j < blockLen; j++) {
                    if (string.charAt(pos + j) != block.charAt(j)) {
                        allMatch = false;
                        break;
                    }
                }
                if (!allMatch) {
                    break;
                }
                pos += blockLen;
            }

            if (allMatch) {
                return true;
            }
        }

        return false;
    }

    /**
     * Erzeugt alle Zahlen von range[0] bis range[1] (inklusive)
     * und gibt sie als Strings in einer ArrayList zurück.
     */
    public static ArrayList<String> rangeCounter(String[] range) {

        long rangeBegin = parseLongClean(range[0]);
        long rangeEnd = parseLongClean(range[1]);

        ArrayList<String> rangeOfNumbers = new ArrayList<>();

        for (long i = rangeBegin; i <= rangeEnd; i++) {
            rangeOfNumbers.add(String.valueOf(i));
        }

        return rangeOfNumbers;
    }

    /**
     * Zerlegt einen Bereichs-String wie "11-22" in ["11", "22"].
     */
    public static String[] getNumbersFromList(String string) {

        string = string.trim();

        String begin;
        String end;
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int temp = -1;

        // Position des '-' suchen
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '-') {
                temp = i;
            }
        }

        if (temp == -1) {
            // Kein '-' gefunden → Notfall: alles als begin/end
            begin = string;
            end = string;
        } else {

            // Alles vor '-' → begin
            for (int i = 0; i < string.length(); i++) {
                if (i < temp) {
                    stringBuilder1.append(string.charAt(i));
                } else if (i > temp) {
                    // Alles nach '-' → end
                    stringBuilder2.append(string.charAt(i));
                }
            }

            begin = stringBuilder1.toString();
            end = stringBuilder2.toString();
        }

        String[] range = new String[2];
        range[0] = begin;
        range[1] = end;

        return range;
    }

    /**
     * Zerlegt die ganze Eingabezeile (z.B. "11-22,95-115,...")
     * in einzelne Bereichs-Strings wie "11-22", "95-115", ...
     */
    public static ArrayList<String> splitRanges(String allIds) {

        ArrayList<String> listOfAllIds = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < allIds.length(); i++) {
            char c = allIds.charAt(i);

            if (c == ',') {
                String rangeString = stringBuilder.toString().trim();
                if (!rangeString.isEmpty()) {
                    listOfAllIds.add(rangeString);
                }
                stringBuilder.setLength(0);
            } else {
                stringBuilder.append(c);
            }
        }

        String lastRange = stringBuilder.toString().trim();
        if (!lastRange.isEmpty()) {
            listOfAllIds.add(lastRange);
        }

        return listOfAllIds;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Sebi\\Downloads\\AdventOfCode_InvalidIDs.csv"));
        String allIds = br.readLine();
        br.close();

        if (allIds == null) {
            System.out.println("Keine Daten in der Datei gefunden.");
            return;
        }

        allIds = allIds.trim();

        ArrayList<String> listOfAllIds = splitRanges(allIds);

        long resultPart1 = 0L;
        long resultPart2 = 0L;

        for (int i = 0; i < listOfAllIds.size(); i++) {

            String rangeString = listOfAllIds.get(i);

            String[] temp = getNumbersFromList(rangeString);

            ArrayList<String> idsInRange = rangeCounter(temp);

            for (int j = 0; j < idsInRange.size(); j++) {
                String idString = idsInRange.get(j);

                // Part 1: genau 2x wiederholt
                if (isInvalidIdPart1(idString)) {
                    long idValue = Long.parseLong(idString);
                    resultPart1 += idValue;
                }

                // Part 2: irgendein Block mindestens 2x wiederholt
                if (isInvalidIdPart2(idString)) {
                    long idValue = Long.parseLong(idString);
                    resultPart2 += idValue;
                }
            }
        }

        System.out.println("Part 1 (genau 2x wiederholt):  " + resultPart1);
        System.out.println("Part 2 (mindestens 2x):        " + resultPart2);
    }
}