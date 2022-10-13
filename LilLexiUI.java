package LilLexi;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Shape;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LilLexiUI {
	
	private LilLexiControl control;
	private JFrame shell;
	private MyCanvas panel;
	private BufferedImage image;
	private ImageIcon icon;
	private JScrollPane scroll;
	
	public LilLexiUI(LilLexiControl control) {
		this.shell = new JFrame("Lil Lexi");
		createMenus();
		shell.setSize(900, 900);
		shell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shell.setVisible(true);
		scroll = new JScrollPane();
		scroll.setSize(20, 900);
		scroll.setVisible(true);
		scroll.setAutoscrolls(true);
		scroll.setBounds(800, 0, 20, 800);
		//JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//pane.setFocusable(false);
		//shell.setContentPane(pane);
		makeCanvas();
		panel.add(scroll);
		shell.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Row: " + arg0.getX());
				System.out.println("Col: " + arg0.getY());
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {	}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		this.setController(control);
	}
	
	private void createMenus() {
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		// Create file options for new, open, save and quit
		JMenuItem newOption = new JMenuItem("New");
		newOption.addActionListener(e -> createNew());
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
		
		// Insert Menu
		JMenu insertMenu = new JMenu("Insert");
		JMenuItem imageOption = new JMenuItem("Image");
		imageOption.addActionListener(e -> addImage());
		JMenuItem shapeOption = new JMenuItem("Shape");
		shapeOption.addActionListener(e -> control.add(new MyShape()));
		insertMenu.add(imageOption);
		insertMenu.add(shapeOption);
		// Add options to menu
		fileMenu.add(newOption);
		menu.add(fileMenu);
		menu.add(editMenu);
		menu.add(fontMenu);
		menu.add(insertMenu);
		shell.setJMenuBar(menu);
		//shell.pack();
	}
	
	private void changeFont(String newFont) {
		control.setFont(newFont);
	}
	
	private void makeCanvas() {
		panel = new MyCanvas();
		shell.add(panel);
		panel.requestFocus();
	}
	
	private void createNew() {
		control.clear();
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
	
	private void addImage() {
		setImage();
		control.add(new MyImage(icon));
	}
	
	public void setImage() {
		System.out.println("before try");
		try {
			image = ImageIO.read(MyCanvas.class.getResourceAsStream("apple.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(image == null);
		icon = new ImageIcon(image);
	}
}
