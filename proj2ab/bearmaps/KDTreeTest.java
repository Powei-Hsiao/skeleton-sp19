package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void insertTest() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(5, 5));
        points.add(new Point(6, 7));
        points.add(new Point(4, 3));
        points.add(new Point(7, 8));
        points.add(new Point(6, 6));
        points.add(new Point(3, 2));
        points.add(new Point(4, 4));
        KDTree kdPoints = new KDTree(points);
    }

    @Test
    public void nearestTest() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 100000; i += 1) {
            double randomX = Math.random();
            double randomY = Math.random();
            points.add(new Point(randomX, randomY));
        }
        NaivePointSet naivePoints = new NaivePointSet(points);
        KDTree kdTree = new KDTree(points);

        double x = Math.random();
        double y = Math.random();

        Stopwatch swp1 = new Stopwatch();
        Point p1 = naivePoints.nearest(x, y);
        for (int i = 0; i < 10000; i += 1) {
            p1 = naivePoints.nearest(x, y);
        }

        Stopwatch swp2 = new Stopwatch();
        Point p2 = kdTree.nearest(x, y);
        for (int i = 0; i < 10000; i += 1) {
            p2 = kdTree.nearest(x, y);
        }

        assertEquals(p1, p2);

        System.out.println("Total time elapsed by NaivePointSet: " + swp1.elapsedTime() +  " seconds.");
        System.out.println("Total time elapsed by KDTree: " + swp2.elapsedTime() +  " seconds.");
    }
}
