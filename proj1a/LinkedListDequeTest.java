/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out get checks. */
	public static boolean checkGet(int expected, int actual) {
		if (expected != actual) {
			System.out.println("get() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(15);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addFirst(10);
		// size should be 2
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast(20);
		// size should be 3
		passed = checkSize(3, lld1.size()) && passed;

		lld1.removeFirst();
		// size should be 2
		passed = checkGet(15, lld1.get(0)) && passed;

		lld1.removeLast();
		// size should be 1
		passed = checkSize(15, lld1.get(0)) && passed;

		System.out.println("Final double linked list is.");
		lld1.printDeque();

		printTestStatus(passed);

	}
	public static void getTest() {
		System.out.println("Running get test.");
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		lld1.addFirst(15);
		lld1.addFirst(10);
		lld1.addLast(20);

		boolean passed = checkGet(10, lld1.get(0));
		passed = checkGet(15, lld1.get(1)) && passed;
		passed = checkGet(20, lld1.get(2)) && passed;
		passed = checkGet(10, lld1.getRecursive(0)) && passed;
		passed = checkGet(15, lld1.getRecursive(1)) && passed;
		passed = checkGet(20, lld1.getRecursive(2)) && passed;

		printTestStatus(passed);
	}
	public static void copyTest() {
		System.out.println("Running copy test.");
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		lld1.addFirst(15);
		lld1.addFirst(10);
		lld1.addLast(20);
		LinkedListDeque<Integer> lld2 = new LinkedListDeque<Integer>(lld1);
		lld2.addFirst(5);

		boolean passed = checkSize(3, lld1.size());
		passed = checkSize(4, lld2.size()) && passed;

		printTestStatus(passed);
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
		getTest();
		copyTest();
	}
} 