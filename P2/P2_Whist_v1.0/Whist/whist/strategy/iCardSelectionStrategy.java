package strategy;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public interface iCardSelectionStrategy {
	
	public Card selectCard(Hand hand);

}
