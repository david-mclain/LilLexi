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
	private List<Glyph> glyphs;	
	private LilLexiControl control;
	private Graphics graphics;
	public MyCanvas() {
		glyphs = new ArrayList<>();
		graphics = null;
		repaint();
		this.addKeyListener(new KeyListener() {	
								public void keyPressed(KeyEvent e) {
									graphics.setFont(control.getFont());
									System.out.println(graphics.getFontMetrics().stringWidth("This"));
									System.out.println(graphics.getFontMetrics().stringWidth("T"));
									System.out.println(graphics.getFontMetrics().stringWidth("h"));
									System.out.println(graphics.getFontMetrics().stringWidth("i"));
									System.out.println(graphics.getFontMetrics().stringWidth("s"));
									int code = e.getKeyCode();
									Glyph curGlyph;
									if (code == KeyEvent.VK_BACK_SPACE) {
										if (control.size() > 0) {
											control.removeLast();
										}
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
			graphics = g;
		g.clearRect(0, 0, 800, 800);
		g.setColor(Color.black);
		g.setFont(control.getFont());
		List<Glyph> glyphs = control.getGlyphs();
		int x = control.getFont().getSize();
		int row = x; //make row
		int col = 0; //make col
		for (Glyph glyph : glyphs) {
			int width = g.getFontMetrics().stringWidth(glyph.toString());
			//System.out.println(width);
			g.drawString(glyph.toString(), col, row);
			if ((col + x) / LilLexiDocument.PIXELS_PER_ROW == 1 || glyph.toString().equals("\n")) {
				row = row + x + 5;
				col = 0;
			}
			else {
				col = (col + width + 1) % (LilLexiDocument.PIXELS_PER_ROW);
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
