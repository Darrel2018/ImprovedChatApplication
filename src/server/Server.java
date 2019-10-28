package server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Server {
	
	private JFrame frame;
	
	private static String Title = "Chat Application Server";
	private static int width = 500, height = 500;
	
	public Server(){
		frame = new JFrame();
		
		createView();
	}
	
	private void createView(){
		frame.add(createmainPanel(0, 0, width, height));
	}
	
	private JPanel createmainPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(153, 153, 153));
		
		panel.add(createHeadPanel(0, 0, width, 100));
		
		return panel;
	}
	
	private JPanel createHeadPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(200, 200, 200));
		
		panel.add(createTextLabel(13, 15, "Server",
					new Font("Segoe UI", 2, 30), 100, setColor(253, 240, 240)));
		
		return panel;
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
	
	private Color setColor(int r, int g, int b){
		Color color = new Color(r, g, b);
		return color;
	}
	
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
