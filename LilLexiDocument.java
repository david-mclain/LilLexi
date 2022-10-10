package LilLexi;
import java.util.List;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class LilLexiDocument {
	public static final int PIXELS_PER_ROW = 800;
	private LilLexiUI UI;
	private Compositor compositor;
	private Graphics g;
	private List<Glyph> inputs;
	private Stack<Glyph> undoStack;
	private Stack<Glyph> redoStack;
	private Font curFont;
	private int  cursorIndex;
	
	public LilLexiDocument() {
		curFont = new Font("Times New Roman", Font.PLAIN, 20);
		inputs = new ArrayList<>();
		undoStack = new Stack<>();
		redoStack = new Stack<>();
		compositor = new Compositor(curFont.getSize(), 0);
		cursorIndex = 0;
	}
	
	public void setUI(LilLexiUI UI) {  this.UI = UI;  }
	
	public void add(Glyph glyph) {
		if (glyph instanceof  MyCharacter) {
			glyph.setWidth(g.getFontMetrics().stringWidth(glyph.toString()));
			//glyph.setLoc(compositor.getRow(), compositor.getCol());
		}
		inputs.add(cursorIndex, glyph);
		undoStack.push(glyph);
		
		//compositor.setLoc(compositor.getRow(), compositor.getCol() + glyph.getWidth() + 2, curFont.getSize());
		
		compositor.reset();
		for (int i = 0; i < inputs.size(); i++) {
			inputs.get(i).setLoc(compositor.getRow(), compositor.getCol());
			compositor.setLoc(compositor.getRow(), compositor.getCol() + inputs.get(i).getWidth() + 2, curFont.getSize());
		}
		UI.update();
		cursorIndex++;
		//System.out.println("Row: " + compositor.getRow() + ", Col: " + compositor.getCol());
		// TODO DELETE, test code for printing
		for (int i = 0; i < inputs.size(); i++) {
			System.out.println("row = " + inputs.get(i).getRow() + "col = " + inputs.get(i).getCol());
		}
		System.out.println();
	}
	
	public void setFont(String newFont) {
		curFont = new Font(newFont, Font.PLAIN, 20);
		UI.update();
	}
	
	public Font getFont() {
		return curFont;
	}
	
	public List<Glyph> getGlyphs() {
		return this.inputs;
	}

	public void clear() {
		inputs.clear();
		cursorIndex = 0;
		UI.update();
	}
	
	public void removeLast() {
		inputs.remove(cursorIndex - 1);
		cursorIndex--;
		compositor.reset();
		for (int i = 0; i < inputs.size(); i++) {
			inputs.get(i).setLoc(compositor.getRow(), compositor.getCol());
			compositor.setLoc(compositor.getRow(), compositor.getCol() + inputs.get(i).getWidth() + 2, curFont.getSize());
		}
		UI.update();
		//compositor.setLoc(compositor.getRow(), compositor.getCol() - inputs.get(inputs.size() - 1).getWidth() - 2, curFont.getSize());
	}
	
	public int size() {
		return inputs.size();
	}
	
	public void undo() {
		inputs.remove(inputs.size() - 1);
		redoStack.push(undoStack.pop());
	}
	
	public void redo() {
		inputs.add(redoStack.pop());
		undoStack.push(inputs.get(inputs.size() - 1));
	}

	public int getRow() {
		return compositor.getRow();
	}

	public int getCol() {
		return compositor.getCol();
	}

	public void updatePos(int x, int y) {
		UI.update();
	}

	public void setGraphics(Graphics g) {
		this.g = g;
	}
	
	public void increaseCursorIndex() {
		if (cursorIndex < inputs.size()) {
			cursorIndex++;
		}
	}
	
	public void decreaseCursorIndex() {
		if (cursorIndex > 0) {
			cursorIndex--;
		}
	}
}
