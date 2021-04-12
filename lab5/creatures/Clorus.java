package creatures;
import java.util.Random;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/** Implement a fierce blue-colored predator Clorus
 * @author Evan
 */

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * Cloruses should always return the color red=34, green=0, blue=231.
     */
    public Color color() {
        g = 0;
        r = 34;
        b = 231;
        return color(r, g, b);
    }

    /**
     * Gain energy C, Cloruses are fierce.
     */
    public void attack(Creature c) {
        // Gain energy from c.
        energy += c.energy();
    }

    /**
     * Clours should lose 0.03 units of energy when moving.
     */
    public void move() {
        energy -= 0.03;
    }


    /**
     * Clours loss 0.01 energy when staying.
     */
    public void stay() {
        energy -= 0.01;
    }

    /**
     * Clorus and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * clorus.
     */
    public Clorus replicate() {
        Clorus baby = new Clorus(this.energy / 2);
        this.energy = this.energy / 2;
        return baby;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.add(d);
            } else if (neighbors.get(d).name().equals("plip")) {
                plipNeighbors.add(d);
            }
        }

        //Rule 1
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        //Rule 2
        if (!plipNeighbors.isEmpty()) {
            Random rand = new Random();
            int plipNumber = rand.nextInt(plipNeighbors.size());
            for (int i = 0; i < plipNumber; i += 1) {
                plipNeighbors.removeFirst();
            }
            return new Action(Action.ActionType.ATTACK, plipNeighbors.getFirst());
        }

        //Rule 3
        if (energy >= 1.0) {
            Random rand = new Random();
            int emptyNumber = rand.nextInt(emptyNeighbors.size());
            for (int i = 0; i < emptyNumber; i += 1) {
                emptyNeighbors.removeFirst();
            }
            return new Action(Action.ActionType.REPLICATE, emptyNeighbors.getFirst());
        }

        //Rule 4
        Random rand = new Random();
        int emptyNumber = rand.nextInt(emptyNeighbors.size());
        for (int i = 0; i < emptyNumber; i += 1) {
            emptyNeighbors.removeFirst();
        }
        return new Action(Action.ActionType.MOVE, emptyNeighbors.getFirst());
    }
}
