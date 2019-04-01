import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] q;
	private int n;

	public RandomizedQueue() {
		q = (Item[]) new Object[2];
		n = 0;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		if (n == q.length)
			resize(2 * q.length);
		q[n++] = item;
	}

	private void resize(int capacity) {
		assert capacity >= n;

		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			temp[i] = q[i];
		}
		q = temp;
	}

	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();
		int r = StdRandom.uniform(n);

		Item item = q[r];
		q[r] = null;
		if (r != n - 1) { // do not swap the last element
			swap(r, n - 1);
		}
		n--;
		if (n > 0 && n == q.length / 4)
			resize(q.length / 2);
		return item;
	}

	private void swap(int i, int j) {
		Item temp = q[i];
		q[i] = q[j];
		q[j] = temp;
	}

	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException();
		return q[StdRandom.uniform(n)];
	}

	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		int[] random = StdRandom.permutation(n);
		int i = 0;

		@Override
		public boolean hasNext() {
			return i < n;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (i >= n)
				throw new NoSuchElementException();
			return q[random[i++]];
		}
	}

}
