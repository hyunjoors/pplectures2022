import java.nio.charset.Charset;
import java.util.Random;

// The Producer of Producer/Consumer
//   a thread the will put items on the queue

public class Producer implements Runnable {

    protected BlockingQueue3 queue = null;
    private int count;

    public Producer(BlockingQueue3 queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public void run() {
        try {
            // Place count objects on the queue
            for (int i=0; i<this.count; i++)
            {
                // a small string for testing
                queue.put(Integer.toString(i));

                // output if your debugging
                System.out.println("put " + Integer.toString(i));
//                System.out.println("put " + queue.print());

                // sleep can slow things down to make output understandable
//                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}
