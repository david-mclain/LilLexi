package LilLexi;
/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author Kyle Elison
 *
 * MyCharacter deals with all glyphs that are of type char
 */

public class MyCharacter extends Glyph {
	private char myChar;
	private boolean isLetter;
	private boolean speltCor;
	/**
	 * Instantiates new character
	 * @param c - char to set glyph to
	 */
	public MyCharacter(char c) {
		this.myChar = c;
		this.isLetter = false;
		speltCor = true;
	}
	/**
	 * Returns glyph value
	 */
	@Override
	public Object get() { return Character.valueOf(myChar); }
	/**
	 * Returns String representation of char
	 */
	@Override
	public String toString() {
		return "" + myChar;
	}
	/**
	 * Sets isLetter 
	 * @param isLetter - sets isLetter to true if char is a-z
	 */
	public void setIsLetter(boolean isLetter) { this.isLetter = isLetter; }
	/**
	 * Returns true if character is a-z
	 * @return true if character is a-z
	 */
	public boolean getIsLetter() { return this.isLetter; }
	/**
	 * Sets if character is in a word that is spelled correctly
	 * @param speltCor - whether word is spelled correctly that or not
	 */
	public void setSpeltCor(boolean speltCor) { this.speltCor = speltCor; }
	/**
	 * Returns true if word that char is a part of is spelled correctly
	 * @return true if word that char is a part of is spelled correctly
	 */
	public boolean getSpeltCor() { return speltCor; }
}

