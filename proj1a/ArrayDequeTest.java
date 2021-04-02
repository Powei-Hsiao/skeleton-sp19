public class ArrayDequeTest {

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

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true, ad1.isEmpty());
        ad1.addFirst(5);
        ad1.addLast(10);
        passed = checkSize(2, ad1.size()) && passed;
        ad1.printArrayDeque();
        printTestStatus(passed);
    }

    public static void addLastTest() {
        System.out.println("Running addLast test.");
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true, ad1.isEmpty());
        for (int i = 0; i < 20; i += 1) {
            ad1.addLast(i);
            passed = ad1.get(i) == i && passed;
        }
        ad1.printArrayDeque();
        printTestStatus(passed);
    }

    public static void removeLastTest() {
        System.out.println("Running removeLast test.");
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true, ad1.isEmpty());
        for (int i = 0; i < 20; i += 1) {
            ad1.addLast(i);
        }
        for (int i = 0; i < 19; i += 1) {
            ad1.removeLast();
        }
        passed = checkSize(1, ad1.size());
        ad1.printArrayDeque();
        printTestStatus(passed);
    }

    public static void removeFirstTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true, ad1.isEmpty());
        for (int i = 0; i < 30; i += 1) {
            ad1.addFirst(i);
        }
        for (int i = 0; i < 25; i += 1) {
            ad1.removeLast();
        }
        passed = checkSize(5, ad1.size());
        ad1.printArrayDeque();
        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addLastTest();
        //removeLastTest();
        removeFirstTest();
    }
}
