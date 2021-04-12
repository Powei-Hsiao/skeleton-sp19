import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    static StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
    static ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();

    /**Test add and remove methods in StudentArrayDeque class.*/
    @Test
    public void testRemoveFirstLast() {
        String msg = "";
        for (int i = 0; i < 10; i += 1) {
            double uni = StdRandom.uniform();
            if (uni > 0.5) {
                sad.addFirst(i);
                sol.addFirst(i);
                assertEquals(msg, sad.get(0), sol.get(0));
                msg += "addFirst(" + sol.get(0) + ")\n";
            } else {
                sad.addLast(i);
                sol.addLast(i);
                assertEquals(msg, sad.get(sad.size() - 1), sol.get(sol.size() - 1));
                msg += "addLast(" + sol.get(sol.size() - 1) + ")\n";
            }
        }
        for (int i = 0; i < 10; i += 1) {
            double uni = StdRandom.uniform();
            if (uni > 0.5) {
                Integer af = sad.removeFirst();
                Integer sf = sol.removeFirst();
                assertEquals(msg, af, sf);
                msg += "removeFirst(): " + sf + "\n";
            } else {
                Integer af = sad.removeLast();
                Integer sf = sol.removeLast();
                assertEquals(msg, af, sf);
                msg += "removeLast(): " + sf + "\n";
            }
        }
    }
}
