package mvc.view.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import mvc.controller.GameController;
import observer.Observer;

/**
 * Define o comportamento e a implementação do tabuleiro
 * 
 * @author Miguel
 *
 */

@SuppressWarnings("serial")
public class GUIBoard extends JPanel implements Observer {

	private ImageIcon liveCell = new ImageIcon("live.jpg");
	private ImageIcon deadCell = new ImageIcon("dead.jpg");
	
	private int height;
	private int width;
	
	private GameController controller;
	private GUICell[][] tabuleiro;
	
	/**
	 * Construtor
	 * @param controller
	 * 		recebe um controller
	 */
	public GUIBoard(GameController controller) {
		super();
		height = controller.getBoardHeight();
		width = controller.getBoardWidth();
		this.controller = controller;
		
		criarTabuleiro();
		update();
		
		GridLayout manager = new GridLayout(height, width);
		setLayout(manager);
		
		for ( int i = 0; i < height; i++ ) {
			for ( int j = 0; j < width; j++ ) {
				add(tabuleiro[i][j]);
			}
		}
		
		setVisible(true);
	}
	
	/**
	 * Instancia tabuleiro
	 */
	private void criarTabuleiro() {
		tabuleiro = new GUICell[height][width];
		
		for ( int i = 0; i < height; i++ ) {
			for ( int j = 0; j < width; j++ ) {
				tabuleiro[i][j] = new GUICell(i, j);
				tabuleiro[i][j].setBackground(Color.white);
				tabuleiro[i][j].setBorderPainted(false);
				tabuleiro[i][j].addActionListener(new CellListener(tabuleiro[i][j]));
			}
		}
	}
	
	/**
	 * Verifica quando um botão foi pressionado e lida com isso
	 * @author Miguel
	 *
	 */
	private class CellListener implements ActionListener {

		private GUICell button;
		
		public CellListener(GUICell button) {
			this.button = button;
		}
		
		/**
		 * Verifica que ação realizar em função do estado da célula clicada
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if ( controller.isCellAlive(button.i, button.j) ) {
				button.setIcon(deadCell);
				controller.makeCellDead(button.i, button.j);
			} else {
				button.setIcon(liveCell);
				controller.makeCellAlive(button.i, button.j);
			}
		}
		
	}

	/**
	 * Atualiza o tabuleiro
	 */
	@Override
	public void update() {
		for ( int i = 0; i < height; i++ ) {
			for ( int j = 0; j < width; j++ ) {
				if ( controller.isCellAlive(i, j) )
					tabuleiro[i][j].setIcon(liveCell);
				else
					tabuleiro[i][j].setIcon(deadCell);
				tabuleiro[i][j].setVisible(true);	
			}
		}
	}

}