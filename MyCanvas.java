import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyCanvas extends JPanel {
	private final int PIXELS_PER_ROW = 800;
	private List<Glyph> glyphs;	
	private LilLexiControl control;
	public MyCanvas() {
		glyphs = new ArrayList<>();
		this.addKeyListener(new KeyListener() {	
								public void keyPressed(KeyEvent e) {
									int code = e.getKeyCode();
									if (code == KeyEvent.VK_BACK_SPACE) {
										if (control.size() > 0) {
											control.removeLast();
										}
									}
									else if ((code >= 44 && code <= 111) || code == 222 || code == 32) {
										control.add(new MyCharacter(e.getKeyChar()));
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
		g.clearRect(0, 0, 800, 800);
		g.setColor(Color.black);
		g.setFont(control.getFont());
		List<Glyph> glyphs = control.getGlyphs();
		int x = g.getFont().getSize();
		int row = x; //make row
		int col = 0; //make col
		for (Glyph glyph : glyphs) {
			int width = g.getFontMetrics().stringWidth(glyph.toString());
			g.drawString(glyph.toString(), col, row);
			if ((col + x) / PIXELS_PER_ROW == 1) {
				row = row + x + 5;
				col = 0;
			}
			else {
				col = (col + width + 1) % (PIXELS_PER_ROW);
			}
		}
	}
	
	public void clear() {
		glyphs.clear();
	}

	public void setControl(LilLexiControl control) {
		this.control = control;
	}
}
