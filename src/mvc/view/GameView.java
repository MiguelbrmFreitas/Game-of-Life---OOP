package mvc.view;

import observer.Observer;
import mvc.controller.GameController;
import mvc.model.GameEngine;

/**
 * Classe abstrata genérica que define uma view para o programa
 * É herdada pela sub-classe, que implementa a view
 * Define métodos essenciais para toda view deste software
 * 
 * @author Miguel
 *
 */

public abstract class GameView implements Observer {

	protected GameController controller;
	
	public GameView(GameController controller, GameEngine engine) {
		this.controller = controller;
		engine.register(this);
	}
	
	/**
	 * Método para começar o jogo
	 */
	public abstract void start();
	/**
	 * Método para mostrar estatísticas
	 */
	public abstract void showStatistics();

}
