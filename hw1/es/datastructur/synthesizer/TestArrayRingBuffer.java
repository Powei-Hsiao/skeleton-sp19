package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertTrue(arb.isEmpty());

        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        Integer d = arb.dequeue();


        assertEquals(2, arb.fillCount());
    }

    @Test
    public void equalsTest() {
        ArrayRingBuffer<Integer> arb1 = new ArrayRingBuffer<>(10);
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(10);
        ArrayRingBuffer<Integer> arb3 = new ArrayRingBuffer<>(10);

        arb1.enqueue(0);
        arb1.enqueue(1);
        arb1.enqueue(2);

        arb2.enqueue(0);
        arb2.enqueue(1);
        arb2.enqueue(2);

        assertTrue(arb1.equals(arb2));
        assertFalse(arb1.equals(arb3));

        arb3.enqueue(8);
        arb3.enqueue(8);
        arb3.enqueue(8);

        assertFalse(arb1.equals(arb3));
    }
}
