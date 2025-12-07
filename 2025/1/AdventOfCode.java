
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class AdventOfCode {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File inputFile = new File("input.txt");
        int passwortPartOne = solvePart1(inputFile);
        int passwortPartTwo = solvePart2(inputFile);
        System.out.println("Passwort part 1 = " + passwortPartOne);
        System.out.println("Passwort part 2 = " + passwortPartTwo);
    }

    private static int solvePart1(File inputFile) throws FileNotFoundException, IOException {
        int currentPosition = 50, steps;
        char direction;
        int password = 0;
        // Read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                steps = Integer.parseInt(line.substring(1));
                direction = line.charAt(0);
                // System.out.println("Current Position: " + currentPosition);

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
        }
        return password;
    }

    private static int solvePart2(File inputFile) throws FileNotFoundException, IOException {
        int currentPosition = 50, steps;
        char direction;
        int password = 0;
        // Read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                steps = Integer.parseInt(line.substring(1));
                direction = line.charAt(0);
                // System.out.println("Current Position: " + currentPosition);

                for (int i = 0; i < steps; i++) {
                    switch (direction) {
                        case 'L':
                            currentPosition--;
                            if (currentPosition < 0) currentPosition = 99;
                            break;
                        case 'R':
                            currentPosition++;
                            if (currentPosition == 100) currentPosition = 0;
                            break;
                        default:
                            System.out.println("Invalid direction: " + direction);
                    }
                    if (currentPosition == 0) {
                    password++;
                }
                }
            }
        }
        return password;
    }
}
