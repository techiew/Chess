package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChessMenu extends JFrame {
	private JPanel containerPanel = new JPanel();
	public JButton chessServerButton = new JButton("ChessBoard Server **JALL KRÆSJER BARE FOR JORGEN**");
	public JButton chessClientButton = new JButton("ChessBoard Client");
	public JButton chessAnalyzeButton = new JButton("ChessBoard Analyzer");
	public JTextField klientIPTextbox = new JTextField(30);
	private String klientIP = null;
	public ChessMenu()
	{
		setTitle("ChessMaster X-TREME3000");
		setVisible(true);
		setSize(600,600);
		setLocation(50,50);
		containerPanel.add(klientIPTextbox);
		containerPanel.add(chessServerButton);
		containerPanel.add(chessClientButton);
		containerPanel.add(chessAnalyzeButton);
		add(containerPanel);
		
		klientIPTextbox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				klientIP = klientIPTextbox.getText();
				System.out.println(klientIP);
				klientIPTextbox.setText("");
			}
		});

		
		chessAnalyzeButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
			{
				new ChessBoardAnalyze();
				setVisible(false);
			}
		});
		
		chessClientButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
			{
				if (klientIP == null)
				{
					System.out.println("Skriv inn en IP adresse REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
				}
				else {
					new ChessBoard(true, true, klientIP, 21337, "Sjakk"); //Klient
					setVisible(false);
				}
			}
		});
		
		chessServerButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
			{
				new ChessBoard(true, false, "localhost", 21337, "Sjakk"); //Server
				setVisible(false);
			}
		});
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}
