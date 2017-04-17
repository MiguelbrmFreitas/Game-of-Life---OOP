package mvc.model.strategies;

import mvc.model.GameStrategy;

public class HighLife extends GameStrategy {
	
	public boolean shouldKeepAlive(int i, int j) {
		return (
					engine.isCellAlive(i, j)
				) && (
					engine.numberOfNeighborhoodAliveCells(i, j) == 2 ||
					engine.numberOfNeighborhoodAliveCells(i, j) == 3 ||
					engine.numberOfNeighborhoodAliveCells(i, j) == 6
				);
	}
	
	public boolean shouldRevive(int i, int j) {
		return (
					!engine.isCellAlive(i, j)
				) && (
					(engine.numberOfNeighborhoodAliveCells(i, j) == 3) ||
					(engine.numberOfNeighborhoodAliveCells(i, j) == 6)
				);
	}

}
