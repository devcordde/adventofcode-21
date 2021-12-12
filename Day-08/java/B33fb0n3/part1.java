import java.io.*;

import java.util.*;

 

public class MyClass {

    public static void main(String args[]) {

        int answer = 0;

        Scanner s = new Scanner(System.in);

        s.useDelimiter("\n");

        int ones = 0;

        int sevens = 0;

        int fours = 0;

        int eights = 0;

        while(s.hasNext()){

            String line = s.next();

            String argsAfter = line.split("\\|")[1];

            String[] split = argsAfter.split(" ");

            for(int i = 1; i < split.length; i++) {

                String teil = split[i];

                String[] letters = teil.split("");

                switch(letters.length) {

                    case 2:

                        ones++;

                        break;

                    case 3:

                        sevens++;

                        break;

                    case 4:

                        fours++;

                        break;

                    case 7:

                        eights++;

                        break;

                }

            }

        }
        answer = ones + sevens + fours + eights;
        System.out.println("Answer: " + answer);

    }

}