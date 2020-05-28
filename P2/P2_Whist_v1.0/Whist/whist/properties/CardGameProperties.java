package properties;

import strategies.CardSelectionStrategyFactory;
import strategies.ICardSelectionStrategy;

public abstract class CardGameProperties {
	
	protected int nbPlayers;
	protected int nbStartCards;
	protected int winningScore;
	
	protected CardSelectionStrategyFactory selectionStrategyFactory;
	protected ICardSelectionStrategy playerStrategies[];
	
	public CardGameProperties() {
		
	}
	
	public CardGameProperties(int nbPlayers, int nbStartCards, int winningScore) {
		this.nbPlayers = nbPlayers;
		this.nbStartCards = nbStartCards;
		this.winningScore = winningScore; 
	}
	
	public ICardSelectionStrategy[] getPlayeStrategies() {
		return playerStrategies;
	}
}
