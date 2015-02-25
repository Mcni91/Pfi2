package se.mah.k3lara.skaneAPI.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;

import se.mah.k3lara.skaneAPI.control.Constants;
import se.mah.k3lara.skaneAPI.model.Journey;
import se.mah.k3lara.skaneAPI.model.Journeys;
import se.mah.k3lara.skaneAPI.model.Station;
import se.mah.k3lara.skaneAPI.xmlparser.Parser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JScrollPane;


public class TestGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private ArrayList<Station> stations;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea_1;
	private Journeys journeys;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUI frame = new TestGUI();
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
	public TestGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stations = new ArrayList<Station>(); 
				stations.addAll( Parser.getStationsFromURL( textField.getText() ));
				for (Station station : stations) {
					textArea.append(station.getStationName() + " (" +station.getStationNbr() + ")\n");
					textArea.append("Latitud: " + station.getLatitude() +" Longitude: " + station.getLongitude() + "\n\n");
				}
			}
		});
		panel_1.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		textField_1 = new JTextField();
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = Constants.getURL(textField_1.getText(), textField_2.getText(), 1);
				journeys = Parser.getJourneys( url );
				ArrayList<Journey> journeysList = journeys.getJourneys();
				Journey journey = journeysList.get(0);
				
				Calendar depTime = journey.getDepDateTime();
				Calendar arrTime = journey.getArrDateTime();
				
				textArea_1.append("Byten: " + journey.getNoOfChanges() + "\n");
				textArea_1.append("Börja med: " + journey.getLineTypeName());
				textArea_1.append(": " + journey.getLineOnFirstJourney()+ "\n");
				textArea_1.append("Antal zoner: " + journey.getNoOfZones() + "\n");
				
				textArea_1.append("Avgår från: " + journey.getStartStation());
				textArea_1.append(" efter " + journey.getTimeToDeparture() + " signalfel\n");
				textArea_1.append("Klockan: " + depTime.HOUR_OF_DAY + ":" + depTime.MINUTE);
				textArea_1.append(" eventuell försening: " + journey.getDepTimeDeviation() + "\n");
				textArea_1.append("Restid: " + journey.getTravelMinutes() + "min\n");
				
				textArea_1.append("Ankommer: " + arrTime.HOUR_OF_DAY + ":" + arrTime.MINUTE);
				textArea_1.append(" till: " + journey.getEndStation() + "\n");
			}
		});
		panel_3.add(btnSearch_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		splitPane.setDividerLocation(280);
	}

}
