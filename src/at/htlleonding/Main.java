package at.htlleonding;

import at.htlleonding._01.SecretEntrance.SecretEntranceMain;

import java.util.List;

public class Main {
    private final static List<MainBaseClass> classes = List.of(
        new SecretEntranceMain()
    );

    public static void main(String[] args) {
        for (MainBaseClass cls : classes) {
            System.out.println("Running day: " + cls.day);
            cls.runInput();
        }
    }
}