package at.htlleonding._08.Playground;

import at.htlleonding.IUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaygroundUtils implements IUtils<List<Box>> {
    @Override
    public List<Box> parseInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        List<Box> boxes = new ArrayList<>();

        for (String line : lines) {
            String[] dimensions = line.split(",");

            int x = Integer.parseInt(dimensions[0]);
            int y = Integer.parseInt(dimensions[1]);
            int z = Integer.parseInt(dimensions[2]);

            boxes.add(new Box(x, y, z));
        }

        return boxes;
    }

    @Override
    public long calculatePartOne(List<Box> values) {
        List<BoxPair> boxPairs = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            Box boxA = values.get(i);

            for (int j = i + 1; j < values.size(); j++) {
                Box boxB = values.get(j);
                double distance = Math.sqrt(Math.pow(boxA.x() - boxB.x(), 2)
                        + Math.pow(boxA.y() - boxB.y(), 2)
                        + Math.pow(boxA.z() - boxB.z(), 2));

                boxPairs.add(new BoxPair(boxA, boxB, distance));
            }
        }

        Collections.sort(boxPairs);

        List<List<Box>> circuits = new ArrayList<>();
        Map<Box, Integer> boxToIndex = new HashMap<>();

        for (int i = 0; i < values.size(); i++) {
            Box box = values.get(i);

            List<Box> circuit = new ArrayList<>();
            circuit.add(box);
            circuits.add(circuit);

            boxToIndex.put(box, i);
        }

        final int PairAmount = 1000;

        for (int i = 0; i < PairAmount; i++) {
            if (i >= boxPairs.size()) {
                break;
            } else {
                BoxPair currentPair = boxPairs.get(i);
                Integer circuitA = boxToIndex.get(currentPair.boxA);
                Integer circuitB = boxToIndex.get(currentPair.boxB);

                if (!circuitA.equals(circuitB)) {
                    List<Box> listA = circuits.get(circuitA);
                    List<Box> listB = circuits.get(circuitB);

                    List<Box> toMove = new ArrayList<>(listB);

                    listB.clear();
                    listA.addAll(toMove);

                    for (Box box : toMove) {
                        boxToIndex.put(box, circuitA);
                    }
                }
            }
        }

        List<Integer> lengths = new ArrayList<>(circuits.size());

        for (List<Box> circuit : circuits) {
            lengths.add(circuit.size());
        }

        lengths.sort(Collections.reverseOrder());

        return (long) lengths.get(0) * lengths.get(1) * lengths.get(2);
    }

    @Override
    public long calculatePartTwo(List<Box> values) {
        return 0;
    }
}
