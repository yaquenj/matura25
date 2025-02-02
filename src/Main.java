// Zadanie 1

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.lang.Integer.parseInt;

public class Main {
    public static String read(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return "Error!";
        }
    }
    public static void main(String[] args) {

        String file_content = read("./src/data/prostokaty.txt");
        System.out.println(file_content);

        String[] lines = file_content.split("\n");
        ArrayList<Integer> results = new ArrayList<>();

        for (String line : lines) {

            if (line.isBlank()) return;

            String[] number_strings = line.split("\\s+");

            int a = parseInt(number_strings[0].trim());
            int b = parseInt(number_strings[1].trim());
            int total = a * b;

            System.out.printf("a: %d, b: %d, P = %d\n", a, b, total);
            results.add(total);

        }

        int min = results.getFirst();
        int max = results.getFirst();

        for (int result : results) {
            if (result < min) min = result;
            if (result > max) max = result;
        }

        System.out.printf("Najmniejszy wynik: %d\n", min);
        System.out.printf("Najwiekszy wynik: %d\n", max);

        zadanie2();

    }
    public static void zadanie2() {

        /**
         ** Cel: Szukamy najdłuższy ciąg kolejnych mieszczacych sie w sobie prostokątów
         * ! WARUNKI:
         *? s - szerokość prostokatu, s <= s'
         *? h - wysokosc prostokatu,  h <= h'
         * ! ZWARACA: długość ciągu ; wysokość i szerokość ostatniego prostokąta.
         */

        String file_content = read("./src/data/prostokaty.txt");
        String[] lines = file_content.split("\n");
        ArrayList<Integer> s = new ArrayList<>();
        ArrayList<Integer> h = new ArrayList<>();
        int total = 0;


        // arraylist of pairs of



    }
}