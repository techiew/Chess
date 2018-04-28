package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AnalyzeInput extends JFrame {
	private JPanel panel = new JPanel();
	//private JLabel label = new JLabel();
	public JTextField textfield = new JTextField(30);
	private String userInput;
	
	public AnalyzeInput(int windowPosX, int windowPosY)
	{
		setTitle("Analyze Sjakk");
		setVisible(true);
		setSize(400,200);
		setLocation(windowPosX + 50, windowPosY);
		panel.add(textfield);
		//panel.add(label);
		add(panel);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	public String getUserInput() {
		return userInput;
	}
	
	
}
