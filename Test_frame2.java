package First;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Icon;
import java.net.URL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Test_frame2{
	public class SimpleActionListener implements ActionListener{
		
		private int clicks = 0;
		private JLabel label;
		
		public SimpleActionListener(JLabel label){
			this.label = label;
		}

		public void actionPerformed(ActionEvent event){
			clicks++;
			
			label.setText("Clicks: " + clicks);
			
		}
		
	}
	public static void main(String[] args){
		JFrame frame = new JFrame("Test Code");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		
		JLabel label = new JLabel("");
		label.setText("Считается количество нажатий на кнопку левой клавишей мышки.");
		panel.add(label);
		JLabel label3 = new JLabel("");
		label3.setText("Указываются ее координаты и вид нажатия - Pressed, Clicked, Released.");
		panel.add(label3);
		JButton button = new JButton();
		ImageIcon icon = createIcon("exit48x48.png");
        button.setIcon(icon);
		
		panel1.add(button);
	
		JLabel label1 = new JLabel("Clicks: 0");
		panel2.add(label1);
		
		JLabel label2 = new JLabel("Mouse: ");
		panel3.add(label2);

		button.addActionListener(new ActionListener(){
			int clicks = 0;
			public void actionPerformed(ActionEvent event){
				clicks++;

				label1.setText("Clicks: " + clicks);
			}
		});


		frame.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent me){
				label2.setText("Mouse clicked. (" + me.getX() + ", " + me.getY() + ")");
			}
			public void mouseEntered(MouseEvent me){
				label2.setText("Mouse entered. (" + me.getX() + ", " + me.getY() + ")");
			}
			public void mouseExited(MouseEvent me){
				label2.setText("Mouse exited. (" + me.getX() + ", " + me.getY() + ")");
			}
			public void mousePressed(MouseEvent me){
				label2.setText("Mouse pressed. (" + me.getX() + ", " + me.getY() + ")");
			}
			public void mouseReleased(MouseEvent me){
				label2.setText("Mouse released. (" + me.getX() + ", " + me.getY() + ")");
			}
		});
		
		frame.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent ke){
				label.setText("Key pressed. (" + ke.getKeyChar() + ")");
			}
				
			public void keyReleased(KeyEvent ke){
				label.setText("Key released. (" + ke.getKeyChar() + ")");
			}
	
			public void keyTyped(KeyEvent ke){
				label.setText("Key typed. (" + ke.getKeyChar() + ")");
			}
		});
		
		
		frame.setSize(500,500);
		frame.setLayout(null);
		panel.setBounds(0, 0, 500, 100);
		panel1.setBounds(0, 100, 500, 100);
		panel2.setBounds(0, 200, 500, 100);
		panel3.setBounds(0, 250, 500, 100);
		panel4.setBounds(0, 300, 500, 100);
		
		frame.add(panel);
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.add(panel4);
		frame.setVisible(true);

	}
	 
    protected static ImageIcon createIcon(String path) {
        URL imgURL = Test_frame2.class.getResource(path);     
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("File not found " + path);
            return null;
        }
    }
}