package LilLexi;
/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author Kyle Elison
 * 
 * MyImage deals with glyphs of type Image
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MyImage extends Glyph {
	private BufferedImage image;
	private ImageIcon icon;
	/**
	 * Instantiates new image
	 */
	public MyImage() {
		setImage();
		super.setBounds(100, 100);
	}
	/**
	 * Returns glyph value
	 */
	@Override
	public Object get() { return icon; }
	/**
	 * Returns string representation of glyph
	 */
	@Override
	public String toString() { return icon.toString(); }
	/**
	 * Sets current image to provided apple.jpg
	 * Can set to a different image but make sure it is in same directory as .class files
	 */
	public void setImage() {
		try {
			image = ImageIO.read(MyImage.class.getResourceAsStream("apple.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		icon = new ImageIcon(image);
	}
}
