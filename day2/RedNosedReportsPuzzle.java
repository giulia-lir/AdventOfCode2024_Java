package day2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RedNosedReportsPuzzle {
    public static void main(String[] args) {
        /**
         * === Part 1 ===
         */
        List<String> file = readFileInList(".\\day2\\test_txt.txt");

        List<List<Integer>> list = file.stream()
            .map(line -> line.trim().split("\\s+"))
            .map(values -> convertStringToListIntegers(values))
            .filter(listInt2 -> isDifferenceBetweenOneAndThree(listInt2))
            .filter(listInt -> isValidList(listInt))
            .collect(Collectors.toList());

        System.out.println(list);
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
        
    private static List<Integer> convertStringToListIntegers(String[] values) {
        return Arrays.stream(values)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }
        
    private static boolean isValidList(List<Integer> listInt) {
        if (isIncreasingOrDecreasingList(listInt)) {
            return true;
        }

        for (int i = 0; i < listInt.size(); i++) {
            List<Integer> listWithRemovedElement = new ArrayList<>(listInt);
            listWithRemovedElement.remove(i);
    
            if (isIncreasingOrDecreasingList(listWithRemovedElement)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isDifferenceBetweenOneAndThree(List<Integer> listInt2) {
        for (int i = 1; i < listInt2.size(); i++) {
            int difference = Math.abs(listInt2.get(i) - listInt2.get(i - 1));
            if (difference < 1 || difference > 3) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIncreasingOrDecreasingList(List<Integer> listInt) {
        boolean increasing = listInt.get(1) > listInt.get(0);
        System.out.println(listInt);
        for (int i = 0; i < (listInt.size() - 1); i++) {
            System.out.println(i);
            if (listInt.get(i) == listInt.get(i + 1)) {
                return false;
            }
            if (increasing && listInt.get(i) > listInt.get(i + 1)) {
                return false;
            }
            if (!increasing && listInt.get(i) < listInt.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
