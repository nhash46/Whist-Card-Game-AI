package properties;

import strategies.CardSelectionStrategyFactory;
import strategies.ICardSelectionStrategy;

public class SmartProperties extends CardGameProperties{

	public SmartProperties() {
		nbPlayers = 4;			//Confirm these values with the peeps in charge
		nbStartCards = 13;		//Confirm these values with the peeps in charge
		winningScore = 20;		//Confirm these values with the peeps in charge
		playerStrategies = new ICardSelectionStrategy[nbPlayers];
		
		selectionStrategyFactory = new CardSelectionStrategyFactory();
		playerStrategies[0] = selectionStrategyFactory.getCardSelectionStrategy("SMART");
		playerStrategies[1] = selectionStrategyFactory.getCardSelectionStrategy("SMART");
		playerStrategies[2] = selectionStrategyFactory.getCardSelectionStrategy("SMART");
		playerStrategies[3] = selectionStrategyFactory.getCardSelectionStrategy("SMART");
		
		roundInfo = new CardRoundProperties(nbPlayers);
	}
	
	
}
