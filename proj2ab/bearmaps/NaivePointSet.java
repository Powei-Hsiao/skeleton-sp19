package bearmaps;

import org.junit.Test;

import java.util.List;

public class NaivePointSet implements PointSet{
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point tempPoint = points.get(0);
        Point targetPoint = new Point(x, y);
        double tempDistance = Point.distance(tempPoint, targetPoint);
        for (Point p : points) {
            if (Point.distance(p, targetPoint) < tempDistance) {
                tempPoint = p;
                tempDistance = Point.distance(p, targetPoint);
            }
        }
        return tempPoint;
    }
}
