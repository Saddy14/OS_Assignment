package NonPrePriority;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Gui extends JFrame {

    static JPanel panel = new JPanel(new BorderLayout());
    private JPanel wPlanel = new JPanel(new GridLayout(3, 1));
    static JPanel cPanel = new JPanel();
    private JButton addProcessB = new JButton("Add a Process");
    // private JButton removeProcessB = new JButton("Remove a Process");
    private JButton calulateB = new JButton("Calculate");
    private JButton clearB = new JButton("Exit ");

    JTextField processName = new JTextField();
    JTextField burstTime = new JTextField();
    JTextField arrivalTime = new JTextField();
    JTextField priority = new JTextField();

    String[] columnNames = {"Process", "Burst Time", "Arrival Time", "Priority", "Waiting Time", "TAT"};
    static JTable table;
    static DefaultTableModel tableModel;

    static JPanel ePanel = new JPanel(new GridLayout(4, 1));

    static JLabel totalTurnAroundLabel = new JLabel("Total TurnAround Time: " + NonPrePriority.totalTurnAround);
    static JLabel avgTurnAroundLabel = new JLabel("Avg. TurnAround Time: " + NonPrePriority.avgTurnAround);
    static JLabel totalWaitingLabel = new JLabel("Total Waiting Time: " + NonPrePriority.totalWaiting);
    static JLabel avgWaitingLabel = new JLabel("Avg Waiting Time: " + NonPrePriority.avgWaiting);

    // static JPanel ganttChartPanel;

    
    Gui() {

        setTitle("NonPrePriority");
        setSize(1000, 800);
        

        wPlanelButtons();
        panel.add(wPlanel, BorderLayout.WEST);
        nPanel();
        cPanel();

        ePanel();
        panel.add(ePanel, BorderLayout.EAST);

        
        add(panel);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
       
    }

    private void wPlanelButtons() {

        addProcessB.setFocusPainted(false);
        calulateB.setFocusPainted(false);
        clearB.setFocusPainted(false);

        addProcessB.addActionListener(e -> addButton() );
        calulateB.addActionListener(e -> {
            new NonPrePriority();
            updateTable();
            updateLabels();
        });
        // calulateB.addActionListener(e -> ePanel());
        clearB.addActionListener(e -> {
            System.exit(0);
        });

        wPlanel.add(addProcessB);
        wPlanel.add(calulateB);
        wPlanel.add(clearB);
        
    }

    private void nPanel() {

        JPanel nPanel = new JPanel(new GridLayout(2, 4));
        // add(nPanel, BorderLayout.NORTH);
        nPanel.setBackground(java.awt.Color.LIGHT_GRAY);

        
        JLabel processNameLabel = new JLabel("Process Name");
        JLabel burstTimeLabel = new JLabel("Burst Time");
        JLabel arrivalTimeLabel = new JLabel("Arrival Time");
        JLabel priorityLabel = new JLabel("Priority");

        nPanel.add(processNameLabel);
        nPanel.add(processName);
        nPanel.add(burstTimeLabel);
        nPanel.add(burstTime);
        nPanel.add(arrivalTimeLabel);
        nPanel.add(arrivalTime);
        nPanel.add(priorityLabel);
        nPanel.add(priority);

        panel.add(nPanel, BorderLayout.NORTH);
    }

    void cPanel() {

        Object[][] data = new Object[NonPrePriority.pList.size()][columnNames.length];
        

        for (int i = 0; i < NonPrePriority.pList.size(); i++) {

            data[i][0] = NonPrePriority.pList.get(i).getpNumber();
            data[i][1] = NonPrePriority.pList.get(i).getBrustTime();
            data[i][2] = NonPrePriority.pList.get(i).getArrivalTime();
            data[i][3] = NonPrePriority.pList.get(i).getPriority();
            data[i][4] = NonPrePriority.pList.get(i).getWaitingTime();
            data[i][5] = NonPrePriority.pList.get(i).getTurnAroundTime();
        }

        // Create table
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);
        cPanel.add(scrollPane);

        // Add scroll pane to main panel
        panel.add(cPanel, BorderLayout.CENTER);
    }

    private void addButton() {
        
        NonPrePriority.pList.add(new Process(processName.getText(), Integer.parseInt(burstTime.getText()), Integer.parseInt(arrivalTime.getText()), Integer.parseInt(priority.getText())));
        System.out.println(NonPrePriority.pList);
        Object[] row = {processName.getText(), Integer.parseInt(burstTime.getText()), Integer.parseInt(arrivalTime.getText()), Integer.parseInt(priority.getText())};
        // tableModel.add(row);
        tableModel.addRow(row);
        table.repaint();

    }

    static void ePanel() {

        
        ePanel.add(totalTurnAroundLabel);
        ePanel.add(avgTurnAroundLabel);
        ePanel.add(totalWaitingLabel);
        ePanel.add(avgWaitingLabel);
    }

    // Method to update JTable
    public void updateTable() {
        Object[][] data = new Object[NonPrePriority.CopypDone.size()][columnNames.length];
        for (int i = 0; i < NonPrePriority.CopypDone.size(); i++) {
            data[i][0] = NonPrePriority.CopypDone.get(i).getpNumber();
            data[i][1] = NonPrePriority.CopypDone.get(i).getBrustTime();
            data[i][2] = NonPrePriority.CopypDone.get(i).getArrivalTime();
            data[i][3] = NonPrePriority.CopypDone.get(i).getPriority();
            data[i][4] = NonPrePriority.CopypDone.get(i).getWaitingTime();
            data[i][5] = NonPrePriority.CopypDone.get(i).getTurnAroundTime();
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }

    // Method to update JLabels
    public void updateLabels() {
        totalTurnAroundLabel.setText("Total TurnAround Time: " + NonPrePriority.totalTurnAround);
        avgTurnAroundLabel.setText("Average TurnAround Time: " + NonPrePriority.avgTurnAround);
        totalWaitingLabel.setText("Total Waiting Time: " + NonPrePriority.totalWaiting);
        avgWaitingLabel.setText("Average Waiting Time: " + NonPrePriority.avgWaiting);
    }

}
