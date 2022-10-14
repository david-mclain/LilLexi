package LilLexi;
/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author Kyle Elison
 * 
 * Composite uses compositor in order to set glyphs in their positions
 */

import java.util.List;

public class Composite {
	private Compositor compositor;
	private List<Glyph> inputs;
	private int rowStart, colStart, curRow, curCol, scroll;
	/**
	 * Instantiates composite class
	 * @param rowStart - row to begin the compositor at
	 * @param colStart - col to begin the compositor at
	 * @param inputs - list of all glyphs to calculate positions
	 */
	public Composite(int rowStart, int colStart, List<Glyph> inputs) {
		this.rowStart = rowStart;
		this.colStart = colStart;
		this.inputs   = inputs;
		scroll = 0;
		compositor = new Compositor(rowStart, colStart);
	}
	/**
	 * Goes through every glyph and sets their location then increments compositor
	 */
	public void compose() {
		compositor = new Compositor(rowStart + scroll, colStart);
		compositor.reset();
		for (int i = 0; i < inputs.size(); i++) {
			Glyph cur = inputs.get(i);
			if (cur instanceof MyImage || cur instanceof MyShape) {
				cur.setLoc(compositor.getRow() - rowStart, compositor.getCol());
				compositor.setLoc(compositor.getRow(), compositor.getCol() + cur.getWidth() + 2, cur.getHeight());
			}
			else if (cur instanceof MyCharacter) {
				((MyCharacter) cur).setSpeltCor(true);
				cur.setLoc(compositor.getRow(), compositor.getCol());
				compositor.setLoc(compositor.getRow(), compositor.getCol() + cur.getWidth() + 2, rowStart);
			}
		}
	}
	/**
	 * Sets starting row of composite
	 * @param rowStart - row to begin the compositor at
	 */
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	/**
	 * Changes cursor row
	 * @param cursorIndex - current index of cursor
	 * @param changeBy - Amount to change current row by
	 * @return cursor index after change
	 */
	public int changeCursorRow(int cursorIndex, int changeBy) {
		int cursor = 0;
		setCurRowCol(cursorIndex);
		curRow += changeBy;
		
		// edge case if trying to go to high up
		if (curRow < rowStart) {
			curRow = rowStart;
			curCol = colStart;
		}
		
		compositor.reset();
		for (int i = 0; i < inputs.size(); i++) {
			if (compositor.getRow() <= curRow && compositor.getCol() <= curCol) {
				cursor = i + 1;
			}
			else if(compositor.getRow() < curRow) {
				cursor = i + 1;
			}
			inputs.get(i).setLoc(compositor.getRow(), compositor.getCol());
			compositor.setLoc(compositor.getRow(), compositor.getCol() + inputs.get(i).getWidth() + 2, rowStart + scroll);
		}
		return cursor;
	}
	/**
	 * Sets current row and column in compositor
	 * @param cursorIndex - cur index of cursor
	 */
	private void setCurRowCol(int cursorIndex) {
		compositor.reset();
		for (int i = 0; i < inputs.size(); i++) {
			if (i == cursorIndex - 1) {
				curRow = compositor.getRow();
				curCol = compositor.getCol();
			}
			inputs.get(i).setLoc(compositor.getRow(), compositor.getCol());
			compositor.setLoc(compositor.getRow(), compositor.getCol() + inputs.get(i).getWidth() + 2, rowStart + scroll);
		}
	}
	/**
	 * Scrolls down
	 */
	public void increaseScroll() { scroll += 10; }
	/**
	 * Scrolls up
	 */
	public void decreaseScroll() { scroll -= 10; }
	/**
	 * Resets scroll to top
	 */
	public void resetScroll() { scroll = 0; }

}