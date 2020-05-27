package strategy;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class RandomSelectionStrategy implements iCardSelectionStrategy {
	
	static final Random random = ThreadLocalRandom.current(); 
	
	 public Card selectCard(Hand hand){
	      int x = random.nextInt(hand.getNumberOfCards());
	      return hand.get(x);
	  }
	

}
