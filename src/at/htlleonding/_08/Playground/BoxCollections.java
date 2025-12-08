package at.htlleonding._08.Playground;

import java.util.List;
import java.util.Map;

public record BoxCollections(List<Box> boxes, List<BoxPair> boxPairs, List<List<Box>> circuits, Map<Box, Integer> boxToIndex) {
}
