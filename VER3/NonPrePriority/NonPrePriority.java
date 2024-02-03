package NonPrePriority;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class NonPrePriority {

    static ArrayList<Process> pList = new ArrayList<>();
    

    static ArrayList<Process> pDone = new ArrayList<>();

    static ArrayList<Process> CopypDone = new ArrayList<>();

    static int totalTurnAround;
    static double avgTurnAround;

    static int totalWaiting;
    static double avgWaiting;

    static int totalBurst;


    static Queue<Process> queue = new LinkedList<Process>(); 
    
    NonPrePriority() {

        // from assignment pdf
        // pList.add(new Process("P0",6,0,3));
       // pList.add(new Process("P1",4,1,3));
       //  pList.add(new Process("P2",6,5,1));
       // pList.add(new Process("P3",6,6,1));
       //  pList.add(new Process("P4",6,7,5));
        // pList.add(new Process("P5",6,8,6));


         //pList.add(new Process("P0",8,0,2));
        // pList.add(new Process("P4",6,0,1));
        // pList.add(new Process("P2",15,4,5));
       //  pList.add(new Process("P3",13,9,4));
        // pList.add(new Process("P1",9,7,3));
        // pList.add(new Process("P0",5,13,1));

         pList.add(new Process("P1",3,6,4));
         pList.add(new Process("P2",4,1,2));
         pList.add(new Process("P3",2,23,1));
         pList.add(new Process("P4",3,13,3));
         pList.add(new Process("P5",1,6,1));
        
        calTotalBrust(); //! Calculate Total Burst Time
        sortByArrival(pList, totalBurst); GanttChar.setSortAT(queue);
        magic();
        // System.out.println(pList);
        calTurnAround(); //Calculate TurnAround for each Process
        waitingTime(); //Calculate Waiting Time for each Process
        calTAT(); //Total & Avg TurnAround Time
        avgWaitingTime(); //Total & Avg Waiting Time
        System.out.println("\n"+pDone);
        System.out.println("TTAT: " + totalTurnAround + " Avg TAT: " + avgTurnAround);

        CopypDone.addAll(pDone);
        
        new GanttChar(pDone);

    }

    public void start() {
       

    }

    // private Queue<Process> sortByPriority(Queue<Process> queue) {
    //     List<Process> temp = new ArrayList<>(queue);
    //     queue.clear();
    
    //     temp.sort((p1, p2) -> {
    //         if (p1.getPriority() == p2.getPriority()) {
    //             if (p1.getArrivalTime() == p2.getArrivalTime()) {
    //                 return p1.getBrustTime() - p2.getBrustTime(); // sort by burst time if priorities and arrival times are the same
    //             }
    //             return p1.getArrivalTime() - p2.getArrivalTime(); // sort by arrival time if priorities are the same
    //         }
    //         return p1.getPriority() - p2.getPriority();
    //     });
    
    //     queue.addAll(temp);
    //     return queue;
    // }

    

    private Queue<Process> sortByArrival(List<Process> pList, int totalBurst) {
        queue.clear();
    
        pList.sort((p1, p2) -> {
            if (p1.getArrivalTime() == p2.getArrivalTime()) {
                if (p1.getPriority() == p2.getPriority()) {
                    return p1.getBrustTime() - p2.getBrustTime(); // sort by burst time if arrival times and priorities are the same
                }
                return p1.getPriority() - p2.getPriority(); // sort by priority if arrival times are the same
            }
            return p1.getArrivalTime() - p2.getArrivalTime();
        });
    
        queue.addAll(pList);
        return queue;
    }


    

    private void magic() {
        int brust = 0;
    
        while (brust <= totalBurst) {
            queue = sortByPriorityAndArrival(pList, brust);
    
            Process temp = queue.poll();
            if (temp == null) {
                break;
            }
    
            // If the current time is less than the arrival time of the process,
            // increment the current time to the arrival time of the process.
            if (brust < temp.getArrivalTime()) {
                brust = temp.getArrivalTime();
            }
    
            int startTime = brust;
            brust += temp.getBrustTime();
            temp.setFinishTime(brust);
    
            Process doneProcess = new Process(temp.getpNumber(), temp.getPriority(), temp.getArrivalTime(), temp.getBrustTime());
            doneProcess.setFinishTime(brust);
            doneProcess.setStartTime(startTime); // Set the start time
            pDone.add(doneProcess);
    
            Iterator<Process> iterator = pList.iterator();
            while (iterator.hasNext()) {
                Process process = iterator.next();
                if (process.getpNumber().equals(temp.getpNumber())) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    private Queue<Process> sortByPriorityAndArrival(List<Process> list, int brust) {
        return list.stream()
                .filter(p -> p.getArrivalTime() <= brust)
                .sorted(Comparator.comparing(Process::getPriority).thenComparing(Process::getArrivalTime))
                .collect(Collectors.toCollection(LinkedList::new));
    }


    

    private void calTotalBrust() {

        for (Process process : pList) {
           totalBurst += process.getBrustTime();
        }
        System.out.println("Total Burst Time: " + totalBurst);
    }

    private void calTurnAround() {

        for (Process process : pDone) {
            
            process.setTurnAroundTime(process.getFinishTime() - process.getArrivalTime());
        }
        
    }

    private void calTAT () { //Calculate Total Turn Around Time

        for (Process process : pDone) {
            
            totalTurnAround += process.getTurnAroundTime();
        }

        double x = pDone.size(); //!
        avgTurnAround = totalTurnAround/(x); //!
    }

    private void waitingTime() {

        for (Process process : pDone) {
            
            process.setWaitingTime(process.getTurnAroundTime() - process.getBrustTime());
        }
    }

    private void avgWaitingTime() { //Calculate Total Waiting Time

        for (Process process : pDone) {
            
            totalWaiting += process.getWaitingTime();
        }

        double x = pDone.size(); //!
        avgWaiting = totalWaiting/(x); //!

        avgWaiting = Double.parseDouble(String.format("%.2f", avgWaiting));
    
    }

}

