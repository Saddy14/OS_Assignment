package GuiVersion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import java.util.Iterator;

public class RoundRobin extends Scheduler {

	private Queue<Process> readyQueue;
	// private List<Process> processList;
	private int currentTime;
	private int quantumCounter;
	private int totalTime;
	
	public void process() {

		readyQueue = new LinkedList<>(); // request queue initially empty
		currentTime = 0; // current time
		quantumCounter = this.getTimeQuantum();
		totalTime = 0; // total time for all process

		// sorting according to Arrival Time and Burst Time
		Collections.sort(this.getInputList(),
				Comparator.comparingInt(Process::getArrivalTime) // primary comparison based on arrival time
						.thenComparingInt(Process::getBurstTime)); // secondary comparison based on burst time

		setBurstTime();
		currentTime += this.getInputList().get(0).getArrivalTime();
		
		checkArrivalTime();

		 List<Process> temporaryArray = new ArrayList<>();
		 temporaryArray = this.getInputList();
		 HashSet<String> tempHS = new HashSet<String>();
		 for (int i = 0; i < this.getInputList().size(); i++) {
			tempHS.add(temporaryArray.get(i).getProcessName());
		 }

		while (!tempHS.isEmpty()) {
			int currTime = currentTime;
			int currTimePlus= ++currentTime;
			
			if (readyQueue.size() == 0 ) {
				checkArrivalTime(); // Check for new arrivals
        		this.getOutputList().add(new Process("--", currTime, currTimePlus));
				continue; 
        	}
        	
			if (readyQueue.peek() != null) { // there is a process ready to be executed
				readyQueue.peek().setBurstTime(readyQueue.peek().getBurstTime() - 1);  //
				quantumCounter--;
				this.getOutputList().add(new Process(readyQueue.peek().getProcessName(), currTime, currTimePlus));

				checkArrivalTime(); // Check for new arrivals

				if (readyQueue.peek().getBurstTime() == 0) {
					if (tempHS.contains(readyQueue.peek().getProcessName()))
						tempHS.remove(readyQueue.peek().getProcessName());
					
					readyQueue.remove(); // Remove from queue if process finished
					quantumCounter = this.getTimeQuantum(); // Reset quantumCounter
				} else if (quantumCounter == 0) {
					Process temp = readyQueue.poll(); // Remove from the front and get the process
					readyQueue.add(temp); // Add to the back
					quantumCounter = this.getTimeQuantum(); // Reset quantumCounter
				}
			}
			
		}

		calculateFTW();
		
	}

	public void checkArrivalTime() {
		for (Process process : this.getInputList()) {
			if (process.getArrivalTime() == currentTime) {
				readyQueue.add(process);
			}
		}
	}

	//set burst time for each process
	public void setBurstTime() {
		for (Process process : this.getInputList()) {
			process.setOriginalBurst(process.getBurstTime());
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

		// calculate finish time, tat time waiting time
		for (Process process : this.getInputList()) {
			for (int i = this.getOutputList().size() - 1; i >= 0; i--) { 
				if (process.getProcessName().equals(this.getOutputList().get(i).getProcessName())) {
					process.setFinishTime(this.getOutputList().get(i).getFinishTime());
					process.setTurnaroundTime(process.getFinishTime() - process.getArrivalTime());
					process.setWaitingTime(process.getTurnaroundTime() - process.getOriginalBurst());
					break;
				}
			}
		}
	}
}