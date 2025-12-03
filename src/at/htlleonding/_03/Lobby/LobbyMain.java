package at.htlleonding._03.Lobby;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

public class LobbyMain extends MainBaseClass<int[][]> {
    @Override
    protected IUtils<int[][]> getUtils() {
        return new LobbyUtils();
    }
}
