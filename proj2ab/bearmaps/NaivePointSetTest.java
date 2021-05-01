package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;


public class NaivePointSetTest {
    @Test
    public void simpleTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(3.2, 4.3);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3, p4));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        assertEquals(3.2, ret.getX(), 0.0);
        assertEquals(4.3, ret.getY(), 0.0);
    }
}
