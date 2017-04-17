package mvc.view.shell;

import java.util.Scanner;

import mvc.controller.GameController;
import mvc.model.GameEngine;
import mvc.model.InvalidStrategyException;
import mvc.model.Strategies;
import mvc.view.GameView;

/**
 * Herda de GameView e implementa uma view baseada em terminal
 * 
 * @author Miguel
 *
 */

public class ShellView extends GameView {

	/**
	 * Constantes
	 */
	private static final String LINE = "+-----+";
	private static final String DEAD_CELL = "|     |";
	private static final String ALIVE_CELL = "|  o  |";
	
	private Scanner s = new Scanner(System.in);
	
	public ShellView(GameController controller, GameEngine engine) {
		super(controller, engine);
	}

	@Override
	public void update() {
		printBoard();
	}
	
	/**
	 *  Imprime uma linha usada como separador das linhas do tabuleiro  
	 */
	private void printLine() {
		for (int j = 0; j < controller.getBoardWidth(); j++) {
			System.out.print(LINE);
		}
		System.out.print("\n");
	}
	
	/**
	 * Imprime os identificadores das colunas na primeira linha do tabuleiro
	 */
	private void printFirstRow() {
		System.out.println("\n \n");
		for (int j = 0; j < controller.getBoardWidth(); j++) {
			System.out.print("   " + j + "   ");
		}
		System.out.print("\n");
	}
	
	/**
	 * Imprime o tabuleiro
	 */
	public void printBoard() {
		printFirstRow();
		printLine();
		
		int height = controller.getBoardHeight();
		int width = controller.getBoardWidth();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(controller.isCellAlive(i, j) ? ALIVE_CELL : DEAD_CELL);
			}
			System.out.println("   " + i);
			printLine();
		}
	}
	
	/**
	 * Mostra o menu
	 */
	public void displayMenu() {
		int option = 0;
		System.out.println("\n");
		
		do {
			System.out.println("Select one of the options: \n \n"); 
			System.out.println("[1] Make a cell alive");
			System.out.println("[2] Next generation");
			System.out.println("[3] Change strategy");
			System.out.println("[4] Animation");
			System.out.println("[5] Statistics");
			System.out.println("[6] Kill Them All!");
			System.out.println("[7] Exit");
		
			System.out.print("\n\nOption: ");
			
			try {
				option = Integer.parseInt(s.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Voce deve digitar um numero!");
				displayMenu();
			}
		} while(option == 0);
		
		switch(option) {
			case 1 	: makeCellAlive(); break;
			case 2 	: nextGeneration(); break;
			case 3 	: changeStrategy(); break;
			case 4 	: animation(); break;
			case 5 	: showStatistics(); break;
			case 6 	: killAllCells(); break;
			case 7 	: exit(); break;
			default	: displayMenu();
		}
	}
	
	/**
	 * Mostra células revividas
	 */
	private void makeCellAlive() {
		try {
			System.out.println("Digite a linha da celula (0 - " + (controller.getBoardHeight() - 1) + "): ");
			int row = Integer.parseInt(s.nextLine());
			
			System.out.println("Digite a coluna da celula (0 - " + (controller.getBoardWidth() - 1) + "): ");
			int column = Integer.parseInt(s.nextLine());
			
			controller.makeCellAlive(row, column);
		} catch (NumberFormatException e) {
			System.out.println("Voce deve digitar valores validos!");
			makeCellAlive();
		} finally {
			displayMenu();
		}
	}
	
	/**
	 * Chama a próxima geração
	 */
	private void nextGeneration() {
		controller.nextGeneration();
		displayMenu();
	}
	
	/**
	 * Menu para mudança de estratégia
	 */
	private void changeStrategy() {
		int strategy;
		
		System.out.println("Choose your strategy: ");
		System.out.println("\t[1] Conway");
		System.out.println("\t[2] High Life");
		System.out.println("\t[3] Seeds");
		System.out.println("\t[4] Day Night");
		System.out.println("\t[5] Life Without Death");
		System.out.print("Strategy: ");
		
		try {
			strategy = Integer.parseInt(s.nextLine());
			if (Strategies.validateStrategy(strategy)) {
				Strategies newStrategy = Strategies.getStrategy(strategy);
				controller.changeStrategy(newStrategy);
			} else throw new InvalidStrategyException();
		} catch (NumberFormatException | InvalidStrategyException e) {
			System.out.println("Voce deve escolher uma estrategia valida!");
			changeStrategy();
		} finally {
			printBoard();
			displayMenu();
		}
	}
	
	/**
	 * Método para animação
	 */
	private void animation() {
		System.out.println("Quantas geracoes deseja exibir?");
		try {
			int geracoes = Integer.parseInt(s.nextLine());
			controller.animation(geracoes);
		} catch (NumberFormatException e) {
			System.out.println("Voce deve digitar um numero!");
			animation();
		} finally {
			displayMenu();
		}
	}
	
	/**
	 * Mostra as estatísticas na tela
	 */
	public void showStatistics() {
		System.out.println("\n \n");
		System.out.println("=================================");
		System.out.println("           Statistics            ");
		System.out.println("=================================");
		System.out.println(		controller.getStatistics()	  );
		System.out.println("=================================");
		displayMenu();
	}
	
	/**
	 * Mata todas as células do tabuleiro
	 */
	private void killAllCells() {
		controller.killAllCells();
		displayMenu();
	}
	
	/**
	 * Sai do jogo
	 */
	private void exit() {
		controller.exit();
	}

	/**
	 * Começa o jogo
	 */
	@Override
	public void start() {
		printBoard();
		displayMenu();
	}
	
}