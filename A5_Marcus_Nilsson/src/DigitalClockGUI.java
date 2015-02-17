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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DigitalClockGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private ClockLogic clockLogic;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel time;
	private JLabel seconds;
	private JLabel alarm;
	private JPanel panel;
	private JButton btnSetAlarm;
	private JButton btnClearAlarm;
	private int[] day = {123, 206, 250};
	private int[] night = {52, 73, 94};

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
		setTitle("Neat clock");
		ImageIcon windowIcon = new ImageIcon(DigitalClockGUI.class.getResource("/resources/icon.png"));
		setIconImage(windowIcon.getImage());
		
		clockLogic =  new ClockLogic(this);
		
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
		
		seconds = new JLabel("52");
		seconds.setHorizontalAlignment(SwingConstants.CENTER);
		seconds.setFont(new Font("Myriad Pro Light", Font.PLAIN, 14));
		seconds.setBounds(416, 138, 24, 14);
		contentPane.add(seconds);
		
		time = new JLabel("13:37");
		time.setFont(new Font("Myriad Pro Light", Font.PLAIN, 60));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setBounds(10, 95, 729, 55);
		contentPane.add(time);
		
		alarm = new JLabel("");
		alarm.setFont(new Font("Myriad Pro Light", Font.PLAIN, 11));
		alarm.setHorizontalAlignment(SwingConstants.CENTER);
		alarm.setBounds(0, 150, 749, 14);
		contentPane.add(alarm);
		
		panel = new JPanel();
		panel.setBounds(10, 484, 729, 40);
		panel.setOpaque(false);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setToolTipText("hh:mm or hhmm");
		panel.add(textField);
		textField.setColumns(7);
		
		btnSetAlarm = new JButton("Set Alarm");
		btnSetAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				if (text.contains(":")) {
				    String[] temp = text.split(":");
				    clockLogic.setAlarm(
				    		Integer.parseInt(temp[0]), 
				    		Integer.parseInt(temp[1])
				    	);
				} else if (text.length() == 4){
					clockLogic.setAlarm(
							Integer.parseInt(text.substring(0,2)),
							Integer.parseInt(text.substring(2,4))
						);
					
				}
			}
		});
		panel.add(btnSetAlarm);
		
		btnClearAlarm = new JButton("Clear Alarm");
		btnClearAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clockLogic.clearAlarm();
			}
		});
		
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
	public void alarmSet(int hour, int minute){
		alarm.setText("Alarm: " + formatTime(hour, minute));
		panel.remove(btnSetAlarm);
		panel.add(btnClearAlarm);
		updateGUI();
	}
	public void alarmClear(){
		alarm.setText("");
		panel.remove(btnClearAlarm);
		panel.add(btnSetAlarm);
		updateGUI();
	}
	
	public void setTime(int hour, int minute, int second){
		time.setText(formatTime(hour, minute));
		seconds.setText(formatSecods(second));
		contentPane.setBackground(getBcgrColor(hour));
	}
	private Color getBcgrColor(int hour){
		int lightlevel = hour;
		if (hour > 12){
			lightlevel = 24-hour;
		}
		int r = Math.round(map(lightlevel, 0, 12, night[0], day[0]));
		int g = Math.round(map(lightlevel, 0, 12, night[1], day[1]));
		int b = Math.round(map(lightlevel, 0, 12, night[2], day[2]));
		return new Color(r, g, b);
	}
	private String formatTime(int hourIn, int minuteIn){
		String hour = String.valueOf(hourIn);
		String minute = String.valueOf(minuteIn);
		if (hourIn < 10) {
			hour = "0" + hourIn;
		}
		if (minuteIn < 10) {
			minute = "0" + minuteIn;			
		}
		return hour + ":" + minute;
	}
	private String formatSecods(int secondIn){
		String seconds = String.valueOf(secondIn);
		if (secondIn < 10) {
			seconds = "0" + secondIn;
		}
		return seconds;
	}
	private void updateGUI(){
		contentPane.validate();
		contentPane.repaint();
	}
	
	/**Glorious map method from Processing!
	 * istart/istop = value range
	 * ostart/ostop = target range
	 * */
	static private final float map(float value, 
			float istart, 
			float istop, 
			float ostart, 
			float ostop) {
		return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}
	
}
