package LilLexi;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyCanvas extends JPanel {
	private LilLexiControl control;
	private Graphics graphics;
	public MyCanvas() {
		graphics = null;
		repaint();
		this.addKeyListener(new KeyListener() {	
								public void keyPressed(KeyEvent e) {
									graphics.setFont(control.getFont());
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
										control.increseCursorIndex();
									}
									//else if (code == 9) {
									//	for (int i = 0; i < 4; i++)
									//		control.add(new MyCharacter(' '));
									//}
									else if ((code >= 44 && code <= 111) || code == 222 || code == 32 || code == 10) {
										curGlyph = new MyCharacter(e.getKeyChar());
										control.add(curGlyph);
										//control.add(new MyCharacter(e.getKeyChar()));
									}
									repaint();
								}
								public void keyReleased(KeyEvent arg0) {}
								public void keyTyped(KeyEvent arg0) {}					
							});
	
		setFocusable(true);
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		if (graphics == null)
			setGraphics(g);
		g.clearRect(0, 0, 800, 800);
		g.setColor(Color.black);
		g.setFont(control.getFont());
		List<Glyph> glyphs = control.getGlyphs();
		for (Glyph glyph : glyphs) {
			g.drawString(glyph.toString(), glyph.getCol(), glyph.getRow());
		}
	}

	public void setControl(LilLexiControl control) {
		this.control = control;
	}
	
	private void setGraphics(Graphics g) {
		this.graphics = g;
		control.setGraphics(g);
	}
}
