package strategies;

import ch.aplu.jcardgame.*;
import properties.CardGameProperties.Suit;

public class HumanSelectionStrategy implements ICardSelectionStrategy{
	
	CardListener cardListener;
	Card selected;
	
	public HumanSelectionStrategy() {
		
	}

	public Card selectCard(Hand hand, Suit trumps, Suit lead) {

		//Set up human player to be able to select a card
		CardListener cardListener = new CardAdapter()
		{
		      public void leftDoubleClicked(Card card) { 
		    	  selected = card;
		    	  hand.setTouchEnabled(false);
		    	  }
		};
		hand.addCardListener(cardListener);
		hand.setTouchEnabled(true);
		
		selected = null;
		while (null == selected) {
			try {
	            Thread.sleep(100);
			} catch (Exception e) {
				System.out.println(e);
			}
		};
		
		return selected;
	}
	
}
