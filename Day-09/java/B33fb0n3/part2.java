import java.io.*;

import java.util.*;

 

public class MyClass {

    private static List<String> alreadyHad = new ArrayList<>();

    private static int singleBasin = 0;

    private static int finalAnswer = 0;

   

    public static void main(String args[]) {

        List<String> lines = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);


        while (scanner.hasNext()) {

            lines.add(scanner.next());

        }


        scanner.close();

        int sum = 0;

        List<Integer> basins = new ArrayList<>();

        for(int i = 0; i < lines.size(); i++) {

            int length = lines.get(i).length();

            for(int letter = 0; letter < length; letter++) {

                int[] arounds = getAround(i, letter, lines, length);

                if(arounds[2] < arounds[0] && arounds[2] < arounds[4] && arounds[2] < arounds[1] && arounds[2] < arounds[3]) {

                    sum += arounds[2] + 1;

                    findNext(i, letter, lines, length, arounds);

                    basins.add(singleBasin);

                    singleBasin = 0;

                }

            }

        }

        setFinalAnswer(basins);

        System.out.println("Answer: " + finalAnswer);

    }

   

    private static void setFinalAnswer(List<Integer> basins) {

        Collections.sort(basins);

        List<Integer> top3 = new ArrayList<Integer>(basins.subList(basins.size() -3, basins.size()));

        finalAnswer = top3.get(0) * top3.get(1) * top3.get(2);

    }

   

    private static int findNext(int line, int letterPosition, List<String> lines, int length, int[] arounds) {

        if(alreadyHad.contains(line+";"+letterPosition))

            return -1;

        singleBasin++;

        int[] newArounds = getAround(line, letterPosition, lines, length);

        for(int side = 0; side < arounds.length; side++) {

            if(side == 2)

                continue;

            if(newArounds[side] != 9) {

                alreadyHad.add(line + ";" + letterPosition);

                switch(side) {

                    case 0:

                        if(line-1 > 0)

                            findNext(line-1, letterPosition, lines, length, newArounds);

                        break;

                    case 1:

                        alreadyHad.add(line + ";" + letterPosition);

                        if(letterPosition-1 >= 0)

                            findNext(line, letterPosition-1, lines, length, newArounds);

                        break;

                    case 3:

                        alreadyHad.add(line + ";" + letterPosition);

                        if(letterPosition+1 < length)

                            findNext(line, letterPosition+1, lines, length, newArounds);

                        break;

                    case 4:

                        alreadyHad.add(line + ";" + letterPosition);

                        if(line+1 < lines.size())

                            findNext(line+1, letterPosition, lines, length, newArounds);

                        break;

                    default:

                        break;

                }

            } else

                continue;

        }

        return singleBasin;

    }

   

    private static int[] getAround(int line, int letterPosition, List<String> lines, int length) {

        /*

            [0] = Above

            [1] = Left

            [2] = Center

            [3] = Right

            [4] = Under

        */

        int[] returner = {-1,-1,-1,-1,-1};

           

        String upperLine = "";

        String downLine = "";

        for(int l = 0; l < length; l++) {

            upperLine += "9";

            downLine += "9";

        }

               

        if(line > 0)

            upperLine = lines.get(line-1);

               

        String middleLine = lines.get(line);

           

        if(line+1 < lines.size())

            downLine = lines.get(line+1);

       

        String[] upperLineLetters = upperLine.split("");

        String[] middleLineLetters = middleLine.split("");

        String[] downLineLetters = downLine.split("");

       

        returner[2] = Integer.parseInt(middleLineLetters[letterPosition]);

 

        returner[0] = Integer.parseInt(upperLineLetters[letterPosition]);

        returner[4] = Integer.parseInt(downLineLetters[letterPosition]);

               

        returner[1] = 9;

        if(letterPosition != 0)

            returner[1] = Integer.parseInt(middleLineLetters[letterPosition-1]);

                   

        returner[3] = 9;

        if(letterPosition+1 < length)

            returner[3] =Integer.parseInt( middleLineLetters[letterPosition+1]);

        return returner;

    }

}