package GuiVersion;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JComboBox;


public class View extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JButton addButton;
	private JButton removeButton;
	private JButton resetButton;
	private JButton calculateButton;
	private JScrollPane tablePanel;
	private JTable table;
	private DefaultTableModel tableModel;
	private GanttChart ganttChartPanel;
	private JTextField avgTATTxtField;
	private JTextField totalTATTxtField;
	private JTextField avgWTTxtField;
	private JTextField totalWTTxtField;
	
	// create the frame
	public View() {
		super("CPU Scheduling Algorithm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		initComponents();
		this.setSize(new Dimension(800, 700));
		this.setResizable(false);
		this.setVisible(true);
		setLocationRelativeTo(null);
		
	}
	
	public void initComponents() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		 initSelectionBox();
		 initTable();
		 initButtons();
		 initChart();
		calculateSec();
	}

	public void initSelectionBox(){
      // algorithm selection creation
       String[] options = 
		{"--Select algorithm--", 
			"Non-Preemptive SJF", 
			"Preemptive SJF", 
			"Round Robin"};
		comboBox = new JComboBox<String>(options);
		comboBox.setBounds(188, 20, 400, 30);
		contentPane.add(comboBox);
	}
	

	public void initTable(){
		// table panel creation
		String[] columnNames = {"Process", "Arrival Time", "Burst Time", "Priority", "Finish Time", "WT", "TAT"};
		String[][] data = {{"P0", "", "", "", "", "", ""}, {"P1", "", "", "", "", "", ""}, {"P2", "", "", "", "", "", ""}};
	
		tableModel = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) { // making Process, FT, WT, TAT not editable
				return column == 1 || column == 2 || column == 3 ? true : false;
			}
		};
		
		table = new JTable(tableModel);
		tablePanel = new JScrollPane(table);
		tablePanel.setBackground(Color.LIGHT_GRAY);
		table.setRowHeight(20);
		tablePanel.setBounds(40, 80, 695, 200);
		contentPane.add(tablePanel);
	}

	public void initButtons(){
		// controls creation
		addButton = new JButton("Add");
		addButton.setBounds(219, 310, 120, 30);
		contentPane.add(addButton);
		addButton.setForeground(Color.WHITE);
		addButton.setBackground(Color.BLACK); 

		removeButton = new JButton("Remove");
		removeButton.setBounds(351, 310, 120, 30);
		contentPane.add(removeButton);
		removeButton.setForeground(Color.WHITE);
		removeButton.setBackground(Color.BLACK); 
		
		resetButton = new JButton("Clear");
		resetButton.setBounds(483, 310, 120, 30);
		contentPane.add(resetButton);
		resetButton.setForeground(Color.WHITE);
		resetButton.setBackground(Color.BLACK); 
		
		calculateButton = new JButton("Calculate");
		calculateButton.setBounds(615, 310, 120, 30);
		contentPane.add(calculateButton);
		calculateButton.setForeground(Color.WHITE);
		calculateButton.setBackground(Color.BLACK); 
	}

	public void initChart(){
		
		JLabel gcLabel = new JLabel("Gantt Chart :");
		gcLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gcLabel.setBounds(45, 500, 107, 20);
		contentPane.add(gcLabel);
		
		ganttChartPanel = new GanttChart();
		ganttChartPanel.setBackground(Color.WHITE);
		JScrollPane scrollPane = new JScrollPane(ganttChartPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(40, 520, 695, 100); // +=40
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		contentPane.add(scrollPane);
		
	}

	public void calculateSec(){
     // calculation summary creation

		JLabel totalWTLabel = new JLabel("Total Waiting Time :");
		totalWTLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalWTLabel.setBounds(40, 430, 175, 40);
		contentPane.add(totalWTLabel);

		JLabel avgWTLabel = new JLabel("Average Waiting Time :");
		avgWTLabel.setHorizontalAlignment(SwingConstants.CENTER);
		avgWTLabel.setBounds(40, 380, 175, 40);
		contentPane.add(avgWTLabel);

		JLabel totalTATLabel = new JLabel("Total Turnaround Time :");
		totalTATLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalTATLabel.setBounds(389, 430, 188, 40);
		contentPane.add(totalTATLabel);
		
		JLabel avgTATLabel = new JLabel("Average Turnaround Time :");
		avgTATLabel.setHorizontalAlignment(SwingConstants.CENTER);
		avgTATLabel.setBounds(389, 380, 188, 40);
		contentPane.add(avgTATLabel);
		
		//textfield for total waiting time
		totalWTTxtField = new JTextField();
		totalWTTxtField.setColumns(10);
		totalWTTxtField.setBounds(219, 430, 150, 40);
		totalWTTxtField.setEditable(false);
		contentPane.add(totalWTTxtField);
		
		//textfield for average waiting time
		avgWTTxtField = new JTextField();
		avgWTTxtField.setColumns(10);
		avgWTTxtField.setBounds(219, 380, 150, 40);
		avgWTTxtField.setEditable(false);
		contentPane.add(avgWTTxtField);
		
		//textfield for total turnaround time
		totalTATTxtField = new JTextField();
		totalTATTxtField.setColumns(10);
		totalTATTxtField.setBounds(585, 430, 150, 40);
		totalTATTxtField.setEditable(false);
		contentPane.add(totalTATTxtField);
		
		//textfield for average turnaround time
		avgTATTxtField = new JTextField();
		avgTATTxtField.setColumns(10);
		avgTATTxtField.setBounds(585, 380, 150, 40);
		avgTATTxtField.setEditable(false);
		contentPane.add(avgTATTxtField);	
		
	}

	 
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	
	public JButton getAddButton() {
		return addButton;
	}
	
	public JButton getRemoveButton() {
		return removeButton;
	}
	
	public JButton getResetButton() {
		return resetButton;
	}
	
	public JButton getCalculateButton() {
		return calculateButton;
	}
	
	public JTable getTable() {
		return table;
	}
	
	public DefaultTableModel getTableModel() {
		return tableModel;
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
	
	
	public JTextField getAvgTATTxtField() {
		return avgTATTxtField;
	}
	
	public JTextField getTotalTATTxtField() {
		return totalTATTxtField;
	}
	
	public JTextField getAvgWTTxtField() {
		return avgWTTxtField;
	}
	
	public JTextField getTotalWTTxtField() {
		return totalWTTxtField;
	}

	public class GanttChart extends JPanel {

		private List<Process> processOutputList;
		boolean clearPane;
		
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			if (processOutputList != null && clearPane)
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
			this.clearPane = true;
			
			// each time box is 30 pixels wide, spacing at front and back = 60
			int calculatedWidth = processOutputList.isEmpty() ? 60 : (processOutputList.get(processOutputList.size() - 1).getFinishTime() * 30) + 60;
			int fixedHeight = 195;
			Dimension preferredSize = new Dimension(calculatedWidth, fixedHeight);
			setPreferredSize(preferredSize);
			repaint();
			revalidate();
		}
	
}}


