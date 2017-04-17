package mvc.view.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;

import mvc.controller.GameController;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	private GUIBoard board;
	private GUIMenu menu;
	private GUIStats stats;
	private Container pane = getContentPane();

	public GameWindow(GameController controller) {
		super("Game of Life (Miguel & Daniel implementation)");
		
		createWindow();
		
		GridBagConstraints c = setWindowLayout();
		setBoardGUI(controller, c);
		setMenuGUI(controller, c);
		setStatsGUI(controller, c);
		
		setVisible(true);
	}

	public GridBagConstraints setWindowLayout() {
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		return c;
	}

	public void createWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		if ( dim.width > 600 )
			dim.width -= 100;
		if ( dim.height > 600 )
			dim.height -= 100;
		setSize(dim);
		setLocationRelativeTo(null);
	}

	public void setStatsGUI(GameController controller, GridBagConstraints c) {
		stats = new GUIStats(controller);
		c.weighty = 0.2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.SOUTH;
		c.weightx = 0.5;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(stats, c);
	}

	public void setMenuGUI(GameController controller, GridBagConstraints c) {
		menu = new GUIMenu(controller);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.weightx = 0;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(menu, c);
	}

	public void setBoardGUI(GameController controller, GridBagConstraints c) {
		board = new GUIBoard(controller);
		c.weighty = 0.8;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(board, c);
	}
	
	public void updateBoard() {
		board.update();
	}
	
	public GUIBoard getGameBoard() {
		return board;
	}
	
}