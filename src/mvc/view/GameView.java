package mvc.view;

import observer.Observer;
import mvc.controller.GameController;
import mvc.model.GameEngine;

/**
 * Classe abstrata gen�rica que define uma view para o programa
 * � herdada pela sub-classe, que implementa a view
 * Define m�todos essenciais para toda view deste software
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
	 * M�todo para come�ar o jogo
	 */
	public abstract void start();
	/**
	 * M�todo para mostrar estat�sticas
	 */
	public abstract void showStatistics();

}
