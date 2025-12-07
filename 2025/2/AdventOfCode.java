
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class AdventOfCode {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        File inputFile = new File("input.txt");
        long summedUpInvalidIds = 0;

        // Read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                summedUpInvalidIds += sumInvalidIDs(line);
            }
        }

        System.out.println("Summed up invalid IDs: " + summedUpInvalidIds);
    }

    public static long sumInvalidIDs(String input) {

        long sum = 0;
        String[] ranges = input.split(",");

        for (String range : ranges) {
            range = range.trim();

            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            for (long i = start; i <= end; i++) {
                if (isInvalidID(i)) {
                    sum += i;
                }
            }
        }
        return sum;
    }

    private static boolean isInvalidID(long id) {
        String s = Long.toString(id);

        int half = s.length() / 2;
        String first = s.substring(0, half);
        String second = s.substring(half);

        return first.equals(second);
    }

}
