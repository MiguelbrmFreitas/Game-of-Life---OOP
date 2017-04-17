package mvc.model;

import observer.Observer;

public class Statistics implements Observer {

	private GameEngine engine;
	
	private int revivedCells;
	private int killedCells;
	
	public Statistics(GameEngine engine) {
		engine.register(this);
		this.engine = engine;
	}
	
	@Override
	public void update() {
		revivedCells = engine.getRevivedCells();
		killedCells = engine.getKilledCells();
	}

	public String getMessage() {
		StringBuilder message = new StringBuilder("Revived cells: " + revivedCells + '\n');
		message.append("Killed cells: " + killedCells);
		return message.toString();
	}

}
