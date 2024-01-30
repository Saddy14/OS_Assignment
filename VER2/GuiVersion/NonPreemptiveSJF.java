package GuiVersion;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NonPreemptiveSJF extends Scheduler {

    private Queue<Process> readyQueue;
    private int currentTime;
    private int totalTime;

    public void process() {

        readyQueue = new LinkedList<>(); // request queue initially empty
        currentTime = 0; // current time
        totalTime = 0; // total time for all process

        Collections.sort(this.getInputList(),
        Comparator.comparingInt(Process::getArrivalTime) // primary comparison based on arrival time
                .thenComparingInt(Process::getBurstTime)); // secondary comparison based on burst time

        calculatetotaltime();
        currentTime += this.getInputList().get(0).getArrivalTime();

        checkArrivalTime();

        while (currentTime < totalTime) {
			if (readyQueue.peek() != null) { // there is a process ready to be executed
				readyQueue.peek().setBurstTime(readyQueue.peek().getBurstTime() - 1);  //
				this.getOutputList().add(new Process(readyQueue.peek().getProcessName(), currentTime, ++currentTime));

				checkArrivalTime(); // Check for new arrivals

				if (readyQueue.peek().getBurstTime() == 0) {
					readyQueue.remove(); // Remove from queue if process finished
				} 
			}
		}

        calculateFTW();
    }

    public int calculatetotaltime() {
        for (Process process : this.getInputList()) {
            process.setOriginalBurst(process.getBurstTime());
            totalTime += process.getBurstTime();
        }
        totalTime += this.getInputList().get(0).getArrivalTime();
        return totalTime;
    }

    public void checkArrivalTime() {
        for (Process process : this.getInputList()) {
            if (process.getArrivalTime() == currentTime) {
                readyQueue.add(process);
            }
        }
    }

    public void calculateFTW(){
        //remove any reduncdency timeline
		List<Process> processOutputList = this.getOutputList();
		Iterator<Process> iterator = processOutputList.iterator();
		Process previous = null;
		while (iterator.hasNext()) {
			Process current = iterator.next();
			if (previous != null && current.getProcessName().equals(previous.getProcessName())) {
				previous.setFinishTime(current.getFinishTime());
				iterator.remove(); // Remove the current element from the list
			} else {
				previous = current;
			}
		}

		// setting FT, TAT and WT for all the Process
		for (Process process : this.getInputList()) {
			for (int i = 0; i < this.getOutputList().size(); i++) { 
				if (process.getProcessName().equals(this.getOutputList().get(i).getProcessName())) {
					process.setFinishTime(this.getOutputList().get(i).getFinishTime());
					// setting TAT and WT
					process.setTurnaroundTime(process.getFinishTime() - process.getArrivalTime());
					process.setWaitingTime(process.getTurnaroundTime() - process.getOriginalBurst());
					break;
				}
			}
		}
	}
}
