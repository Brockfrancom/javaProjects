import java.util.LinkedList;
import java.util.Queue;

public class SchedulerRR extends SchedulerBase implements Scheduler  {
    Platform thisPlatform;
    Queue<Process> processes;
    int timeQuantum;

    public SchedulerRR(Platform platform, int timeQuantum1) {
        thisPlatform = platform;
        timeQuantum = timeQuantum1;
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
            if (p.getElapsedTotal() == 0) {
                thisPlatform.log("Scheduled: " + processes.peek().getName());
            }
            if (p.isExecutionComplete()) {
                processes.remove();
                contextSwitches += 1;
                thisPlatform.log("Process " + p.getName() + " execution complete");
                if (processes.peek() != null) {
                    thisPlatform.log("Scheduled: " + processes.peek().getName());
                }
                return processes.peek();
            }
            if (p.getElapsedTotal() % timeQuantum == 0 && p.getElapsedTotal() != 0) {
                processes.remove();
                thisPlatform.log("Time quantum complete for process " + p.getName());
                contextSwitches += 1;
                Process updated = new Process(p.getName(), p.getStartTime(), p.getBurstTime(), p.getTotalTime() - timeQuantum);
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
