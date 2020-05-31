package strategies;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import properties.CardGameProperties.Suit;

public class LegalSelectionStrategy implements ICardSelectionStrategy{

	
public LegalSelectionStrategy() {
		
	}
	
	// return random legal Card from Hand
	public Card selectCard(Hand hand, Suit trumps, Suit lead){
		
		int i = 0;
		
		while(i < hand.getNumberOfCards()) {
			// check if they have a card that follows the lead
			if(hand.get(i).getSuit() == lead) {
				return hand.get(i);
			} 
			// check if they have a card that follows the trump
			else if (hand.get(i).getSuit() == trumps) {
				return hand.get(i);
			} 
			else {
				i++;
			}
		} 
		// else play a random card
		Random random = ThreadLocalRandom.current();
	    int x = random.nextInt(hand.getNumberOfCards());
	    return hand.get(x);
	}
	
}
