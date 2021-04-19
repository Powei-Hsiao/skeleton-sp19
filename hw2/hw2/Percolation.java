package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean [][] grid;
    private WeightedQuickUnionUF set;
    private WeightedQuickUnionUF setTop;
    private int openCount;
    private int virtualTop;
    private int virtualDown;

    public Percolation(int N) {
        grid = new boolean[N][N];
        set = new WeightedQuickUnionUF(N * N + 2);
        setTop = new WeightedQuickUnionUF(N * N + 1); //Create another disjoint set to check the site is full or not.
                                                         //Since union virtualTop and virtualDown will cause backwash.
        virtualTop = N * N ;
        virtualDown = N * N + 1;
        openCount = 0;
    }

    private int posTo1D(int row, int col) {
        return row * grid.length + col;
    }

    private void validate(int row, int col) {
        if (row >= grid.length || row < 0){
            throw new IndexOutOfBoundsException("Please make sure 0 <= row < N");
        }
        if (col >= grid.length || col < 0){
            throw new IndexOutOfBoundsException("Please make sure 0 <= col < N");
        }
    }

    /**
     * Open site (row, col) if it is not open already.
     * If we flap one site status from block into open, union left, right, up, down sides if it's open.
     */
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            openCount += 1;
            if (row == 0) {
                set.union(virtualTop, posTo1D(row, col));
                setTop.union(virtualTop, posTo1D(row, col));
            }
            if (row == grid.length - 1) {
                set.union(virtualDown, posTo1D(row, col));
            }
            if (row - 1 >= 0 && isOpen(row - 1, col)) {
                int here = posTo1D(row, col);
                int up = posTo1D(row - 1, col);
                if (!set.connected(here, up)) {
                    set.union(here, up);
                }
                if (!setTop.connected(here, up)) {
                    setTop.union(here, up);
                }
            }
            if (row + 1 < grid.length && isOpen(row + 1, col)) {
                int here = posTo1D(row, col);
                int down = posTo1D(row + 1, col);
                if (!set.connected(here, down)) {
                    set.union(here, down);
                }
                if (!setTop.connected(here, down)) {
                    setTop.union(here, down);
                }
            }
            if (col - 1 >= 0 && isOpen(row, col - 1)) {
                int here = posTo1D(row, col);
                int left = posTo1D(row, col - 1);
                if (!set.connected(here, left)) {
                    set.union(here, left);
                }
                if (!setTop.connected(here, left)) {
                    setTop.union(here, left);
                }
            }
            if (col + 1 < grid.length && isOpen(row, col + 1)) {
                int here = posTo1D(row, col);
                int right = posTo1D(row, col + 1);
                if (!set.connected(here, right)) {
                    set.union(here, right);
                }
                if (!setTop.connected(here, right)) {
                    setTop.union(here, right);
                }
            }
        }
    }

    /**
     * Check the site is open or blocked.
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    /**
     * Check the site is full or not.
     */
    public boolean isFull(int row, int col) {
        validate(row, col);
        int here = posTo1D(row, col);
        return setTop.connected(virtualTop, here);
    }

    /**
     * Return number of open sites.
     */
    public int numberOfOpenSites() {
        return this.openCount;
    }

    /**
     * Check the system is percolated or not.
     */
    public boolean percolates() {
        return set.connected(virtualDown, virtualTop);
    }

    //Leave it for test??
    public static void main(String[] args) { }
}
