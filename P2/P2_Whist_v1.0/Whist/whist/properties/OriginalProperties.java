package properties;

import strategy.iCardSelectionStrategy;
import strategy.RandomSelectionStrategy;

public class OriginalProperties extends cardgameproperties {
	
	public OriginalProperties() {
		this.nbPlayers = 4;
		this.nbStartCards = 13;
		this.winningScore = 11; //confirmLater
		
		playerStrategies = new iCardSelectionStrategy[nbPlayers];
		//playerStrategies[0] = new HumanSelectionStrategy();
		playerStrategies[1] = new RandomSelectionStrategy();
		playerStrategies[2] = new RandomSelectionStrategy();
		playerStrategies[3] = new RandomSelectionStrategy();
		
		
	}
	

}
