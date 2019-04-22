package queue;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueue<T> implements Queue<T> {

	List<T> queue = null;
	int maxQueueLength = 10;
	Object lock = new Object();

	public BlockingQueue(int maxLength) {

		if (maxLength == 0)
			throw new IllegalArgumentException("Queue Length cannot be 0");

		queue = new ArrayList<T>();

		setMaxQueueLength(maxLength);
	}

	@Override
	public int enqueue(T item) {

		synchronized (lock) {
			try {
				if (queue.size() == maxQueueLength) {
					System.out.println("Queue is full.. cannot enqueue: " + item + "..waiting");
					lock.notifyAll();
					lock.wait();
				} else {
					System.out.println("Queueing item " + item);
					queue.add(item);
				}
			} catch (InterruptedException e) {
				System.out.println("Enqueue: Thread interrupted " + e.getMessage());
			}
		}

		// print();
		return queue.size();
	}

	@Override
	public T dequeue() {
		synchronized (lock) {
			try {
				if (queue.isEmpty()) {
					System.out.println("Queue is empty.. cannot dequeue.. waiting");
					lock.notifyAll();
					lock.wait();
				}
			} catch (InterruptedException e) {
				System.out.println("Dequeue: Thread interrupted " + e.getMessage());
			}
			System.out.println("removing item " + queue.get(0));

			return queue.remove(0);
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (lock) {
			if (queue.size() == 0) {
				return true;
			}
			return false;
		}
	}

	@Override
	public boolean isFull() {
		synchronized (lock) {
			if (queue.size() == maxQueueLength) {
				return false;
			}
			return true;
		}
	}

	@Override
	public void setMaxQueueLength(int len) {
		synchronized (lock) {
			this.maxQueueLength = len;
		}
	}

	@Override
	public void print() {
		synchronized (lock) {
			System.out.println("Printing the queue...length: " + queue.size());

			for (T item : queue) {
				System.out.print(item + ",");
			}
			System.out.println();
		}
	}

}
