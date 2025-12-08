package at.htlleonding._08.Playground;

public class BoxPair implements Comparable<BoxPair> {
    public Box boxA;
    public Box boxB;
    public double distance;

    public BoxPair(Box boxA, Box boxB, double distance) {
        this.boxA = boxA;
        this.boxB = boxB;
        this.distance = distance;
    }

    @Override
    public int compareTo(BoxPair other) {
        return Double.compare(this.distance, other.distance);
    }
}
