package LilLexi;
import java.awt.Font;
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
	
	public int getRow() {
		return doc.getRow();
	}
	
	public int getCol() {
		return doc.getCol();
	}
	
	public void updatePos(int x, int y) {
		doc.updatePos(x, y);
	}
	
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
}
