import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HistorianHysteriaPuzzle {
    public static void main(String[] args) {
        /**
         * === Part 1 ===
         */
        List<String> allValuesList = readFileInList(
            "file_path");

        List<Integer> column1 = getColumn1Values(allValuesList);
        List<Integer> column2 = getColumn2Values(allValuesList);
        
        Collections.sort(column1);
        Collections.sort(column2);

        Integer summedDistance = getSummedDistance(column1, column2);

        System.out.println(summedDistance);

        /**
         * === Part 2 ===
         */
        Integer result = getFrequencySum(column1, column2);

        System.out.println(result);
    }

    private static List<String> readFileInList(String fileName) {
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

    private static List<Integer> getColumn1Values(List<String> allValuesList) {
        return allValuesList.stream()
                .map(line -> line.split("\\s+"))
                .filter(values -> values.length == 2)
                .map(values -> Integer.parseInt(values[0]))
                .collect(Collectors.toList());
    }

    private static List<Integer> getColumn2Values(List<String> allValuesList) {
        return allValuesList.stream()
                .map(line -> line.split("\\s+"))
                .filter(values -> values.length == 2)
                .map(values -> Integer.parseInt(values[1]))
                .collect(Collectors.toList());
    }

    private static Integer getSummedDistance(List<Integer> column1, List<Integer> column2) {
        return IntStream.range(0, column1.size())
                .map(i -> Math.abs(column2.get(i) - column1.get(i)))
                .sum();
    }

    private static Integer getFrequencySum(List<Integer> list1, List<Integer> list2) {
        return list1.stream()
                .map(value -> value * Collections.frequency(list2, value))
                .reduce(0, Integer::sum);
    }
}