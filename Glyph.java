package LilLexi;
/**
 * Houses any sort of marking elements.
 * @author Admin
 *
 */
public abstract class Glyph {
	
	private int row, col, height, width;
	
	/**
	 * Gets the object 
	 * @return
	 */
	public abstract Object get();
	
	/**
	 * Sets the object
	 * @param setVal
	 */
	//public abstract void set(Object setVal);

	/**
	 * Returns a string of object
	 */
	public abstract String toString();
	
	
	public int getCol() {
		return this.col;
	}
	public int getRow() {
		return this.row;
	}
	public int getHeight() {
		return this.height;
	}
	public int getWidth() {
		return this.width;
	}
	
	public void setLoc( int row, int col ) {
		this.row = row;
		this.col = col;
	}
	public void setBounds( int hei, int wid) {
		this.height = hei;
		this.width = wid;
	}

	protected void setWidth(int width) {
		this.width = width;
	}
}
