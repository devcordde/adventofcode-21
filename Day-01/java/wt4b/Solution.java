package wt4b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - WT4B
 * @GitHub - https://github.com/WT4B
 * @Twitter - https://twitter.com/WT4B_
 * Erstellt - 01.12.2021 12:04
 */
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("Day-01.txt"));
        List<Integer> numbers = new ArrayList<>();
        String number;
        while ((number = bufferedReader.readLine()) != null) numbers.add(Integer.parseInt(number));
        bufferedReader.close();

        int previousMeasurement;
        int currentMeasurement;
        int increasements = 0;

        // First part
        for (int i = 0; i < numbers.size(); i++) {
            if (i - 1 < 0) continue;
            previousMeasurement = numbers.get(i - 1);
            currentMeasurement = numbers.get(i);
            if (currentMeasurement > previousMeasurement) increasements++;
        }
        System.out.println("There are " + increasements + " measurements that are larger than the previous measurement.");

        // Second part
        int sum;
        int lastSum = -1;
        int firstMeasurement;
        int secondMeasurement;
        int thirdMeasurement;
        increasements = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (i + 2 >= numbers.size()) break;
            firstMeasurement = numbers.get(i);
            secondMeasurement = numbers.get(i + 1);
            thirdMeasurement = numbers.get(i + 2);
            sum = firstMeasurement + secondMeasurement + thirdMeasurement;
            if (sum > lastSum && lastSum != -1) increasements++;
            lastSum = sum;
        }
        System.out.println("There are " + increasements + " sums that are larger than the previous sum.");
    }
}
