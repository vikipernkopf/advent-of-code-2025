package at.htlleonding._01.SecretEntrance;

import at.htlleonding.MainBaseClass;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SecretEntranceMain extends MainBaseClass {
    public final int day;

    public SecretEntranceMain() {
        super();
        this.day = getDay();
    }

    @Override
    public void runPartOne(Path path) {
        List<ValuePair> values;

        try {
            values = SecretEntranceUtils.parseInput(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int count = SecretEntranceUtils.calculatePointingZero(50, values);

        System.out.println(count);
    }

    @Override
    public void runPartTwo(Path path) {
        List<ValuePair> values;

        try {
            values = SecretEntranceUtils.parseInput(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int count = SecretEntranceUtils.calculateAnyZeros(50, values);

        System.out.println(count);
    }
}
