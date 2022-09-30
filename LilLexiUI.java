package LilLexi;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.List;

public class LilLexiUI {
	
	private LilLexiDocument doc;
	private LilLexiControl control;
	private char c;
	private JFrame shell;
	private MyCanvas panel;
	
	public LilLexiUI() {
		this.shell = new JFrame("Lil Lexi");
		shell.setSize(900, 900);
		shell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shell.setVisible(true);
		panel = new MyCanvas(control);
		panel.setSize(800, 800);
		panel.setBackground(Color.white);
		panel.setVisible(true);
		shell.setLocationRelativeTo(null);
		shell.add(panel);
	}
	
	public void start() {
		
	}
	
	public void update() {
		
	}
	
	public void setDoc(LilLexiDocument doc) {  this.doc = doc;  }
	
	public void setController(LilLexiControl control) {  this.control = control;  }
}
