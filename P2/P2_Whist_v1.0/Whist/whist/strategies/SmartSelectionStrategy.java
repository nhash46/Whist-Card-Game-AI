package strategies;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import properties.CardGameProperties.*;
import properties.CardRoundProperties;

import java.util.ArrayList;

public class SmartSelectionStrategy implements ICardSelectionStrategy{
	
	public SmartSelectionStrategy() {
		
	}
	
	// return random Card from Hand
	public Card selectCard(Hand hand, Suit lead, Suit trump){
		
		int selected;
		CardRoundProperties properties = new CardRoundProperties();
		
		System.out.println("Turns taken in round before this smart player: " + properties.getTurnsTaken());
		System.out.println("Cards Played: ");
		for(int i = 0; i < properties.getTurnsTaken(); i++) {
			System.out.println(properties.getCardsPlayed()[i].toString());
		}
		
		ArrayList<Card> cardList = new ArrayList<Card>();
		// Get a list of legal cards
		if( lead == null ) {
			cardList = hand.getCardList();
		}
		else{
			for(int i = 0; i < hand.getNumberOfCards(); i++) {
				if( (hand.get(i).getSuit().toString().equals(lead.toString())) || 
						(hand.get(i).getSuit().toString().equals(trump.toString())) ) {
					cardList.add(hand.get(i));
				}
			}
		}
			
			
		
			// If can win, play a winner
		
			// If cannot win, play lowest legal card
			
			
		//If no legal cards, play card with lowest rank
		
		
		
		
		selected = selectLowest(hand);
		return hand.get(selected);
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
	
	
	
	public int selectLowest(Hand hand) {
		
		return ;
	}
	  
	  
}