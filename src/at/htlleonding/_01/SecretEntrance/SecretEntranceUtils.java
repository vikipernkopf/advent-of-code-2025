package at.htlleonding._01.SecretEntrance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class SecretEntranceUtils {
    public static List<ValuePair> parseInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        List<ValuePair> values = new ArrayList<>();

        for (String line : lines) {
            char firstChar = line.charAt(0);
            boolean direction = firstChar == 'L';
            ValuePair value = new ValuePair(direction, Integer.parseInt(line.substring(1)));
            values.add(value);
        }

        return values;
    }

    public static int calculatePointingZero(int start, List<ValuePair> values) {
        int current = start;
        int count = 0;

        for (ValuePair value : values) {
            if (value.direction()) { //left is true
                current = (current - value.number()) % 100;
            } else {
                current = (current + value.number()) % 100;
            }

            if (current == 0) {
                count++;
            }
        }

        return count;
    }

    public static int calculateAnyZeros(int start, List<ValuePair> values) {
        int current = start;
        int count = 0;

        for (ValuePair value : values) {
            int distance = value.number();

            if (value.direction()) { //left
                for (int i = 0; i < distance; i++) {
                    current = (current + 99) % 100;

                    if (current == 0) {
                        count++;
                    }
                }
            } else { //right
                for (int i = 0; i < distance; i++) {
                    current = (current + 1) % 100;

                    if (current == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
