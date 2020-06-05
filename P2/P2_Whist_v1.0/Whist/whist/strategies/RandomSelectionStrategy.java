package strategies;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import properties.CardGameProperties;
import properties.CardRoundProperties;
import properties.CardGameProperties.Suit;
import properties.OriginalProperties;

public class RandomSelectionStrategy implements ICardSelectionStrategy{
	
	public RandomSelectionStrategy() {
		
	}
	
	// return random Card from Hand
	public Card selectCard(Hand hand, Suit lead, Suit trump){
		//CardRoundProperties properties = new CardRoundProperties();
		Random random = ThreadLocalRandom.current();
	    int x = random.nextInt(hand.getNumberOfCards());
	    return hand.get(x);
	}
	  
	  
}
