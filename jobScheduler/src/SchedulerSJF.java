import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class SchedulerSJF extends SchedulerBase implements Scheduler {
    Platform thisPlatform;
    Queue<Process> processes;

    public SchedulerSJF(Platform platform) {
        thisPlatform = platform;
        Comparator<Process> jobLengthComparator = new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getTotalTime() - p2.getTotalTime();
            }
        };
        processes = new PriorityQueue<>(jobLengthComparator);
    }

    @Override
    public void notifyNewProcess(Process p) {
        contextSwitches +=1;
        processes.add(p);
    }

    @Override
    public Process update(Process cpu) {
        Process p = processes.peek();
        if (p != null) {
            if (p.getElapsedTotal() == 0 ) {
                thisPlatform.log("Scheduled: " + processes.peek().getName());
            }
            if (p.isBurstComplete()){
                processes.remove();
                thisPlatform.log("Process " + p.getName() + " burst complete");
                contextSwitches +=1;
                if(p.isExecutionComplete()) {
                    thisPlatform.log("Process " + p.getName() + " execution complete");
                    if (processes.peek() != null) {
                        thisPlatform.log("Scheduled: " + processes.peek().getName());
                    }
                    return processes.peek();
                }
                Process updated = new Process(p.getName(),p.getBurstTime(),p.getBurstTime(),p.getTotalTime()-p.getBurstTime());
                notifyNewProcess(updated);
                if (processes.peek() != null) {
                    thisPlatform.log("Scheduled: " + processes.peek().getName());
                }
                return processes.peek();
            }
        }
        return p;
    }
}