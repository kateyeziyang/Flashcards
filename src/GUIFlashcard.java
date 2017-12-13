import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import javax.swing.*;  

public class GUIFlashcard extends JFrame {
	private JTextField correctCounter;
	private JTextField incorrectCounter;
	private JTextField input;
	private JLabel promptText;
	private JButton submitBtn;
	Flashcard flashcard = new Flashcard();
	
	public GUIFlashcard() {
		Container cpane = getContentPane();
		cpane.setLayout(new BorderLayout());
		promptText = new JLabel(Flashcard.getPrompt());
		cpane.add(promptText, BorderLayout.NORTH);
		input = new JTextField();
		cpane.add(input, BorderLayout.CENTER);
		correctCounter = new JTextField(flashcard.getCorrect() + " correct.");
		correctCounter.setEditable(false);
		cpane.add(correctCounter, BorderLayout.EAST);
		incorrectCounter = new JTextField(flashcard.getIncorrect() + " incorrect.");
		incorrectCounter.setEditable(false);
		cpane.add(incorrectCounter, BorderLayout.WEST);
		submitBtn = new JButton("Check");
		cpane.add(submitBtn, BorderLayout.SOUTH);
		
		submitBtn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {
	        	 flashcard.isCorrect(input.getText());
	    	     correctCounter.setText(flashcard.getCorrect() + " correct.");
	    	     incorrectCounter.setText(flashcard.getIncorrect() + " incorrect.");
	    	     flashcard.newWord();
	    		 promptText.setText(Flashcard.getPrompt());
	         }
	      });
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("GUI Russian Flashcards");
		this.setSize(500, 100);
		
		setVisible(true); 
	}
	
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "This is a flashcard program for learning the conjugations of common Russian Verbs.");
		new GUIFlashcard();
	}
}
