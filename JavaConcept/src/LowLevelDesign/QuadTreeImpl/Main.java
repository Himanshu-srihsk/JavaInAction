package LowLevelDesign.QuadTreeImpl;

import java.util.List;

/*
Design and implement a QuadTree-based spatial indexing system to store and query points in a two-dimensional coordinate space.
FR:
1. Insert a Point
2. Given a rectangular region, return all points located inside that region.
3. Given a point, determine which smallest sub-region (leaf node) of the QuadTree contains the point.

Note:
Each node can store at most N points before subdivision.
All points lie within the root region.
The tree subdivides recursively as needed

 */
public class Main {
    public static void main(String[] args) {
        Region area = new Region(0, 0, 400, 400);
        QuadTree quadTree = new QuadTree(area);

        float[][] points = {
                {21,25}, {55,53}, {70,318}, {98,302},
                {49,229}, {135,229}, {224,292},
                {206,321}, {197,258}, {245,238}
        };

        for (float[] p : points) {
            quadTree.insert(new Point(p[0], p[1]));
        }

        quadTree.print("");

        Region searchRegion = new Region(200,200,250,250);

        List<Point> result = quadTree.search(searchRegion);

        System.out.println("\nPoints in region:");

        for (Point p : result)
            System.out.println(p);

        Region r = quadTree.findRegion(new Point(245,238));
        System.out.println(r);
    }
}
/*
[Region (x1=0.0, y1=0.0), (x2=400.0, y2=400.0)]
  [Region (x1=0.0, y1=0.0), (x2=200.0, y2=200.0)]
    [21.0 , 25.0]
    [55.0 , 53.0]
  [Region (x1=0.0, y1=200.0), (x2=200.0, y2=400.0)]
    [Region (x1=0.0, y1=200.0), (x2=100.0, y2=300.0)]
      [49.0 , 229.0]
    [Region (x1=0.0, y1=300.0), (x2=100.0, y2=400.0)]
      [70.0 , 318.0]
      [98.0 , 302.0]
    [Region (x1=100.0, y1=300.0), (x2=200.0, y2=400.0)]
    [Region (x1=100.0, y1=200.0), (x2=200.0, y2=300.0)]
      [135.0 , 229.0]
      [197.0 , 258.0]
  [Region (x1=200.0, y1=200.0), (x2=400.0, y2=400.0)]
    [224.0 , 292.0]
    [206.0 , 321.0]
    [245.0 , 238.0]
  [Region (x1=200.0, y1=0.0), (x2=400.0, y2=200.0)]

Points in region:
[245.0 , 238.0]
[Region (x1=200.0, y1=200.0), (x2=400.0, y2=400.0)]
 */