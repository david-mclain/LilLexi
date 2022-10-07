package LilLexi;
public class Compositor {
	private int row, col;
	private int maxHeight;
	private LilLexiControl con;
	
	public Compositor(LilLexiControl con) {
		this.con = con;
		row = this.con.getFont().getSize();
		col = 0;
	}
	
	public Compositor(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
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
	
	public int getRow() { return row; }
	
	public int getCol() { return col; }
	
}
