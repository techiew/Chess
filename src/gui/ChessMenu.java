package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class ChessMenu extends JFrame {
	public String clientIP;
	public int clientPort;
	public int hostPort;
	
	private JPanel containerPanel = new JPanel();
	public JButton singleplayerButton = new JButton("Enkeltspiller");
	public JButton chessAnalyzeButton = new JButton("Analysemodus");
	public JButton hostButton = new JButton("Host");
	public JButton joinButton = new JButton("Join");
	public JTextField clientIPTextfield = new JTextField(30);
	private JLabel ipAdressText = new JLabel("Skriv inn IP adressen:");
	private JLabel addPortText1 = new JLabel("Skriv inn porten:");
	private JLabel lblNewLabel = new JLabel("");
	private JLabel addPortText2 = new JLabel("Skriv inn porten: ");
	private BufferedImage img;
	private JTextField hostPortTextfield;
	private JTextField clientPortTextfield;
	private JLabel picLabel;
	
	
	public ChessMenu()
	{		
		setTitle("ChessMaster X-TREME3000");
		setVisible(true);
		setSize(600,600);
		setLocation(50,50);
		containerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		containerPanel.setBorder(null);
		containerPanel.setLayout(null);
		ipAdressText.setFont(new Font("Tahoma", Font.BOLD, 12));
		ipAdressText.setBounds(50, 214, 136, 33);
		containerPanel.add(ipAdressText);
		clientIPTextfield.setFont(new Font("Tahoma", Font.PLAIN, 20));
		clientIPTextfield.setBounds(50, 258, 190, 33);
		containerPanel.add(clientIPTextfield);
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
		singleplayerButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		singleplayerButton.setBounds(50, 412, 446, 48);
		containerPanel.add(singleplayerButton);
		getContentPane().add(containerPanel);
		
		hostPortTextfield = new JTextField(30);
		hostPortTextfield.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hostPortTextfield.setBounds(50, 354, 271, 33);
		containerPanel.add(hostPortTextfield);
		
		joinButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		joinButton.setBounds(331, 258, 165, 33);
		containerPanel.add(joinButton);
		
		hostButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		hostButton.setBounds(331, 354, 165, 33);
		containerPanel.add(hostButton);
		
		addPortText2.setFont(new Font("Tahoma", Font.BOLD, 12));
		addPortText2.setBounds(50, 316, 114, 14);
		containerPanel.add(addPortText2);
		
		clientPortTextfield = new JTextField(30);
		clientPortTextfield.setFont(new Font("Tahoma", Font.PLAIN, 20));
		clientPortTextfield.setBounds(239, 258, 82, 33);
		containerPanel.add(clientPortTextfield);
		
		addPortText1.setFont(new Font("Tahoma", Font.BOLD, 12));
		addPortText1.setBounds(226, 214, 136, 33);
		containerPanel.add(addPortText1);		
		
		//JPanel imagePanel = new JPanel();
		//imagePanel.setBounds(10, 49, 552, 154);
		//containerPanel.add(imagePanel);
		
		JLabel welcomeText = new JLabel("Velkommen til ChessMaster X-TREME3000!");
		welcomeText.setFont(new Font("Tahoma", Font.BOLD, 25));
		welcomeText.setBounds(10, 11, 564, 33);
		containerPanel.add(welcomeText);
		
		
		BufferedImage myPicture;
		
		try {
			myPicture = ImageIO.read(new File("menuImage.png"));
			JLabel label = new JLabel(new ImageIcon(myPicture));
			label.setSize(new Dimension(463, 159));
			//picLabel.setSize(200,200);
			label.setBounds(33, 52, 463, 159);
			containerPanel.add(label);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		//Image img = new ImageIcon(this.getClass().getResource("/menuImage.png")).getImage();
		//lblNewLabel.setIcon(new ImageIcon(img));
		//lblNewLabel.setBounds(33, 52, 463, 159);
		//containerPanel.add(img);
		
		clientIPTextfield.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				
			}
			public void focusLost(FocusEvent e) {
				clientIP = clientIPTextfield.getText();
				System.out.println(clientIP);
			}
		});
		
		clientPortTextfield.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				
			}
			public void focusLost(FocusEvent e) {
				String clientPortString = clientPortTextfield.getText();
				if (isANumber(clientPortString)) {
					clientPort = Integer.parseInt(clientPortString);
					System.out.println(clientPort);
				}
			}
		});
		
		hostPortTextfield.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				
			}
			public void focusLost(FocusEvent e) {
				String hostPortString = hostPortTextfield.getText();
				if (isANumber(hostPortString)) {
					System.out.println(hostPortString);
					hostPort = Integer.parseInt(hostPortString);
					System.out.println(hostPort);
				}
			}
		});
	
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	private boolean isANumber(String incompleteSplitFenCode)
	  {
	    try
	    {
	      double check = Double.parseDouble(incompleteSplitFenCode);
	    }
	    catch(NumberFormatException nfe)
	    {
	      return false;
	    }
	    return true;
	  }
}