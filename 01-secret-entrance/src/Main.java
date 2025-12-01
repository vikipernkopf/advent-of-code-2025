import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ValuePair> values;

        try {
            //values = SecretEntrance.parseInput(Path.of("data/test.in"));
            values = SecretEntrance.parseInput(Path.of("data/input.in"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int count = SecretEntrance.calculatePointingZero(50, values);

        System.out.println(count);
    }
}
