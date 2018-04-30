package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ChessMenu extends JFrame {
	public String clientIP = null;
	public int clientPort = 0;
	public int hostPort = 0;
	
	private JPanel containerPanel = new JPanel();
	public JButton chessClientButton = new JButton("Enkeltspiller");
	public JButton chessAnalyzeButton = new JButton("Analysemodus");
	public JButton btnHost = new JButton("Host");
	public JButton btnJoin = new JButton("Join");
	public JTextField klientIPTextbox = new JTextField(30);
	private JLabel ipAdresseTekst = new JLabel("Skriv inn IP adressen:");
	private JLabel lblSkrivInnPorten_1 = new JLabel("Skriv inn porten:");
	private JTextPane txtpnVelkommenTilChessmaster = new JTextPane();
	private JLabel lblNewLabel = new JLabel("");
	private JLabel lblSkrivInnPorten = new JLabel("Skriv inn porten: ");
	private BufferedImage bilde;
	private JTextField hostPortTextfield;
	private JTextField clientPortTextfield;
	
	public ChessMenu()
	{		
		setTitle("ChessMaster X-TREME3000");
		setVisible(true);
		setSize(600,600);
		setLocation(50,50);
		containerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		containerPanel.setBorder(null);
		containerPanel.setLayout(null);
		ipAdresseTekst.setFont(new Font("Tahoma", Font.BOLD, 12));
		ipAdresseTekst.setBounds(50, 214, 136, 33);
		containerPanel.add(ipAdresseTekst);
		klientIPTextbox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		klientIPTextbox.setBounds(50, 258, 190, 33);
		containerPanel.add(klientIPTextbox);
		chessAnalyzeButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		chessAnalyzeButton.setBounds(50, 484, 446, 48);
		containerPanel.add(chessAnalyzeButton);
		
				
				chessAnalyzeButton.addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent e)
					{
						new ChessBoardAnalyze();
						setVisible(false);
					}
				});
		chessClientButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		chessClientButton.setBounds(50, 412, 446, 48);
		containerPanel.add(chessClientButton);
		getContentPane().add(containerPanel);
		
		txtpnVelkommenTilChessmaster.setFont(new Font("Tahoma", Font.PLAIN, 29));
		txtpnVelkommenTilChessmaster.setBackground(SystemColor.menu);
		txtpnVelkommenTilChessmaster.setText("Velkommen til ChessMaster X-TREME3000!");
		txtpnVelkommenTilChessmaster.setBounds(10, 11, 564, 48);
		containerPanel.add(txtpnVelkommenTilChessmaster);
		
		hostPortTextfield = new JTextField(30);
		hostPortTextfield.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hostPortTextfield.setBounds(50, 354, 271, 33);
		containerPanel.add(hostPortTextfield);
		
		btnJoin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnJoin.setBounds(331, 258, 165, 33);
		containerPanel.add(btnJoin);
		
		btnHost.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnHost.setBounds(331, 354, 165, 33);
		containerPanel.add(btnHost);
		
		lblSkrivInnPorten.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSkrivInnPorten.setBounds(50, 316, 114, 14);
		containerPanel.add(lblSkrivInnPorten);
		
		clientPortTextfield = new JTextField(30);
		clientPortTextfield.setFont(new Font("Tahoma", Font.PLAIN, 20));
		clientPortTextfield.setBounds(239, 258, 82, 33);
		containerPanel.add(clientPortTextfield);
		
		lblSkrivInnPorten_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSkrivInnPorten_1.setBounds(226, 214, 136, 33);
		containerPanel.add(lblSkrivInnPorten_1);
		
		//Image img = new ImageIcon(this.getClass().getResource("/menuImage.png")).getImage();
		//lblNewLabel.setIcon(new ImageIcon(img));
		//lblNewLabel.setBounds(33, 52, 463, 159);
		containerPanel.add(lblNewLabel);
		
		klientIPTextbox.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				
			}
			public void focusLost(FocusEvent e) {
				clientIP = klientIPTextbox.getText();
			}
		});
		
		clientPortTextfield.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				
			}
			public void focusLost(FocusEvent e) {
				String clientPortString = clientPortTextfield.getText();
				clientPort = Integer.parseInt(clientPortString);
			}
		});
		
		hostPortTextfield.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				
			}
			public void focusLost(FocusEvent e) {
				String hostPortString = hostPortTextfield.getText();
				hostPort = Integer.parseInt(hostPortString);
			}
		});
	
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}
