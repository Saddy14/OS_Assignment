package GuiVersion;

public class Process
{
    private int finishTime;
    private int originalBurst;
    private int startTime;  
    private boolean endStatus;
    
    private String processName;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int waitingTime;
    private int turnaroundTime;
   
    public Process(String processName, int arrivalTime, int burstTime, int priority, int waitingTime, int turnaroundTime)
    {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.waitingTime = waitingTime;
        this.turnaroundTime = turnaroundTime;
    }
    

    public Process(String processName, int startTime, int finishTime)
    {
        this.processName = processName;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
    
    public Process(String processName, int arrivalTime, int burstTime, int priorityLevel)
    {
        this(processName, arrivalTime, burstTime, priorityLevel, 0, 0);
    }

    //set arrival time
    public void setArrivalTime(int arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }
    
    //set burst time
    public void setBurstTime(int burstTime)
    {
        this.burstTime = burstTime;
    }
    
    //set priority
    public void setPriorityLevel(int priority)
    {
        this.priority = priority;
    }
    
    public void setEndStatus(boolean endStatus){
        this.endStatus=endStatus;
    }

    public boolean getEndStatus(){
        return this.endStatus;
    }

    //set waiting time
    public void setWaitingTime(int waitingTime)
    {
        this.waitingTime = waitingTime;
    }

        //return starting time
        public int getStartTime()
        {
            return this.startTime;
        }
    
    //set turnaround time
    public void setTurnaroundTime(int turnaroundTime)
    {
        this.turnaroundTime = turnaroundTime;
    }
    
    public void setStartTime(int starttime)
    {
        this.startTime = starttime;
    }
    
    //set finish time
    public void setFinishTime(int finishTime) 
    {
    	this.finishTime = finishTime;
    }

    public void setOriginalBurst(int originalBurst){
        this.originalBurst=originalBurst;
    }
    
   
    public int getOriginalBurst(){
        return originalBurst;
    }

    
    public String getProcessName()
    {
        return this.processName;
    }
    
    //return arrival time
    public int getArrivalTime()
    {
        return this.arrivalTime;
    }
    
    //return burst time
    public int getBurstTime()
    {
        return this.burstTime;
    }
    
    //return priority
    public int getPriority()
    {
        return this.priority;
    }
    
    //return waiting time
    public int getWaitingTime()
    {
        return this.waitingTime;
    }
    
    //return turnaround time
    public int getTurnaroundTime()
    {
        return this.turnaroundTime;
    }
    
    //return finish time
    public int getFinishTime() 
    {
    	return this.finishTime;
    }
}
