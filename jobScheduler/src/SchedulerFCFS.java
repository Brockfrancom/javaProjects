import java.util.LinkedList;
import java.util.Queue;

public class SchedulerFCFS extends SchedulerBase implements Scheduler {
    Platform thisPlatform;
    Queue<Process> processes;

    public SchedulerFCFS(Platform platform) {
        thisPlatform = platform;
        processes = new LinkedList<>();
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
                Process updated = new Process(p.getName(),p.getBurstTime(),p.getBurstTime(),p.getTotalTime()-p.getElapsedTotal());
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