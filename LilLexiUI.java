package LilLexi;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.Shape;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		/*
		JTextArea are = new JTextArea();
		shell.add(are);
		are.setSize(new Dimension(800, 800));
		are.setVisible(true);
		are.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		are.setLineWrap(true);
		are.setWrapStyleWord(true);
		are.setEditable(false);
		are.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				System.out.println("Key clicked");
				//graphics.setFont(control.getFont());
				int code = e.getKeyCode();
				Glyph curGlyph;
				if (code == KeyEvent.VK_BACK_SPACE) {
					if (control.size() > 0) {
						are.setCaretPosition(are.getCaretPosition() - 1);
						are.insert("", are.getCaretPosition());
						control.removeLast();
					}
				}
				//else if (code == 9) {
				//	for (int i = 0; i < 4; i++)
				//		control.add(new MyCharacter(' '));
				//}
				else if ((code >= 44 && code <= 111) || code == 222 || code == 32 || code == 10) {
					curGlyph = new MyCharacter(e.getKeyChar());
					control.add(curGlyph);
					are.insert(curGlyph.toString(), are.getCaretPosition());
					//control.add(new MyCharacter(e.getKeyChar()));
				}
				//repaint();
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
		});
		/*
		 * 
		 * 
		 * 
		 * TEST TO SEE IF YOU CAN USE TEXT AREA INSTEAD OF USING A CANVAS
		 * 
		 * 
		 * 
		 */
		//JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//pane.setFocusable(false);
		//shell.setContentPane(pane);
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
		// Edit Menu
		JMenu editMenu = new JMenu("Edit");
		JMenuItem undoOption = new JMenuItem("Undo");
		undoOption.addActionListener(e -> control.undo());
		editMenu.add(undoOption);
		
		// Font Menu
		JMenu fontMenu = new JMenu("Font");
		JMenuItem fontTNR = new JMenuItem("Times New Roman");
		fontTNR.addActionListener(e -> changeFont("Times New Roman"));
		JMenuItem fontMono = new JMenuItem("Monospaced");
		fontMono.addActionListener(e -> changeFont("Monospaced"));
		JMenuItem fontArial = new JMenuItem("Arial");
		fontArial.addActionListener(e -> changeFont("Arial"));
		fontMenu.add(fontMono);
		fontMenu.add(fontTNR);
		fontMenu.add(fontArial);
		
		
		// Add options to menu
		fileMenu.add(newOption);
		fileMenu.add(openOption);
		fileMenu.add(saveOption);
		fileMenu.add(quitOption);
		menu.add(fileMenu);
		menu.add(editMenu);
		menu.add(fontMenu);
		shell.setJMenuBar(menu);
		//shell.pack();
	}
	
	private void changeFont(String newFont) {
		control.setFont(newFont);
	}
	
	private void makeCanvas() {
		panel = new MyCanvas();
		panel.setSize(800, 800);
		panel.setBackground(Color.white);
		panel.setVisible(true);
		shell.add(panel);
	}
	
	private void createNew() {
		control.clear();
	}

	private void openFile() {
		
	}

	private void saveFile() {
		
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
