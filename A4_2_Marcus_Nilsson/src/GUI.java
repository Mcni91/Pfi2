import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Animal> animals;

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
		
		animals = new ArrayList<Animal>();
		Animal lastAnimal;
		
		//Add dog
		animals.add(new Dog("Canis latrans", 247, false));
		//Give it a friendly name
		lastAnimal = animals.get(animals.size()-1);
		lastAnimal.setFriendlyName("Vovven");
		
		//Add cat
		animals.add(new Cat("Lynx lynx", 9001, 9));
		//Give it a friendly name
		lastAnimal = animals.get(animals.size()-1);
		lastAnimal.setFriendlyName("Dunder");
		
		//Add snake
		animals.add(new Snake("Python regious", true));
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		for (Animal animal : animals) {
			textArea.append(animal.getInfo() + "\n");
		}
	}

}
