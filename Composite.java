package LilLexi;

import java.util.List;

public class Composite {
	private Compositor compositor;
	private List<Glyph> inputs;
	private int rowStart, colStart, curRow, curCol, scroll;
	
	public Composite(int rowStart, int colStart, List<Glyph> inputs) {
		this.rowStart = rowStart;
		this.colStart = colStart;
		this.inputs   = inputs;
		scroll = 0;
		compositor = new Compositor(rowStart, colStart);
	}
	
	public void compose() {
		compositor = new Compositor(rowStart + scroll, colStart);
		compositor.reset();
		for (int i = 0; i < inputs.size(); i++) {
			Glyph cur = inputs.get(i);
			if (cur instanceof MyImage || cur instanceof MyShape) {
				cur.setLoc(compositor.getRow() - cur.getHeight(), compositor.getCol());
				compositor.setLoc(compositor.getRow(), compositor.getCol() + cur.getWidth() + 2, cur.getHeight());
			}
			else if (cur instanceof MyCharacter) {
				((MyCharacter) inputs.get(i)).setSpeltCor(true);
				cur.setLoc(compositor.getRow(), compositor.getCol());
				compositor.setLoc(compositor.getRow(), compositor.getCol() + cur.getWidth() + 2, rowStart);
				//checkWord(inputs.get(i));
			}
		}
	}
	
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	
	public int changeCursorRow(int cursorIndex, int changeBy) {
		int cursor = 0;
		getCurRowCol(cursorIndex);
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
	
	private void getCurRowCol(int cursorIndex) {
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
	
	public void increaseScroll() { scroll += 10; }
	
	public void decreaseScroll() { scroll -= 10; }
	
	public void resetScroll() { scroll = 0; }

}