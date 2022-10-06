package LilLexi;
public class LexiDriver {
	private static LilLexiDocument doc = null;
	public static void main(String[] args) {
		doc = new LilLexiDocument();
		LilLexiControl control = new LilLexiControl(doc);
		LilLexiUI UI = new LilLexiUI(control);
		UI.setController(control);
		doc.setUI(UI);
		UI.start();
		//new TextEditor();
	}
}
