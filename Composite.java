package LilLexi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Composite {
	private Compositor compositor;
	private List<Glyph> inputs;
	private List<Glyph> curChars;
	private List<String> dict;
	private int rowStart, colStart;
	private int curRow, curCol;
	private boolean isWord;
	private String curWord;
	
	public Composite(int rowStart, int colStart, List<Glyph> inputs) throws FileNotFoundException {
		this.rowStart = rowStart;
		this.colStart = colStart;
		this.inputs   = inputs;
		createDict();
		compositor = new Compositor(rowStart, colStart);
	}
	
	public void compose() {
		compositor.reset();
		isWord = false;
		curWord = "";
		curChars = new ArrayList<Glyph>();
		for (int i = 0; i < inputs.size(); i++) {
			Glyph cur = inputs.get(i);
			if (cur instanceof MyImage) {
				cur.setLoc(compositor.getRow() - cur.getHeight(), compositor.getCol());
				compositor.setLoc(compositor.getRow(), compositor.getCol() + cur.getWidth() + 2, cur.getHeight());
			}
			else if (cur instanceof MyCharacter) {
				((MyCharacter) inputs.get(i)).setSpeltCor(true);
				cur.setLoc(compositor.getRow(), compositor.getCol());
				compositor.setLoc(compositor.getRow(), compositor.getCol() + cur.getWidth() + 2, rowStart);
				checkWord(inputs.get(i));
			}
			else if (cur instanceof MyShape) {
				cur.setLoc(compositor.getRow() - cur.getHeight(), compositor.getCol());
				compositor.setLoc(compositor.getRow(), compositor.getCol() + cur.getWidth() + 2, cur.getHeight());
			}
//			inputs.get(i).setLoc(compositor.getRow(), compositor.getCol());
//			compositor.setLoc(compositor.getRow(), compositor.getCol() + inputs.get(i).getWidth() + 2, rowStart);
//			
		}
	}
	
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	
	public int changeCursorRow(int cursorIndex, int changeBy) {
		int cursor = 0;
		getCurRowCol(cursorIndex);
		curRow += changeBy;
		
		// edge case if trying to go to high up
		if (curRow < rowStart) {
			curRow = rowStart;
			curCol = colStart;
		}
		
		compositor.reset();
		for (int i = 0; i < inputs.size(); i++) {
			if (compositor.getRow() <= curRow && compositor.getCol() <= curCol) {
				cursor = i + 1;
			}
			else if(compositor.getRow() < curRow) {
				cursor = i + 1;
			}
			inputs.get(i).setLoc(compositor.getRow(), compositor.getCol());
			compositor.setLoc(compositor.getRow(), compositor.getCol() + inputs.get(i).getWidth() + 2, rowStart);
		}
		return cursor;
	}
	
	private void getCurRowCol(int cursorIndex) {
		compositor.reset();
		for (int i = 0; i < inputs.size(); i++) {
			if (i == cursorIndex - 1) {
				curRow = compositor.getRow();
				curCol = compositor.getCol();
			}
			inputs.get(i).setLoc(compositor.getRow(), compositor.getCol());
			compositor.setLoc(compositor.getRow(), compositor.getCol() + inputs.get(i).getWidth() + 2, rowStart);
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
	
	private void checkWord(Glyph glyph) {
		if (glyph instanceof MyCharacter) {
			// Checks if end of word char appears, then ends the word
			if (glyph.get() == Character.valueOf(' ') || glyph.get() == Character.valueOf('.') ||
				glyph.get() == Character.valueOf(',')) {
				
				isWord = true;
			} else {
				curChars.add(glyph);
			}
		}
			
		if (isWord) {
			for(Glyph character: curChars) {
				curWord += character;
			}
			if(!dict.contains(curWord)) {
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