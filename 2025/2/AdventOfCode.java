
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class AdventOfCode {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        File inputFile = new File("input.txt");
        long summedUpInvalidIdsPartOne = 0;
        long summedUpInvalidIdsPartTwo = 0;

        // Read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                summedUpInvalidIdsPartOne += sumInvalidIDsPartOne(line);
                summedUpInvalidIdsPartTwo += sumInvalidIDsPartTwo(line);
            }
        }

        System.out.println("Summed up invalid IDs - Part One: " + summedUpInvalidIdsPartOne);
        System.out.println("Summed up invalid IDs - Part Two: " + summedUpInvalidIdsPartTwo);
    }

    public static long sumInvalidIDsPartOne(String input) {

        long sum = 0;
        String[] ranges = input.split(",");

        for (String range : ranges) {
            range = range.trim();

            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            for (long i = start; i <= end; i++) {
                if (isInvalidIdPartOne(i)) {
                    sum += i;
                }
            }
        }
        return sum;
    }

    private static boolean isInvalidIdPartOne(long id) {
        
        String s = Long.toString(id);
        int half = s.length() / 2;
        String first = s.substring(0, half);
        String second = s.substring(half);

        return first.equals(second);
    }

    public static long sumInvalidIDsPartTwo(String input) {
        
        long sum = 0;
        String[] ranges = input.split(",");

        for (String range : ranges) {
            range = range.trim();

            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            for (long i = start; i <= end; i++) {
                if (isInvalidIdPartTwo(i)) {
                    sum += i;
                }
            }
        }
        return sum;
    }

    private static boolean isInvalidIdPartTwo(long id) {
        
        String s = Long.toString(id);
        int stringLength = s.length();

        for (int patternLength = 1; patternLength <= stringLength / 2; patternLength++) {
            if (stringLength % patternLength != 0) continue;

            String pattern = s.substring(0, patternLength);
            int repeats = stringLength / patternLength;
            StringBuilder expected = new StringBuilder();

            for (int i = 0; i < repeats; i++) {
                expected.append(pattern);
            }

            if (expected.toString().equals(s)) {
                return true;
            }
        }

        return false;
    }

}
