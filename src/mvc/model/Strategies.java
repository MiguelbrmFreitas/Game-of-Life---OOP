package mvc.model;

import mvc.model.strategies.*;

/**
 * Responsável por instanciar e escolher as estratégias;
 * @author Miguel
 *
 */

public enum Strategies {
	CONWAY {
		public GameStrategy getGameStrategy() {
			return new Conway();
		}
	},
	HIGH_LIFE {		
		public GameStrategy getGameStrategy() {
			return new HighLife();
		}
	},
	SEEDS {
		public GameStrategy getGameStrategy() {
			return new Seeds();
		}
	},
	DAY_NIGHT {
		public GameStrategy getGameStrategy() {
			return new DayNight();
		}
	},
	LIFE_WO_DEATH {
		public GameStrategy getGameStrategy() {
			return new LifeWithoutDeath();
		}
	},
	BOLHA{
		public GameStrategy getGameStrategy() {
			return new Bolha();
		}
	};
	
	public abstract GameStrategy getGameStrategy();
	
	public static GameStrategy fabricate(Strategies strategy) {		
		return strategy.getGameStrategy();
	}
	
	public static Strategies getStrategy(int strategy) throws InvalidStrategyException {
		switch (strategy) {
			case 1: return CONWAY;
			case 2: return HIGH_LIFE;
			case 3: return SEEDS;
			case 4: return DAY_NIGHT;
			case 5: return LIFE_WO_DEATH;
			case 6: return BOLHA;
		}
		throw new InvalidStrategyException();
	}
	
	public static boolean validateStrategy(int strategy) {
		int amountOfStrategies = Strategies.values().length;
		
		return (strategy > 0) && (strategy <= amountOfStrategies);
	}
}
