package GuiVersion;

import java.util.Collections;
import java.util.Comparator;
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

		calculatetotaltime();
		currentTime += this.getInputList().get(0).getArrivalTime();
		
		checkArrivalTime();

		while (currentTime < totalTime) {
			//Process p = readyQueue.peek();
			if (readyQueue.peek() != null) { // there is a process ready to be executed
				readyQueue.peek().setBurstTime(readyQueue.peek().getBurstTime() - 1);  //
				quantumCounter--;
				this.getOutputList().add(new Process(readyQueue.peek().getProcessName(), currentTime, ++currentTime));

				checkArrivalTime(); // Check for new arrivals

				if (readyQueue.peek().getBurstTime() == 0) {
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

		// rearrange the timeline
		// for (int i = this.getProcessOutputList().size() - 1; i > 0; i--) {
		// List<ProcessOutput> processOutputList = this.getProcessOutputList();
		// if (processOutputList.get(i -
		// 1).getProcessName().equals(processOutputList.get(i).getProcessName())) {
		// processOutputList.get(i -
		// 1).setFinishTime(processOutputList.get(i).getFinishTime());
		// processOutputList.remove(i);
		// }
		// }
		
	}

	public void checkArrivalTime() {
		for (Process process : this.getInputList()) {
			if (process.getArrivalTime() == currentTime) {
				readyQueue.add(process);
			}
		}
	}

	public int calculatetotaltime() {
		for (Process process : this.getInputList()) {
			process.setOriginalBurst(process.getBurstTime());
			totalTime += process.getBurstTime();
		}
		totalTime += this.getInputList().get(0).getArrivalTime();
		return totalTime;
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
			for (int i = this.getOutputList().size() - 1; i >= 0; i--) { // looping from the back
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