
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class AdventOfCode {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // read input from input.txt 
        File inputFile = new File("input.txt");
        int currentPosition = 50, tempPosition = 50, password = 0, steps;
        char direction;

        // Read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                steps = Integer.parseInt(line.substring(1));
                direction = line.charAt(0);
                System.out.println("Current Position: " + currentPosition);

                switch (direction) {
                    case 'L':
                        currentPosition = (currentPosition - steps) % 100;
                        if (currentPosition < 0) {
                            currentPosition += 100;
                        }
                        break;
                    case 'R':
                        currentPosition = (currentPosition + steps) % 100;
                        break;
                    default:
                        System.out.println("Invalid direction: " + direction);
                }
                if (currentPosition == 0) {
                    password++;
                }
            }
            System.out.println("Passwort = " + password);
        }
    }
}
