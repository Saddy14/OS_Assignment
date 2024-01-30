package GuiVersion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PreemptiveSJF extends CPUScheduler {
    private Queue<Process> readyQueue;
    private Process currentProcess;
    private List<Process> processOutputList = new ArrayList<>();
    int timeCounter;

    @Override
    public void process() {
        // priority with burst time, when a shorter burst time is added the priority is higher
        readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getBurstTime));
        //readyQueue = new LinkedList<>();
        List<Process> inputList = this.getInputList();
        int SchedulerTotalTime = calculatetotaltime();
        // Sort input list with Arrival Time 1st and then Burst Time
        Collections.sort(inputList,Comparator.comparingInt(Process::getArrivalTime).thenComparingInt(Process::getBurstTime));

        timeCounter = this.getInputList().get(0).getArrivalTime(); // earliest arrival time
        System.out.println("Initial Time Counter:" + timeCounter);
        while (timeCounter <= SchedulerTotalTime) {
            System.out.println("Executed Time Counter:" + timeCounter);
            checkArrivalTime(inputList);
            assignNewProcessor();
            if (currentProcess != null && currentProcess.getStartTime() != timeCounter) {
                // Execute the current process
                currentProcess.setBurstTime(currentProcess.getBurstTime() - 1);
                System.out.println(currentProcess.getProcessName()+"Burst Time:" +currentProcess.getBurstTime());
                System.out.println("2");
                        
            }
            
            checkPreemptive(inputList);
            // If current process is finished, remove it
            if (currentProcess != null && currentProcess.getBurstTime() == 0) {
                currentProcess.setFinishTime(timeCounter);
                currentProcess.setTurnaroundTime(timeCounter - currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getOriginalBurst());
                processOutputList.add(currentProcess);
                currentProcess = null;
                System.out.println("3");
                assignNewProcessor();
                
            }


            // Increment time counter
            timeCounter++;
        }

        this.getOutputList().addAll(processOutputList);
        for (Process p : this.getOutputList()) {
            System.out.println("Start Time:" + p.getStartTime());
            System.out.println("Finish Time:" + p.getFinishTime());
        }
    }

    public void setStartTime() {
        //if (currentProcess.getOriginalBurst() == currentProcess.getBurstTime()) {
            currentProcess.setStartTime(timeCounter);
        //}
    }
    
    public void checkArrivalTime(List<Process> inputList) {
        for (Process process : this.getInputList()) {
            if (process.getArrivalTime() == timeCounter) {
                readyQueue.add(process);
            }
        }
    }

    public void checkPreemptive(List<Process> inputList) {
        if (currentProcess != null && currentProcess.getBurstTime() > 0) {
            // Check if there are processes with shorter burst times in the input list
            for (Process process : inputList) {
                if (process.getArrivalTime() == timeCounter && process.getOriginalBurst() < currentProcess.getBurstTime()) {
                    // Preempt the current process
                    readyQueue.add(currentProcess);
                    processOutputList.add(new Process(currentProcess.getProcessName(), currentProcess.getStartTime(), timeCounter));
                    //currentProcess.setBurstTime(currentProcess.getBurstTime() - (timeCounter - currentProcess.getStartTime()));
                    currentProcess.setBurstTime(currentProcess.getBurstTime());
                    currentProcess = readyQueue.poll();
                    currentProcess.setStartTime(timeCounter);
                    System.out.println("Preemptive: "+currentProcess.getProcessName());
                }
            }
        }
    }

    public int calculatetotaltime() {
        int totalTime = 0;
        for (Process process : this.getInputList()) {
            process.setOriginalBurst(process.getBurstTime());
            totalTime += process.getBurstTime();
        }
        totalTime += this.getInputList().get(0).getArrivalTime();
        return totalTime;
    }

    public void assignNewProcessor(){
        if (currentProcess == null&& !readyQueue.isEmpty()) {
            currentProcess = readyQueue.poll();
            setStartTime();
            System.out.println(currentProcess.getProcessName());
            System.out.println("1");
        }
    }
}
