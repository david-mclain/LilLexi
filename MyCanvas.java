import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
public class MyCanvas extends JPanel {
	private List<Character> chars;	
	private LilLexiControl control;
	MyCanvas(LilLexiControl control) {
		this.control = control;
		chars = new ArrayList<>();
		this.addKeyListener(new KeyListener() {
	
	        public void keyPressed(KeyEvent e) {
	            chars.add(e.getKeyChar());
	            repaint();
	            System.out.println("Pressed: " + e.getKeyChar());
	        }
	
	        public void keyReleased(KeyEvent arg0) {}
	        public void keyTyped(KeyEvent arg0) {}
	
	    });
	
	    setFocusable(true);
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
	    //Graphics2D g2d = (Graphics2D) g;
		int column = 0;
	    g.clearRect(0, 0, 500, 500);
	    g.setColor(Color.black);
	    g.setFont(new Font("Cambria", Font.BOLD, 20));
	    int row = g.getFont().getSize();
	    for (Character c : chars) {
	    	g.drawString(String.valueOf(c), column, row + 10);
	    	column = (column + 18) % (40*18);
			if (column == 0) row += 32;
			System.out.println(c);
	    }
	}
}
