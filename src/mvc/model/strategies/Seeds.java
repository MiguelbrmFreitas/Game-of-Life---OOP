package mvc.model.strategies;

import mvc.model.GameStrategy;
/**
 * Estratégia de jogo Seeds. As células sempre morrem
 * na prÃ³xima geraÃ§Ã£o, embora seja mais fácil uma cé-
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