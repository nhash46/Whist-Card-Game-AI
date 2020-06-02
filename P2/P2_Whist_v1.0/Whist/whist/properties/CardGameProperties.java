package properties;

import strategies.CardSelectionStrategyFactory;
import strategies.ICardSelectionStrategy;

public abstract class CardGameProperties {
	
	protected int nbPlayers;
	protected int nbStartCards;
	protected int winningScore;
	
	protected CardSelectionStrategyFactory selectionStrategyFactory;
	protected ICardSelectionStrategy playerStrategies[];
	
	protected CardRoundProperties roundInfo;
	
	public enum Suit
	{
		SPADES, HEARTS, DIAMONDS, CLUBS
	}
	
	public enum Rank
	{
		// Reverse order of rank importance (see rankGreater() below)
		// Order of cards is tied to card images
		ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO
	}
	
	
	public CardGameProperties() {
		
	}
	
	/*public CardGameProperties(int nbPlayers, int nbStartCards, int winningScore) {
		this.nbPlayers = nbPlayers;
		this.nbStartCards = nbStartCards;
		this.winningScore = winningScore; 
	}*/
	
	public ICardSelectionStrategy[] getPlayeStrategies() {
		return playerStrategies;
	}
}
