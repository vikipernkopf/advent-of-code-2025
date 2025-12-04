package at.htlleonding._04.PrintingDepartment;

import at.htlleonding.IUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PrintingDepartmentUtils implements IUtils<boolean[][]> {
    @Override
    public boolean[][] parseInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        boolean[][] values = new boolean[lines.size()][];

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            values[i] = new boolean[line.length()];

            for (int j = 0; j < line.length(); j++) {
                values[i][j] = line.charAt(j) == '@';
            }
        }

        return values;
    }

    @Override
    public long calculatePartOne(boolean[][] values) {
        int count = 0;

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                if (values[i][j] && getNumberOfNeighbors(values, i, j) < 4) {
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    public long calculatePartTwo(boolean[][] values) {
        int count = 0;
        int countRemoved = 0;

        do {
            countRemoved = 0;
            boolean[][] valuesCopy = values;

            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < values[i].length; j++) {
                    if (values[i][j] && getNumberOfNeighbors(values, i, j) < 4) {
                        countRemoved++;
                        count++;

                        valuesCopy[i][j] = false;
                    }
                }
            }
        } while (countRemoved != 0);

        return count;
    }

    private int getNumberOfNeighbors(boolean[][] values, int x, int y) { //x is row, y is column
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                if (isIndexValid(x + i, values, true) && isIndexValid(y + j, values, false)) {
                    count += values[x + i][y + j] ? 1 : 0;
                }
            }
        }

        return count;
    }

    private boolean isIndexValid(int index, boolean[][] values, boolean isX) { //x is row, y is column
        if (isX) {
            return index >= 0 && index < values.length;
        } else {
            return index >= 0 && index < values[0].length;
        }
    }
}
