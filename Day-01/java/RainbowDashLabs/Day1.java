import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class Day1 {
    public static void main(String[] args) throws IOException {
        var inputs = Files.readAllLines(Path.of("puzzle.txt")).stream().map(Integer::parseInt).collect(Collectors.toList());

        var last = Integer.MAX_VALUE;
        var increased = 0;
        for (var input : inputs) {
            if (last < input) increased++;
            last = input;
        }

        System.out.println("First part " + increased);

        increased = 0;
        var frame = new ArrayDeque<Integer>();
        last = 0;
        for (var input : inputs) {
            last = frame.stream().mapToInt(value -> value).sum();
            frame.add(input);
            if(frame.size() > 3){
                frame.pop();
                if(last < frame.stream().mapToInt(value -> value).sum()) increased++;
            }
        }
        System.out.println("Second part " + increased);
    }
}
