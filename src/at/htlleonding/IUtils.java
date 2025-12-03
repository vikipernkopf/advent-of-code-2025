package at.htlleonding;

import java.io.IOException;
import java.nio.file.Path;

public interface IUtils<T> {
    T parseInput(Path path) throws IOException;

    long calculatePartOne(T values);

    long calculatePartTwo(T values);
}
