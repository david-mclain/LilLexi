package LilLexi;

import java.awt.Shape;

import javax.swing.ImageIcon;

public class MyShape extends Glyph {
	private int row, col, hei, wid;
	private Shape shape;

	public MyShape() {
		row = -1; col = -1;
		hei = -1; wid = -1;
	}
	
	@Override
	public Object get() { return shape; }

	@Override
	public void set(Object setVal) {
		if (!(setVal instanceof Shape)) {
			throw new IllegalArgumentException();
		}
		shape = (Shape) setVal;
	}

	@Override
	public String toString() { return shape.toString(); }

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
