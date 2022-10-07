package LilLexi;
import javax.swing.ImageIcon;

public class MyImage extends Glyph {
	private ImageIcon image;
	private int row, col, hei, wid;
	
	public MyImage() {
		// Default Values
		row = -1; col = -1;
		hei = -1; wid = -1;
	}
	
	public MyImage(ImageIcon inputImage) {
		image = inputImage;
		// Default values
		row = -1; col = -1;
		hei = -1; wid = -1;
	}
	
	@Override
	public Object get() { return image; }

	@Override
	public void set(Object setVal) {
		if (!(setVal instanceof ImageIcon)) {
			throw new IllegalArgumentException();
		}
		image = (ImageIcon) setVal;
	}

	@Override
	public String toString() { return image.toString(); }

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

	@Override
	protected void setWidth(int width) {
		// TODO Auto-generated method stub
		
	}

}
