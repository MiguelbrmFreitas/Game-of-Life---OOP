package mvc.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import mvc.model.strategies.Conway;
import observer.Observer;
import observer.Subject;

/**
 * Definição da engine do jogo
 * Define atributos do campo do jogo
 * Define métodos para o funcionamento do jogo
 *
 * @author Miguel
 *
 */

public class GameEngine implements Subject {

	private List<Observer> observers = new ArrayList<Observer>();

	private int height;
	private int width;
	private Cell[][] cells;
	
	private GameStrategy strategy;
	
	private int revivedCells;
	private int killedCells;
	
	/**
	 * Construtor da classe Environment.
	 * 
	 * @param height
	 *            dimensao vertical do ambiente
	 * @param width
	 *            dimentsao horizontal do ambiente
	 */
	public GameEngine(int height, int width) {
		revivedCells = 0;
		killedCells = 0;
		
		this.height = height;
		this.width = width;

		cells = new Cell[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell();
			}
		}
		
		strategy = new Conway(); // DefiniÃ§Ã£o da estratÃ©gia inicial
		strategy.setEngine(this);
	}
	
	/**
	 * Calcula uma nova geracao do ambiente. Essa implementacao e flexivel
	 * e permite diferentes estrategias e regras de jogo.
	 */
	public void nextGeneration() {
		List<Cell> mustRevive = new ArrayList<Cell>();
		List<Cell> mustKill = new ArrayList<Cell>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (strategy.shouldRevive(i, j)) {
					mustRevive.add(cells[i][j]);
				} 
				else if ((!strategy.shouldKeepAlive(i, j)) && cells[i][j].isAlive()) {
					mustKill.add(cells[i][j]);
				}
			}
		}
		
		for (Cell cell : mustRevive) {
			cell.revive();
			revivedCells++;
		}
		
		for (Cell cell : mustKill) {
			cell.kill();
			killedCells++;
		}
		notifyObservers();
	}
	
	/**
	 * Torna a celula de posicao (i, j) viva
	 * 
	 * @param i posicao vertical da celula
	 * @param j posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
	 */
	public void makeCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].revive();
			revivedCells++;
			notifyObservers();
		}
		else {
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}
	
	public void makeCellDead(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].kill();
			killedCells++;
			notifyObservers();
		}
		else {
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}
	
	/**
	 * Mata toda as celulas do jogo
	 */
	public void killAllCells(){
		for(int i = 0;  i < height; i++){
			for(int j = 0; j < width; j++){
				cells[i][j].kill();
			}
		}
		notifyObservers();
	}
	
	/**
	 * Verifica se uma celula na posicao (i, j) estah viva.
	 * 
	 * @param i Posicao vertical da celula
	 * @param j Posicao horizontal da celula
	 * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
	 * 
	 * @throws InvalidParameterException caso a posicao (i,j) nao seja valida. 
	 */
	public boolean isCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			return cells[i][j].isAlive();
		}
		else {
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	/**
	 * Retorna o numero de celulas vivas no ambiente. 
	 * Esse metodo eh particularmente util para o calculo de 
	 * estatisticas e para melhorar a testabilidade.
	 * 
	 * @return  numero de celulas vivas.
	 */
	public int numberOfAliveCells() {
		int aliveCells = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(isCellAlive(i,j)) {
					aliveCells++;
				}
			}
		}
		return aliveCells;
	}

	public int getRevivedCells() {
		return revivedCells;
	}
	public int getKilledCells() {
		return killedCells;
	}
	
	/**
	 * Torna possivel o mundo infinito atraves das bordas
	 * @param x numero a ser normalizado
	 * @return
	 */
	public int normalize(int x){
		if(x >= height){
			return x - height;
		}
		else if (x < 0){
			return x + height;
		}
		
		return x;
	}

	/**
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 * @param i
	 * 		linha
	 * @param j
	 * 		coluna
	 * @return
	 * 		número de vizinhos vivos
	 */
	public int numberOfNeighborhoodAliveCells(int i, int j) {
		int alive = 0;
		for (int a = i - 1; a <= i + 1; a++) {
			for (int b = j - 1; b <= j + 1; b++) {
				int aux_a = normalize(a);
				int aux_b = normalize(b);
				if (validPosition(aux_a, aux_b)  && (!(aux_a==i && aux_b == j)) && cells[aux_a][aux_b].isAlive()) {
					alive++;
				}
			}
		}
		return alive;
	}

	
	/**
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 * @param a
	 * 		linha
	 * @param b
	 * 		coluna
	 * @return
	 * 		valor booleano que indica se a posição é válida
	 */
	private boolean validPosition(int a, int b) {
		return a >= 0 && a < height && b >= 0 && b < width;
	}
	
	public void setStrategy(GameStrategy strategy) {
		strategy.setEngine(this);
		this.strategy = strategy;
	}
	
	/**
	 * Retorna a largura
	 * @return
	 * 		largura
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Retorna a altura
	 * @return
	 * 		altura
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Função implementada da interface Subject. Registra observers
	 */
	@Override
	public void register(Observer observer) {
		observers.add(observer);
	}

	/**
	 * Função implementada da interface Subject. Notifica observers
	 */
	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

}
