import java.util.ArrayList;

public class computePI {
    /**
     * Brock Francom
     * A02052161
     * CS 3100
     * Assign 4
     *
     * This is the main class for computing 1000 digits of pi. Dependencies of this file are BPP.java,
     * TaskQueue.java, and ResultTable.java.
     */
    public static void main(String[] args) {
        //Initialize the privateQueue and result classes.
        int numberOfDigitsToCompute = 1000;
        TaskQueue queue = new TaskQueue();
        ResultTable result = new ResultTable();

        //Create all the tasks, and add them to a temporary array.
        ArrayList tempArray = new ArrayList();
        for (int i=0;i<=numberOfDigitsToCompute;i++) {
            computeDigit task = new computeDigit(i);
            tempArray.add(task);
        }

        //Shuffle the array and then add each one to the queue.
        java.util.Collections.shuffle(tempArray);
        for (int i=0;i<tempArray.size();i++) {
            queue.enqueue(tempArray.get(i));
        }

        //Start a thread for each core.
        //Record start time.
        long endTime;
        long startTime = System.currentTimeMillis();
        for (int i=0;i<Runtime.getRuntime().availableProcessors();i++) {
            computeThread thread = new computeThread(queue, result);
            thread.start();
        }

        //Once the queue is empty, print out result.
        String resultString = "3."; //Start with the "3."
        int dotsPrinted = 0;
        boolean dotPrinted = false;
        while (true) {
            //This logic handles the printing of the "." for every 10 results calculated.
            if (result.results.size() % 10 == 0) {
                if (!dotPrinted) {
                    System.out.print(".");
                    dotPrinted = true;
                    dotsPrinted += 1;
                }
                //Print a new line so the dots don't get too crazy.
                if (dotsPrinted == 20) {
                    System.out.println("\n");
                    dotsPrinted = 0;
                }
            }
            //Reset the dotPrinted boolean.
            if (result.results.size() % 10 != 0) {
                dotPrinted = false;
            }

            //All results are in, print the results.
            if (result.results.size() == numberOfDigitsToCompute + 1) {
                endTime = System.currentTimeMillis();
                //Start at 1, because 0 is calculated to be a 4.
                System.out.println();
                for (int i=1;i<result.results.size();i++) {
                    resultString += (String)result.results.get(i);
                }
                break;
            }
            System.out.flush(); //Flush the buffer
        }

        //Print the result.
        System.out.println(resultString);
        //Print out the time elapsed.
        System.out.println("Pi Computation took " + (endTime-startTime) + " ms");
    }
}

//Class for the Threads
class computeThread extends Thread {
    private static TaskQueue privateQueue;
    private static ResultTable privateResult;

    //Constructor for the Threads
    public computeThread(TaskQueue queueClass, ResultTable resultClass) {
        privateQueue = queueClass;
        privateResult = resultClass;
    }

    public void run() {
        //While the privateQueue is not empty, get a task and run it.
        while (!privateQueue.isEmpty()) {
            computeDigit task;
            task = (computeDigit) privateQueue.dequeue();
            int result = BPP.getDecimal(task.digitToCompute);
            String resultString = Integer.toString(result);
            //This logic adds a "0" if the result string is less than 9 characters.
            if (resultString.length() != 9) {
                privateResult.results.put(task.digitToCompute, "0");
            } else {
                String finalResult = Character.toString(Integer.toString(result).charAt(0));
                privateResult.results.put(task.digitToCompute, finalResult);
            }
        }
    }
}

//Class for the Tasks
class computeDigit {
    int digitToCompute;
    //Constructor for the tasks
    public computeDigit(int i) {
        digitToCompute = i;
    }
}