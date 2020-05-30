package strategies;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import properties.CardGameProperties.Suit;

public class LegalSelectionStrategy implements ICardSelectionStrategy{
	
	public LegalSelectionStrategy() {
		
	}
	
	
	// Return card that is legally allowed to be played
	public Card selectCard(Hand hand, Suit lead, Suit trump) {
		System.out.println("here");
		if(lead == null) {
			System.out.println("lead == null");
		}
		if( lead != null ) {
			int pos;
			//Check if we can play a card from lead suit
			pos = hasSuit(hand, lead);	
			if( pos >= 0) {
				return hand.get(pos);
			}
			//Check if we can play a card from trump suit
			pos = hasSuit(hand, trump);
			if( pos >= 0) {
				return hand.get(pos);
			}
		}
		
		//If we cannot play either, play a random card
		Random random = ThreadLocalRandom.current();
	    int x = random.nextInt(hand.getNumberOfCards());
	    System.out.println();
	    return hand.get(x);
		
	}
	
	public int hasSuit(Hand hand, Suit suit) {

		int num_cards = hand.getNumberOfCards();
		String suitString = suit.toString();
		
		for(int i = 0; i < num_cards; i++) {
			String currSuitString = hand.get(i).getSuit().toString();
			if( currSuitString.equals(suitString) ) {
				return i;
			}
		}
		return -1;
	}
	
	

}
