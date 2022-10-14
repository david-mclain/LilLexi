package LilLexi;
/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author Kyle Elison
 * 
 * SpellChecker goes through glyphs and checks if words are mispelled based off provided dictionary
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpellChecker {
	private List<Glyph> curChars;
	private List<String> dict;
	private GlyphIterator iterator;
	private boolean isWord;
	private String curWord;
	
	public SpellChecker(List<Glyph> inputs) {
		iterator = new GlyphIterator(inputs);
		try {
			createDict();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void checkSpelling() {
		isWord = false;
		curWord = "";
		curChars = new ArrayList<Glyph>();
		iterator.reset();
		while ( iterator.hasNext()) {
			Glyph glyph = iterator.next();
			if (glyph instanceof MyCharacter) {
				// Checks if end of word char appears, then ends the word
				if (!((MyCharacter) glyph).getIsLetter()) {
					isWord = true;
				} else {
					curChars.add(glyph);
				}
			} else {
				isWord = true;
			}
				
			if (isWord) {
				for(Glyph character: curChars) {
					curWord += character;
				}
				if(!dict.contains(curWord.toLowerCase())) {
					for(Glyph character: curChars) {
						((MyCharacter) character).setSpeltCor(false);
					}
				}
				curWord = "";
				curChars.clear();
				isWord = false;
			}
		}
	}
	
	
	
	private void createDict() throws FileNotFoundException {
		Scanner s = new Scanner(new File("src/LilLexi/dict.txt"));
		dict = new ArrayList<String>();
		while (s.hasNext()){
		    dict.add(s.next());
		}
		s.close();
	}
}
