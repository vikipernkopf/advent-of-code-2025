package at.htlleonding;

import at.htlleonding._01.SecretEntrance.SecretEntranceMain;
import at.htlleonding._02.GiftShop.GiftShopMain;
import at.htlleonding._03.Lobby.LobbyMain;
import at.htlleonding._04.PrintingDepartment.PrintingDepartmentMain;
import at.htlleonding._05.Cafeteria.CafeteriaMain;
import at.htlleonding._06.TrashCompactor.TrashCompactorMain;
import at.htlleonding._07.Laboratories.LaboratoriesMain;
import at.htlleonding._08.Playground.PlaygroundMain;
import at.htlleonding._09.MovieTheater.MovieTheaterMain;

import java.util.List;

public class Main {
    private final static List<MainBaseClass<?>> classes = List.of(
        /*new SecretEntranceMain(),
        new GiftShopMain(),
        new LobbyMain(),
        new PrintingDepartmentMain(),
	    new CafeteriaMain(),
	    new TrashCompactorMain(),
        new LaboratoriesMain(),
        new PlaygroundMain(),*/
        new MovieTheaterMain()
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
