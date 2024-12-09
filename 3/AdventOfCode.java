import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AdventOfCode {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File inputFile = new File("input.txt");
        String input = "", regex = "mul\\s*\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*\\)"; // regex to get all x and y values
        int sum = 0;
        Matcher matcher;
        
        // Read the file input
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                input = line;
            }
        }

        System.out.println("Input: " + input);
        
        // Find all matches in the input and add them to the list for x and y
        Pattern pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input.toString());

        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            System.out.println("x: " + x + " y: " + y + " mul: " + mul(x, y));
            sum += mul(x, y);
        }

        System.out.println("Sum: " + sum);
    }

    public static int mul(int x, int y) {
        return x * y;
    }
}
