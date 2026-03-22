package LowLevelDesign.QuadTreeImpl;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private static final int MAX_POINTS = 3;
    private static final int MAX_DEPTH = 10;

    private final Region region;
    private final int depth;

    private final List<Point> points = new ArrayList<>();
    private QuadTree[] children = null;

    public QuadTree(Region region) {
        this(region, 0);
    }

    private QuadTree(Region region, int depth) {
        this.region = region;
        this.depth = depth;
    }

    public boolean insert(Point point) {
        if (!region.contains(point)){
            return false;
        }

        if (children == null) {
            if (points.size() < MAX_POINTS || depth >= MAX_DEPTH) {
                points.add(point);
                return true;
            }
            subdivide();
        }

        return insertIntoChild(point);
    }

    private void subdivide() {
        children = new QuadTree[4];
        for (int i = 0; i < 4; i++) {
            children[i] = new QuadTree(region.quadrant(i), depth + 1);
        }
        // redistribute existing points
        List<Point> oldPoints = new ArrayList<>(points);
        points.clear();
        for (Point p : oldPoints) {
            insertIntoChild(p);
        }
    }

    private boolean insertIntoChild(Point p) {

        for (QuadTree child : children) {
            if (child.insert(p))
                return true;
        }

        return false;
    }

    public List<Point> search(Region range) {

        List<Point> result = new ArrayList<>();
        search(range, result);
        return result;
    }

    private void search(Region range, List<Point> result) {

        if (!region.overlaps(range))
            return;

        for (Point p : points) {
            if (range.contains(p)){
                result.add(p);
            }
        }

        if (children != null) {
            for (QuadTree child : children) {
                child.search(range, result);
            }
        }
    }

    public void print(String indent) {
        System.out.println(indent + region);
        for (Point p : points) {
            System.out.println(indent + "  " + p);
        }
        if (children != null) {
            for (int i = 0; i < 4; i++) {
                children[i].print(indent + "  ");
            }
        }
    }

    public Region findRegion(Point point) {
        if (!region.contains(point)){
            return null;
        }
        if (children == null){
            return region;
        }
        for (QuadTree child : children) {
            Region r = child.findRegion(point);
            if (r != null){
                return r;
            }
        }
        return region;
    }
}