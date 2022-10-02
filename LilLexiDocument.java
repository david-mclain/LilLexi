package LilLexi;

import java.util.List;
import java.util.ArrayList;

public class LilLexiDocument {
	private List<Glyph> inputs;
	private LilLexiUI UI;
	private Stack<Glyph> undoStack;
	private Stack<Glyph> redoStack;
	
	public LilLexiDocument() {
		inputs = new ArrayList<>();
		undoStack = new Stack<>();
		redoStack = new Stack<>();
	}
	
	public void setUI(LilLexiUI UI) {  this.UI = UI;  }
	
	public void add(Glyph glyph) {
		inputs.add(glyph);
		undoStack.push(glyph);
		UI.update();
	}
	
	public List<Glyph> getGlyphs() {  return this.inputs;  }

	public void clear() {
		inputs.clear();
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
