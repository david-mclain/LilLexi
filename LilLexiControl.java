package LilLexi;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

public class LilLexiControl {
	private LilLexiDocument doc;
	
	public LilLexiControl(LilLexiDocument doc) {
		this.doc = doc;
	}
	
	public void add(Glyph glyph) {
		doc.add(glyph);
	}

	public void setFont(String newFont) {
		doc.setFont(newFont);
	}

	public Font getFont() {
		return doc.getFont();
	}
	
//	public int getRow() {
//		return doc.getRow();
//	}
//	
//	public int getCol() {
//		return doc.getCol();
//	}
	
	public void clear() {
		doc.clear();
	}

	public int size() {
		return doc.size();
	}
	
	public void quit() {
		System.exit(0);
	}

	public void removeLast() {
		doc.removeLast();
	}

	public List<Glyph> getGlyphs() {
		return doc.getGlyphs();
	}
	
	public void undo() {
		doc.undo();
	}
	
	public void redo() {
		doc.redo();
	}

	public void setGraphics(Graphics g) {
		doc.setGraphics(g);
	}
	
	public void increseCursorIndex() {
		doc.increaseCursorIndex();
	}
	
	public void decreaseCursorIndex() {
		doc.decreaseCursorIndex();
	}
	
	public void decreaseCursorRow() {
		doc.decreaseCursorRow();
	}
	
	public void increaseCursorRow() {
		doc.increaseCursorRow();
	}
	
	public void increaseScroll() { doc.increaseScroll(); }
	
	public void decreaseScroll() { doc.decreaseScroll(); }
	
	public int[] getCursorLoc() {
		return doc.getCursorLoc();
	}
}
