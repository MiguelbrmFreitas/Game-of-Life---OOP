package mvc.model.strategies;

import mvc.model.GameStrategy;
/**
 * Estrat�gia de jogo Seeds. As c�lulas sempre morrem
 * na próxima geração, embora seja mais f�cil uma c�-
 * lula nascer (apenas 2 vizinhos).
 * @author Miguel
 */
public class Seeds extends GameStrategy {
	
	public boolean shouldKeepAlive(int i, int j) {
		return false; 
	}
	
	public boolean shouldRevive(int i, int j) {
		return (!engine.isCellAlive(i, j))
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 2);
	}

}