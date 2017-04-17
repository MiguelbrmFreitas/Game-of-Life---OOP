package mvc.model;

/**
 * Representa��o de uma classe abstrata gen�rica para uma estrat�gia de jogo
 * Descreve dois m�todos que devem ser implementados pelas classes filhas
 * Tem um atributo engine que atualizar� a engine original
 * 
 * @author Miguel
 */
public abstract class GameStrategy {
	
	protected GameEngine engine;
	
	/**
	 * M�todo booleano para definir se uma c�lula viva c�lula deve permanecer viva
	 * @param i	
	 * 		linha da c�lula
	 * @param j
	 * 		coluna da c�lula
	 * @return	
	 * 		valor booleano com a resposta
	 */
	public abstract boolean shouldKeepAlive(int i, int j);
	
	/**
	 * M�todo booleano para definir se uma c�lula morta deve renascer
	 * @param i	
	 * 		linha da c�lula
	 * @param j
	 * 		coluna da c�lula
	 * @return
	 * 		valor booleano com a resposta
	 */
	public abstract boolean shouldRevive(int i, int j);
	
	/**
	 * Recebe a engine do jogo
	 * @param engine
	 * 		engine do jogo
	 */
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
	
}
