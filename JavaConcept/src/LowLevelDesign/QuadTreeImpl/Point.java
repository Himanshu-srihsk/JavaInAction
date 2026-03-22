package LowLevelDesign.QuadTreeImpl;

public record Point(float x, float y) {
    @Override
    public String toString() {
        return "[" + x + " , " + y + "]";
    }
}
