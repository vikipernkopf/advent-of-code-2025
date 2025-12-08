package at.htlleonding._08.Playground;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

import java.util.List;

public class PlaygroundMain extends MainBaseClass<BoxCollections> {
    @Override
    protected IUtils<BoxCollections> getUtils() {
        return new PlaygroundUtils();
    }
}
