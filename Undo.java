package LilLexi;
/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author Kyle Elison
 * 
 * UI for LilLexi
 */

public class Undo {
	private Glyph glyph;
	private int   index;
	private boolean insert;
	
	public Undo(Glyph glyph, int index, boolean insert) {
		this.glyph = glyph;
		this.index = index;
		this.insert = insert;
	}
	
	public int getIndex() { return index; }
	
	public Glyph getGlyph() { return glyph; }
	
	public boolean isInsert() { return insert; }
}
