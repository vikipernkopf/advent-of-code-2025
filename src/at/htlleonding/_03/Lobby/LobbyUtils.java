package at.htlleonding._03.Lobby;

import at.htlleonding.IUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LobbyUtils implements IUtils<int[][]> {
    @Override
    public int[][] parseInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);

        int[][] values = new int[lines.size()][];

        for (int i = 0; i < lines.size(); i++) {
            values[i] = new int[lines.get(i).length()];

            for (int j = 0; j < lines.get(i).length(); j++) {
                values[i][j] = lines.get(i).charAt(j) - '0';
            }
        }

        return values;
    }

    @Override
    public long calculatePartOne(int[][] values) {
        int totalSum = 0;

        for (int[] array : values) {
            int highestIndex = getMaxIndex(array);
            int rightSum = 0;
            int leftSum = 0;

            if (highestIndex < array.length - 1) {
                int[] rightPart = Arrays.copyOfRange(array, highestIndex + 1, array.length);
                int highestRightIndex = getMaxIndex(rightPart);
                rightSum = array[highestIndex] * 10 + rightPart[highestRightIndex];
            }

            if (highestIndex > 0) {
                int[] leftPart = Arrays.copyOfRange(array, 0, highestIndex);
                int highestLeftIndex = getMaxIndex(leftPart);
                leftSum = leftPart[highestLeftIndex] * 10 + array[highestIndex];
            }

            totalSum += Math.max(rightSum, leftSum);
        }

        return totalSum;
    }

    @Override
    public long calculatePartTwo(int[][] values) {
        long sum = 0;

        for (int[] array : values) {
            sum += findMaxSum(array);
        }

        return sum;
    }

    private static long findMaxSum(int[] array) {
        Stack<Integer> result = new Stack<>();

        for (int i = 0; i < array.length; i++) {
            int current = array[i];
            int remainingPossibleDigits = array.length - i;

            while (!result.isEmpty() && current > result.peek() && remainingPossibleDigits >= 12 - result.size() + 1) {
                result.pop();
            }

            if (result.size() < 12) {
                result.push(current);
            }
        }

        return sumWeightedDigits(result);
    }

    private static long sumWeightedDigits(Stack<Integer> stack) {
        long sum = 0;

        for (Integer digit : stack) {
            sum = sum * 10 + digit;
        }

        return sum;
    }

    private static int getMaxIndex(int[] array) {
        int highestIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[highestIndex]) {
                highestIndex = i;
            }
        }

        return highestIndex;
    }
}
