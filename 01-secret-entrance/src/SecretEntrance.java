import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class SecretEntrance {
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
}
