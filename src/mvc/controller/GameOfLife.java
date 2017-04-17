package mvc.controller;

import javax.swing.JOptionPane;

import mvc.model.GameEngine;
import mvc.model.Statistics;
import mvc.view.GameView;
import mvc.view.gui.GUIView;
import mvc.view.shell.ShellView;

/**
 * Classe que começa o jogo
 * @author Miguel
 *
 */

public class GameOfLife {

	private GameEngine engine;
	private GameController controller;
	private GameView view;
	private Statistics statistics;
	
	/**
	 * Define condifurações iniciais e inicia o jogo
	 */
	public GameOfLife() {
		engine = new GameEngine(getHeight(), getWidth());
		statistics = new Statistics(engine);
		controller = new GameController();
		controller.setStatistics(statistics);
		controller.setEngine(engine);
		view = selectGameView();
		view.start(); // Começa o jogo com a view escolhida
	}
	
	/**
	 * Pergunta a altura do tabuleiro ao usuário
	 * @return
	 * 		altura do tabuleiro
	 */
	public int getHeight() {
		int altura = Integer.parseInt (JOptionPane.showInputDialog("Digite a altura do tabuleiro: ") );
		
		while ( altura < 10 || altura > 50) {
			altura = Integer.parseInt (JOptionPane.showInputDialog(null, "Voce deve digitar um numero entre 10 e 50!") );
		}
		return altura;
	}

	/**
	 * Pergunta a largura do tabuleiro ao usuário
	 * @return
	 * 		largura do tabuleiro
	 */
	public int getWidth() {
		int largura = Integer.parseInt (JOptionPane.showInputDialog("Digite a largura do tabuleiro: ") );
		
		while ( largura < 10 || largura > 50) {
			largura = Integer.parseInt (JOptionPane.showInputDialog(null, "Voce deve digitar um numero entre 10 e 50!") );
		}
		
		return largura;
	}
	
	/**
	 * Pergunta que view o usuário que executar
	 * @return
	 * 		Retorna a View escolhida
	 */
	public GameView selectGameView() {
		int view;
		view = Integer.parseInt( JOptionPane.showInputDialog(null, "Por favor, selecione a view que deseja utilizar:"
				+ "\n[1] Shell (Console)\n[2] Grafica (Swing)\nOpcao: ") );
		
		try {
			if ((view != 1) && (view != 2)) {
				System.out.println("Voce deve escolher uma view valida!");
				selectGameView();
			}
			switch (view) {
			case 1: return new ShellView(controller, engine);
			case 2: return new GUIView(controller, engine);
			default: return new GUIView(controller, engine);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Voce deve digitar um numero!");
			selectGameView();
		}
		
		return null; // Nunca ocorre
	}
	
}
