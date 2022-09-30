import java.util.List;
import java.util.ArrayList;

public class LilLexiDocument {
	private List<Glyph> inputs;
	private LilLexiUI UI;
	
	public LilLexiDocument() {
		inputs = new ArrayList<>();
	}
	
	public void setUI(LilLexiUI UI) {  this.UI = UI;  }
	
	public void add(Glyph glyph) {
		inputs.add(glyph);
		UI.update();
	}
	
	public List<Glyph> getGlyphs() {  return this.inputs;  }
}
