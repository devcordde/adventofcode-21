import java.util.*;
import java.io.*;

public class MyClass {
    private static ArrayList<String> fishes;

    public static void main(String args[]) {
        int day = 1;
        fishes = new ArrayList<>(Arrays.asList(args));
        for(int i = 0; i < 256; i++) { // bzw. hier 80 für Part 1
            removeOne();
            day++;
        }
        System.out.println("After "+(day-1)+" day: " + fishes.size());
    }

    private static void removeOne() {
        for(int i = 0; i < fishes.size(); i++) {
            int fishI = Integer.parseInt(fishes.get(i));
            int newTime = fishI - 1;
            if(newTime < 0) {
                fishes.set(i, String.valueOf(6));
                fishes.add(String.valueOf(9));
                continue;
            }
            fishes.set(i, String.valueOf(newTime));
        }
    }
}