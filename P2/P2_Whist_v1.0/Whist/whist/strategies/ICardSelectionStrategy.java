package strategies;

import ch.aplu.jcardgame.*;
import properties.CardGameProperties.Suit;

public interface ICardSelectionStrategy {
	
	public Card selectCard(Hand hand, Suit lead, Suit trump);
	
}
