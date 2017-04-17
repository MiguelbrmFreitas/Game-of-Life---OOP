package mvc.model;

/**
 * Representa��o de uma c�lula em um tabuleiro do GoL.
 */
public class Cell {
	private boolean alive = false;

	/**
	 * Verifica se a c�lula est� viva
	 * @return
	 * 		resposta booleana da quest�o
	 */
	public boolean isAlive() {
		return alive;
	}

	/**	
	 * Mata uma c�lula
	 */
	public void kill() {
		this.alive = false;
	}
	
	/**
	 * Revive uma c�lula
	 */
	public void revive() {
		this.alive = true;
	}
}
