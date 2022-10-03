import java.util.List;
import java.awt.Font;
import java.util.ArrayList;

public class LilLexiDocument {
	private List<Glyph> inputs;
	private LilLexiUI UI;
	private Stack<Glyph> undoStack;
	private Stack<Glyph> redoStack;
	private Font curFont;
	
	public LilLexiDocument() {
		inputs = new ArrayList<>();
		undoStack = new Stack<>();
		redoStack = new Stack<>();
		curFont = new Font("Monospaced", Font.PLAIN, 20);
	}
	
	public void setUI(LilLexiUI UI) {  this.UI = UI;  }
	
	public void add(Glyph glyph) {
		inputs.add(glyph);
		undoStack.push(glyph);
		UI.update();
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
		UI.update();
	}
	
	public void removeLast() {
		inputs.remove(inputs.size() - 1);
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
}
