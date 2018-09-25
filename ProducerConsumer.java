package ProducerConsumer;
import java.util.Vector;


public class ProducerConsumer {
	public static void main(String[] args)
	{
		
		Vector buffer = new Vector();
		int size = 5; // for example
		
		Thread prodThr = new Thread(new Producer(buffer,size), "Producer");
		Thread consThr = new Thread(new Consumer(buffer,size), "Consumer");
		prodThr.start();
		consThr.start();
		
		
	}

}

class Producer implements Runnable{
	private final Vector buffer;
	private final int size;
	
	public Producer( Vector bfr, int sz) {
		this.buffer = bfr;
		this.size = sz;
		
	}
	
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println("Producer produced: "+ i);
		
		    try {
		    	produce(i);
			
		    }
		    catch (Exception e) {
			// TODO: handle exception
		    }
		}
	}

	private void produce(int i) throws Exception{
		if (buffer.size() == size) {
            synchronized (buffer) { //synchronized allows this thread to complete it till the condition
                System.out.println("Buffer is full " + Thread.currentThread().getName()
                                    + " is sleeping , size: " + buffer.size());

                buffer.wait();
            }
        }
		synchronized (buffer) {
            buffer.add(i);
            buffer.notifyAll();
        }
		
	}
}


class Consumer implements Runnable {

    private final Vector buffer;
    private final int size;

    public Consumer(Vector bfr, int sz) {
        this.buffer = bfr;
        this.size = sz;
    }

   
    public void run() {
        while (true) {
            try {
                System.out.println("Consumed: " + consume());
                Thread.sleep(100);
            } catch (Exception e) {
               
            }

        }
    }

    private int consume() throws Exception {
        //wait if queue is empty
        if (buffer.isEmpty()) {
            synchronized (buffer) {
                System.out.println("Buffer is empty " + Thread.currentThread().getName()
                                    + " is sleeping , size: " + buffer.size());

                buffer.wait();
            }
        }

        //Otherwise consume element and notify waiting producer
        synchronized (buffer) {
            buffer.notifyAll();
            return (Integer) buffer.remove(0);
        }
    }
}
		

