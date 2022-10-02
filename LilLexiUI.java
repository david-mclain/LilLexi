package LilLexi;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class LilLexiUI {
	
	private LilLexiControl control;
	private JFrame shell;
	private MyCanvas panel;
	
	public LilLexiUI(LilLexiControl control) {
		this.shell = new JFrame("Lil Lexi");
		createMenus();
		shell.setSize(900, 900);
		shell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shell.setVisible(true);
		makeCanvas();
		this.setController(control);
	}
	
	private void createMenus() {
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		// Create file options for new, open, save and quit
		JMenuItem newOption = new JMenuItem("New");
		newOption.addActionListener(e -> createNew());
		JMenuItem openOption = new JMenuItem("Open");
		openOption.addActionListener(e -> openFile());
		JMenuItem saveOption = new JMenuItem("Save");
		saveOption.addActionListener(e -> saveFile());
		JMenuItem quitOption = new JMenuItem("Quit");
		quitOption.addActionListener(e -> control.quit());
		JMenu editMenu = new JMenu("Edit");
		JMenuItem undoOption = new JMenuItem("Undo");
		undoOption.addActionListener(e -> control.undo());
		editMenu.add(undoOption);
		// Add options to menu
		fileMenu.add(newOption);
		fileMenu.add(openOption);
		fileMenu.add(saveOption);
		fileMenu.add(quitOption);
		menu.add(fileMenu);
		menu.add(editMenu);
		shell.setJMenuBar(menu);
		//shell.pack();
	}
	
	private void makeCanvas() {
		panel = new MyCanvas();
		panel.setSize(800, 800);
		panel.setBackground(Color.white);
		panel.setVisible(true);
		shell.add(panel);
	}
	
	private void createNew() {
		System.out.println("New file");
		panel.setVisible(false);
		control.clear();
		panel.clear();
		shell.remove(panel);
		makeCanvas();
	}

	private void openFile() {
		System.out.println("Open file");
	}

	private void saveFile() {
		System.out.println("Save file");
	}

	
	public void start() {
		
	}
	
	public void update() {
		panel.repaint();
	}
	
	public void setController(LilLexiControl control) {
		panel.setControl(control);
		this.control = control;
	}
}
