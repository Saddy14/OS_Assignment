package NonPreSJF;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NonPreSjf {
    
    private ArrayList<Process> pList;
    private ArrayList<Process> pDone;
    private int totalTurnAround;
    private double avgTurnAround;

    private int totalWaiting;
    private double avgWaiting;

    private int totalBurst;

    private int howManyP = 6; 

    Queue<Process> queue = new LinkedList<Process>();

    NonPreSjf() {

        pList = new ArrayList<>();
        pDone = new ArrayList<>();
        

        // for (int i = 0; i < howManyP ; i++) {

        //     myprocess.add(new Process(mymenu.setpNumber(), mymenu.setBrustTime(), mymenu.setArrivalTime(), mymenu.setPriority()));
        //     myprocess.add(new Process());
        // }
        pList.add(new Process("P0",6,0));
        pList.add(new Process("P1",4,1));
        pList.add(new Process("P2",6,5));
        pList.add(new Process("P3",6,6));
        pList.add(new Process("P4",6,7));
        pList.add(new Process("P5",6,8));

        calTotalBrust(); // Calculate Total Burst Time
        sortByArrival(pList, totalBurst);
        magic();
    }

    private void calTotalBrust() {

        for (Process process : pList) {
           totalBurst += process.getBrustTime();
        }
        System.out.println("Total Burst Time: " + totalBurst);
    }

    private Queue<Process> sortByArrival(List<Process> pList, int totalBurst) { //Sort by Arrival Time

        queue.clear();

        for (int i = 0; i < totalBurst; i++) {

            for (int j = 0; j < pList.size(); j++) {

                if ((i) == pList.get(j).getArrivalTime()) {
                    queue.offer(pList.get(j));
                }
            }
        }  
        
        
        // System.out.println("i am from sortbyArrival " + queue);
        return queue;
    }

    private Queue<Process> sortByBurstTime(Queue<Process> queue) { //Sort by Burst Time

        List<Process> temp = new ArrayList<>();
        temp.addAll(queue);
        queue.clear();

        for (int i = 0; i < 50; i++) {

            for (int j = 0; j < temp.size(); j++) {

                if ((i) == temp.get(j).getBrustTime()) {
                    queue.offer(temp.get(j));
                }
            }
        }
        // queue.clear();
        // System.out.println(queue);
        return queue;
    }

    private void magic() {

        
        int brust = 0;

        while (brust <= totalBurst) {

            Process temp = queue.poll();
            if (temp == null) {
                break;
            }

            

            brust += temp.getBrustTime();
            // System.out.println(brust+"I am inner Brust");
            temp.setFinishTime(brust);

            // System.out.println(pList.contains(temp));
            pDone.add(temp);
            pList.remove(temp);
            // queue = sortByArrival(pList, brust);
            queue = sortByBurstTime(queue);
        }
        
        System.out.println(pDone+ " magic");

    }
}
