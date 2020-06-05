package strategies;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import properties.RandomProperty;
import properties.CardGameProperties.Suit;



public class RandomSelectionStrategy implements ICardSelectionStrategy{
	
	public RandomSelectionStrategy() {
		
	}
	
	// return random Card from Hand
	public Card selectCard(Hand hand, Suit lead, Suit trump){
		RandomProperty random = new RandomProperty();
	    int x = random.getRandom().nextInt(hand.getNumberOfCards());
	    return hand.get(x);
	}
	  
	  
}
