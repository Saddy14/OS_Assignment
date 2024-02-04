package GuiVersion;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;


public class GUI extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JButton addBtn, removeButton, resetBtn, calcBtn;
	private JScrollPane tablePanel;
	private JTable table;
	private DefaultTableModel tblModel;
	private GanttChart ganttChartPanel;
	private JTextField avgTAT;
	private JTextField totalTAT;
	private JTextField avgWTT;
	private JTextField totalWTT;
	
	// create the frame
	public GUI() {
		initializeFrame();
        initComponents();
        this.setVisible(true);
		
	}
	private void initializeFrame() {
        setTitle("CPU Scheduling");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);
        setResizable(false);
        setLocationRelativeTo(null);
    }
	
	public void initComponents() {
		contentPane = createPanel();
        setContentPane(contentPane);
		 initSelectionBox();
		 initTable();
		 initButtons();
		 initChart();
		 addCalculationSection();
	}
	
	private JComboBox<String> createComboBox(String[] options) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(188, 20, 400, 30);
        contentPane.add(comboBox);
        return comboBox;
    }

	private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setBackground(new Color(173, 216, 230));
        panel.setLayout(null);
        return panel;
    }

	public void initSelectionBox(){
      // algorithm selection creation
	  comboBox = createComboBox(new String[] {
		"--Select algorithm--", 
		"Non-Preemptive Shortest Job First", 
		"Preemptive Shortest Job First", 
		"Non-Preemptive Priority",
		"Round Robin"
	});
	}
	

	public void initTable(){
		// table panel creation
		tblModel = createTableModel();
        table = createTable(tblModel);
        tablePanel = createScrollPane(table, 40, 80, 695, 200);
	}

	private DefaultTableModel createTableModel() {
        String[] columns = {"Process", "Arrival Time", "Burst Time", "Priority", "Finish Time", "WT", "TAT"};
        String[][] data = {{"P0", "", "", "", "", "", ""}, {"P1", "", "", "", "", "", ""}, {"P2", "", "", "", "", "", ""}};
        return new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 1 && column <= 3;
            }
        };
    }

    private JTable createTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setRowHeight(20);
        return table;
    }
	public void initButtons(){
		// controls creation
		addBtn = createButton("Add", 219, 310);
        removeButton = createButton("Remove", 351, 310);
        resetBtn = createButton("Clear", 483, 310);
        calcBtn = createButton("Compute", 615, 310);
	}

	private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 120, 30);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        contentPane.add(button);
        return button;
    }
	
	private JScrollPane createScrollPane(Component component, int x, int y, int width, int height) {
        JScrollPane scrollPane = new JScrollPane(component);
        scrollPane.setBounds(x, y, width, height);
        contentPane.add(scrollPane);
        return scrollPane;
    }

	public void initChart() {
		JLabel ganttChartLabel = createGanttChartLabel();
		contentPane.add(ganttChartLabel);
	
		ganttChartPanel = createGanttChart();
		JScrollPane ganttScrollPane = createGanttScrollPane(ganttChartPanel);
		contentPane.add(ganttScrollPane);
	}
	
	private JLabel createGanttChartLabel() {
		JLabel gcLabel = new JLabel("Gantt Chart :");
		gcLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gcLabel.setBounds(45, 500, 107, 20);
		return gcLabel;
	}
	
	private GanttChart createGanttChart() {
		GanttChart ganttChart = new GanttChart();
		ganttChart.setBackground(Color.WHITE);
		return ganttChart;
	}
	
	private JScrollPane createGanttScrollPane(GanttChart ganttChart) {
		JScrollPane scrollPane = new JScrollPane(ganttChart, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(40, 520, 695, 100);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		return scrollPane;
	}


	public Scheduler quantumDialog() {
		String q = JOptionPane.showInputDialog("Enter Time Quantum\n (default quantum = 3), Press ok to proceed\n with quantum=3");  
		int quantum = 3; 
	
		try {
			quantum = Integer.parseInt(q);
		} catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Proceed with default Quantum = 3");
		}
	
		RoundRobin scheduler = new RoundRobin();
		scheduler.setTimeQuantum(quantum);
		return scheduler;
	}

	private void addCalculationSection() {
        addLabel("Average Waiting Time :", 40, 380, 175, 40);
        avgWTT = createTextField(219, 380, 150, 40);

        addLabel("Total Waiting Time :", 40, 430, 175, 40);
        totalWTT = createTextField(219, 430, 150, 40);

        addLabel("Average Turnaround Time :", 389, 380, 188, 40);
        avgTAT= createTextField(585, 380, 150, 40);

        addLabel("Total Turnaround Time :", 389, 430, 188, 40);
        totalTAT = createTextField(585, 430, 150, 40);
    }


	private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setEditable(false);
        contentPane.add(textField);
        return textField;
    }

	private void addLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(x, y, width, height);
        contentPane.add(label);
    }

	 
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	
	public JButton getaddBtn() {
		return addBtn;
	}
	
	public JButton getRemoveButton() {
		return removeButton;
	}
	
	public JButton getclrBtn() {
		return resetBtn;
	}
	
	public JButton getcalcBtn() {
		return calcBtn;
	}
	
	public JTable getTable() {
		return table;
	}
	
	public DefaultTableModel gettblModel() {
		return tblModel;
	}
	
	public JPanel getGanttChartPanel() {
		return ganttChartPanel;
	}

	public void setGanttChartPanel(GanttChart p) {
		ganttChartPanel = p;
	}
	
	public void setGanttChart(List<Process> output) {
		ganttChartPanel.setProcessOutputList(output);
	}
	
	
	public JTextField getAvgTat() {
		return avgTAT;
	}
	
	public JTextField getTotalTat() {
		return totalTAT;
	}
	
	public JTextField getAvgWt() {
		return avgWTT;
	}
	
	public JTextField getTotalWt() {
		return totalWTT;
	}

	public class GanttChart extends JPanel {

		private List<Process> processOutputList;
		
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			if (processOutputList != null)
			{
				int previousWidth = 0;
				for (int i = 0; i < processOutputList.size(); i++)
				{
					Process event = processOutputList.get(i);
					int width = 30 * (event.getFinishTime() - event.getStartTime() - 1);
					int locationX = 30 * (i + 1) + previousWidth;
					int locationY = 20;
					
					g.drawRect(locationX, locationY, 30 + width, 30);
					g.drawString(event.getProcessName(), locationX + 10, locationY + 20);
					g.drawString(Integer.toString(event.getStartTime()), locationX - 5, locationY + 45);
					
					if (i == processOutputList.size() - 1)
					{
						g.drawString(Integer.toString(event.getFinishTime()), locationX + width + 27, locationY + 45);
					} 
					else {
						previousWidth += width;
					}
				}
			} else {       
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 600, 150);
				setPreferredSize(new Dimension(100, 195));
				repaint();
				revalidate();
			}
		}
		
		public void setProcessOutputList(List<Process> processOutputList)
		{
			this.processOutputList = processOutputList;
			// each time box is 30 pixels wide, spacing at front and back = 60
			int calculatedWidth = processOutputList.isEmpty() ? 60 : (processOutputList.get(processOutputList.size() - 1).getFinishTime() * 30) + 60;
			int fixedHeight = 195;
			Dimension preferredSize = new Dimension(calculatedWidth, fixedHeight);
			setPreferredSize(preferredSize);
			repaint();
			revalidate();
		}
	
}}
