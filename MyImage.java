package LilLexi;
import javax.swing.ImageIcon;

public class MyImage extends Glyph {
	private ImageIcon image;
	
	public MyImage(ImageIcon inputImage) {
		image = inputImage;
		super.setBounds(100, 100);
	}
	
	@Override
	public Object get() { return image; }

	@Override
	public String toString() { return image.toString(); }

}
