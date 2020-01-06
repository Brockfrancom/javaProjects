import java.util.LinkedList;

public class TaskLRU implements Runnable {
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

    public TaskLRU(String[] osequence, int omaxMemoryFrames, int omaxPageReference, int[] opageFaults, int osimulation) {
        sequence = osequence;
        maxMemoryFrames = omaxMemoryFrames;
        maxPageReference = omaxPageReference;
        pageFaults = opageFaults;
        simulation = osimulation;
    }

    public TaskLRU(String[] osequence, int omaxMemoryFrames, int omaxPageReference, int[] opageFaults) {
        sequence = osequence;
        maxMemoryFrames = omaxMemoryFrames;
        maxPageReference = omaxPageReference;
        pageFaults = opageFaults;
    }

    public synchronized void run() {
        pageFaults[maxMemoryFrames] = 0;
        for (String i : sequence) {
            if (!queue.contains(i) && queue.size() < maxMemoryFrames) {
                queue.addLast(i);
                pageFaults[maxMemoryFrames] += 1;
            } else if (!queue.contains(i)) {
                queue.removeFirst();
                queue.addLast(i);
                pageFaults[maxMemoryFrames] += 1;
            } else {
                queue.removeFirstOccurrence(i);
                queue.addLast(i);
            }
        }
        Assign6.lruPF[maxMemoryFrames][simulation] = pageFaults[maxMemoryFrames];
    }
}