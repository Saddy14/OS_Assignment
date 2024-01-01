import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NonPrePriority {

    private ArrayList<Process> pList;
    private ArrayList<Process> pDone;
    private int totalTurnAround;
    private double avgTurnAround;

    private int totalWaiting;
    private double avgWaiting;

    private int totalBurst;

    private int howManyP = 6; 

    Queue<Process> queue = new LinkedList<Process>(); 
    // Queue<Process> queueAfterPrioritySort = new LinkedList<Process>(); //! Remove
    // private List<Process> sortedPri = new ArrayList<>();

    NonPrePriority() {

        Menu mymenu= new Menu();
        // howManyP = mymenu.howManyP();
        pList = new ArrayList<>();
        pDone = new ArrayList<>();
        

        // for (int i = 0; i < howManyP ; i++) {

        //     myprocess.add(new Process(mymenu.setpNumber(), mymenu.setBrustTime(), mymenu.setArrivalTime(), mymenu.setPriority()));
        //     myprocess.add(new Process());
        // }
        pList.add(new Process("P0",6,0,3));
        pList.add(new Process("P1",4,1,3));
        pList.add(new Process("P2",6,5,1));
        pList.add(new Process("P3",6,6,1));
        pList.add(new Process("P4",6,7,5));
        pList.add(new Process("P5",6,8,6));

        // pList.add(new Process("P6",6,8,6));
        // pList.add(new Process("P7",6,8,6));
        // pList.add(new Process("P8",6,5,1));

        // pList.add(new Process("P9",6,8,5));
        // pList.add(new Process("P10",6,8,5));
       
        

        // System.out.println(mymenu.setpNumber());
        // System.out.println(pList.get(0));
        // sortByPriority(pList);
        // System.out.println(pList);
        calTotalBrust(); //! Calculate Total Burst Time
        sortByArrival(pList, totalBurst); GanttChar.setSortAT(queue);
        magic();
        // System.out.println(pList);
        calTurnAround(); //Calculate TurnAround for each Process
        calTAT(); //Total & Avg TurnAround Time
        System.out.println("\n"+pDone);
        System.out.println("TTAT: " + totalTurnAround + " Avg TAT: " + avgTurnAround);

        new GanttChar(pDone);

    }

    public void start() {
       

    }

    //TODO Make it return queue & make parameter queue as well 
    private Queue<Process> sortByPriority(Queue<Process> queue) {

        int x = 1; //! Priority 1 compare to all other process Priority Level...so forth

        List<Process> temp = new ArrayList<>();
        temp.addAll(queue);
        queue.clear();

        for (int i = 0; i < 25 /*temp.size()*/; i++) {

            // for (int j = 0; j < temp.size(); j++) {

            //     if ((x) == temp.get(j).getPriority()) {
            //         queue.offer(temp.get(j));
            //     }
            // }
            for (Process process : temp) {

                if ((x) == process.getPriority()) {
                    queue.offer(process);
                }

            }
            x++;
        }  
        // queue.clear();
        // System.out.println(queue);
        return queue;
    }



    private void calTotalBrust() {

        for (Process process : pList) {
           totalBurst += process.getBrustTime();
        }
        System.out.println("Total Burst Time: " + totalBurst);
    }

    

    private Queue<Process> sortByArrival(List<Process> pList, int totalBurst) {

        queue.clear();

        for (int i = 0; i < totalBurst; i++) {

            for (int j = 0; j < pList.size(); j++) {

                if ((i) == pList.get(j).getArrivalTime()) {
                    queue.offer(pList.get(j));
                }
            }
        }  
        
        
        // System.out.println("i am from sortbyArrival " + queue.size());
        return queue;
    }

    private void magic() {

        int brust = 0;

        while (brust <= totalBurst) {

            Process temp = queue.poll();
            if (temp == null) {
                break;
            }

            if (queue.peek() != null) { //Same arrival time but different Priority

                if (temp.getPriority() > queue.peek().getPriority()) {
                    Process putBackProcess = temp;
                    temp = queue.poll();
                    queue.offer(putBackProcess);
                }
                // else
                // continue;
            }
            

            brust += temp.getBrustTime();
            // System.out.println(brust+"I am inner Brust");
            temp.setFinishTime(brust);

            // System.out.println(pList.contains(temp));
            pDone.add(temp);
            pList.remove(temp);
            queue = sortByArrival(pList, brust);
            queue = sortByPriority(queue);

        }
        // System.out.println(pDone+ " magic");

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



}

