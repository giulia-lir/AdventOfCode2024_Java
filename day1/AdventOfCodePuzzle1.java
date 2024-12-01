import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdventOfCodePuzzle1 {
    public static void main(String[] args) {
        List<String> allValuesList = readFileInList(
            "file_path");

        List<Integer> column1 = allValuesList.stream()
                .map(line -> line.split("\\s+"))
                .filter(values -> values.length == 2)
                .map(values -> Integer.parseInt(values[0]))
                .collect(Collectors.toList());

        List<Integer> column2 = allValuesList.stream()
                .map(line -> line.split("\\s+"))
                .filter(values -> values.length == 2)
                .map(values -> Integer.parseInt(values[1]))
                .collect(Collectors.toList());
        
        Collections.sort(column1);
        Collections.sort(column2);

        Integer summedDistance = IntStream.range(0, column1.size())
                .map(i -> Math.abs(column2.get(i) - column1.get(i)))
                .sum();

        System.out.println(summedDistance);
    }
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(
            Paths.get(fileName),
            StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}