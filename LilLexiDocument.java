package LilLexi;
import java.util.List;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class LilLexiDocument {
	public static final int PIXELS_PER_ROW = 800;
	private LilLexiUI UI;
	private Composite composite;
	private Graphics g;
	private List<Glyph> inputs;
	private Stack<Undo> undoStack;
	private Stack<Glyph> redoStack;
	private Font curFont;
	private int  cursorIndex;
	
	public LilLexiDocument() {
		curFont = new Font("Times New Roman", Font.PLAIN, 20);
		inputs = new ArrayList<>();
		undoStack = new Stack<>();
		redoStack = new Stack<>();
		composite = new Composite(curFont.getSize(), 0, inputs);
		cursorIndex = 0;
	}
	
	public void setUI(LilLexiUI UI) {  this.UI = UI;  }
	
	public void add(Glyph glyph) {
		if (glyph instanceof  MyCharacter) {
			glyph.setWidth(g.getFontMetrics().stringWidth(glyph.toString()));
		}
		else if (glyph instanceof MyShape) {
			glyph.setLoc(glyph.getRow() - curFont.getSize(), glyph.getCol());
		}
		inputs.add(cursorIndex, glyph);
		cursorIndex++;
		Undo change = new Undo(glyph, cursorIndex, true);
		undoStack.push(change);
		composite.compose();
		UI.update();
	}
	
	public void setFont(String newFont) {
		curFont = new Font(newFont, Font.PLAIN, 20);
		g.setFont(curFont);

		for (Glyph glyph : inputs) {
			if (glyph instanceof  MyCharacter) {
				glyph.setWidth(g.getFontMetrics().stringWidth(glyph.toString()));
			}
		}
		composite.compose();
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
		if (cursorIndex > 0) {
			cursorIndex--;
			//inputs.remove(cursorIndex);
			Undo change = new Undo(inputs.remove(cursorIndex), cursorIndex, false);
			undoStack.push(change);
			composite.compose();
			UI.update();
		}
	}
	
	public int size() {
		return inputs.size();
	}
	
	public void undo() {
		Undo action = undoStack.pop();
		if(action.isInsert()) {
			cursorIndex = action.getIndex();
			removeLast();
		} else {
			cursorIndex = action.getIndex();
			add(action.getGlyph());
		}
		undoStack.pop();
		//inputs.remove(inputs.size() - 1);
		//redoStack.push(undoStack.pop());
	}
	
	public void redo() {
		inputs.add(redoStack.pop());
		//undoStack.push(inputs.get(inputs.size() - 1));
	}

//	public int getRow() {
//		return compositor.getRow();
//	}
//
//	public int getCol() {
//		return compositor.getCol();
//	}

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
	
	public void decreaseCursorRow() {
		cursorIndex = composite.changeCursorRow(cursorIndex, -(curFont.getSize() + 10));
	}
	
	public void increaseCursorRow() {
		cursorIndex = composite.changeCursorRow(cursorIndex, curFont.getSize() + 10);
	}

	public int[] getCursorLoc() {
		int[] ret = new int[2];
		Glyph cur = inputs.get(cursorIndex - 1);
		ret[0] = cur.getRow();
		ret[1] = cur.getCol() + cur.getWidth();
		return ret;
	}
}
