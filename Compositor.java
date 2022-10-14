package LilLexi;
/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author David McLain
 * 
 * Compositor determines row and col for every glyph
 */

public class Compositor {
	private int startRow, row, col;
	private int maxHeight;
	private LilLexiControl con;
	/**
	 * Instantiates compositor with control
	 * @param con - control for compositor
	 */
	public Compositor(LilLexiControl con) {
		this.con = con;
		row = this.con.getFont().getSize();
		col = 0;
	}
	/**
	 * Instantiates compositor with row and col
	 * @param row - row for compositor
	 * @param col - col for compositor
	 */
	public Compositor(int row, int col) {
	    startRow = row;
		this.row = row;
		this.col = col;
	}
	/**
	 * Sets location of compositor
	 * @param row - row of glyph
	 * @param col - col of glyph
	 * @param height - height of glyph
	 */
	public void setLoc(int row, int col, int height) {
		if (height > maxHeight)
			maxHeight = height;
		if (col > LilLexiDocument.PIXELS_PER_ROW) {
			row = row + maxHeight + 10;
			col = 0;
			maxHeight = 0;
		}
		if (col  < 0) {
			row =  row - maxHeight - 10;
			col = LilLexiDocument.PIXELS_PER_ROW - col;
			maxHeight = 0;
		}
		this.row = row;
		this.col = col;
	}
	/**
	 * Returns row of compositor
	 * @return row of compositor
	 */
	public int getRow() { return row; }
	/**
	 * Returns col of compositor
	 * @return col of compositor
	 */
	public int getCol() { return col; }
	/**
	 * Resets compositor to start from zero
	 */
	public void reset() {
		row = startRow;
		col = 0;
	}
	
}
