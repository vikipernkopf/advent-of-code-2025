package at.htlleonding;

import at.htlleonding._01.SecretEntrance.SecretEntranceMain;
import at.htlleonding._02.GiftShop.GiftShopMain;
import at.htlleonding._03.Lobby.LobbyMain;

import java.util.List;

public class Main {
    private final static List<MainBaseClass> classes = List.of(
        new SecretEntranceMain(),
        new GiftShopMain(),
        new LobbyMain()
    );

    public static void main(String[] args) {
        for (MainBaseClass cls : classes) {
            System.out.println("Running day: " + cls.day);
            System.out.println("Test:");
            cls.runTest();
            System.out.println("Input:");
            cls.runInput();
            System.out.println();
        }
    }
}