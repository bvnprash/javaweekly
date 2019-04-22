package queue;

public class CodeTest{
	
   BlockingQueue<String> blq;	
   
   public CodeTest() {
	   blq = new BlockingQueue<String>(10);
   }
   
   public void runProducer() {
	   Thread tr = new Thread(new Producer());
	   tr.start();
   }
   
   public void runConsumer() {
	   Thread tr = new Thread(new Consumer());
	   tr.start();
   }
	
   class Producer implements Runnable{
	   @Override
	   public void run() {
		 int indx = 0;  
		 for(int i=0;i<100;i++)
		 {
			 blq.enqueue("p"+(indx+1));
			 indx++;
		 }
		   
	   }
   }
   
   class Consumer implements Runnable{
	   @Override
	   public void run() {
		   for(int i=0;i<100;i++)
			 {
				 blq.dequeue();
			 }
	   }   
   }
	
   public static void main(String args[]) {
	   CodeTest ct = new CodeTest();
	   ct.runConsumer();
	   ct.runProducer();
   }
}
