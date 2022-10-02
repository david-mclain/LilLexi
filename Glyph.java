package LilLexi;
/**
 * Houses any sort of marking elements.
 * @author Admin
 *
 */
public abstract class Glyph {
	
	/**
	 * Gets the object 
	 * @return
	 */
	public abstract Object get();
	
	/**
	 * Sets the object
	 * @param setVal
	 */
	public abstract void set( Object setVal);

	/**
	 * Returns a string of object
	 */
	public abstract String toString();
	
	
	public abstract int getCol();
	public abstract int getRow();
	public abstract int getHeight();
	public abstract int getWidth();
	
	public abstract void setLoc( int row, int col );
	public abstract void setBounds( int hei, int wid);
}
