package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Denne klassen tar seg av det grafiske vinduet som inneholder UCI knappene og viser resultatet fra Stockfish. 
public class AnalyzeInput extends JFrame {
	private JPanel containerPanel = new JPanel();
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	//private JLabel label = new JLabel();
	public JTextField fenInputTextfield;
	public JButton bestMoveButton = new JButton("Foresl�tt trekk");
	public JButton getEvalScore = new JButton("Get eval score");
	private JLabel bestMoveLabel = new JLabel();
	private JLabel evalScoreLabel = new JLabel();
	private JLabel mateScoreLabel = new JLabel();
	private JLabel showCurrentFenLabel = new JLabel();
	private JLabel fenTitle = new JLabel("N�v�rende FEN: ");
	private JLabel fenInputTitle = new JLabel("Sett inn ny FEN: ");
	private JLabel uciCommandTitle = new JLabel("Velg en funksjon: ");
	
	public AnalyzeInput(int windowPosX, int windowPosY, int windowSizeY) {
		setTitle("Analyze Sjakk");
		setVisible(true);
		setSize(600,windowSizeY);
		setLayout(new BorderLayout());
		setLocation(windowPosX + 50, windowPosY);
		containerPanel.setBackground(new Color(204, 201, 182));
		panel1.setBackground(new Color(204, 201, 182));
		panel2.setBackground(new Color(204, 201, 182));
		fenInputTextfield = new JTextField(30);
		fenTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fenInputTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		uciCommandTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel1.add(fenTitle);
		panel1.add(showCurrentFenLabel);
		panel1.add(fenInputTitle);
		panel1.add(fenInputTextfield);
		panel1.add(uciCommandTitle);
		panel1.add(bestMoveButton);
		panel1.add(getEvalScore);
		//panel.add(label);
		containerPanel.add(panel1);
		containerPanel.add(panel2);
		containerPanel.setBorder(null);
		panel1.setLayout(new GridLayout(0,1));
		containerPanel.setLayout(new GridLayout(0,1));
		add(containerPanel);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	//Tar imot en string og viser det i en label. Brukes til � vise hva Stockfish svarer oss n�r vi sp�r om anbefalt trekk. 
	public void showBestMove(String bestMove){
		bestMoveLabel.setText(bestMove);
		panel2.add(bestMoveLabel);
		revalidate();
		System.out.println(bestMove);
	}

	//Tar imot en string og viser det i en label. Brukes til � vise hva Stockfish svarer n�r vi sp�r etter evaluation score. 
	public void showEvalScore(String evalScore) {
		evalScoreLabel.setText(evalScore);
		panel2.add(evalScoreLabel);
		revalidate();
		System.out.println(evalScore);
	}
	
	//Likt de andre to, bare med antall trekk f�r sjakk matt. 
	public void showMateScore(String mateScore) {		
		mateScoreLabel.setVisible(true);	
		mateScoreLabel.setText(mateScore);
		panel2.add(mateScoreLabel);
		revalidate();
		System.out.println(mateScore);
	}
	
	//Viser den n�v�rende FEN n�kkelen som vises p� brettet og behandles internt i Stockfish. 
	public void showCurrentFen(String fen)
	{
		showCurrentFenLabel.setText(fen);
		revalidate();
		System.out.println("showCurrentFen ble bes�kt");
	}
	
}
