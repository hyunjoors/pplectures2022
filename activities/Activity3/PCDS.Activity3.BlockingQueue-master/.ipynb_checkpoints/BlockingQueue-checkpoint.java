// Implementation of a subset of the java.util.concurrent.BlockingQueue interface 
public class BlockingQueue {

    // queue and dequeue string data -- not objects -- makes it easier to read
    private String [] queue;

    // queue metadata  
    private int limit = 10;
    private int head = 0;
    private int qlen = 0;

    // Create an array of strings as the queue
    public BlockingQueue(int limit){
        this.limit = limit;
        this.queue = new String [limit];
    }

    public synchronized void put(String item)
    throws InterruptedException  {
    
        // variable for slot item goes in
        int slot;

        // wait and don't add if the queue is full
        // TODO
        if (this.queue.size() == this.limit){
            wait();
        } 

        // get slot and update head and length
        // TODO 
        slot = this.head;
        if (this.head <= this.limit) {
            this.head += 1;
        } else {
            this.head = 1;
        }
        
        this.qlen += 1;

        // notify takers if this is the first item in queue
        // TODO
        if (this.queue.size() == 1) {
            notifyAll();
        }

        // add the item
        this.queue[slot] = item;
    }

    public synchronized String take()
    throws InterruptedException {

        // slot to be taken and deleted
        int tail;

        // don't take from an empty queue
        // TODO 

        //get slot 
        // TODO 

        // if taking from a full queue, notify putters
        
        // update queue length

        // take the item and dereference pointer for garbage collection
        String ret_obj = this.queue[tail];
        queue[tail]=null;

        // return item
        return ret_obj;
    }
}
