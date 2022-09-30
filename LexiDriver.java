package LilLexi;

public class LexiDriver {
	private static LilLexiDocument doc = null;
	public static void main(String[] args) {
		doc = new LilLexiDocument();
		LilLexiUI UI = new LilLexiUI();
		LilLexiControl control = new LilLexiControl(doc);
		UI.setController(control);
		UI.start();
		//new TextEditor();
	}
}
