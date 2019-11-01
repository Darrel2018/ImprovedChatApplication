package server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server {
	
	private JFrame frame;
	private JTextArea textArea;
	
	private static String Title = "Chat Application Server";
	private static int width = 500, height = 500;
	
	// Constructor.
	public Server(){
		frame = new JFrame();
		textArea = new JTextArea();
		
		createView();
	}
	
	// adds GUI to the JFrame
	private void createView(){
		frame.add(createmainPanel(0, 0, width, height));
	}
	
	// creates the main panel.
	private JPanel createmainPanel(int x, int y, int width, int height){
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
		
		panel.add(createTextLabel(13, 15, "Server",
					new Font("Segoe UI", 2, 30), 100, setColor(253, 240, 240)));
		
		return panel;
	}
	
	// creates the bottom panel.
	private JPanel createBottomPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		JPanel startServer = new JPanel();
		JPanel stopServer = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(200, 200, 200));
		
		startServer.setLayout(null);
		startServer.setBackground(setColor(153, 153, 153));
		startServer.setBounds(10, 10, 100, 50);
		
		startServer.add(createTextLabel(13, -2, "Start Server",
				new Font("Segoe UI", 2, 15), 100, setColor(253, 240, 240)));
		
		stopServer.setLayout(null);
		stopServer.setBackground(setColor(153, 153, 153));
		stopServer.setBounds((20+100), 10, 100, 50);
		
		stopServer.add(createTextLabel(15, -2, "Stop Server",
				new Font("Segoe UI", 2, 15), 100, setColor(253, 240, 240)));
		
		addListeners(startServer, "startServer");
		addListeners(stopServer, "stopServer");
		
		panel.add(startServer);
		panel.add(stopServer);
		
		return panel;
	}
	
	// creates the middle panel.
	private JPanel createMiddlePanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		JScrollPane pane = new JScrollPane(textArea);
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(153, 153, 153));
		
		pane.setBounds(0, 0, width, height);
		textArea.setEditable(false);
		
		panel.add(pane);
		
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
	
	// exevutes the functions of buttons pressed.
	private void executeFunc(String button){
		
		if(button.equals("startServer")){
			System.out.println(button + " func was executed");
			textArea.append("Starting Server\n");
		}
		else if(button.equals("stopServer")){
			System.out.println(button + " func was executed");
			System.exit(0);
		}
		else {
			System.err.println("ERROR: please add button func");
		}
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
	
	// returns a color with the set RGB value.
	private Color setColor(int r, int g, int b){
		Color color = new Color(r, g, b);
		return color;
	}
	
	//----====Main====----\\
	public static void main(String[] args) {
		Server server = new Server();
		server.frame.setSize(new Dimension(width, height));
		server.frame.setLocationRelativeTo(null);
		server.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		server.frame.setResizable(false);
		server.frame.setTitle(Title);
		
		server.frame.setVisible(true);
	}
}
