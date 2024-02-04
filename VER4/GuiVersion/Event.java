package GuiVersion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Event {
	private GUI view;
	private int numProcess = 3;
	private Scheduler scheduler;

	public Event(GUI view) {
		this.view = view;
		view.getaddBtn().addActionListener(addButtonListener);
		view.getRemoveButton().addActionListener(removeButtonListener);
		view.getclrBtn().addActionListener(resetButtonListener);
		view.getcalcBtn().addActionListener(calculateButtonListener);
	}

	// add new row
	ActionListener addButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getTable().clearSelection();
			if (numProcess >= 10) {
				JOptionPane.showMessageDialog(view, "Maximum number of processes reached!");
				return;
			}
			
			String[] newRow = new String[] { "P" + (numProcess), "", "", "", "", "", "" };
			view.gettblModel().addRow(newRow);
			numProcess++;
		}
	};

	// remove row
	ActionListener removeButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getTable().clearSelection();
			if (numProcess <= 3) {
				JOptionPane.showMessageDialog(view, "Minimum number of processes reached!");
				return;
			}
			numProcess--;
			view.gettblModel().removeRow(numProcess);
		}
	};

	// reset
	ActionListener resetButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getTable().clearSelection();

			// Clearing the table cells
			for (int row = 0; row < numProcess; row++) {
				for (int col = 1; col < view.gettblModel().getColumnCount(); col++) {
					view.gettblModel().setValueAt("", row, col);
				}
			}

			// Clearing Gantt Chart and calcualtion areas
			view.repaint();
			view.getAvgTat().setText("");
			view.getTotalTat().setText("");
			view.getAvgWt().setText("");
			view.getTotalWt().setText("");
		}
	};

	ActionListener calculateButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					boolean editing = view.getTable().getCellEditor() != null;
					if (editing) {
						view.getTable().getCellEditor().stopCellEditing();
						view.repaint();
						actionPerformed(e); // Recursively invoke actionPerformed if editing
					} else {
						int choice = view.getComboBox().getSelectedIndex();
						if (choice == 0) {
							JOptionPane.showMessageDialog(view, "Please select an algorithm!");
							return;
						}
	
						scheduler = getScheduler(choice);
	
						// Calculate the process
						if (readData(scheduler)) {
							scheduler.process();
							writeData(scheduler);
						}
					}
				}
			});
		}
	};

	

		public Scheduler getScheduler(int choice) {
			if (choice == 1) {
				return new NonPreemptiveSJF();
			} else if (choice == 2) {
				return new PreemptiveSJF();
			} else if (choice == 4) {
				return view.quantumDialog();
			} else if (choice ==3){
				return new NonPreemptivePriority();
			}else {
				return null;
			}
		}
	

	// read all data from table
	public boolean readData(Scheduler scheduler) {
		String errorMessage = "";
		try {
			for (int i = 0; i < numProcess; i++) {
				errorMessage = "Invalid data input at P" + (i);
				String arrivalTime = (String) view.gettblModel().getValueAt(i, 1);
				String burstTime = (String) view.gettblModel().getValueAt(i, 2);
				String priorityStr = (String) view.gettblModel().getValueAt(i, 3);

				if("".equals(arrivalTime) || "".equals(burstTime) )
				{
					errorMessage= errorMessage + ". Please fill in the arrival time and burst time.";
					System.out.println(errorMessage);
					throw new Exception(errorMessage);
				}
				int at = parseInteger(arrivalTime, errorMessage);
				int bt = parseInteger(burstTime, errorMessage);
				int priority = determinePriority(scheduler, priorityStr, i);

				if (at < 0 || bt < 1 || priority < 0) {
					System.out.println(errorMessage);
					throw new Exception(errorMessage);
				}
				scheduler.add(new Process("P" + (i), at, bt, priority));
			}
			return true;

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, errorMessage);
		}
		return false;
	}

	public int parseInteger(String value, String errorMessage) throws Exception {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException ex) {
			throw new Exception(errorMessage);
		}
	}

	public int determinePriority(Scheduler scheduler, String priorityString, int rowIndex) throws Exception {
		if (scheduler instanceof RoundRobin || scheduler instanceof PreemptiveSJF || scheduler instanceof NonPreemptiveSJF) {
			if (priorityString.isEmpty()) {
				view.gettblModel().setValueAt("1", rowIndex, 3);
				return 1;
			}
		}
		return parseInteger(priorityString, "Invalid priority at P" + (rowIndex));
	}

	// write all data to table
	public void writeData(Scheduler scheduler) {
		// writing FT, WT, TAT to table
		List<Process> processList = scheduler.getInputList();
		for (int i = 0; i < numProcess; i++) {
			Process process = processList.get(i);
			int processNumber = extractProcessNumber(process.getProcessName());
			int index = processNumber;

			view.gettblModel().setValueAt(process.getFinishTime(), index, 4);
			view.gettblModel().setValueAt(process.getWaitingTime(), index, 5);
			view.gettblModel().setValueAt(process.getTurnaroundTime(), index, 6);
		}
		

		// Draw gantt chart
		view.setGanttChart(scheduler.getOutputList());

		// writing summary
		view.getAvgTat().setText(String.valueOf(scheduler.getAverageTurnAroundTime()));
		view.getTotalTat().setText(String.valueOf(scheduler.getTotalTurnAroundTime()));
		view.getAvgWt().setText(String.valueOf(scheduler.getAverageWaitingTime()));
		view.getTotalWt().setText(String.valueOf(scheduler.getTotalWaitingTime()));
	}

	public int extractProcessNumber(String processName) {
		try {
			return Integer.parseInt(processName.substring(1));
		} catch (NumberFormatException e) {
			System.out.println("Invalid process name: " + processName);
			return -1;
		}
	}

}