import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class SchedulerSRTF extends SchedulerBase implements Scheduler {
    Platform thisPlatform;
    Queue<Process> processes;

    public SchedulerSRTF(Platform platform) {
        thisPlatform = platform;
        Comparator<Process> jobLengthComparator = new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return (p1.getTotalTime()-p1.getElapsedTotal()) - (p2.getTotalTime()-p2.getElapsedTotal());
            }
        };
        processes = new PriorityQueue<>(jobLengthComparator);
    }

    @Override
    public void notifyNewProcess(Process p) {
        contextSwitches += 1;
        processes.add(p);
    }

    @Override
    public Process update(Process cpu) {
        Process p = processes.peek();
        if (p != null) {
            if (p.getElapsedTotal() == 0) {
                if (cpu != null) {
                    thisPlatform.log("Preemptively removed: " + cpu.getName());
                }
                thisPlatform.log("Scheduled: " + processes.peek().getName());
                contextSwitches += 1;
            }
            if (p.isBurstComplete()){
                processes.remove();
                thisPlatform.log("Process " + p.getName() + " burst complete");
                contextSwitches +=1;
                if(p.isExecutionComplete()) {
                    thisPlatform.log("Process " + p.getName() + " execution complete");
                    if (processes.peek()!= null) {
                        thisPlatform.log("Scheduled: " + processes.peek().getName());
                    }
                    return processes.peek();
                }
            }
        }
        return p;
    }
}