package mvc.model;

/**
 * Representação de uma classe abstrata genérica para uma estratégia de jogo
 * Descreve dois métodos que devem ser implementados pelas classes filhas
 * Tem um atributo engine que atualizará a engine original
 * 
 * @author Miguel
 */
public abstract class GameStrategy {
	
	protected GameEngine engine;
	
	/**
	 * Método booleano para definir se uma célula viva célula deve permanecer viva
	 * @param i	
	 * 		linha da célula
	 * @param j
	 * 		coluna da célula
	 * @return	
	 * 		valor booleano com a resposta
	 */
	public abstract boolean shouldKeepAlive(int i, int j);
	
	/**
	 * Método booleano para definir se uma célula morta deve renascer
	 * @param i	
	 * 		linha da célula
	 * @param j
	 * 		coluna da célula
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
