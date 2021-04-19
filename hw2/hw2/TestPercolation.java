package hw2;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestPercolation {
    @Test
    public void testOpenIsFull() {
        Percolation g = new Percolation(5);
        g.open(0, 0);

        assertTrue(g.isFull(0, 0));
        assertFalse(g.isOpen(0, 1));

        g.open(1, 3);
        g.open(2, 3);
        g.open(2, 4);
        g.open(0, 3);

        assertTrue(g.isOpen(2, 4));
        assertTrue(g.isFull(0, 3));
        assertTrue(g.isFull(1, 3));
        assertTrue(g.isFull(2, 3));
        assertTrue(g.isFull(2, 4));

        g.open(3, 1);
        assertFalse(g.isFull(3, 2));

        g.open(1, 1);
        g.open(1, 2);
        g.open(2, 2);
        g.open(2, 1);

        assertTrue(g.isFull(3, 1));
        assertTrue(g.isFull(1, 1));
        assertTrue(g.isFull(1, 2));
        assertTrue(g.isFull(2, 2));
    }

    @Test
    public void testPercolates() {
        Percolation g = new Percolation(5);
        g.open(0, 3);
        g.open(1, 3);
        g.open(2, 3);
        g.open(3, 3);

        assertFalse(g.percolates());

        g.open(3, 2);
        g.open(4, 2);

        assertTrue(g.percolates());
    }
}
