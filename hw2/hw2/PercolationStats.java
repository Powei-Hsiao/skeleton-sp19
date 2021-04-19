package hw2;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    double [] thresholds;
    PercolationFactory pf;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 0 || T < 0) {
            throw new IllegalArgumentException();
        }
        this.pf = pf;
        thresholds = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            thresholds[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    /**
     * sample mean of percolation threshold
     * @return
     */
    public double mean(){
        double sum = 0.0;
        for (double i : thresholds) {
            sum += i;
        }
        return sum / thresholds.length;
    }

    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        double mean = this.mean();
        double sum = 0.0;
        for (double i : thresholds) {
            sum += (i - mean) * (i - mean);
        }
        return sum / (thresholds.length - 1);
    }

    /**
     * low endpoint of 95% confidence interval
     * @return
     */
    public double confidenceLow(){
        double mean = mean();
        double std = stddev();
        return mean - 1.96 * std / Math.sqrt(std);
    }

    /**
     * high endpoint of 95% confidence interval
     * @return
     */
    public double confidenceHigh() {
        double mean = mean();
        double std = stddev();
        return mean + 1.96 * std / Math.sqrt(std);
    }
}
