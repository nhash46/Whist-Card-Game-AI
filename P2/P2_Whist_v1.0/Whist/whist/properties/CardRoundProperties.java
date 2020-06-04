package properties;
import ch.aplu.jcardgame.Card;

public class CardRoundProperties {
	
	protected static int turnsTaken;
	protected static Card cardsPlayed[];
	protected static Card winningCard;
	
	public CardRoundProperties(int nbPlayers) {
		turnsTaken = 0;
		winningCard = null;
		
		cardsPlayed = new Card[nbPlayers];
		for(int i = 0; i < nbPlayers; i++) {
			cardsPlayed[i] = null;
		}
	}
	
	public CardRoundProperties() {
		
	}
	
	public int getTurnsTaken() {
		return turnsTaken;
	}
	
	public Card[] getCardsPlayed() {
		return cardsPlayed;
	}
	
	public void addTurnTaken() {
		turnsTaken = turnsTaken + 1;
	}
	
	public void resetTurnsTaken() {
		turnsTaken = 0;
	}
	
	public void setCardsPlayed(int turnsTaken, Card card) {
		cardsPlayed[turnsTaken] = card;
	}
	
	public void resetCardsPlayed() {
		for(int i = 0; i < cardsPlayed.length; i++) {
			cardsPlayed[i] = null;
		}
	}
	
	public void setWinningCard(Card newWinner) {
		winningCard = newWinner;
	}
	
	public Card getWinningCard() {
		return winningCard;
	}
	
	
}
