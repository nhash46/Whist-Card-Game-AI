package properties;

import strategies.CardSelectionStrategyFactory;
import strategies.ICardSelectionStrategy;

public class LegalProperties extends CardGameProperties{

		public LegalProperties() {
			nbPlayers = 4;			//Confirm these values with the peeps in charge
			nbStartCards = 4;		//Confirm these values with the peeps in charge
			winningScore = 6;		//Confirm these values with the peeps in charge
			playerStrategies = new ICardSelectionStrategy[nbPlayers];
			
			selectionStrategyFactory = new CardSelectionStrategyFactory();
			playerStrategies[0] = selectionStrategyFactory.getCardSelectionStrategy("LEGAL");
			playerStrategies[1] = selectionStrategyFactory.getCardSelectionStrategy("LEGAL");
			playerStrategies[2] = selectionStrategyFactory.getCardSelectionStrategy("LEGAL");
			playerStrategies[3] = selectionStrategyFactory.getCardSelectionStrategy("LEGAL");
		}
		
		
}
