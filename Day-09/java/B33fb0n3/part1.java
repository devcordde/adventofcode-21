import java.io.*;

import java.util.*;

 

public class MyClass {

    public static void main(String args[]) {

        List<String> lines = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);


        while (scanner.hasNext()) {

            lines.add(scanner.next());

        }


        scanner.close();

        int sum = 0;

        for(int i = 0; i < lines.size(); i++) {

            int length = lines.get(i).length();

           

            String upperLine = "";

            String downLine = "";

            for(int l = 0; l < length; l++) {

                upperLine += "9";

                downLine += "9";

            }

               

            if(i > 0)

                upperLine = lines.get(i-1);

               

            String middleLine = lines.get(i);

           

            if(i+1 < lines.size())

                downLine = lines.get(i+1);

           

            String[] upperLineLetters = upperLine.split("");

            String[] middleLineLetters = middleLine.split("");

            String[] downLineLetters = downLine.split("");

           

            //System.out.println("U: "+ upperLine +"\nM: "+ middleLine +"\nD: "+ downLine +"");

           

            for(int letter = 0; letter < length; letter++) {

                int center = Integer.parseInt(middleLineLetters[letter]);

 

                int above = Integer.parseInt(upperLineLetters[letter]);

                int under = Integer.parseInt(downLineLetters[letter]);

               

                int left = 9;

                if(letter != 0)

                    left = Integer.parseInt(middleLineLetters[letter-1]);

                   

                int right = 9;

                if(letter+1 < length)

                    right =Integer.parseInt( middleLineLetters[letter+1]);

               

                //System.out.println("XXXX A: "+above + " XXXX");

                //System.out.println("L: "+left+" C: "+center+" R: "+right);

                //System.out.println("XXXX U: "+under + " XXXX");

               

                //System.out.println("C: "+center + " < A: " + above);

                //System.out.println("C: "+center + " < U: " + under);

                //System.out.println("C: "+center + " < L: " + left);

                //System.out.println("C: "+center + " < R: " + right);

                if(center < above && center < under && center < left && center < right) {

                    sum += center + 1;

                    //System.out.println("XXX COUNT XXX");

                }

            }

        }

        System.out.println("Answer: " + sum);

    }

}