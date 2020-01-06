import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Assign6 {
    /**
     * This code simulates the page replacement algorithms.
     */
    private static final int MAX_PAGE_REFERENCE = 250;
    //[memframe][simulation]
    static int[][] fifoPF = new int[101][1000];
    static int[][] lruPF = new int[101][1000];
    static int[][] mruPF = new int[101][1000];

    public static void main(String[] args) {
        //If you want to run the test code, uncomment out the following 3 lines.
//        testLRU();
//        testMRU();
//        System.exit(0);
        ExecutorService threadpool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int simulations = 0;
        //Do 1000 simulations
        while (simulations < 1000) {
            //Generate the page reference sequence of length 1000.
            String[] theSequence = new String[1000];
            int j = 0;
            for (String i : theSequence) {
                theSequence[j] = Integer.toString((int)(Math.random()* ((250 - 1)) + 1) + 1);
                j += 1;
            }
            //For each memory frame from 1 to 100, create a new task for each algorithm, add it to threadpool.
            int memFrame = 1;
            while (memFrame <= 100) {
                int[] pageFaults1 = new int[101];  // 101 because maxMemoryFrames is 100
                int[] pageFaults2 = new int[101];  // 101 because maxMemoryFrames is 100
                int[] pageFaults3 = new int[101];  // 101 because maxMemoryFrames is 100
                TaskFIFO fifoTask = new TaskFIFO(theSequence, memFrame, MAX_PAGE_REFERENCE, pageFaults1, simulations);
                TaskLRU lruTask = new TaskLRU(theSequence, memFrame, MAX_PAGE_REFERENCE, pageFaults2, simulations);
                TaskMRU mruTask = new TaskMRU(theSequence, memFrame, MAX_PAGE_REFERENCE, pageFaults3, simulations);
                threadpool.execute(fifoTask);
                threadpool.execute(lruTask);
                threadpool.execute(mruTask);
                memFrame += 1;
            }
            simulations += 1;
        }
        //Start timer, when threadpool is finished, end.
        long start = System.currentTimeMillis();
        while (true) {
            threadpool.shutdown();
            if (threadpool.isTerminated()) {
                break;
            }
        }

        // Print results
        System.out.println("Simulation took " + (System.currentTimeMillis()-start) + " ms\n");
        printMins();
        printReport("FIFO", fifoPF);
        printReport("LRU", lruPF);
        printReport("MRU", mruPF);
    }

    //This function prints how many times each one equals the minimum.
    public static void printMins() {
        long fifomin = 0;
        long lrumin = 0;
        long mrumin = 0;
        int iterator = 1;
        int simulation = 0;
        while (simulation < 1000) {
            iterator = 0;
            while (iterator <= 100) {
                long min = Math.min(Math.min(fifoPF[iterator][simulation], lruPF[iterator][simulation]), mruPF[iterator][simulation]);
                if (min == fifoPF[iterator][simulation]) {
                    fifomin += 1;
                }
                if (min == lruPF[iterator][simulation]) {
                    lrumin += 1;
                }
                if (min == mruPF[iterator][simulation]) {
                    mrumin += 1;
                }
                iterator += 1;
            }
            simulation += 1;
        }
        System.out.println("FIFO min PF: " + fifomin);
        System.out.println("LRU min PF: " + lrumin);
        System.out.println("MRU min PF: " + mrumin);
    }

    //This prints out the results of checking for baladys anamoly.
    public synchronized static void printReport(String simu, int[][] list) {
        int anomalies = 0;
        int maxDif = 0;
        long previous=0;
        int simulation = 0;
        int iterator = 1;
        System.out.println("\nBelady's Anomaly Report for " + simu);
        while (simulation < 1000) {
            previous = 0;
            iterator = 0;
            while (iterator < 100) {
                if (list[iterator][simulation] > previous && previous != 0){
                    int dif = (int)(list[iterator][simulation]-previous);
                    if (dif > maxDif){
                        maxDif = dif;
                    }
                    System.out.println("\tdetected - Previous " + previous + " : Current " + list[iterator][simulation] + "(" + dif + ")");
                    anomalies += 1;
                }
                previous = list[iterator][simulation];
                iterator += 1;
            }
            simulation += 1;
        }
        System.out.println("\t Anomaly detected " + anomalies + " times with a max difference of " + maxDif);
    }

    public static void testLRU() {
        String[] sequence1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] sequence2 = {"1", "2", "1", "3", "2", "1", "2", "3", "4"};
        int[] pageFaults = new int[4];  // 4 because maxMemoryFrames is 3

        // Replacement should be: 1, 2, 3, 4, 5, 6, 7, 8
        // Page Faults should be 9
        (new TaskLRU(sequence1, 1, MAX_PAGE_REFERENCE, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[1]);

        // Replacement should be: 2, 1, 3, 1, 2
        // Page Faults should be 7
        (new TaskLRU(sequence2, 2, MAX_PAGE_REFERENCE, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[2]);

        // Replacement should be: 1
        // Page Faults should be 4
        (new TaskLRU(sequence2, 3, MAX_PAGE_REFERENCE, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[3]);
    }

    public static void testMRU() {
        String[] sequence1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] sequence2 = {"1", "2", "1", "3", "2", "1", "2", "3", "4"};
        int[] pageFaults = new int[4];  // 4 because maxMemoryFrames is 3

        // Replacement should be: 1, 2, 3, 4, 5, 6, 7, 8
        // Page Faults should be 9
        (new TaskMRU(sequence1, 1, MAX_PAGE_REFERENCE, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[1]);

        // Replacement should be: 1, 2, 1, 3
        // Page Faults should be 6
        (new TaskMRU(sequence2, 2, MAX_PAGE_REFERENCE, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[2]);

        // Replacement should be: 3
        // Page Faults should be 4
        (new TaskMRU(sequence2, 3, MAX_PAGE_REFERENCE, pageFaults)).run();
        System.out.printf("Page Faults: %d\n", pageFaults[3]);
    }
}
