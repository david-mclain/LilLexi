/**
 * package LilLexi contains all components for WYSIWYG text editor
 * 
 * @author David McLain
 * 
 * UI for LilLexi
 */

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class LilLexiUI {
	
	private LilLexiControl control;
	private JFrame shell;
	private MyCanvas canvas;
	private JPanel panel;
	private JScrollPane scroll;
	/**
	 * Instantiates UI and all of its elements
	 * @param control - controller to communicate to document
	 */
	public LilLexiUI(LilLexiControl control) {
		this.setController(control);
	}
	/**
	 * Starts UI up
	 */
	public void start() {
		this.shell = new JFrame("Lil Lexi");
		createMenus();
		makeCanvas();
		setScroller();
		panel = new JPanel(null);
		panel.add(scroll);
		shell.setContentPane(panel);
		shell.pack();
		shell.setSize(900, 900);
		shell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shell.setVisible(true);
	}
	/**
	 * Creates all menus for LilLexi to display at top
	 */
	private void createMenus() {
		// Create menu bars
		JMenuBar menu = new JMenuBar();
		// Create file menu on menu bar
		JMenu fileMenu = new JMenu("File");
		// Create new and quit options
		JMenuItem newOption = new JMenuItem("New");
		newOption.addActionListener(e -> createNew());
		JMenuItem quitOption = new JMenuItem("Quit");
		quitOption.addActionListener(e -> control.quit());
		// Add new and quit options to file menu
		fileMenu.add(newOption);
		fileMenu.add(quitOption);
		
		// Create edit menu
		JMenu editMenu = new JMenu("Edit");
		// Create undo and redo options
		JMenuItem undoOption = new JMenuItem("Undo");
		undoOption.addActionListener(e -> control.undo());
		JMenuItem redoOption = new JMenuItem("Redo");
		redoOption.addActionListener(e -> control.redo());
		// Add undo and redo options to edit menu
		editMenu.add(undoOption);
		editMenu.add(redoOption);
		
		// Create font menu
		JMenu fontMenu = new JMenu("Font");
		// Create options for a few fonts
		JMenuItem fontTNR = new JMenuItem("Times New Roman");
		fontTNR.addActionListener(e -> changeFont("Times New Roman"));
		JMenuItem fontMono = new JMenuItem("Monospaced");
		fontMono.addActionListener(e -> changeFont("Monospaced"));
		JMenuItem fontArial = new JMenuItem("Arial");
		fontArial.addActionListener(e -> changeFont("Arial"));
		// Add fonts to font menu
		fontMenu.add(fontMono);
		fontMenu.add(fontTNR);
		fontMenu.add(fontArial);
		
		// Create insert menu
		JMenu insertMenu = new JMenu("Insert");
		// Create image and shape options
		JMenuItem imageOption = new JMenuItem("Image");
		imageOption.addActionListener(e -> control.add(new MyImage()));
		JMenuItem shapeOption = new JMenuItem("Shape");
		shapeOption.addActionListener(e -> control.add(new MyShape()));
		// Add image and shape options to insert menu
		insertMenu.add(imageOption);
		insertMenu.add(shapeOption);
		
		// Create scroll menu
		JMenu scrollMenu = new JMenu("Scroll");
		// Create scroll down and up
		JMenuItem down = new JMenuItem("Down");
		down.addActionListener(e -> control.decreaseScroll());
		JMenuItem up = new JMenuItem("Up");
		up.addActionListener(e -> control.increaseScroll());
		// Add down and up to scroll menu
		scrollMenu.add(up);
		scrollMenu.add(down);
		
		// Add all menus to menu bar
		menu.add(fileMenu);
		menu.add(editMenu);
		menu.add(fontMenu);
		menu.add(insertMenu);
		menu.add(scrollMenu);
		
		// Set menu bar
		shell.setJMenuBar(menu);
	}
	/**
	 * Sets scroll bar on canvas
	 */
	private void setScroller() {
		scroll = new JScrollPane(canvas);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(0, 0, 870, 800);
		scroll.setPreferredSize(new Dimension(870, 800));
		scroll.setAutoscrolls(true);
		scroll.getViewport().add(canvas);
	}
	/**
	 * Changes font
	 * @param newFont - name of font to change to
	 */
	private void changeFont(String newFont) {
		control.setFont(newFont);
	}
	/**
	 * Creates canvas
	 */
	private void makeCanvas() {
		canvas = new MyCanvas(control);
	}
	/**
	 * Clears document
	 */
	private void createNew() {
		control.clear();
	}
	/**
	 * Updates UI
	 */
	public void update() {
		canvas.revalidate();
		int height = (int)scroll.getPreferredSize().getHeight();
		scroll.getVerticalScrollBar().setValue(height);
		canvas.repaint();
	}
	/**
	 * Sets controller to communicate with document
	 * @param control - controller to communicate with document
	 */
	public void setController(LilLexiControl control) {
		this.control = control;
	}
}
