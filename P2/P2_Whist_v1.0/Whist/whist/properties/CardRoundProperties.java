package properties;
import ch.aplu.jcardgame.Card;

public class CardRoundProperties {
	
	public int turnsTaken;
	public Card cardsPlayed[];
	
	public CardRoundProperties(int nbPlayers) {
		turnsTaken = 0;
		
		cardsPlayed = new Card[nbPlayers];
		for(int i = 0; i < nbPlayers; i++) {
			cardsPlayed[i] = null;
		}
	}
	
}
