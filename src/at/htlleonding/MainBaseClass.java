package at.htlleonding;

import java.io.IOException;
import java.nio.file.Path;

public abstract class MainBaseClass<T> {
    public final int day;
    public final IUtils<T> utils;

    protected MainBaseClass() {
        Package pkg = this.getClass().getPackage();

        if (pkg != null) {
            String pkgName = pkg.getName();
            int start = pkgName.indexOf("0");

            this.day = Integer.parseInt(pkgName.substring(start, start + 2));
        } else {
            this.day = -1;
        }

        this.utils = getUtils();
    }

    public void run(Path path) {
        T values;

        try {
            values = utils.parseInput(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        runPartOne(values);
        runPartTwo(values);
    }

    public void runPartOne(T values) {
        System.out.println(utils.calculatePartOne(values));
    }

    public void runPartTwo(T values) {
        System.out.println(utils.calculatePartTwo(values));
    }

    public void runTest() {
        run(Path.of("data/" + String.format("%02d", day) + "/test.in"));
    }

    public void runInput() {
        run(Path.of("data/" + String.format("%02d", day) + "/input.in"));
    }

    protected IUtils<T> getUtils() {
        return null;
    }
}
