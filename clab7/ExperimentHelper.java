/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int res = 0;
        for (int i = 1; i < N + 1; i += 1) {
            res += (int) (Math.log(i) / Math.log(2));
        }
        return res;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) / N;
    }

    public static void randomlyDelete(BST bst) {
        bst.deleteTakingSuccessor(bst.getRandomKey());
    }

    public static void randomlyInsert(BST bst, double rand) {
        bst.add(rand);
    }

    public static void randomlyDeleteBoth(BST bst) {
        bst.deleteTakingRandom(bst.getRandomKey());
    }
}
