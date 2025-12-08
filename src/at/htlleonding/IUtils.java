package at.htlleonding;

import java.io.IOException;
import java.nio.file.Path;

public interface IUtils<T> {
    T parseInput(Path path) throws IOException;

    long calculatePartOne(T values);

    default long calculatePartOne(T values, Long extraParam) {
        return calculatePartOne(values);
    }

    long calculatePartTwo(T values);

    default long calculatePartTwo(T values, Long extraParam) {
        return calculatePartTwo(values);
    }
}
