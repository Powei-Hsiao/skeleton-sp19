import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 * @https://github.com/ZTong1201/Java/blob/master/lab/clab8/FlightSolver.java
 */
public class FlightSolver {

    private PriorityQueue<Flight> startMinPQ;
    private PriorityQueue<Flight> endMinPQ;

    public FlightSolver(ArrayList<Flight> flights) {
        Comparator<Flight> startTimeComparator = (Flight f1, Flight f2) -> f1.startTime() - f2.startTime();
        Comparator<Flight> endTimeComparator = (Flight f1, Flight f2) -> f1.endTime() - f2.endTime();
        this.startMinPQ = new PriorityQueue<>(startTimeComparator);
        this.endMinPQ = new PriorityQueue<>(endTimeComparator);
        for (Flight flight : flights) {
            startMinPQ.add(flight);
            endMinPQ.add(flight);
        }
    }

    public int solve() {
        int res = 0;
        int count = 0;

        while (startMinPQ.peek() != null) {
            Flight startFlight = startMinPQ.peek();
            Flight endFlight = endMinPQ.peek();
            if (startFlight.startTime() < endFlight.endTime()) {
                count += startMinPQ.poll().passengers();
                if (count > res) {
                    res = count;
                }
            } else {
                count -= endMinPQ.poll().passengers();
            }
        }

        return res;
    }

}
