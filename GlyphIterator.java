/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author Kyle Elison
 * 
 * Glyph iterator handles the iterator design pattern for the glyphs
 */

import java.util.Iterator;
import java.util.List;

public class GlyphIterator implements Iterator<Glyph>{
	private List<Glyph> inputs;
	private int curIndex;
	
	public GlyphIterator(List<Glyph> inputs) {
		this.inputs = inputs;
		curIndex = 0;
	}

	@Override
	public boolean hasNext() {
		return curIndex < inputs.size();
	}

	@Override
	public Glyph next() {
		curIndex += 1;
		return inputs.get(curIndex-1);
	}
	
	public void reset() {
		curIndex = 0;
	}

}
