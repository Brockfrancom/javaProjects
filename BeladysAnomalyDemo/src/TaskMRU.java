import java.util.LinkedList;

public class TaskMRU implements Runnable {
    LinkedList queue = new LinkedList();
    String[] sequence;
    int maxMemoryFrames;
    int maxPageReference;
    int[] pageFaults;
    int simulation;
    
    /**
     * Notes about this class, I have 2 constructors, one is for the test code, and the other is for the sumulations. I
     * decided to modify it this way to make it easier to record the results.
     *
     * if you are testing like is done in the assignment description, please use the constructor with 5 arguments.
     */

    public TaskMRU(String[] osequence, int omaxMemoryFrames, int omaxPageReference, int[] opageFaults, int osim) {
        sequence = osequence;
        maxMemoryFrames = omaxMemoryFrames;
        maxPageReference = omaxPageReference;
        pageFaults = opageFaults;
        simulation = osim;
    }

    public TaskMRU(String[] osequence, int omaxMemoryFrames, int omaxPageReference, int[] opageFaults) {
        sequence = osequence;
        maxMemoryFrames = omaxMemoryFrames;
        maxPageReference = omaxPageReference;
        pageFaults = opageFaults;
    }

    public synchronized void run() {
        pageFaults[maxMemoryFrames] = 0;
        for (String i : sequence) {
            if (!queue.contains(i) && queue.size() < maxMemoryFrames) {
                queue.addFirst(i);
                pageFaults[maxMemoryFrames] += 1;
            } else if (!queue.contains(i)) {
                queue.removeFirst();
                queue.addFirst(i);
                pageFaults[maxMemoryFrames] += 1;
            } else {
                queue.removeFirstOccurrence(i);
                queue.addFirst(i);
            }
        }
        Assign6.mruPF[maxMemoryFrames][simulation] = pageFaults[maxMemoryFrames];
    }
}