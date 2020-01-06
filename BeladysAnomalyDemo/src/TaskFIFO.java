import java.util.LinkedList;

public class TaskFIFO implements Runnable {
    LinkedList queue = new LinkedList();
    String[] sequence;
    int maxMemoryFrames;
    int maxPageReference;
    int[] pageFaults;
    int simulation;

    public TaskFIFO(String[] osequence, int omaxMemoryFrames, int omaxPageReference, int[] opageFaults, int osimulation) {
        sequence = osequence;
        maxMemoryFrames = omaxMemoryFrames;
        maxPageReference = omaxPageReference;
        pageFaults = opageFaults;
        simulation = osimulation;
    }

    @Override
    public synchronized void run() {
        pageFaults[maxMemoryFrames] = 0;
        for (String i : sequence) {
            if (!queue.contains(i) && queue.size() < maxMemoryFrames) {
                queue.add(i);
                pageFaults[maxMemoryFrames] += 1;
            } else if (!queue.contains(i)) {
                queue.remove();
                queue.add(i);
                pageFaults[maxMemoryFrames] += 1;
            } else {
                //Do nothing.
            }
        }
        Assign6.fifoPF[maxMemoryFrames][simulation] = pageFaults[maxMemoryFrames];
    }
}