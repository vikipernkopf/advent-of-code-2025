package at.htlleonding;

import java.nio.file.Path;

public abstract class MainBaseClass {
    public final int day;

    protected MainBaseClass() {
        Package pkg = this.getClass().getPackage();

        if (pkg != null) {
            String pkgName = pkg.getName();
            int start = pkgName.indexOf("0");

            this.day = Integer.parseInt(pkgName.substring(start, start + 2));
        } else {
            this.day = -1;
        }
    }

    protected int getDay() {
        return day;
    }

    public void run(Path path) {
        runPartOne(path);
        runPartTwo(path);
    }

    public void runPartOne(Path path) {

    }

    public void runPartTwo(Path path) {

    }

    public void runTest() {
        run(Path.of("data/" + String.format("%02d", day) + "/test.in"));
    }

    public void runInput() {
        run(Path.of("data/" + String.format("%02d", day) + "/input.in"));
    }
}


