package NonPrePriority;

import java.util.ArrayList;
import java.util.Queue;

public class GanttChar {
    
    private ArrayList<Process> allP = new ArrayList<>();

    private static ArrayList<Process> sortAT = new ArrayList<>();

    public static void setSortAT(Queue<Process> sortAT) {
        
        GanttChar.sortAT.addAll(sortAT);
    }

    GanttChar(ArrayList<Process> p) {

        this.allP = p;
        System.out.println("\n");
        topLine();
        middleLine();
        topLine();
        bottomLine();
        // draw();
    }

    private void draw() {

        int size = allP.size() - 1;

        do {

        

            
        } while (size < 0);

    }

    private void topLine() {

        System.out.print("+");
        for (int i = 0; i < allP.size(); i++) {
            
            for (int j = 0; j < 10; j++) {
                
                System.out.print("-");
            }

            System.out.print("+");
        }
       
    }

    private void middleLine() {

        System.out.print("\n");

        for (int i = 0; i < allP.size(); i++) {
            
            System.out.print("| " + allP.get(i).getpNumber());

            for (int j = 0; j < 7; j++) {
                
                System.out.print(" ");
            }
            
        }
        System.out.println("|");
    }

    private void bottomLine() {

        System.out.print("\nFT");

        for (int i = 0; i < allP.size(); i++) {
            
            for (int j = 0; j < 9; j++) {
                
                System.out.print(" ");

            }

            System.out.print(allP.get(i).getFinishTime());
        }

        //TODO Get Arrival Time
        // System.out.println();
        // for (int i = 0; i < sortAT.size(); i++) {
            
        //     for (int j = 0; j < sortAT.get(i).getArrivalTime()-4; j++) {
                
        //         System.out.print(" ");

        //     }

        //     System.out.print(sortAT.get(i).getpNumber());
        // }

        

        // System.out.println(sortAT.size());


       
    }


}
