import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Day2 {
    public static void main(String[] args) throws IOException {
        var values = Files.readAllLines(Path.of("puzzle.txt")).stream()
                .map(e -> new Entry(e.split(" ")[0], Integer.parseInt(e.split(" ")[1])))
                .collect(Collectors.toList());

        var depth = 0;
        var horizontal = 0;

        for (var entry : values) {
            if ("forward".equals(entry.direction)) {
                horizontal += entry.amount;
            } else {
                depth += "down".equals(entry.direction) ? entry.amount : -entry.amount;
            }
        }

        System.out.println("Part one is " + (depth * horizontal));


        depth = 0;
        horizontal = 0;
        var aim = 0;

        for (var entry : values) {
            if ("forward".equals(entry.direction)) {
                horizontal += entry.amount;
                depth += aim  * entry.amount;
            } else {
                aim += "down".equals(entry.direction) ? entry.amount : -entry.amount;
            }
        }

        System.out.println("Part two is " + (depth * horizontal));
    }

    private record Entry(String direction, int amount) {
    }
}
