package properties;

import strategies.CardSelectionStrategyFactory;
import strategies.ICardSelectionStrategy;

public abstract class CardGameProperties {
	
	public enum Suit
	  {
	    SPADES, HEARTS, DIAMONDS, CLUBS
	  }
	
	protected int nbPlayers;
	protected int nbStartCards;
	protected int winningScore;
	
	protected CardSelectionStrategyFactory selectionStrategyFactory;
	protected ICardSelectionStrategy playerStrategies[];
	
	public CardGameProperties() {
		
	}
	
	public ICardSelectionStrategy[] getPlayeStrategies() {
		return playerStrategies;
	}
	
	public int getNumberOfPlayers() {
		return nbPlayers;
	}
	
	public int getNumberOfStartCards() {
		return nbStartCards;
	}
	
	public int getWinningScore() {
		return winningScore;
	}
}
