import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3 {
    private static int toNum(String str, int index){
        return Character.getNumericValue(str.charAt(index));
    }

    private static int commonBit(List<String> val, int index, Method method) {
        var zeroCommon = val.stream().mapToInt(e -> toNum(e, index)).sum() < val.size() / 2.0;
        return method == Method.MOST ? zeroCommon ? 0 : 1 : zeroCommon ? 1 : 0;
    }

    private static String calcBits(List<String> val, Method method) {
        return IntStream.range(0, val.get(0).length())
                .mapToObj(i -> String.valueOf(commonBit(val, i, method)))
                .collect(Collectors.joining());
    }

    private static String bitFilter(List<String> val, int index, Method method) {
        if (val.size() <= 1) return val.get(0);
        var bit = commonBit(val, index, method);
        return bitFilter(val.stream().filter(e -> toNum(e, index) == bit).collect(Collectors.toList()), index + 1, method);
    }

    public static void main(String[] args) throws IOException {
        var input = Files.readAllLines(Path.of("puzzle.txt"));

        System.out.printf("Part one %s%n", Integer.parseInt(calcBits(input, Method.MOST), 2) * Integer.parseInt(calcBits(input, Method.LEAST), 2));

        System.out.printf("Part two %s%n", Integer.parseInt(bitFilter(input, 0, Method.MOST), 2) * Integer.parseInt(bitFilter(input, 0, Method.LEAST), 2));
    }

    private enum Method {
        LEAST, MOST
    }
}
