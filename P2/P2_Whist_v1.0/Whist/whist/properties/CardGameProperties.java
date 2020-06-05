package properties;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import strategies.CardSelectionStrategyFactory;
import strategies.ICardSelectionStrategy;

public abstract class CardGameProperties {
	
	protected int nbPlayers;
	protected int nbStartCards;
	protected int winningScore;
	
	protected CardSelectionStrategyFactory selectionStrategyFactory;
	protected ICardSelectionStrategy playerStrategies[];
	
	protected CardRoundProperties roundInfo;
	
	protected static final Random random = new Random(30006);
	public CardGameProperties(){

	}
	
	
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
	
	
	public ICardSelectionStrategy[] getPlayeStrategies() {
		return playerStrategies;
	}
	
	
	
	public int getNumPlayers() {
		return nbPlayers;
	}
	
	public int getNumStartCards() {
		return nbStartCards;
	}
	
	public int getWinningScore() {
		return winningScore;
	}
	
	public CardRoundProperties getRoundInfo() {
		return roundInfo;
	}
	
	public Random getRandom() {
		return random;
	}
	
}
