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
	
	//correctCounter.setEditable(false);
	//incorrectCounter.setEditable(false);
	
	public GUIFlashcard() {
		Container cpane = getContentPane();
		cpane.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
		promptText = new JLabel(Flashcard.getPrompt());
		cpane.add(promptText);
		input = new JTextField();
		cpane.add(input);
		correctCounter = new JTextField(flashcard.getCorrect() + " correct.");
		correctCounter.setEditable(false);
		cpane.add(correctCounter);
		incorrectCounter = new JTextField(flashcard.getIncorrect() + " incorrect.");
		incorrectCounter.setEditable(false);
		cpane.add(incorrectCounter);
		submitBtn = new JButton("Check");
		cpane.add(submitBtn);
		
		submitBtn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {
	        	 flashcard.isCorrect(input.getText());
	    	     correctCounter.setText(flashcard.getCorrect() + " correct.");
	    	     incorrectCounter.setText(flashcard.getIncorrect() + " incorrect.");
	    	     flashcard.newWord();
	    		 promptText.setText(Flashcard.getPrompt()); // Convert int to String
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
