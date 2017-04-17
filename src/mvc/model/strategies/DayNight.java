package mvc.model.strategies;

import mvc.model.GameStrategy;
/**
 * Estratégia de jogo DayNight. Causa um efeito interessante,
 * visto que se aceita várias possibilidades de vizinhos para
 * renascer e morrer.
 * @author Miguel
 */
public class DayNight extends GameStrategy {
	
	public boolean shouldKeepAlive(int i, int j) {
		return (!engine.isCellAlive(i, j))
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 3 ||
						engine.numberOfNeighborhoodAliveCells(i, j) == 6 ||
						engine.numberOfNeighborhoodAliveCells(i, j) == 7 ||
						engine.numberOfNeighborhoodAliveCells(i, j) == 8);
	}
	
	public boolean shouldRevive(int i, int j) {
		return (!engine.isCellAlive(i, j))
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 3 ||
						(engine.numberOfNeighborhoodAliveCells(i, j) == 4 ||
						engine.numberOfNeighborhoodAliveCells(i, j) == 6 ||
						engine.numberOfNeighborhoodAliveCells(i, j) == 7 ||
						engine.numberOfNeighborhoodAliveCells(i, j) == 8));
	}
	

}