package bearmaps;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {

    @Test
    public void addTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        for (int i = 100; i > 0; i -= 1) {
            pq.add(i, i);
            assertTrue(pq.contains(i));

        }

        assertEquals(100, pq.size());
        Integer actual = pq.getSmallest();
        assertEquals((Integer) 1, actual);

    }

    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        for (int i = 5; i < 10; i += 1) {
            pq.add(i, i);
        }

        Integer actual = pq.getSmallest();
        assertEquals((Integer) 5, actual);

        pq.changePriority(6, 4);

        actual = pq.removeSmallest();
        assertEquals((Integer) 6, actual);

        actual = pq.getSmallest();
        assertEquals((Integer) 5, actual);

        pq.changePriority(9, 4);

        actual = pq.removeSmallest();
        assertEquals((Integer) 9, actual);

        actual = pq.getSmallest();
        assertEquals((Integer) 5, actual);
    }

    @Test
    public void randomAddTest() {
        ArrayHeapMinPQ<Integer> arrayPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        for (int i = 0; i < 10000; i += 1) {
            double randomPriority = Math.random();
            arrayPQ.add(i, randomPriority);
            naivePQ.add(i, randomPriority);
            assertEquals(arrayPQ.getSmallest(), naivePQ.getSmallest());
            assertEquals(arrayPQ.size(), naivePQ.size());
        }
    }

    @Test
    public void randomRemoveMinTest() {
        ArrayHeapMinPQ<Integer> arrayPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        for (int i = 0; i < 10000; i += 1) {
            double randomPriority = Math.random();
            arrayPQ.add(i, randomPriority);
            naivePQ.add(i, randomPriority);
        }

        for (int i = 0; i < 10000; i += 1) {
            Integer minArray = arrayPQ.removeSmallest();
            Integer minNaive = naivePQ.removeSmallest();
            assertEquals(minArray, minNaive);
        }
    }

    @Test
    public void randomChangePriorityTest() {
        ArrayHeapMinPQ<Integer> arrayPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        for (int i = 0; i < 10000; i += 1) {
            double randomPriority = Math.random();
            arrayPQ.add(i, randomPriority);
            naivePQ.add(i, randomPriority);
        }

        for (int i = 0; i < 10000; i += 1) {
            double updatePriority = Math.random();
            arrayPQ.changePriority(i, updatePriority);
            naivePQ.changePriority(i, updatePriority);
            assertEquals(arrayPQ.getSmallest(), naivePQ.getSmallest());
        }
    }

    @Test
    public void addRunTimeTest() {
        ArrayHeapMinPQ<Integer> pq1 = new ArrayHeapMinPQ<>();
        ArrayHeapMinPQ<Integer> pq2 = new ArrayHeapMinPQ<>();

        Stopwatch pq1sw = new Stopwatch();
        for (int i = 0; i < 100000; i += 1) {
            pq1.add(i, i);
        }
        System.out.println("ArrayPQ add 100,000 items:\n" + "Total time elapsed: " + pq1sw.elapsedTime() +  " seconds.");


        Stopwatch pq2sw = new Stopwatch();
        for (int i = 0; i < 1000000; i += 1) {
            pq2.add(i, i);
        }
        System.out.println("ArrayPQ add 1,000,000 items:\n" + "Total time elapsed: " + pq2sw.elapsedTime() +  " seconds.");
    }

    @Test
    public void changePriorityRunTimeTest() {
        ArrayHeapMinPQ<Integer> arrayPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();

        for (int i = 0; i < 10000; i += 1) {
            double randomPriority = Math.random();
            arrayPQ.add(i, randomPriority);
            naivePQ.add(i, randomPriority);
        }

        Stopwatch sw1 = new Stopwatch();
        for (int i = 0; i < 10000; i += 1) {
            double updatePriority = Math.random();
            arrayPQ.changePriority(i, updatePriority);
        }
        System.out.println("ArrayPQ change priority runtime test:\n" + "Total time elapsed: " + sw1.elapsedTime() +  " seconds.");


        Stopwatch sw2 = new Stopwatch();
        for (int i = 0; i < 10000; i += 1) {
            double updatePriority = Math.random();
            naivePQ.changePriority(i, updatePriority);
        }
        System.out.println("NaivePQ change priority runtime test:\n" + "Total time elapsed: " + sw2.elapsedTime() +  " seconds.");
    }
}
