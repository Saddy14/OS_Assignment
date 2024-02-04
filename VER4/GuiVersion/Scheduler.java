package GuiVersion;

import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler {
	
	private List<Process> processInputList; 
	private List<Process> processOutputList; 
	private int timeQuantum; // for round robin

	
	//Depends on the process name
	public abstract void process();
	
	public Scheduler()
	{
		processInputList = new ArrayList<>();
		processOutputList = new ArrayList<>();
		timeQuantum = 3;
	}

	public boolean add(Process processInput)
	{
		return processInputList.add(processInput);
	}

	public void setTimeQuantum(int timeQuantum)
	{
		this.timeQuantum = timeQuantum;
	}
	
	
	// return time quantum
	public int getTimeQuantum()
	{
		return timeQuantum;
	}
	
	// return total waiting time
	public int getTotalWaitingTime() 
	{
		int total = 0;
		
		for (Process p : processInputList) 
		{
			total += p.getWaitingTime();
		}
		
		return total;
	}
	
	// return total turnaround time
	public int getTotalTurnAroundTime() 
	{
		int total = 0;
		
		for (Process p : processInputList) 
		{
			total += p.getTurnaroundTime();
		}
		
		return total;
	}
	
	public List<Process> getInputList()
	{
		return processInputList;
	}

	public List<Process> getOutputList()
	{
		return processOutputList;
	}
	
	// return average waiting time
	public float getAverageWaitingTime()
	{
		float avg = (float) 0.0;

		for (Process p : processInputList)
		{
			avg += p.getWaitingTime();
		}

		return avg / processInputList.size();
	}
	
	// return average turnaround time
	public float getAverageTurnAroundTime()
	{
		float avg = (float) 0.0;

		for (Process p: processInputList)
		{
			avg += p.getTurnaroundTime();
		}

		return avg / processInputList.size();
	}
	
	public Process getOutput(Process processInput)
	{
		for (Process pOutput : processOutputList)
		{
			if (processInput.getProcessName().equals(pOutput.getProcessName()))
			{
				return pOutput;
			}
		}

		return null;
	}

	public Process getInput(String processName)
	{
		for (Process p : processInputList)
		{
			if (p.getProcessName().equals(processName))
			{
				return p;
			}
		}

		return null;
	}


	

	
}


