package Aufgaben2025;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day_1_SecretEntrance {

    public static void main(String[] args) throws IOException {

        int counter = 0;     // Anzahl der Klicks auf 0
        int start = 50;      // Startposition

        BufferedReader br = new BufferedReader(
                new FileReader("C:\\Users\\Sebi\\Downloads\\AdventOfCode_Rotationen.csv")
        );
        ArrayList<String> rotations = new ArrayList<>();
        String line;

        // Datei einlesen
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                rotations.add(line);
            }
        }

        String number;
        int temp;

        for (String rotation : rotations) {

            rotation = rotation.trim();

            char richtung = rotation.charAt(0);
            number = rotation.substring(1).trim();
            temp = Integer.parseInt(number);

            int alt = start;   // alte Position
            int clicksToZero = 0;

            if (richtung == 'R') {
                if (alt == 0) {
                    // von 0 aus jede 100 Schritte wieder 0
                    clicksToZero = temp / 100;
                } else {
                    int k0 = (100 - alt) % 100;   // erster Klick, der 0 trifft (1..99)
                    if (k0 != 0 && temp >= k0) {
                        clicksToZero = 1 + (temp - k0) / 100;
                    }
                }

                counter += clicksToZero;
                start = (alt + temp) % 100;   // neue Position

            } else if (richtung == 'L') {
                if (alt == 0) {
                    // von 0 aus jede 100 Schritte rückwärts wieder 0
                    clicksToZero = temp / 100;
                } else {
                    int k0 = alt;   // erster Klick, der 0 trifft (1..99)
                    if (temp >= k0) {
                        clicksToZero = 1 + (temp - k0) / 100;
                    }
                }

                counter += clicksToZero;
                // sicheres Modulo für negative Werte
                start = ((alt - temp) % 100 + 100) % 100;
            }

            // Debug zum Testen:
             System.out.println(rotation + ": " + alt + " -> " + start + " (Clicks auf 0 in diesem Zug: " + clicksToZero + ", total: " + counter + ")");
        }

        String hex = Integer.toHexString(counter).toUpperCase();
        System.out.println("Das Passwort ist: 0x" + hex);
        System.out.println("Endposition: " + start);
        System.out.println("Anzahl der Clicks auf 0: " + counter);
    }
}
