package at.htlleonding;

import at.htlleonding._01.SecretEntrance.SecretEntranceMain;
import at.htlleonding._02.GiftShop.GiftShopMain;
import at.htlleonding._03.Lobby.LobbyMain;

import java.util.List;

public class Main {
    private final static List<MainBaseClass<?>> classes = List.of(
        new SecretEntranceMain(),
        new GiftShopMain(),
        new LobbyMain()
    );

    static void main() {
        for (MainBaseClass<?> cls : classes) {
            System.out.println("Day: " + cls.day);
            System.out.println("\nTest:");
            cls.runTest();
            System.out.println("\nInput:");
            cls.runInput();
            System.out.println();
        }
    }
}
