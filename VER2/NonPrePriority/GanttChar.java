package NonPrePriority;

import java.util.ArrayList;
import java.util.Queue;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GanttChar extends JPanel {

    private ArrayList<Process> allP = new ArrayList<>();
    private static ArrayList<Process> sortAT = new ArrayList<>();
    private JTextArea textArea;

    public static void setSortAT(Queue<Process> sortAT) {
        GanttChar.sortAT.addAll(sortAT);
    }

    GanttChar(ArrayList<Process> p) {
        this.allP = p;
        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        add(textArea);
        draw();
        // setBackground(java.awt.Color.BLACK);
        // setForeground(java.awt.Color.WHITE);
        
        Gui.panel.add(this, java.awt.BorderLayout.SOUTH);

        // Gui.cPanel.revalidate();
        Gui.panel.revalidate();
    }

    private void draw() {
        StringBuilder sb = new StringBuilder();
        sb.append(topLine());
        sb.append(middleLine());
        sb.append(topLine());
        sb.append(bottomLine());
        textArea.setText(sb.toString());
    }

    private String topLine() {
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int i = 0; i < allP.size(); i++) {
            for (int j = 0; j < 10; j++) {
                sb.append("-");
            }
            sb.append("+");
        }
        sb.append("\n");
        return sb.toString();
    }

    private String middleLine() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allP.size(); i++) {
            sb.append("| " + allP.get(i).getpNumber());
            for (int j = 0; j < 9; j++) {
                sb.append(" ");
            }
        }
        sb.append("|\n");
        return sb.toString();
    }

    private String bottomLine() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nFT");
        for (int i = 0; i < allP.size(); i++) {
            for (int j = 0; j < 11; j++) {
                sb.append(" ");
            }
            sb.append(allP.get(i).getFinishTime());
        }
        sb.append("\n");
        return sb.toString();
    }
}
