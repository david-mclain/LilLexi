import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	private List<Character> chars;
	private int x;
	public MyFrame() {
		super();
		x = 0;
		chars = new ArrayList<>();
		this.addKeyListener(new KeyListener() {
			
	        public void keyPressed(KeyEvent e) {
	            chars.add(e.getKeyChar());
	            repaint();
	            //System.out.println("Pressed: " + e.getKeyChar());
	            System.out.println(chars);
	        }
	
	        public void keyReleased(KeyEvent arg0) {
	
	        }
	
	        public void keyTyped(KeyEvent arg0) {
	            // TODO Auto-generated method stub
	
	        }
	
	    });
	}
	
	public void repaint(Graphics g) {
		super.paintComponents(g);
	    //Graphics2D g2d = (Graphics2D) g;
	    g.clearRect(0, 0, 500, 500);
	    g.setColor(Color.black);
	    g.setFont(new Font("Cambria", Font.BOLD, 30));
	    for (Character c : chars) {
	    	x = x + 4;
	    	g.drawString(String.valueOf(c), 10, x % 500) ;
	    }
	}
	
}
