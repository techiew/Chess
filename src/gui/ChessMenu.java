package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Selve vinduet for sjakkmenyen som kommer på oppstart. 
public class ChessMenu extends JFrame {
	public String clientIP;
	public int clientPort;
	public int hostPort;
	
	private JPanel containerPanel = new JPanel();
	private ImageIcon imageSolo = new ImageIcon("src/images/button_lokal-flerspiller.png");
	private ImageIcon imageAnalyze = new ImageIcon("src/images/button_analysemodus.png");
	private ImageIcon join = new ImageIcon("src/images/button_join.png");
	private ImageIcon host = new ImageIcon("src/images/button_host.png");
	public JLabel singleplayerLabel = new JLabel(imageSolo);
	public JLabel analyzeLabel = new JLabel(imageAnalyze);
	public JLabel joinLabel = new JLabel(join);
	public JLabel hostLabel = new JLabel(host);
	public JTextField clientIPTextfield = new JTextField(30);
	private JLabel ipAdressText = new JLabel("Skriv inn IP adressen:");
	private JLabel addPortText1 = new JLabel("Skriv inn porten:");
	private JLabel lblNewLabel = new JLabel("");
	private JLabel addPortText2 = new JLabel("Skriv inn porten: ");
	private BufferedImage img;
	public JTextField hostPortTextfield;
	public JTextField clientPortTextfield;
	
	
	public ChessMenu()
	{		
		setTitle("OBJ2000V Eksamen 2018");
		setVisible(true);
		setSize(600,600);
		setLocation(50,50);
		containerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		containerPanel.setBorder(null);
		containerPanel.setLayout(null);
		containerPanel.setBackground(new Color(204, 201, 182));
		ipAdressText.setFont(new Font("Tahoma", Font.BOLD, 12));
		ipAdressText.setBounds(50, 288, 136, 33);
		containerPanel.add(ipAdressText);
		clientIPTextfield.setFont(new Font("Tahoma", Font.PLAIN, 20));
		clientIPTextfield.setBounds(50, 326, 190, 33);
		containerPanel.add(clientIPTextfield);
		analyzeLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		analyzeLabel.setBounds(50, 502, 446, 48);
		containerPanel.add(analyzeLabel);
				
		singleplayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		singleplayerLabel.setBounds(50, 436, 446, 48);
		containerPanel.add(singleplayerLabel);
		getContentPane().add(containerPanel);
		
		hostPortTextfield = new JTextField(30);
		hostPortTextfield.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hostPortTextfield.setBounds(50, 395, 271, 33);
		containerPanel.add(hostPortTextfield);
		
		joinLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		joinLabel.setBounds(331, 326, 165, 33);
		containerPanel.add(joinLabel);
		
		hostLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		hostLabel.setBounds(331, 395, 165, 33);
		containerPanel.add(hostLabel);
		
		addPortText2.setFont(new Font("Tahoma", Font.BOLD, 12));
		addPortText2.setBounds(50, 370, 114, 14);
		containerPanel.add(addPortText2);
		
		clientPortTextfield = new JTextField(30);
		clientPortTextfield.setFont(new Font("Tahoma", Font.PLAIN, 20));
		clientPortTextfield.setBounds(239, 326, 82, 33);
		containerPanel.add(clientPortTextfield);
		
		addPortText1.setFont(new Font("Tahoma", Font.BOLD, 12));
		addPortText1.setBounds(224, 288, 136, 33);
		containerPanel.add(addPortText1);	
		
		JLabel welcomeText = new JLabel("OBJ2000V EKSAMEN 2018 SJAKK");
		welcomeText.setFont(new Font("Tahoma", Font.BOLD, 25));
		welcomeText.setBounds(10, 11, 564, 33);
		containerPanel.add(welcomeText);
		
		
		BufferedImage myPicture;
		
		try {
			myPicture = ImageIO.read(new File("src/images/menuImage.png"));
			JLabel label = new JLabel(new ImageIcon(myPicture));
			label.setSize(new Dimension(463, 159));
			//picLabel.setSize(200,200);
			label.setBounds(33, 52, 463, 160);
			containerPanel.add(label);
			
			JLabel lblOnlineFlerspiller = new JLabel("Online flerspiller:");
			lblOnlineFlerspiller.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblOnlineFlerspiller.setBounds(50, 244, 271, 33);
			containerPanel.add(lblOnlineFlerspiller);
			repaint();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Legger til en focuslistener til tekstfeltet. Når den mister fokus, så blir informasjonen i feltet sendt videre. 
		clientIPTextfield.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				
			}
			public void focusLost(FocusEvent e) {
				clientIP = clientIPTextfield.getText();
				System.out.println(clientIP);
			}
		});
		
		//Focuslistener for porten til join. Gjør det samme som den over. 
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
		
		//Focuslistener for porten til host. Gjør det samme som de to over. 
		hostPortTextfield.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				
			}
			public void focusLost(FocusEvent e) {
				String hostPortString = hostPortTextfield.getText();
				if (isANumber(hostPortString)) {
					hostPort = Integer.parseInt(hostPortString);
					System.out.println(hostPort + "hei");
				}
			}
		});
		
		//Lukker vinduet når du lukker vinduet. 
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	//Metode hentet fra stackoverflow https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
	//Sjekker om verdien tilsendt er et tall. 
	private boolean isANumber(String incompleteSplitFenCode) {
	    try {
	      double check = Double.parseDouble(incompleteSplitFenCode);
	    } catch(NumberFormatException nfe) {
	      return false;
	    }
	    return true;
	  }
	
}
