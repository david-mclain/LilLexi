/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author David McLain
 * 
 * Control helps UI communicate with document in order to display everything properly
 */

import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

public class LilLexiControl {
	private LilLexiDocument doc;
	/**
	 * Instantiates new Control
	 * @param doc - document for UI to communicate with
	 */
	public LilLexiControl(LilLexiDocument doc) {
		this.doc = doc;
	}
	/**
	 * Sends new glyph to document
	 * @param glyph - glyph to send to document
	 */
	public void add(Glyph glyph) {
		doc.add(glyph);
	}
	/**
	 * Sends new font to document
	 * @param newFont - font name to send to document
	 */
	public void setFont(String newFont) {
		doc.setFont(newFont);
	}
	/**
	 * Returns cur font in document
	 * @return cur font in document
	 */
	public Font getFont() {
		return doc.getFont();
	}
	/**
	 * Clears document glyphs
	 */
	public void clear() {
		doc.clear();
	}
	/**
	 * Returns amount of glyphs
	 * @return amount of glyphs
	 */
	public int size() {
		return doc.size();
	}
	/**
	 * Quits program when quit choice is selected
	 */
	public void quit() {
		System.exit(0);
	}
	/**
	 * Removes last glyph (last is glyph behind cursor)
	 */
	public void removeLast() {
		doc.removeLast();
	}
	/**
	 * Returns list of all glyphs in canvas
	 * @return list of all glyphs in canvas
	 */
	public List<Glyph> getGlyphs() {
		return doc.getGlyphs();
	}
	/**
	 * Calls undo method in document
	 */
	public void undo() {
		doc.undo();
	}
	/**
	 * Calls redo method in document
	 */
	public void redo() {
		doc.redo();
	}
	/**
	 * Sets graphics in document in order to get font metrics
	 * @param g - graphics to set in document
	 */
	public void setGraphics(Graphics g) {
		doc.setGraphics(g);
	}
	/**
	 * Increases current cursor index
	 */
	public void increaseCursorIndex() {
		doc.increaseCursorIndex();
	}
	/**
	 * Decreases current cursor index
	 */
	public void decreaseCursorIndex() {
		doc.decreaseCursorIndex();
	}
	/**
	 * Decreases current cursor row
	 */
	public void decreaseCursorRow() {
		doc.decreaseCursorRow();
	}
	/**
	 * Increases current cursor row
	 */
	public void increaseCursorRow() {
		doc.increaseCursorRow();
	}
	/**
	 * Scrolls down document
	 */
	public void increaseScroll() { doc.increaseScroll(); }
	/**
	 * Scrolls up document
	 */
	public void decreaseScroll() { doc.decreaseScroll(); }
	/**
	 * Returns current cursor location
	 * @return current cursor location
	 */
	public int[] getCursorLoc() {
		return doc.getCursorLoc();
	}
}
