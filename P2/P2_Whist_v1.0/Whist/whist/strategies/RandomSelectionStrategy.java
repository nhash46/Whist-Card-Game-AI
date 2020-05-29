package strategies;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import properties.CardGameProperties.Suit;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSelectionStrategy implements ICardSelectionStrategy{
	
	public RandomSelectionStrategy() {
		
	}
	
	// return random Card from Hand
	public Card selectCard(Hand hand, Suit lead, Suit trump){
		Random random = ThreadLocalRandom.current();
	    int x = random.nextInt(hand.getNumberOfCards());
	    return hand.get(x);
	}
	  
	  
}
