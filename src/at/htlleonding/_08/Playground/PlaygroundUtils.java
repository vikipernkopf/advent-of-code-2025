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

public class PlaygroundUtils implements IUtils<BoxCollections> {
    @Override
    public BoxCollections parseInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        List<Box> boxes = new ArrayList<>();

        for (String line : lines) {
            String[] dimensions = line.split(",");

            int x = Integer.parseInt(dimensions[0]);
            int y = Integer.parseInt(dimensions[1]);
            int z = Integer.parseInt(dimensions[2]);

            boxes.add(new Box(x, y, z));
        }

        List<BoxPair> boxPairs = new ArrayList<>();

        for (int i = 0; i < boxes.size(); i++) {
            Box boxA = boxes.get(i);

            for (int j = i + 1; j < boxes.size(); j++) {
                Box boxB = boxes.get(j);
                double distance = Math.sqrt(Math.pow(boxA.x() - boxB.x(), 2)
                        + Math.pow(boxA.y() - boxB.y(), 2)
                        + Math.pow(boxA.z() - boxB.z(), 2));

                boxPairs.add(new BoxPair(boxA, boxB, distance));
            }
        }

        Collections.sort(boxPairs);

        List<List<Box>> circuits = new ArrayList<>();
        Map<Box, Integer> boxToIndex = new HashMap<>();

        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);

            List<Box> circuit = new ArrayList<>();
            circuit.add(box);
            circuits.add(circuit);

            boxToIndex.put(box, i);
        }

        return new BoxCollections(boxes, boxPairs, circuits, boxToIndex);
    }

    @Override
    public long calculatePartOne(BoxCollections values) {
        return -1;
    }

    @Override
    public long calculatePartOne(BoxCollections values, Long extraParam) {
        List<BoxPair> boxPairs = new ArrayList<>(values.boxPairs());

        //copy so original list isn't modified for part 2
        List<List<Box>> circuits = new ArrayList<>();

        for (List<Box> c : values.circuits()) {
            circuits.add(new ArrayList<>(c));
        }

        Map<Box, Integer> boxToIndex = new HashMap<>(values.boxToIndex());

        for (int i = 0; i < extraParam.intValue(); i++) {
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
    public long calculatePartTwo(BoxCollections values) {
        List<BoxPair> boxPairs = values.boxPairs();
        List<List<Box>> circuits = values.circuits();
        Map<Box, Integer> boxToIndex = values.boxToIndex();

        for (BoxPair currentPair : boxPairs) {
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

            if (listContainsOnlyOneCircuit(circuits)) {
                return (long) currentPair.boxA.x() * currentPair.boxB.x();
            }
        }

        return -1;
    }

    private boolean listContainsOnlyOneCircuit(List<List<Box>> circuits) {
        int nonEmptyCircuits = 0;

        for (List<Box> circuit : circuits) {
            if (!circuit.isEmpty()) {
                nonEmptyCircuits++;

                if (nonEmptyCircuits > 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
