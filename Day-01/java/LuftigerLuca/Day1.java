import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    static List<Integer> measurements = new ArrayList<>();

    public static void main(String[] args) {
        int increasedMeasurements = 0;
        getMeasurements();

        //Part1
        for (int i = 0; i < measurements.size(); i++) {
            if(i != 0){
                if(measurements.get(i) > measurements.get(i-1)) increasedMeasurements++;
            }
        }
        System.out.println("There are " + increasedMeasurements + " measurements deeper than the previous one");

        //Part2
        int window1 = 0;
        int window2 = -1;
        increasedMeasurements = 0;

        for (int i = 0; i < measurements.size(); i++) {
            if(i+2 >= measurements.size()) break;
            window1 = measurements.get(i) + measurements.get(i+1) + measurements.get(i+2);
            if(window1 > window2 && window2 != -1) increasedMeasurements++;

            window2 = window1;
            window1 = 0;

        }

        System.out.println("There are " + increasedMeasurements + " windows deeper than the previous window");
    }

    private static void getMeasurements(){
        try {
            for (String line : Files.readAllLines(Path.of("shared/LuftigerLuca/inputs/day-01.txt"))) {
                measurements.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
