import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.Integer.parseInt;

// Suppress high complexity errors in IDE
@SuppressWarnings("ALL")

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

        System.out.println("Zadanie 1");
        zadanie1();
        System.out.println("Zadanie 2");
        zadanie2();
        System.out.println("Zadanie 3");
        zadanie3();

    }
    public static void zadanie1() {

        String file_content = read("./src/data/prostokaty.txt");

        String[] lines = file_content.split("\n");
        ArrayList<Integer> results = new ArrayList<>();

        for (String line : lines) {

            if (line.isBlank()) return;

            String[] number_strings = line.split("\\s+");

            int a = parseInt(number_strings[0].trim());
            int b = parseInt(number_strings[1].trim());
            int total = a * b;

            results.add(total);

        }

        int min = results.getFirst();
        int max = results.getFirst();

        for (int result : results) {
            if (result < min) min = result;
            if (result > max) max = result;
        }

        System.out.printf("Najmniejszy wynik: %d\n", min);
        System.out.printf("Największy wynik: %d\n", max);

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

        for (String line : lines) {
            if (line.isBlank()) return;
            String[] number_strings = line.split("\\s+");
            int a = parseInt(number_strings[0].trim());
            int b = parseInt(number_strings[1].trim());
            h.add(a);
            s.add(b);
        }

        int length = s.size();
        ArrayList<Integer> temp_chain_s = new ArrayList<>();
        ArrayList<Integer> temp_chain_h = new ArrayList<>();
        ArrayList<Integer> result_chain_s = new ArrayList<>();
        ArrayList<Integer> result_chain_h = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                if (s.get(i) <= s.get(i-1) && h.get(i) <= h.get(i-1)) {
                    temp_chain_s.add(s.get(i));
                    temp_chain_h.add(h.get(i));
                } else {
                    if (result_chain_s.size() <= temp_chain_s.size() && result_chain_h.size() <= temp_chain_h.size()) {
                        result_chain_s = temp_chain_s;
                        result_chain_h = temp_chain_h;
                    }
                    temp_chain_s = new ArrayList<>();
                    temp_chain_h = new ArrayList<>();
                    temp_chain_s.add(s.get(i));
                    temp_chain_h.add(h.get(i));
                }
            } else {
                temp_chain_s.add(s.get(i));
                temp_chain_h.add(h.get(i));
            }
        }

        if (!result_chain_s.isEmpty() && !result_chain_h.isEmpty()) {
            System.out.println("Długość najdłuższego ciągu liczb: " + result_chain_h.size());
            System.out.println("Ostatnie s ciągu: " + result_chain_s.getLast());
            System.out.println("Ostatnie h ciągu: " + result_chain_h.getLast());
        } else {
            System.out.println("Nie znaleziono odpowiednich prostokątów.");
        }

    }
    public static void zadanie3() {

        String file_content = read("./src/data/prostokaty.txt");
        String[] lines = file_content.split("\n");
        Map<Integer, ArrayList<Integer>> heights = new HashMap<>();

        for (String line : lines) {

            if (line.isBlank()) return;

            String[] number_strings = line.split("\\s+");

            int h = parseInt(number_strings[0].trim());
            int s = parseInt(number_strings[1].trim());

            if (!heights.containsKey(h))
                heights.put(h, new ArrayList<>());

            heights.get(h).add(s);

        }

        heights.forEach((Integer height, ArrayList<Integer> widths) -> {
            heights.get(height).sort( (a,b) -> { return -a.compareTo(b); } );
        });

        // For 2
        zadanie3_totalFor(2, heights);
        // For 3
        zadanie3_totalFor(3, heights);
        // For 5
        zadanie3_totalFor(5, heights);

    }
    private static void zadanie3_totalFor(int maxindex, Map<Integer, ArrayList<Integer>> heights) {

        AtomicInteger maxwidth = new AtomicInteger();
        heights.forEach((Integer height, ArrayList<Integer> widths) -> {
            AtomicInteger maxwidth_temp = new AtomicInteger();
            for (int i = 0; i < (widths.size() > maxindex ? maxindex : widths.size()); i++)
                maxwidth_temp.addAndGet(widths.get(i));
            if (maxwidth_temp.intValue() > maxwidth.intValue())
                maxwidth.getAndSet(maxwidth_temp.intValue());
        });

        System.out.printf("Maksymalna długość dla 2 prostokątów: %d\n", maxwidth.intValue());

    }
}