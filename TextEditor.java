import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextEditor {
	private JFrame shell;
	private JTextArea text;
	List<Glyph<?>> list;
	public TextEditor() {
		shell = new JFrame("Lil Lexi");
		text = new JTextArea();
		list = new ArrayList<>();
		initializeShell();
		createMenus();
	}

	private void initializeShell() {
		shell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shell.add(text);
		shell.setSize(770, 595);
		shell.setVisible(true);
	}

	private void createMenus() {
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		// Create file options for new, open, save and quit
		JMenuItem newOption = new JMenuItem("New");
		newOption.addActionListener(e -> newFile());
		JMenuItem openOption = new JMenuItem("Open");
		openOption.addActionListener(e -> openFile());
		JMenuItem saveOption = new JMenuItem("Save");
		saveOption.addActionListener(e -> saveFile());
		JMenuItem quitOption = new JMenuItem("Quit");
		quitOption.addActionListener(e -> quit());
		// Add options to menu
		fileMenu.add(newOption);
		fileMenu.add(openOption);
		fileMenu.add(saveOption);
		fileMenu.add(quitOption);
		menu.add(fileMenu);
		shell.setJMenuBar(menu);
		text.addKeyListener(new KeyListener() {
									@Override
									public void keyPressed(KeyEvent e) {
										add(e);
									}

									@Override
									public void keyReleased(KeyEvent arg0) {
										//add(arg0);
									}

									@Override
								 	public void keyTyped(KeyEvent arg0) {
								 		//add(arg0);
								 	}
							});
	}

	private void add(KeyEvent e) {
		System.out.println(e.getKeyCode());
		System.out.println(e.getKeyChar());
		list.add(new Glyph<Character>(e.getKeyChar()));
		System.out.println(list);
	}
	
	private void newFile() {
		System.out.println("New file");
	}

	private void openFile() {
		System.out.println("Open file");
	}

	private void saveFile() {
		System.out.println("Save file");
	}

	private void quit() {
		System.out.println("Quitting");
		System.exit(0);
	}

}