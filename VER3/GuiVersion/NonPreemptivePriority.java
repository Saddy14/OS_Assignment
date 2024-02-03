package GuiVersion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NonPreemptivePriority extends Scheduler {
    private Queue<Process> readyQueue;
    private int currentTime;
    private int totalTime;

	private List<Process> processList;

    public void process() {

        readyQueue = new LinkedList<>(); // request queue initially empty
        currentTime = 0; // current time
        totalTime = 0; // total time for all process

        Collections.sort(this.getInputList(),
        Comparator.comparingInt(Process::getArrivalTime) // primary comparison based on arrival time
                .thenComparingInt(Process::getBurstTime)); // secondary comparison based on burst time

		processList = new ArrayList<Process>();
	
		for (Process process : this.getInputList()) {
			processList.add(new Process(process.getProcessName(),process.getArrivalTime(),process.getBurstTime(), process.getPriority()));
	   }
	  
        while (!processList.isEmpty()|| !readyQueue.isEmpty()) {
            if (!readyQueue.isEmpty()) {
                // Select the process with the shortest prioerity
                Process currentProcess = selectShortestPriority();
                int startTime = Math.max(currentTime, currentProcess.getArrivalTime());
                int finishTime = startTime + currentProcess.getBurstTime();
                this.getOutputList().add(new Process(currentProcess.getProcessName(), startTime, finishTime));
				System.out.println(currentProcess.getProcessName());
				

                // Remove executed process from ready queue
                readyQueue.remove(currentProcess);
				for (int i = 0; i < processList.size(); i++) {
					if (processList.get(i).getProcessName().equals(currentProcess.getProcessName())) {
						processList.remove(i);
						break;
					}
				}

                // Check for processes arriving during the current process's execution
                for (Process p : processList) {
                    if (p.getArrivalTime() <= finishTime) {
						if(!readyQueue.contains(p)){
							readyQueue.add(p);
						}
                       
                    }
                }
			
                currentTime = finishTime; // Update current time to the finish time of the current process
            } else {
				
                // If ready queue is empty, move time to the arrival of the next process
                if (!processList.isEmpty()) {
                    currentTime = processList.get(0).getArrivalTime();
                    readyQueue.add(processList.remove(0));
                }
            }
        }
		calculateFTW();
    }

	
    private Process selectShortestPriority() {
		Process shortestJob = Collections.min(readyQueue, Comparator.comparingInt(Process::getPriority));
        return shortestJob;
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
					process.setTurnaroundTime(process.getFinishTime() - process.getArrivalTime());
					process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());
					break;
				}
			}
		}
	}
}


