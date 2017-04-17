package mvc.model.strategies;

import mvc.model.GameStrategy;

/**
 * Estrat√©gia de jogo Conway. Regras padrıes:
 * 2 ou 3 vizinhos vivos: Continua viva
 * 3 vizinhos vivos: revive
 * @author Miguel
 */
public class Conway extends GameStrategy {
	
	public boolean shouldKeepAlive(int i, int j) {
		return (engine.isCellAlive(i, j))
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 2 ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 3);
	}
	
	public boolean shouldRevive(int i, int j) {
		return (!engine.isCellAlive(i, j))
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 3);
	}

}
