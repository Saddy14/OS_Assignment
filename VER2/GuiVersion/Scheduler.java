package GuiVersion;

import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler {
	
	private final List<Process> processInputList; // Process Input = data collected in every processInput
	private final List<Process> processOutputList; // Process Output = result after running scheduler
	private int timeQuantum; // for round robin

	public Scheduler()
	{
		processInputList = new ArrayList<>();
		processOutputList = new ArrayList<>();
		timeQuantum = 3;
	}

	public boolean add(Process inputprocessInput)
	{
		return processInputList.add(inputprocessInput);
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
		
		for (Process processInput : processInputList) 
		{
			total += processInput.getWaitingTime();
		}
		
		return total;
	}
	
	// return total turnaround time
	public int getTotalTurnAroundTime() 
	{
		int total = 0;
		
		for (Process processInput : processInputList) 
		{
			total += processInput.getTurnaroundTime();
		}
		
		return total;
	}
	
	// return average waiting time
	public float getAverageWaitingTime()
	{
		float avg = (float) 0.0;

		for (Process processInput : processInputList)
		{
			avg += processInput.getWaitingTime();
		}

		return avg / processInputList.size();
	}
	
	// return average turnaround time
	public float getAverageTurnAroundTime()
	{
		float avg = (float) 0.0;

		for (Process inptprocessInput : processInputList)
		{
			avg += inptprocessInput.getTurnaroundTime();
		}

		return avg / processInputList.size();
	}
	
	public Process getOutput(Process processInput)
	{
		for (Process processOutput : processOutputList)
		{
			if (processInput.getProcessName().equals(processOutput.getProcessName()))
			{
				return processOutput;
			}
		}

		return null;
	}

	public Process getInput(String processName)
	{
		for (Process processInput : processInputList)
		{
			if (processInput.getProcessName().equals(processName))
			{
				return processInput;
			}
		}

		return null;
	}


	public List<Process> getInputList()
	{
		return processInputList;
	}

	public List<Process> getOutputList()
	{
		return processOutputList;
	}

	
	//Depends on the process name
	public abstract void process();
}


