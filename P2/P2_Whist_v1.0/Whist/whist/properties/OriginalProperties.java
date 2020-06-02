package properties;

import strategies.CardSelectionStrategyFactory;
import strategies.ICardSelectionStrategy;

public class OriginalProperties extends CardGameProperties{

		public OriginalProperties() {
			nbPlayers = 4;			//Confirm these values with the peeps in charge
			nbStartCards = 13;		//Confirm these values with the peeps in charge
			winningScore = 11;		//Confirm these values with the peeps in charge
			playerStrategies = new ICardSelectionStrategy[nbPlayers];
			
			selectionStrategyFactory = new CardSelectionStrategyFactory();
			playerStrategies[0] = selectionStrategyFactory.getCardSelectionStrategy("HUMAN");
			playerStrategies[1] = selectionStrategyFactory.getCardSelectionStrategy("ORIGINAL");
			playerStrategies[2] = selectionStrategyFactory.getCardSelectionStrategy("ORIGINAL");
			playerStrategies[3] = selectionStrategyFactory.getCardSelectionStrategy("ORIGINAL");
			
			roundInfo = new CardRoundProperties(nbPlayers);
		}
		
		
}
