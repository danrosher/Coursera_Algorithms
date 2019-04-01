import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private final double[] results;
	private final int trials;
	private final double stddev;
	private final double mean;

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0)
			throw new java.lang.IllegalArgumentException();
		this.trials = trials;
		results = new double[trials];
		for (int i = 0; i < trials; i++) {
			Percolation p = new Percolation(n);
			int c = 0;
			while (!p.percolates()) {
				int row = StdRandom.uniform(1, n + 1);
				int col = StdRandom.uniform(1, n + 1);
				if (!p.isOpen(row, col)) {
					p.open(row, col);
					c++;
				}
			}
			results[i] = (c + 0.0) / (n * n);
		}
		mean = StdStats.mean(results);
		stddev = StdStats.stddev(results);
	}

	public double mean() {
		return mean;
	}

	public double stddev() {
		return stddev;

	}

	public double confidenceLo() {
		return mean() - (1.96 * stddev()) / Math.sqrt(trials);
	}

	public double confidenceHi() {
		return mean() + (1.96 * stddev()) / Math.sqrt(trials);
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		PercolationStats stats = new PercolationStats(n, trials);
		StdOut.println("mean                    = " + stats.mean());
		StdOut.println("stddev                  = " + stats.mean());
		StdOut.println("95% confidence interval = [" + stats.confidenceLo() + "," + stats.confidenceHi() + "]");

	}
}
