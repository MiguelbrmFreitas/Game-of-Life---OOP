package tests;

import static org.junit.Assert.*;
import mvc.controller.GameController;
import mvc.model.GameEngine;
import mvc.model.Strategies;


import org.junit.Before;
import org.junit.Test;

public class GameOfLifeTests {

	private GameController controller = new GameController();
	private GameEngine engine = new GameEngine(10, 10);	
	
	@Before
	public void setUp() throws Exception {
		controller.setEngine(engine);
		controller.killAllCells();
	}
	
	@Test
	public void testMakeCellAlive() {
		assertEquals(0, controller.numberOfAliveCells());
		controller.makeCellAlive(4, 4);
		assertEquals(1, controller.numberOfAliveCells());
		controller.makeCellAlive(4, 5);
		controller.makeCellAlive(4, 3);
		assertEquals(3, controller.numberOfAliveCells());
	}
	
	@Test
	public void testMakeCellDead() {
		controller.makeCellAlive(4, 4);
		assertEquals(1, controller.numberOfAliveCells());
		controller.makeCellDead(4, 4);
		assertEquals(0, controller.numberOfAliveCells());
	}
	
	@Test
	public void testConwayStrategy() {
		controller.makeCellAlive(4, 3);
		controller.makeCellAlive(4, 4);
		controller.makeCellAlive(4, 5);
		controller.nextGeneration();
		assertTrue(controller.isCellAlive(3, 4));
		assertTrue(controller.isCellAlive(4, 4));
		assertTrue(controller.isCellAlive(5, 4));
		assertFalse(controller.isCellAlive(4, 3));
		assertFalse(controller.isCellAlive(4, 5));
	}
	
	@Test
	public void testSeedsStrategy() {
		controller.changeStrategy(Strategies.SEEDS);
		controller.makeCellAlive(4, 3);
		controller.makeCellAlive(4, 4);
		controller.makeCellAlive(4, 5);
		controller.nextGeneration();
		assertTrue(controller.isCellAlive(3, 3));
		assertTrue(controller.isCellAlive(3, 5));
		assertTrue(controller.isCellAlive(5, 3));
		assertTrue(controller.isCellAlive(5, 5));
		assertFalse(controller.isCellAlive(4, 3));
		assertFalse(controller.isCellAlive(4, 4));
		assertFalse(controller.isCellAlive(4, 5));
	}
	
	@Test
	public void testKillAllCells() {
		controller.makeCellAlive(4, 4);
		controller.killAllCells();
		assertEquals(0, controller.numberOfAliveCells());
	}
	
	@Test
	public void testEngine() {
		engine.makeCellAlive(4, 4);
		assertTrue(engine.isCellAlive(4, 4));
		engine.makeCellDead(4, 4);
		assertFalse(engine.isCellAlive(4, 4));
	}

}
