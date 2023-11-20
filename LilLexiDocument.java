/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author David McLain
 * 
 * UI for LilLexi
 */

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
	private Stack<Undo> redoStack;
	private Font curFont;
	private SpellChecker spellCheck;
	private int  cursorIndex;
	/**
	 * Instantiates new document
	 */
	public LilLexiDocument() {
		curFont = new Font("Times New Roman", Font.PLAIN, 20);
		inputs = new ArrayList<>();
		undoStack = new Stack<>();
		redoStack = new Stack<>();
		spellCheck = new SpellChecker(inputs);
		composite = new Composite(curFont.getSize(), 0, inputs);
		cursorIndex = 0;
	}
	/**
	 * Sets UI that will display document
	 * @param UI - UI for document
	 */
	public void setUI(LilLexiUI UI) {  this.UI = UI;  }
	/**
	 * Sets current documents font
	 * @param newFont - font to set document to
	 */
	public void setFont(String newFont) {
		curFont = new Font(newFont, Font.PLAIN, 20);
		g.setFont(curFont);

		for (Glyph glyph : inputs) {
			if (glyph instanceof  MyCharacter) {
				glyph.setWidth(g.getFontMetrics().stringWidth(glyph.toString()));
			}
		}
		update();
	}
	/**
	 * Returns current font
	 * @return current font
	 */
	public Font getFont() {
		return curFont;
	}
	/**
	 * Returns list of all glyphs in document
	 * @return list of all glyphs in document
	 */
	public List<Glyph> getGlyphs() {
		return this.inputs;
	}
	/**
	 * Clears all glyphs from document
	 */
	public void clear() {
		inputs.clear();
		cursorIndex = 0;
		composite.resetScroll();
		update();
	}
	/**
	 * Adds glyph to document
	 * @param glyph - glyph to add
	 */
	public void add(Glyph glyph) {
		add(glyph, true);
	}
	/**
	 * Adds glyph to document
	 * @param glyph - glyph to add
	 * @param clearRedo - whether or not to clear redoStack
	 */
	public void add(Glyph glyph, boolean clearRedo) {
		if (glyph instanceof  MyCharacter) {
			glyph.setWidth(g.getFontMetrics().stringWidth(glyph.toString()));
		}
		else if (glyph instanceof MyShape) {
			glyph.setLoc(glyph.getRow() - curFont.getSize(), glyph.getCol());
		}
		inputs.add(cursorIndex, glyph);
		cursorIndex++;
		Undo change = new Undo(glyph, cursorIndex, true);
		if (clearRedo)
			redoStack.clear();
		undoStack.push(change);
		update();
	}
	/**
	 * Removes last where last is element before cursor
	 */
	public void removeLast() {
		removeLast(true);
	}
	/**
	 * Removes last where last is element before cursor
	 * @param clearRedo - whether or not to clear redoStack
	 */
	private void removeLast(boolean clearRedo) {
		if (cursorIndex > 0) {
			cursorIndex--;
			Undo change = new Undo(inputs.remove(cursorIndex), cursorIndex, false);
			undoStack.push(change);
			if (clearRedo)
				redoStack.clear();
			update();
		}
	}
	/**
	 * Returns amount of glyphs in document
	 * @return amount of glyphs in document
	 */
	public int size() {
		return inputs.size();
	}
	/**
	 * Undoes most recent change in document
	 */
	public void undo() {
		if (!undoStack.isEmpty()) {
			Undo action = undoStack.pop();
			if(action.isInsert()) {
				cursorIndex = action.getIndex();
				removeLast(false);
			} else {
				cursorIndex = action.getIndex();
				add(action.getGlyph(), false);
			}
			undoStack.pop();
			redoStack.push(new Undo(action.getGlyph(), action.getIndex(), !action.isInsert()));
		}
	}
	/**
	 * Redoes anything that was just undone
	 */
	public void redo() {
		if (!redoStack.isEmpty()) {
			Undo action = redoStack.pop();
			if (!action.isInsert()) {
				cursorIndex = action.getIndex() - 1;
				add(action.getGlyph(), false);
			}
			else {
				cursorIndex = action.getIndex() - 1;
				removeLast(false);
			}
		}
	}
	/**
	 * Sets graphics for calculating font width
	 * @param g - graphics  for calculating font width
	 */
	public void setGraphics(Graphics g) {
		this.g = g;
	}
	/**
	 * Increases cursor index
	 */
	public void increaseCursorIndex() {
		if (cursorIndex < inputs.size()) {
			cursorIndex++;
		}
	}
	/**
	 * Decreases cursor index
	 */
	public void decreaseCursorIndex() {
		if (cursorIndex > 0) {
			cursorIndex--;
		}
	}
	/**
	 * Decreases cursor row
	 */
	public void decreaseCursorRow() {
		cursorIndex = composite.changeCursorRow(cursorIndex, -(curFont.getSize() + 10));
	}
	/**
	 * Increases cursor row
	 */
	public void increaseCursorRow() {
		cursorIndex = composite.changeCursorRow(cursorIndex, curFont.getSize() + 10);
	}
	/**
	 * Gets current location to display cursor at
	 * @return current location to display cursor at
	 */
	public int[] getCursorLoc() {
		int[] ret = new int[2];
		if (cursorIndex > 0) {
			Glyph cur = inputs.get(cursorIndex - 1);
			ret[0] = cur.getRow();
			ret[1] = cur.getCol() + cur.getWidth();
		}
		else {
			ret[0] = curFont.getSize();
			ret[1] = 0;
		}
		return ret;
	}
	/**
	 * Increases scroll
	 */
	public void increaseScroll() { 
		composite.increaseScroll();
		update();
	}
	/**
	 * Decreases scroll
	 */
	public void decreaseScroll() { 
		composite.decreaseScroll();
		update();
	}
	/**
	 * recomposes document and updates UI
	 */
	private void update() {
		composite.compose();
		spellCheck.checkSpelling();
		UI.update();
	}
}
