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
		
		ArrayList<Card> legalCards = new ArrayList<Card>();
		// Get a list of legal cards
		if( lead == null ) {
			legalCards = hand.getCardList();
		}
		else{
			for(int i = 0; i < hand.getNumberOfCards(); i++) {
				if( (hand.get(i).getSuit().toString().equals(lead.toString())) || 
						(hand.get(i).getSuit().toString().equals(trump.toString())) ) {
					legalCards.add(hand.get(i));
				}
			}
		}
		
		if( legalCards.isEmpty() ) {
			//If no legal cards, play card with lowest rank
			//selected = selectLowest(hand);
			//for now play any random card
		}
		else {
				// If we have a lead suit
				if( hasSuit(hand, lead) == true ) {
					// If maxLead() is a winner, play it
					// If maxLead() is not a winner, play minLead()
				}
				// else we have we have a trump suit
				else if( hasSuit(hand, trump) == true ) {
					// If maxTrump() is a winner, play it
					// If maxTrump() is not a winner, play minTrump()
				}
		}
		
		selected = 1;
		return hand.get(selected);
	}
	
	
	
	public int selectLowest(Hand hand) {
		
		return 0;
	}
	
	
	
	public boolean hasSuit(Hand hand, Suit suit) {

		int num_cards = hand.getNumberOfCards();
		String suitString = suit.toString();
		
		for(int i = 0; i < num_cards; i++) {
			String currSuitString = hand.get(i).getSuit().toString();
			if( currSuitString.equals(suitString) ) {
				return true;
			}
		}
		return false;
	}
	
	
	public Card findWinner(Card[] cardsPlayed, Suit lead, Suit trump) {
		
		Card currWinner = null;
		int cardsPlayedLen = 0;
		while(cardsPlayed[cardsPlayedLen] != null) {
			
			if(currWinner == null) {
				currWinner = cardsPlayed[cardsPlayedLen];
				continue;
			}
			
			if( cardsPlayed[cardsPlayedLen].getSuit().toString().equals(trump.toString()) ) {
				// Lower int is better for rank
				if( cardsPlayed[cardsPlayedLen].getRankId() < currWinner.getRankId() ) {
					currWinner = cardsPlayed[cardsPlayedLen];
				}
			}
			
			if( cardsPlayed[cardsPlayedLen].getSuit().toString().equals(lead.toString()) ) {
				if(currWinner.getSuit().toString().equals(trump.toString())) {
					continue;
				}
				if( cardsPlayed[cardsPlayedLen].getRankId() < currWinner.getRankId() ) {
					currWinner = cardsPlayed[cardsPlayedLen];
				}
			}
			cardsPlayedLen++;
		}
		
		return currWinner;
	}
	  
	  
}