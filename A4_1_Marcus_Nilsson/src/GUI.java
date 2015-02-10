
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField_Human;
	private JTextField textField_Dog;
	
	private ArrayList<Human> humans;
	
	JTextPane txtpnInfo;
	JTextPane txtpnError;

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
		
		humans = new ArrayList<Human>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHumansAndDogs = new JLabel("Humans and Dogs");
		lblHumansAndDogs.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHumansAndDogs.setHorizontalAlignment(SwingConstants.CENTER);
		lblHumansAndDogs.setBounds(10, 11, 414, 28);
		contentPane.add(lblHumansAndDogs);
		
		textField_Human = new JTextField();
		textField_Human.setBounds(20, 50, 86, 20);
		contentPane.add(textField_Human);
		textField_Human.setColumns(10);
		
		JButton btnNewHuman = new JButton("New Human");
		btnNewHuman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = textField_Human.getText();
				if (temp.length() < 3) {
					printError("Name too short. Your name is bad and you should feel bad.");
				} else {
					humans.add(new Human(temp));
					printInfo("New human named " + temp + " created");
				}
			}
		});
		btnNewHuman.setBounds(116, 49, 191, 23);
		contentPane.add(btnNewHuman);
		
		textField_Dog = new JTextField();
		textField_Dog.setBounds(20, 81, 86, 20);
		contentPane.add(textField_Dog);
		textField_Dog.setColumns(10);
		
		JButton btnBuyDog = new JButton("Buy Dog");
		btnBuyDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = textField_Dog.getText();
				if (humans.isEmpty()) {
					printError("Without humans, there can be no dogs.");
				} else {
					Human human = humans.get(humans.size()-1);
					human.buyDog(new Dog(temp));
					printInfo("New dog named " + temp + " bought");
				}
			}
		});
		btnBuyDog.setBounds(116, 80, 191, 23);
		contentPane.add(btnBuyDog);
		
		JButton btnPrintInfo = new JButton("Print info");
		btnPrintInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!humans.isEmpty()) {
					for (Human human : humans) {
						printInfo(human.getInfo());
					}
					
				} else {
					printError("No humans, no nothing.");
				}
			}
		});
		btnPrintInfo.setBounds(116, 114, 191, 23);
		contentPane.add(btnPrintInfo);
		
		txtpnInfo = new JTextPane();
		txtpnInfo.setEditable(false);
		txtpnInfo.setBounds(10, 188, 414, 54);
		contentPane.add(txtpnInfo);
		
		txtpnError = new JTextPane();
		txtpnError.setEditable(false);
		txtpnError.setBounds(10, 268, 414, 54);
		contentPane.add(txtpnError);
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setBounds(10, 174, 46, 14);
		contentPane.add(lblInfo);
		
		JLabel lblErrormessage = new JLabel("ErrorMessage");
		lblErrormessage.setBounds(10, 253, 96, 14);
		contentPane.add(lblErrormessage);
	}
	private void printInfo(String message){
		txtpnInfo.setText(message);
		txtpnError.setText(null); //Clear the other text field to avoid confusion
	}
	private void printError(String message){
		txtpnError.setText(message);
		txtpnInfo.setText(null); //Clear the other text field to avoid confusion
	}
}
