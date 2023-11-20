/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author David McLain
 * 
 * Canvas to paint glyphs on
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyCanvas extends JPanel {
	private LilLexiControl control;
	/**
	 * Instantiates new canvas for printing
	 * @param control - controller to communicate to document
	 */
	public MyCanvas(LilLexiControl control) {
		setControl(control);
		repaint();
		this.setPreferredSize(new Dimension(800, 800));
		this.setBackground(Color.white);
		this.setVisible(true);
		this.setAutoscrolls(true);
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getX() <= 800)
					requestFocus();
//				System.out.println("Row: " + arg0.getX());
//				System.out.println("Col: " + arg0.getY());
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {	}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		this.addKeyListener(new KeyListener() {	
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				Glyph curGlyph;
				if (code == KeyEvent.VK_BACK_SPACE) {
					if (control.size() > 0) {
							control.removeLast();
					}
				}
				else if(code == KeyEvent.VK_LEFT) {
					control.decreaseCursorIndex();
				}
				else if (code == KeyEvent.VK_RIGHT) {
					control.increaseCursorIndex();
				}
				else if (code == KeyEvent.VK_UP) {
					control.decreaseCursorRow();
				}
				else if (code == KeyEvent.VK_DOWN) {
					control.increaseCursorRow();
				}
				else if ((code >= 44 && code <= 111) || code == 222 || code == 32 || code == 10) {
					curGlyph = new MyCharacter(e.getKeyChar());
					if (code >= 65 && code <= 90)
						((MyCharacter) curGlyph).setIsLetter(true);
					control.add(curGlyph);
				}
				repaint();
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}					
		});
		setFocusable(true);
	}
	/**
	 * Requests focus to canvas
	 */
	public void requestFocus() {
		super.requestFocus();
	}
	/**
	 * Paints all glyphs to canvas
	 * @param g - graphics to print
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setGraphics(g);
		g.clearRect(0, 0, 800, 800);
		g.setColor(Color.black);
		g.setFont(control.getFont());
		int[] loc = control.getCursorLoc();
		g.drawString("|", loc[1], loc[0]);
		List<Glyph> glyphs = control.getGlyphs();
		for (Glyph glyph : glyphs) {
			if (glyph instanceof MyCharacter) {
				g.drawString(glyph.toString(), glyph.getCol(), glyph.getRow());
				if (!((MyCharacter)glyph).getSpeltCor()) {
					g.setColor(Color.red);
					g.drawLine(glyph.getCol(), glyph.getRow(), glyph.getCol() + glyph.getWidth(), glyph.getRow() + 1);
					g.setColor(Color.black);
				}
			}
			else if (glyph instanceof MyImage) {
				((ImageIcon) glyph.get()).paintIcon(this, g, glyph.getCol(), glyph.getRow());
			}
			else if (glyph instanceof MyShape) {
				g.drawRect(glyph.getCol(), glyph.getRow(), glyph.getWidth(), glyph.getHeight());
			}
		}
	}
	/**
	 * Sets controller to communicate with document
	 * @param control - controller to communicate with document
	 */
	public void setControl(LilLexiControl control) {
		this.control = control;
	}
	/**
	 * Sets graphics in document for font metric reasons
	 * @param g - graphics to set in document
	 */
	private void setGraphics(Graphics g) {
		control.setGraphics(g);
	}
	
}
