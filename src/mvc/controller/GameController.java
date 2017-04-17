package mvc.controller;

import mvc.model.GameEngine;
import mvc.model.Statistics;
import mvc.model.Strategies;

/**
 * Controla o tr�fego de dados entre as camadas Model e View
 * @author Miguel
 *
 */
public class GameController {

	private GameEngine engine;
	private Statistics statistics;
	
	/**
	 * Define a engine a ser usada
	 * @param engine
	 * 		engine a ser utilizada
	 */
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
	
	/**
	 * Pergunta � engine a largura do tabuleiro
	 * @return
	 * 		largura do tabuleiro
	 */
	public int getBoardWidth() {
		return engine.getWidth();
	}

	/**
	 * Pergunta � engine a altura do tabuleiro
	 * @return
	 * 		altura do tabuleiro
	 */
	public int getBoardHeight() {
		return engine.getHeight();
	}
	
	/**
	 * Pergunta � engine se a c�lula est� viva
	 * @param i
	 * 		linha
	 * @param j
	 * 		coluna
	 * @return
	 * 		resposta booleana
	 */
	public boolean isCellAlive(int i, int j) {
		return engine.isCellAlive(i, j);
	}
	
	/**
	 * Manda a engine reviver uma c�lula (i,j)
	 * @param i
	 * 		linha
	 * @param j
	 * 		coluna
	 */
	public void makeCellAlive(int i, int j) {
		engine.makeCellAlive(i, j);
	}

	/**
	 * Manda a engine trocar a estrat�gia
	 * @param strategy
	 * 		estrat�gia em quest�o
	 */
	public void changeStrategy(Strategies strategy) {
		engine.setStrategy(Strategies.fabricate(strategy));
	}

	/**
	 * Faz um loop pelo n�mero de gera��es e faz uso de Thread para as pausas.
	 * Faz tratamento de exce��es
	 * @param geracoes
	 * 		n� de gera��es
	 */
	public void animation(int geracoes) {
		for ( int i = 0; i < geracoes; i++ ) {
			if (engine.numberOfAliveCells() > 0) {
				nextGeneration();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else break;
		}
	}

	/**
	 * Manda a engine matar todas as c�lulas
	 */
	public void killAllCells() {
		engine.killAllCells();
	}

	/**
	 * Sai do sistema
	 */
	public void exit() {
		System.exit(0);
	}

	/**
	 * Retorna uma string com as estat�sticas notificadas pela engine
	 * @return
	 */
	public String getStatistics() {
		return statistics.getMessage();
	}

	/**
	 * Chama o m�todo de pr�xima gera��o da engine
	 */
	public void nextGeneration() {
		engine.nextGeneration();
	}

	/**
	 * Recebe uma nova estat�stica de acordo com a entrada do usu�rio na view
	 * @param statistics
	 * 		nova estat�stica
	 */
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	/**
	 * Manda a engine matar uma c�lula (i,j)
	 * @param i
	 * 		linha
	 * @param j
	 * 		coluna
	 */
	public void makeCellDead(int i, int j) {
		engine.makeCellDead(i, j);		
	}
	
	/**
	 * Pergunta � engine o n�mero de c�lulas vivas atualmente
	 * @return
	 * 		n�mero inteiro com o n�mero de c�lulas vivas
	 */
	public int numberOfAliveCells() {
		return engine.numberOfAliveCells();
	}
	
}
