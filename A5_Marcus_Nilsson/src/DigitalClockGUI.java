import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;


public class DigitalClockGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DigitalClockGUI frame = new DigitalClockGUI();
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
	public DigitalClockGUI() {
		
		String className = getLookAndFeelClassName("Nimbus");
		try {
			UIManager.setLookAndFeel(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 574);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel seconds = new JLabel("52");
		seconds.setHorizontalAlignment(SwingConstants.CENTER);
		seconds.setFont(new Font("Myriad Pro Light", Font.PLAIN, 14));
		seconds.setBounds(416, 138, 24, 14);
		contentPane.add(seconds);
		
		JLabel time = new JLabel("13:37");
		time.setFont(new Font("Myriad Pro Light", Font.PLAIN, 60));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setBounds(10, 95, 729, 55);
		contentPane.add(time);
		
		JLabel alarm = new JLabel("Alarm: 14:30");
		alarm.setFont(new Font("Myriad Pro Light", Font.PLAIN, 11));
		alarm.setHorizontalAlignment(SwingConstants.CENTER);
		alarm.setBounds(0, 150, 749, 14);
		contentPane.add(alarm);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 484, 729, 40);
		panel.setOpaque(false);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setToolTipText("HH:MM, or HHMM");
		panel.add(textField);
		textField.setColumns(7);
		
		JButton btnSetAlarm = new JButton("Set Alarm");
		panel.add(btnSetAlarm);
		
		JButton btnClearAlarm = new JButton("Clear Alarm");
		panel.add(btnClearAlarm);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(DigitalClockGUI.class.getResource("/resources/mountains.png")));
		backgroundImage.setBounds(0, 0, 749, 535);
		contentPane.add(backgroundImage);
	}
	
	/**
	 * Returns the class name of the installed LookAndFeel with a name
	 * containing the name snippet or null if none found.
	 * 
	 * @param nameSnippet a snippet contained in the Laf's name
	 * @return the class name if installed, or null
	 */
	public static String getLookAndFeelClassName(String nameSnippet) {
	    LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
	    for (LookAndFeelInfo info : plafs) {
	        if (info.getName().contains(nameSnippet)) {
	            return info.getClassName();
	        }
	    }
	    return null;
	}
	
}
