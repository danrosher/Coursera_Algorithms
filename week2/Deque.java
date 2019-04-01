import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

	private Node first, last;
	private int size;

	private class Node {
		Item item;
		Node next;
		Node prev;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {
		if (item == null)
			throw new java.lang.IllegalArgumentException();
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		if (last == null)
			last = first;
		else
			oldfirst.prev = first;
		size++;
	}

	public Item removeLast() {
		if (size == 0)
			throw new NoSuchElementException();
		Node n = last;
		Item item = n.item;
		last = last.prev;
		if (last == null)
			first = null;
		else
			last.next = null;
		size--;
		n.prev = n.next = n = null;
		return item;
	}

	public void addLast(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.prev = oldlast;
		if (first == null)
			first = last;
		else
			oldlast.next = last;
		size++;
	}

	public Item removeFirst() {
		if (size == 0)
			throw new NoSuchElementException();
		Node n = first;
		Item item = n.item;
		first = first.next;
		if (first == null)
			last = null;
		else
			first.prev = null;
		size--;
		n.prev = n.next = n = null;
		return item;
	}

	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (current == null)
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {
		Deque<Integer> d = new Deque<>();
		for (int i = 1; i <= 10; i++) {
			d.addFirst(i);
		}

		for (int i : d) {
			StdOut.println(i);
		}

		for (int i = 1; i <= 10; i++) {
			StdOut.println(d.removeLast());
		}
	}
}
