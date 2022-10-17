import java.util.Arrays;
import java.util.Objects;

// Implementation of a subset of the java.util.concurrent.BlockingQueue interface 
public class BlockingQueue2 {

    // queue and dequeue string data -- not objects -- makes it easier to read
    private String [] queue;

    // queue metadata  
    private int limit = 10;
    private int head = 0;
    private int tail = 0;
    private int qlen = 0;

    // Create an array of strings as the queue
    public BlockingQueue2(int limit){
        this.limit = limit;
        this.queue = new String [limit];
    }
    
    public String print() {
		return Arrays.toString(this.queue);
    }

    public synchronized void put(String item)
    throws InterruptedException  {
        // variable for slot item goes in
        int slot;

        // wait and don't add if the queue is full
        // TODO
        while (this.qlen == this.limit){
        	System.out.println("put func waiting");
            wait();
        } 
//        System.out.println("put bf "+this.head+" "+this.tail+" "+this.qlen+" "+Arrays.toString(this.queue));

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

        // add the item
        this.queue[slot] = item;
        
        System.out.println("put af "+this.head+" "+this.tail+" "+this.qlen+" "+Arrays.toString(this.queue));
//        System.out.println("put "+Arrays.toString(this.queue));
    }

    public synchronized String take()
    throws InterruptedException {
        // slot to be taken and deleted
        int slot;

        // don't take from an empty queue
        // TODO 
        while (this.qlen == 0){
        	System.out.println("take func waiting");
        	wait();
        }

        //get slot 
        // TODO 
//        System.out.println("take bf "+this.head+" "+this.tail+" "+this.qlen+" "+Arrays.toString(this.queue));
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

        // take the item and dereference pointer for garbage collection
        String ret_obj = this.queue[slot];
        queue[slot]=null;
        
//        System.out.println("take af "+this.head+" "+this.tail+" "+this.qlen+" "+Arrays.toString(this.queue));

        return ret_obj;
    }
}
