import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public static List<String> movement = new ArrayList<>();

    public static void main(String[] args) {
        getMovement();

        //Part1
        int positionForward = 0;
        int positionDepth = 0;

        for (String move : movement) {
            String[] splitMove = move.split(" ");
            switch (splitMove[0].toLowerCase()){
                case "forward" -> positionForward += Integer.parseInt(splitMove[1]);
                case "down" -> positionDepth += Integer.parseInt(splitMove[1]);
                case "up" -> positionDepth -= Integer.parseInt(splitMove[1]);
            }
        }

        System.out.println("The submarine is at position " + positionForward * positionDepth);

        //Part2
        int aim = 0;
        positionForward = 0;
        positionDepth = 0;
        for (String move : movement){
            String[] splitMove = move.split(" ");
            int i = Integer.parseInt(splitMove[1]);

            if(splitMove[0].equalsIgnoreCase("down")) aim += i;
            if (splitMove[0].equalsIgnoreCase("up")) aim -= i;
            if(splitMove[0].equalsIgnoreCase("forward")){
              positionForward += i;
              positionDepth += i * aim;
            }
        }

        System.out.println("The submarine is at position " + positionForward * positionDepth);
    }

    public static void getMovement(){
        try {
            movement.addAll(Files.readAllLines(Path.of("src/main/resources/movement.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
