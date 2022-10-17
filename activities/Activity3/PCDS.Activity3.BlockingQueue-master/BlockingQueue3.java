import java.util.Arrays;
import java.util.Objects;

// Implementation of a subset of the java.util.concurrent.BlockingQueue interface 
public class BlockingQueue3 {

    // queue and dequeue string data -- not objects -- makes it easier to read
    private String [] queue;

    // queue metadata  
    private int limit = 10;
    private int head = 0;
    private int tail = 0;
    private int qlen = 0;

    // Create an array of strings as the queue
    public BlockingQueue3(int limit){
        this.limit = limit;
        this.queue = new String [limit];
    }
    
    public String print() {
		return Arrays.toString(this.queue);
    }

    public void put(String item)
    throws InterruptedException  {
    	
        // variable for slot item goes in
        int slot;
        synchronized(this) {
        // wait and don't add if the queue is full
        // TODO
        while (this.qlen == this.limit){
            wait();
        } 

        // get slot and update head and length
        // TODO 
        
        slot = this.tail;
        this.tail = (this.tail + 1) % this.limit;
        
        if (this.qlen <= this.limit) {
            this.qlen += 1;
        } else {
        	this.qlen += 1;
        }

        // notify takers if this is the first item in queue
        // TODO
        if (this.qlen == 1) {
            notifyAll();
        }
        }
        // add the item
        System.out.println("put "+item+" "+Arrays.toString(this.queue));
        this.queue[slot] = item;

    }

    public String take()
    throws InterruptedException {
    	
        // slot to be taken and deleted
        int slot;
        synchronized(this) {
        // don't take from an empty queue
        // TODO 
        while (this.qlen == 0){
        	wait();
        }

        //get slot 
        // TODO 
        
        slot = this.head;
        this.head = (this.head + 1) % this.limit;

        // if taking from a full queue, notify putters
        if (this.qlen == this.limit) {
        	notifyAll();
        }
        
        // update queue length
        if (this.qlen > 0) {
            this.qlen -= 1;
        } else {
        	this.qlen = 0;
        }
        }
        // take the item and dereference pointer for garbage collection
        String ret_obj = this.queue[slot];
        System.out.println("take "+ret_obj+" "+Arrays.toString(this.queue));
        queue[slot]=null;
        
        return ret_obj;
    }
}
