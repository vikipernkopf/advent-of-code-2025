package at.htlleonding._09.MovieTheater;

import at.htlleonding.IUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MovieTheaterUtils implements IUtils<int[][]> {
    @Override
    public int[][] parseInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        int[][] values = new int[lines.size()][2];

        for (String line : lines) {
            String[] parts = line.split(",");

            int row = Integer.parseInt(parts[0]);
            int column = Integer.parseInt(parts[1]);

            values[lines.indexOf(line)][0] = row;
            values[lines.indexOf(line)][1] = column;
        }

        return values;
    }

    @Override
    public long calculatePartOne(int[][] values) {
        long maxArea = 0;

        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                int rowDiff = Math.abs(values[i][0] - values[j][0]);
                int colDiff = Math.abs(values[i][1] - values[j][1]);
                long area = (long) (rowDiff + 1) * (colDiff + 1);

                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }

        return maxArea;
    }

    @Override
    public long calculatePartTwo(int[][] values) {
        return 0;
    }
}
