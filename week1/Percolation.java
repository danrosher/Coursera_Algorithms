import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private final WeightedQuickUnionUF uf;
	private final WeightedQuickUnionUF ufFull;

	private final int top = 0;
	private final int bottom;
	private final int size;
	private final boolean[] open;
	private int opened = 0;

	public Percolation(int n) {
		if (n < 1)
			throw new java.lang.IllegalArgumentException();
		size = n;
		uf = new WeightedQuickUnionUF(n * n + 2);
		ufFull = new WeightedQuickUnionUF(n * n + 1);
		bottom = n * n + 1;
		open = new boolean[n * n];
	}

	public boolean isFull(int row, int col) {
		if (row < 1 || row > size || col < 1 || col > size)
			throw new java.lang.IllegalArgumentException();
		int i = getIndex(row, col);
		return ufFull.connected(i, top);
	}

	public boolean isOpen(int row, int col) {
		if (row < 1 || row > size || col < 1 || col > size)
			throw new java.lang.IllegalArgumentException();
		return open[getIndex(row, col) - 1];
	}

	public boolean percolates() {
		return uf.connected(top, bottom);
	}

	public void open(int row, int col) {
		if (row < 1 || row > size || col < 1 || col > size)
			throw new java.lang.IllegalArgumentException();	

		int i = getIndex(row, col);
		if(open[i - 1])
			return;

		open[i - 1] = true;
		opened++;

		if (row == 1) {
			uf.union(i, top);
			ufFull.union(i, top);
		}

		 if (row == size) {
			 uf.union(i, bottom);
		 }

		if (row > 1 && isOpen(row - 1, col)) {
			int j = getIndex(row - 1, col);
			uf.union(i, j);
			ufFull.union(i, j);
		}

		if (row < size && isOpen(row + 1, col)) {
			int j = getIndex(row + 1, col);
			uf.union(i, j);
			ufFull.union(i, j);
		}

		if (col > 1 && isOpen(row, col - 1)) {
			int j = getIndex(row, col - 1);
			uf.union(i, j);
			ufFull.union(i, j);
		}

		if (col < size && isOpen(row, col + 1)) {
			int j = getIndex(row, col + 1);
			uf.union(i, j);
			ufFull.union(i, j);
		}
	}

	public int numberOfOpenSites() {
		return opened;
	}

	private int getIndex(int row, int col) {
		// 1<=row<=size && 1<=col<=size
		return size * (row - 1) + col;
	}

}
