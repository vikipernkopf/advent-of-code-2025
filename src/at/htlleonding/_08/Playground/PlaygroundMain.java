package at.htlleonding._08.Playground;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

import java.util.List;

public class PlaygroundMain extends MainBaseClass<List<Box>> {
    @Override
    protected IUtils<List<Box>> getUtils() {
        return new PlaygroundUtils();
    }
}
