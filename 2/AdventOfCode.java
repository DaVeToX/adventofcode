import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class AdventOfCode {

    public static int safeReportCounterWithoutDampener = 0; // 1st version
    public static int safeReportCounterWithDampener = 0; // 2nd version
    public static int unsafeReportCounter = 0;
    public static int amountReports = 0;
    public static List<List<Integer>> safeReports = new ArrayList<>();
    public static List<List<Integer>> unsafeReports = new ArrayList<>();
    public static enum LevelDirection {
        INCREASING,
        DECREASING
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File inputFile = new File("input.txt");
        List<List<Integer>> reports = new ArrayList<>();

        // Read the file input
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Integer> report = new ArrayList<>();
                // get numbers (aka levels) from current line (aka report)
                String[] levels = line.split(" ");
                // cast from number to integer and add to report
                for (String level : levels) {
                    report.add(Integer.parseInt(level));
                }
                reports.add(report);
            }
        }

        amountReports = reports.size();

        // Calculate amount of safe reports without dampener (1st version)
        // safeReportCounterWithoutDampener = amountReports - calculateAmountOfUnsafeReportsWithoutDampener(reports);
        // Calculate amount of safe reports with dampener (2nd version)
        safeReportCounterWithDampener = amountReports - calculateAmountOfUnsafeReportsWithDampener(reports);
        
        System.out.println("Amount ALL reports: " + amountReports);
        System.out.println("Amount SAFE reports WITHOUT dampener: " + safeReportCounterWithoutDampener);
        System.out.println("Amount SAFE reports WITH dampener: " + safeReportCounterWithDampener);
        for (List<Integer> report : safeReports) {
            System.out.println("safeReports report: " + report);
        }
    }

    public static int calculateAmountOfUnsafeReportsWithDampener(List<List<Integer>> reports) {
        int counter = 0;
        // in 1st loop through reports, check if the levels per report are steadily increasing or decreasing
        outerLoop:
        for(List<Integer> report : reports) {
            LevelDirection levelDirection = null;
            boolean isLevelRemoved = false;
            for(int i = 0; i < report.size() - 1; i++) {
                // System.out.println("Report " + report + " comparing " + report.get(i) + " with " + report.get(i+1));
                // in 1st comparison, determine if level is increasing or decreasing
                // and check, if level change is within limits
                if (i == 0) {
                    // determine initial level direction
                    if (report.get(i) < report.get(i+1)) levelDirection = LevelDirection.INCREASING;
                    else if (report.get(i) > report.get(i+1))levelDirection = LevelDirection.DECREASING;
                    else 
                    // check if level change is within limits
                    if (!isLevelDirectionChangingWithinLimit(report.get(i), report.get(i+1))) {
                        // System.out.println("Report " + report + " is not safe, because level change is not within limits");
                        if (!isLevelRemoved) {
                            // remove 1st bad level from report & start again for this report
                            report.remove(i);
                            isLevelRemoved = true;
                            i = -1;
                            continue;
                        } else {
                            // already removed a single bad lebel from report => report is unsafe
                            counter++;
                            unsafeReports.add(report);
                            isLevelRemoved = false;
                            continue outerLoop;
                        }
                    }
                }
                // in every other comparison, check if the leveldirection is still the same
                // and check if level change is within limits
                else {
                    if(!(isLevelDirectionSteady(levelDirection, report.get(i), report.get(i+1)) && (isLevelDirectionChangingWithinLimit(report.get(i), report.get(i+1))))) {
                        // System.out.println("Report " + report + "  is not safe, because level direction is not steady or level change is not within limits");
                        if (!isLevelRemoved) {
                            // remove 1st bad level from report & start again for this report
                            report.remove(i);
                            isLevelRemoved = true;
                            i = -1;
                            continue;
                        } else {
                            // already removed a single bad lebel from report => report is unsafe
                            counter++;
                            unsafeReports.add(report);
                            // System.out.println("Current amount of unsafe reports: " + counter);
                            isLevelRemoved = false;
                            continue outerLoop;
                        }
                    }
                }
            }
            // if report is safe, add to safeReports
            safeReports.add(report);
        }
        return counter;
    }

    public static int calculateAmountOfUnsafeReportsWithoutDampener(List<List<Integer>> reports) {
        int counter = 0;
        // in 1st loop through reports, check if the levels per report are steadily increasing or decreasing
        for(List<Integer> report : reports) {
            LevelDirection levelDirection = null;
            for(int i = 0; i < report.size() - 1; i++) {
                // in 1st comparison, determine if level is increasing or decreasing
                // and check, if level change is within limits
                if (i == 0) {
                    // determine initial level direction
                    if (report.get(i) < report.get(i+1)) levelDirection = LevelDirection.INCREASING;
                    else if (report.get(i) > report.get(i+1))levelDirection = LevelDirection.DECREASING;
                    // check if level change is within limits
                    if (!isLevelDirectionChangingWithinLimit(report.get(i), report.get(i+1))) {
                        // System.out.println("Report " + report + " is not safe, because level change is not within limits");
                        counter++;
                        break;
                    }
                }
                // in every other comparison, check if the leveldirection is still the same
                // and check if level change is within limits
                else {
                    if(!(isLevelDirectionSteady(levelDirection, report.get(i), report.get(i+1)) && (isLevelDirectionChangingWithinLimit(report.get(i), report.get(i+1))))) {
                        // System.out.println("Report " + report + "  is not safe, because level direction is not steady or level change is not within limits");
                        counter++;
                        break;
                    }
                }
            }
        }
        return counter;
    }

    public static boolean isLevelDirectionSteady(LevelDirection levelDirection, int currentLevel, int nextLevel) {
        if ((currentLevel < nextLevel) && (levelDirection == LevelDirection.INCREASING)) return true;
        if ((currentLevel > nextLevel) && (levelDirection == LevelDirection.DECREASING)) return true;
        return false;
    }

    public static boolean isLevelDirectionChangingWithinLimit(int currentLevel, int nextLevel) {
        int diff = currentLevel - nextLevel;
        // make diff positive
        if (diff < 1) diff = diff * -1;
        // if diff is between 1 and 3, the report is safe => return true
        if (diff >= 1 && diff <= 3) return true;
        return false;
    }
}
