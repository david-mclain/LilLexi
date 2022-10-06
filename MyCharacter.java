package LilLexi;
public class MyCharacter extends Glyph {
	private char myChar;
	private int row, col, hei, wid;

	
	public MyCharacter() {
		// Default values
		row = -1;
		col = -1;
		hei = -1;
		wid = -1;
	}
	
	public MyCharacter(char c) {
		// Default values
		this.myChar = c;
	}
	
	@Override
	public Object get() { return Character.valueOf(myChar); }

	@Override
	public void set(Object setVal) {
		if (!(setVal instanceof Character)) {
			throw new IllegalArgumentException();
		}
		myChar = Character.valueOf((char) setVal);
	}

	@Override
	public String toString() {
		return "" + myChar;
	}

	@Override
	public int getCol() { return col; }

	@Override
	public int getRow() { return row; }
	
	@Override
	public int getHeight() { return hei; }

	@Override
	public int getWidth() { return wid; }

	@Override
	public void setLoc(int row, int col) { this.col = col; this.row = row; }
	
	@Override
	public void setBounds(int hei, int wid) { this.hei = hei; this.wid = wid; }
	

}

