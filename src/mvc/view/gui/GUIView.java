package mvc.view.gui;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import mvc.controller.GameController;
import mvc.model.GameEngine;
import mvc.view.GameView;

/**
 * Herda de GameView e implementa uma view gr�fica com o pacote Java Swing
 * 
 * @author Miguel
 *
 */

public class GUIView extends GameView implements Runnable {
	
	private GameWindow frame;
	private String statisticsMessage;
	
	public GUIView(GameController controller, GameEngine engine) {
		super(controller, engine);
		SwingUtilities.invokeLater(this);
	}

	/**
	 * Método update() responśavel por atualizar
	 * o tabuleiro na GUI sempre que houver mudan-
	 * ças na Model.
	 * 
	 * Também é responsável por atualizar a men-
	 * sagem das estatísticas a serem exibidas
	 * sempre que houver mudanças na Statistics.
	 */
	@Override
	public void update() {
		frame.updateBoard();
		statisticsMessage = controller.getStatistics();
	}

	@Override
	public void start() {}

	@Override
	public void showStatistics() {
		JOptionPane.showMessageDialog(null, statisticsMessage);
	}

	@Override
	public void run() {
		frame = new GameWindow(controller);
	}

}
