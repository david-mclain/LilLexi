package LilLexi;
public class MyCharacter extends Glyph {
	private char myChar;
	private boolean isLetter;
	private boolean speltCor;


	public MyCharacter() {
		this.isLetter = false;
	}
	
	public MyCharacter(char c) {
		this.myChar = c;
		this.isLetter = false;
		speltCor = true;
	}
	
	@Override
	public Object get() { return Character.valueOf(myChar); }

	@Override
	public String toString() {
		return "" + myChar;
	}
	
	public void setIsLetter(boolean isLetter) { this.isLetter = isLetter; }
	
	public boolean getIsLetter() { return this.isLetter; }
	
	public void setSpeltCor(boolean speltCor) { this.speltCor = speltCor; }
	
	public boolean getSpeltCor() { return speltCor; }
	

}

