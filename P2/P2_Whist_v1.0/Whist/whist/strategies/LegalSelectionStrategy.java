package strategies;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class LegalSelectionStrategy {
	
public LegalSelectionStrategy() {
		
	}
	
	// return random Card from Hand
	public Card selectCard(Hand hand){
		Random random = ThreadLocalRandom.current();
	    int x = random.nextInt(hand.getNumberOfCards());
	    return hand.get(x);
	}
	
}
