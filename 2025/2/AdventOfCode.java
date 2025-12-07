import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class AdventOfCode {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        File inputFile = new File("testInput.txt");

        // Read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ranges = line.split(",");
                for (String range : ranges) {
                    String[] ids = range.split("-");
                    int firstId = Integer.parseInt(ids[0].trim());
                    int lastId = Integer.parseInt(ids[1].trim());
                    System.out.println("firstId: " + firstId + ", lastId: " + lastId);

                    for (int i = firstId; i <= lastId; i++) {
                        System.out.println("Now at ID: " + i);
                    }
                }
            }
        }
    }
}
