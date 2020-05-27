package properties;

import strategies.ICardSelectionStrategy;
import strategies.RandomSelectionStrategy;

public class OriginalProperties extends CardGameProperties{

		public OriginalProperties() {
			nbPlayers = 4;			//Confirm these values with the peeps in charge
			nbStartCards = 13;		//Confirm these values with the peeps in charge
			winningScore = 11;		//Confirm these values with the peeps in charge
			playerStrategies = new ICardSelectionStrategy[nbPlayers];
			//playerStrategies[0] = new HumanSelectionStrategy();
			playerStrategies[1] = new RandomSelectionStrategy();
			playerStrategies[2] = new RandomSelectionStrategy();
			playerStrategies[3] = new RandomSelectionStrategy();
		}
		
		
}
