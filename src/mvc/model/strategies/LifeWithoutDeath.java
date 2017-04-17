package mvc.model.strategies;

import mvc.model.GameStrategy;
/**
 * Estratégia de jogo LifeWithoutDeath. As células nunca
 * morrem e precisa-se de 3 vizinhos para uma nova célula
 * nascer.
 * 
 * @author Miguel
 */
public class LifeWithoutDeath extends GameStrategy {
	/**
	 * Retorna verdadeiro para toda célula já viva
	 */
	public boolean shouldKeepAlive(int i, int j) {
		return true;
	}
	
	public boolean shouldRevive(int i, int j) {
		return engine.numberOfNeighborhoodAliveCells(i, j) == 3;
	}
	

}