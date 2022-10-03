package LilLexi;

public class Compositor {
	private int row, col;
	
	public Compositor() {
		// Default Values
		row = -1;
		col = -1;
	}
	
	public Compositor(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void setLoc(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() { return row; }
	
	public int getCol() { return col; }
	
}
