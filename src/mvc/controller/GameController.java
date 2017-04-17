package mvc.controller;

import mvc.model.GameEngine;
import mvc.model.Statistics;
import mvc.model.Strategies;

/**
 * Controla o tráfego de dados entre as camadas Model e View
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
	 * Pergunta à engine a largura do tabuleiro
	 * @return
	 * 		largura do tabuleiro
	 */
	public int getBoardWidth() {
		return engine.getWidth();
	}

	/**
	 * Pergunta à engine a altura do tabuleiro
	 * @return
	 * 		altura do tabuleiro
	 */
	public int getBoardHeight() {
		return engine.getHeight();
	}
	
	/**
	 * Pergunta à engine se a célula está viva
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
	 * Manda a engine reviver uma célula (i,j)
	 * @param i
	 * 		linha
	 * @param j
	 * 		coluna
	 */
	public void makeCellAlive(int i, int j) {
		engine.makeCellAlive(i, j);
	}

	/**
	 * Manda a engine trocar a estratégia
	 * @param strategy
	 * 		estratégia em questão
	 */
	public void changeStrategy(Strategies strategy) {
		engine.setStrategy(Strategies.fabricate(strategy));
	}

	/**
	 * Faz um loop pelo número de gerações e faz uso de Thread para as pausas.
	 * Faz tratamento de exceções
	 * @param geracoes
	 * 		n° de gerações
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
	 * Manda a engine matar todas as células
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
	 * Retorna uma string com as estatísticas notificadas pela engine
	 * @return
	 */
	public String getStatistics() {
		return statistics.getMessage();
	}

	/**
	 * Chama o método de próxima geração da engine
	 */
	public void nextGeneration() {
		engine.nextGeneration();
	}

	/**
	 * Recebe uma nova estatística de acordo com a entrada do usuário na view
	 * @param statistics
	 * 		nova estatística
	 */
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	/**
	 * Manda a engine matar uma célula (i,j)
	 * @param i
	 * 		linha
	 * @param j
	 * 		coluna
	 */
	public void makeCellDead(int i, int j) {
		engine.makeCellDead(i, j);		
	}
	
	/**
	 * Pergunta à engine o número de células vivas atualmente
	 * @return
	 * 		número inteiro com o número de células vivas
	 */
	public int numberOfAliveCells() {
		return engine.numberOfAliveCells();
	}
	
}
