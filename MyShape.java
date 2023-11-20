/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author Kyle Elison
 *
 * MyShape deals with all glyphs that are of type shape
 */

public class MyShape extends Glyph {
	/**
	 * Instantiates new rectangle with height of 30, width of 50
	 */
	public MyShape() {
		super.setBounds(30,  50);
	}
	/**
	 * Returns null
	 */
	@Override
	public Object get() {
		return null;
	}
	/**
	 * Returns string representation of shape
	 */
	@Override
	public String toString() {
		return "";
	}

}
