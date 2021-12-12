import java.io.*;

import java.util.*;

 

public class MyClass {

    public static void main(String args[]) {

        List<Integer> fuel = new ArrayList<>();

        int maxValue = -1;

        for(String arg : args[0].split(",")) {

            int value = Integer.parseInt(arg);

            if(maxValue < value)
                maxValue = value;

            fuel.add(value);

        }

        int minLostFuel = Integer.MAX_VALUE;

        for(int i = 1; i < maxValue; i++) {

            int positionLostFuel = 0;

            for(int f = 0; f < fuel.size(); f++) {
                int minus = fuel.get(f) - i;

                if(minus < 0)
                    minus = minus / -1;

                int currentCosts = 1;
                int fuelCosts = 0;
                for(int step = 0; step < minus; step++) {
                    fuelCosts += currentCosts;
                    currentCosts++;
                }

                positionLostFuel += fuelCosts;

            }

            if(positionLostFuel < minLostFuel)
                minLostFuel = positionLostFuel;

        }

        System.out.println(minLostFuel + " | Answer");

    }

}