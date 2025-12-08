package at.htlleonding._08.Playground;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

import java.nio.file.Path;

public class PlaygroundMain extends MainBaseClass<BoxCollections> {
    @Override
    protected IUtils<BoxCollections> getUtils() {
        return new PlaygroundUtils();
    }

    @Override
    public void runTest() {
        run(Path.of("data/" + String.format("%02d", day) + "/test.in"), 10L);
    }

    @Override
    public void runInput() {
        run(Path.of("data/" + String.format("%02d", day) + "/input.in"), 1000L);
    }
}
