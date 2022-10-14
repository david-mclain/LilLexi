package LilLexi;
/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author Kyle Elison
 *
 * Glyph is abstract class of all elements that will be inserted into canvas
 */

public abstract class Glyph {
	
	private int row, col, height, width;
	
	/**
	 * Returns glyph
	 */
	public abstract Object get();
	
	/**
	 * Returns String representation of glyph
	 */
	public abstract String toString();
	
	/**
	 * Returns glyphs column position in canvas
	 * @return glyphs column position in canvas
	 */
	public int getCol() {
		return this.col;
	}
	/**
	 * Returns glyphs row position in canvas
	 * @return glyphs row position in canvas
	 */
	public int getRow() {
		return this.row;
	}
	/**
	 * Returns height of glyph
	 * @return height of glyph
	 */
	public int getHeight() {
		return this.height;
	}
	/**
	 * Returns width of glyph
	 * @return width of glyph
	 */
	public int getWidth() {
		return this.width;
	}
	/**
	 * Sets row and col positions of glyph
	 * @param row - row position of glyph in canvas
	 * @param col - col position of glyph in canvas
	 */
	public void setLoc( int row, int col ) {
		this.row = row;
		this.col = col;
	}
	/**
	 * Sets height and width of glyph
	 * @param hei - height of glyph
	 * @param wid - width of glyph
	 */
	public void setBounds( int hei, int wid) {
		this.height = hei;
		this.width = wid;
	}
	/**
	 * Sets width of glyph
	 * @param width - width of glyph
	 */
	protected void setWidth(int width) {
		this.width = width;
	}
}
