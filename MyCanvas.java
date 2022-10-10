package LilLexi;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyCanvas extends JPanel {
	private LilLexiControl control;
	private Graphics graphics;
	private BufferedImage image;
	private ImageIcon icon;
	private int i;
	public MyCanvas() {
		graphics = null;
		repaint();
		this.addKeyListener(new KeyListener() {	
								public void keyPressed(KeyEvent e) {
									i++;
									//graphics.setFont(control.getFont());
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
									else if (code == KeyEvent.VK_UP) {
										control.decreaseCursorRow();
									}
									else if (code == KeyEvent.VK_DOWN) {
										control.increaseCursorRow();
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
		System.out.println("test");
		super.paintComponent(g);
		setGraphics(g);
		g.clearRect(0, 0, 800, 800);
		g.setColor(Color.black);
		g.setFont(control.getFont());
		//drawImage();
		//icon.paintIcon(this, g, 100, 100);
		if (control.size() > 0) {
			int[] loc = control.getCursorLoc();
			g.drawString("|", loc[1], loc[0]);
		}
		List<Glyph> glyphs = control.getGlyphs();
		for (Glyph glyph : glyphs) {
			if (glyph instanceof MyCharacter) {
				g.drawString(glyph.toString(), glyph.getCol(), glyph.getRow());
				if (true) {
					g.setColor(Color.red);
					g.drawLine(glyph.getCol(), glyph.getRow(), glyph.getCol() + glyph.getWidth(), glyph.getRow());
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
	
	public void drawImage() {
		try {
			image = ImageIO.read(MyCanvas.class.getResourceAsStream("apple.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		icon = new ImageIcon(image);
	}

	public void setControl(LilLexiControl control) {
		this.control = control;
	}
	
	private void setGraphics(Graphics g) {
		this.graphics = g;
		control.setGraphics(g);
	}
}
