package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.Component;

public class ChessMenu extends JFrame {
	
	private JPanel containerPanel = new JPanel();
	public JButton chessClientButton = new JButton("Enkeltspiller");
	public JButton chessAnalyzeButton = new JButton("Analysemodus");
	public JTextField klientIPTextbox = new JTextField(30);
	private JLabel ipAdresseTekst = new JLabel("Skriv inn IP adressen:");
	private String klientIP = null;
	private BufferedImage bilde;
	private JTextField textField;
	private JTextField textField_1;
	
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
		
		JTextPane txtpnVelkommenTilChessmaster = new JTextPane();
		txtpnVelkommenTilChessmaster.setFont(new Font("Tahoma", Font.PLAIN, 29));
		txtpnVelkommenTilChessmaster.setBackground(SystemColor.menu);
		txtpnVelkommenTilChessmaster.setText("Velkommen til ChessMaster X-TREME3000!");
		txtpnVelkommenTilChessmaster.setBounds(10, 11, 564, 48);
		containerPanel.add(txtpnVelkommenTilChessmaster);
		
		textField = new JTextField(30);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(50, 354, 271, 33);
		containerPanel.add(textField);
		
		JButton btnJoin = new JButton("Join");
		btnJoin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnJoin.setBounds(331, 258, 165, 33);
		containerPanel.add(btnJoin);
		
		JButton btnHost = new JButton("Host");
		btnHost.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnHost.setBounds(331, 354, 165, 33);
		containerPanel.add(btnHost);
		
		JLabel lblSkrivInnPorten = new JLabel("Skriv inn porten: ");
		lblSkrivInnPorten.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSkrivInnPorten.setBounds(50, 316, 114, 14);
		containerPanel.add(lblSkrivInnPorten);
		
		textField_1 = new JTextField(30);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setBounds(239, 258, 82, 33);
		containerPanel.add(textField_1);
		
		JLabel lblSkrivInnPorten_1 = new JLabel("Skriv inn porten:");
		lblSkrivInnPorten_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSkrivInnPorten_1.setBounds(226, 214, 136, 33);
		containerPanel.add(lblSkrivInnPorten_1);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/menuImage.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(33, 52, 463, 159);
		containerPanel.add(lblNewLabel);
		
		klientIPTextbox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				klientIP = klientIPTextbox.getText();
				System.out.println(klientIP);
				klientIPTextbox.setText("");
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
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}
