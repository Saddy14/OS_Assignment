public class Process {
    
    private static int pIdCounter;
    private int pId;

    private String pNumber;
    private int brustTime;
    private int arrivalTime;
    private int finishTime;
    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    private int priority;
    private int turnAroundTime; 
    private int waitingTime; 
    //Constructor for process object
    Process () {

        this.pId = pIdCounter++;
    }
    
    Process(String pNumber, int brustTime, int arrivalTime, int priority) {

        this.pNumber = pNumber;
        this.brustTime = brustTime;
        this.arrivalTime = arrivalTime;

        if (priority != 0)
        this.priority = priority;
        // this.turnAroundTime = turnAroundTime;
        // this.waitingTime = waitingTime;
        this.pId = pIdCounter++;
    }

    // //! No Priority
    // Process(String pNumber, int brustTime, int arrivalTime, int turnAroundTime, int waitingTime) {
    //     this.pNumber = pNumber;
    //     this.brustTime = brustTime;
    //     this.arrivalTime = arrivalTime;
    //     this.turnAroundTime = turnAroundTime;
    //     this.waitingTime = waitingTime;
    // }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public int getBrustTime() {
        return brustTime;
    }

    public void setBrustTime(int brustTime) {
        this.brustTime = brustTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getpId() {
        return pId;
    }

    public int getFinishTime() {
        return finishTime;
    }

    // @Override
    // public String toString() {
    //     return "pId= " + pId + ", pNumber= " + pNumber + ", brustTime= " + brustTime + ", arrivalTime= "
    //             + arrivalTime + ", priority= " + priority + ", turnAroundTime= " + turnAroundTime + ", waitingTime= "
    //             + waitingTime;
    // }

     @Override
    public String toString() {
        return "pNumber= " + pNumber + ", priority= " + priority + ", AT= " + arrivalTime + ", FT= " + finishTime + ", TAT= "
         + turnAroundTime; 
    }
    
    public void pew() {
        // return "ff";
        System.out.printf("Pew %d", pId, pIdCounter);
        
    }

    

}
