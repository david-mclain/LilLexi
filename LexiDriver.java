package LilLexi;
/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author David McLain
 * 
 * Driver program for LilLexi that starts up UI
 */

import java.io.FileNotFoundException;

public class LexiDriver {
	private static LilLexiDocument doc = null;
	public static void main(String[] args) throws FileNotFoundException {
		doc = new LilLexiDocument();
		LilLexiControl control = new LilLexiControl(doc);
		LilLexiUI UI = new LilLexiUI(control);
		UI.setController(control);
		doc.setUI(UI);
		UI.start();
	}
}
