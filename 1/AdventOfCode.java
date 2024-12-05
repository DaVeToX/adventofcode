import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class AdventOfCode {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // read input from input.txt 
        File inputFile = new File("input.txt");
        List<Integer> leftNumbers = new ArrayList<>();
        List<Integer> rightNumbers = new ArrayList<>();
        int distanceSum = 0;
        
        // Read the file 
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into two numbers
                String[] numbers = line.trim().split("\\s+");
                leftNumbers.add(Integer.parseInt(numbers[0]));
                rightNumbers.add(Integer.parseInt(numbers[1]));
            }
        }

        // sort both arrays
        leftNumbers = sortArray(leftNumbers);
        rightNumbers = sortArray(rightNumbers);

        int tempDistance;
        for (int i = 0; i < leftNumbers.size(); i++) {
            tempDistance = leftNumbers.get(i) - rightNumbers.get(i);
            // make distance positive if needed
            if (tempDistance < 0) {
                tempDistance = tempDistance * -1;
            }
            distanceSum += tempDistance;
        }
        System.out.println("Distance sum: " + distanceSum);
    }

    public static List<Integer> sortArray(List<Integer> numbers) {
        // buublesort
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size() - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, temp);
                }
            }
        }
        return numbers;
    }
}
