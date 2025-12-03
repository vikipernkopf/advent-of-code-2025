package at.htlleonding._03.Lobby;

import at.htlleonding.MainBaseClass;

import java.io.IOException;
import java.nio.file.Path;

public class LobbyMain extends MainBaseClass {
    public final int day;

    public LobbyMain() {
        this.day = getDay();
    }

    @Override
    public void runPartOne(Path path) {
        int[][] values;

        try {
            values = LobbyUtils.parseInput(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int sum = LobbyUtils.calculateMaxVoltage(values);

        System.out.println(sum);
    }

    @Override
    public void runPartTwo(Path path) {
        int[][] values;

        try {
            values = LobbyUtils.parseInput(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long sum = LobbyUtils.calculateMaxVoltageFor12(values);

        System.out.println(sum);
    }
}
