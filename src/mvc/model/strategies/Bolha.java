package mvc.model.strategies;

import mvc.model.GameStrategy;

/**
 * EstratÃ©gia de jogo Conway. Regras padrões:
 * 2 ou 3 vizinhos vivos: Continua viva
 * 3 vizinhos vivos: revive
 * @author Miguel
 */
public class Bolha extends GameStrategy {
	
	public boolean shouldKeepAlive(int i, int j) {
		return false;
	}
	
	public boolean shouldRevive(int i, int j) {
		return (  !engine.isCellAlive(i,j) && 
				(engine.numberOfNeighborhoodAliveCells(i, j) > 0 &&  
						(engine.numberOfNeighborhoodAliveCells(i, j) <= 8)) 
				);
	}

}
