package LilLexi;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MyImage extends Glyph {
	private BufferedImage image;
	private ImageIcon icon;
	
	public MyImage(ImageIcon inputImage) {
		setImage();
		super.setBounds(100, 100);
	}
	
	@Override
	public Object get() { return icon; }

	@Override
	public String toString() { return icon.toString(); }
	
	public void setImage() {
		try {
			image = ImageIO.read(MyImage.class.getResourceAsStream("apple.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		icon = new ImageIcon(image);
	}

}
