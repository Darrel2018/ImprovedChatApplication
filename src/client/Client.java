package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	
	private JFrame frame;
	private JTextArea textArea;
	private JTextField submitField, userNameField;
	private JPanel mPanel;
	private DatagramSocket socket;
	
	private static String userName = "";
	private static String Title = "Chat Application Client";
	private static int width = 500, height = 500;
	
	// Constructor.
	public Client(){
		frame = new JFrame();
		textArea = new JTextArea();
		submitField = new JTextField();
		userNameField = new JTextField();
		mPanel = new JPanel();
		
		createView();
	}
	
	// adds GUI components to JFrame.
	private void createView(){
		frame.setLayout(null);
		mPanel = createUserNamePanel(0, 0, width, height);
		frame.add(mPanel);
	}
	
	// creates the userName Panel.
	private JPanel createUserNamePanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		JPanel submitButton = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(153, 153, 153));
		
		userNameField.setBounds((width/2)-100, 150, 190, 25);
		
		submitButton.setLayout(null);
		submitButton.setBounds((width/2)-55, 200, 100, 50);
		submitButton.setBackground(setColor(153, 153, 153));
		submitButton.setBorder(BorderFactory.createLineBorder(setColor(253, 240, 240)));
		
		submitButton.add(createTextLabel(26, -2, "Submit",
				new Font("Segoe UI", 2, 15), 200, setColor(253, 240, 240)));
		
		addListeners(submitButton, "submitUserName");
		
		panel.add(createTextLabel((width/2)-100, 100, "Submit UserName",
				new Font("Segoe UI", 2, 25), 200, setColor(253, 240, 240)));
		
		panel.add(userNameField);
		panel.add(submitButton);
		
		return panel;
	}
	
	// creates the MainPanel
	private JPanel createMainPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(153, 153, 153));
		
		panel.add(createHeadPanel(0, 0, width, 100));
		panel.add(createMiddlePanel(0, 100, width, 300));
		panel.add(createBottomPanel(0, (height-100), width, 100));
		
		return panel;
	}
	
	// creates the headPanel
	private JPanel createHeadPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(200, 200, 200));
		
		panel.add(createTextLabel(13, 15, "Client",
					new Font("Segoe UI", 2, 30), 100, setColor(253, 240, 240)));
		
		return panel;
	}
	
	// creates the middle panel.
	private JPanel createMiddlePanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		JPanel submitButton = new JPanel();
		JScrollPane pane = new JScrollPane(textArea);
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(130, 130, 130));
		
		pane.setBounds(0, 0, width-10, height-50);
		textArea.setEditable(false);
		
		submitField.setBounds(0, height-50, 400, 30);
		
		submitButton.setLayout(null);
		submitButton.setBounds(400, height-50, 100, 50);
		submitButton.setBackground(setColor(153, 153, 153));
		submitButton.add(createTextLabel(25, -2, "Submit",
				new Font("Segoe UI", 2, 15), 100, setColor(253, 240, 240)));
		
		addListeners(submitButton, "submitButton");
		
		panel.add(pane);
		panel.add(submitField);
		panel.add(submitButton);
		
		return panel;
	}
	
	// creates the bottom panel.
	private JPanel createBottomPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		JPanel startClient = new JPanel();
		JPanel stopClient = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(200, 200, 200));
		
		startClient.setLayout(null);
		startClient.setBackground(setColor(153, 153, 153));
		startClient.setBounds(10, 10, 100, 50);
		
		startClient.add(createTextLabel(13, -2, "Start Client",
				new Font("Segoe UI", 2, 15), 100, setColor(253, 240, 240)));
			
		stopClient.setLayout(null);
		stopClient.setBackground(setColor(153, 153, 153));
		stopClient.setBounds((20+100), 10, 100, 50);
		
		stopClient.add(createTextLabel(15, -2, "Stop Client",
				new Font("Segoe UI", 2, 15), 100, setColor(253, 240, 240)));
			
		addListeners(startClient, "startClient");
		addListeners(stopClient, "stopClient");
			
		panel.add(startClient);
		panel.add(stopClient);
		
		return panel;
	}
	
	// add listeners to buttons.
	private void addListeners(JPanel panel, String button){
		
		panel.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e){
				panel.setBackground(setColor(200, 200, 200));
			}
			
			public void mouseReleased(MouseEvent e){
				executeFunc(button);
				panel.setBackground(setColor(153, 153, 153));
			}
		});
	}
	
	// executes the functions of buttons pressed.
	private void executeFunc(String button){
		
		if(button.equals("startClient")){
			System.out.println(button + " func was executed");
			
			if(socket == null){
				startClient();
				textArea.append("Starting Client\nWelcome, " + userName + "\n");
			}
			else {
				textArea.append("ERROR: Client has already started!!!\n");
			}
		}
		else if(button.equals("stopClient")){
			System.out.println(button + " func was executed");
			System.exit(0);
		}
		else if(button.equals("submitButton")){
			System.out.println(button + " func was executed");
			
			if(!(socket == null)){
				textArea.append(userName + ": " + submitField.getText() + "\n");
				send(userName + ": " + submitField.getText(), "localhost", 1234);
			}
			else {
				textArea.append("ERROR: please start Client...");
			}
			
			submitField.setText("");
		}
		else if(button.equals("submitUserName")){
			System.out.println(button + " func was executed");
			
			if(testUserName(userNameField.getText())){
				mPanel.removeAll();
				mPanel.repaint();
				
				mPanel.add(createMainPanel(0, 0, width, height));
			}
		}
		else {
			System.err.println("ERROR: please add button func");
		}
	}
	
	// creates a new DATAGRAM socket.
	private void startClient(){
		try {
			socket = new DatagramSocket();
			
			send(userName + " has joined", "localhost", 1234);
			
			receive();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	// waits to receive packets from the server.
	private void receive(){
		
		Thread thread = new Thread("Client: Waiting to receive packet"){
			
			public void run(){
				
				while(true){
					
					byte[] rData = new byte[1024];
					DatagramPacket packet = new DatagramPacket(rData, rData.length);
					
					try {
						socket.receive(packet);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					String msg = new String(rData);
					msg = msg.substring(0, msg.indexOf("/e/"));
					
					textArea.append(msg + "\n");
				}
			}
			
		}; thread.start();
	}
	
	// sends a message to the server.
	private void send(String msg, String ip, int port){
		try {
			
			msg += "/e/";
			
			byte[] rData = msg.getBytes();
			
			DatagramPacket packet = new DatagramPacket(rData, rData.length, InetAddress.getByName(ip), port);
			
			socket.send(packet);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// tests to see if the userName is not blank.
	private boolean testUserName(String name){
		
		if(!name.equals("")){
			userName = name;
			return true;
		}
		
		return false;
	}
	
	// Returns a JLabel with the set location/font/width/color
	private JLabel createTextLabel(int x, int y, String text, Font font, int textWidth, Color color){
		JLabel textLabel = new JLabel();
		
		textLabel.setText(text);
		textLabel.setFont(font);
		textLabel.setBounds(x, y, textWidth, 50);
		
		textLabel.setForeground(color);
		
		return textLabel;
	}
	
	// returns a color object based on the RGB values.
	private Color setColor(int r, int g, int b){
		Color color = new Color(r, g, b);
		return color;
	}
	
	//----====MAIN====----\\
	public static void main(String[] args) {
		Client client = new Client();
		client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.frame.setSize(new Dimension(width, height));
		client.frame.setLocationRelativeTo(null);
		client.frame.setResizable(false);
		client.frame.setTitle(Title);
		
		client.frame.setVisible(true);
	}
}
