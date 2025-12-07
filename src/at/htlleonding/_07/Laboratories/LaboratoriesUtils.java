package at.htlleonding._07.Laboratories;

import at.htlleonding.IUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LaboratoriesUtils implements IUtils<Field[][]> {
    @Override
    public Field[][] parseInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        Field[][] values = new Field[lines.size()][lines.getFirst().length()];

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);

                switch (c) {
                    case '.' -> values[i][j] = Field.Empty;
                    case 'S' -> values[i][j] = Field.Spawn;
                    case '|' -> values[i][j] = Field.Ray;
                    case '^' -> values[i][j] = Field.Splitter;
                    default -> throw new IllegalArgumentException("Invalid character in input: " + c);
                }
            }
        }

        return values;
    }

    @Override
    public long calculatePartOne(Field[][] values) {
        long count = 0;

        for (int i = 0; i < values.length; i++) { //always calculating for next row
            for (int j = 0; j < values[i].length; j++) {
                Field current = values[i][j];

                if (current == Field.Ray || current == Field.Spawn) {
                    if (i + 1 < values.length) {
                        if (values[i + 1][j] == Field.Empty) {
                            values[i + 1][j] = Field.Ray;
                        } else if (values[i + 1][j] == Field.Splitter) {
                            if (j - 1 >= 0 && values[i + 1][j - 1] == Field.Empty) {
                                values[i + 1][j - 1] = Field.Ray;
                            }

                            if (j + 1 < values[i].length && values[i + 1][j + 1] == Field.Empty) {
                                values[i + 1][j + 1] = Field.Ray;
                            }

                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    @Override
    public long calculatePartTwo(Field[][] values) {
        int rows = values.length;
        int cols = values[0].length;

        long[][] routesPerField = new long[rows][cols];

        for (int i = 0; i < cols; i++) {
            routesPerField[rows - 1][i] = 1;
        }

        for (int i = rows - 2; i >= 0; i--) { //start counting from the bottom
            for (int j = 0; j < cols; j++) {
                Field current = values[i][j];

                if (current == Field.Splitter) {
                    long left = (j - 1 >= 0) ? routesPerField[i + 1][j - 1] : 0;
                    long right = (j + 1 < cols) ? routesPerField[i + 1][j + 1] : 0;

                    routesPerField[i][j] = left + right;
                } else if (current == Field.Empty || current == Field.Ray || current == Field.Spawn) {
                    routesPerField[i][j] = routesPerField[i + 1][j];
                } else {
                    routesPerField[i][j] = 0;
                }
            }
        }

        long sum = 0;

        for (int i = 0; i < cols; i++) {
            if (values[0][i] == Field.Spawn) {
                sum += routesPerField[1][i];
            }
        }

        return sum;
    }

    /*private int calculatePossibleRoutes(Field[][] values, int row, int col, int sum) {
        if (row < 0 || row >= values.length || col < 0 || col >= values[0].length) {
            return sum;
        }

        Field current = values[row][col];

        if (current == Field.Splitter) {
            sum = calculatePossibleRoutes(values, row + 1, col - 1, sum);
            sum = calculatePossibleRoutes(values, row + 1, col + 1, sum);
        } else if (current == Field.Empty || current == Field.Ray) {
            sum = calculatePossibleRoutes(values, row + 1, col, sum);
        }

        if (row == values.length - 1) {
            sum++;
        }

        return sum;
    }*/
}
