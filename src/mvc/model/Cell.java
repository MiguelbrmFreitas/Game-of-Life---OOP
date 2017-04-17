package mvc.model;

/**
 * Representação de uma célula em um tabuleiro do GoL.
 */
public class Cell {
	private boolean alive = false;

	/**
	 * Verifica se a célula está viva
	 * @return
	 * 		resposta booleana da questão
	 */
	public boolean isAlive() {
		return alive;
	}

	/**	
	 * Mata uma célula
	 */
	public void kill() {
		this.alive = false;
	}
	
	/**
	 * Revive uma célula
	 */
	public void revive() {
		this.alive = true;
	}
}
