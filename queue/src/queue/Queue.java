package queue;

public interface Queue<T> {

	// add the item to the end of the queue
	// and return the queue length
	int enqueue(T item);
	
	// Remove the first item in the queue and return
	// the item to caller
	T dequeue();
	
	// Check if queue is empty
	boolean isEmpty();
	
	// Check if queue is full
	boolean isFull();
	
	void setMaxQueueLength(int len);
	
	void print();
}
