package se.mah.k3lara.skaneAPI.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import se.mah.k3lara.skaneAPI.control.FetchData;
import se.mah.k3lara.skaneAPI.model.Line;
import se.mah.k3lara.skaneAPI.model.Lines;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("Ub\u00E5tshallen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		createTable("80046");
	}
	
	private void createTable(String stationID){
		
		Thread thread = new FetchData(this, stationID);
		thread.setName("Get data");
		thread.start();
		
	}
	
	
	public void updateTable(Lines linesObject){
		ArrayList<Line> lines =  new ArrayList<>();
		
		//Remove all lines not departing from station B
		for (Line line : linesObject.getLines()) {
			if(line.getStopPoint().equals("B")) lines.add(line);
		}
		
		Object rowData[][] = new Object[lines.size()][2];
		int index = 0;
		
		for (Line l : lines) {
			
			Object row[] = {l.getLine(), l.getTowards().toUpperCase(), l.getFormatedDeparture(30)};
			rowData[index] = row;
			index++;
		}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				rowData,
				new String[] {
						"Linje", "Destination", "Avg\u00E5r"
					}
				) {
					private static final long serialVersionUID = 1L;
					boolean[] columnEditables = new boolean[] {
						false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setForeground(Color.ORANGE);
		table.setBackground(Color.BLACK);
		table.setFont(new Font("Courier New", Font.BOLD, 11));
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
	}
}
