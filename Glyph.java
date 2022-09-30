package LilLexi;

public class Glyph <T> {
	private T glyph;
	
	public Glyph() {
		this(null);
	}
	
	public Glyph(T glyph) {
		this.glyph = glyph;
	}
	
	public T get() {  return this.glyph;  }
	
	public void set(T glyph) {  this.glyph = glyph;  }

	public String toString() {
		return "" + glyph.toString();
	}
}
