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
		
		

		// If a lead suit exists
		/*if(lead != null) {
			
			// If we can play a lead
			if( hasSuit(hand, lead) == true ) {
				
				// If maxLead() is a winner, play it
				
				// If maxLead() is not a winner, play minLead()
				
			}
			
			// If we can play a trump
			if( hasSuit(hand, trump) == true ) {
				
				// If maxTrump() is a winner, play it
				
				// If maxTrump() is not a winner, play minTrump()
				
			}
			
			if( We have to play a burner card ) {
				Card selectedCard = selectLowest(hand);
				return hand.getCard(selectedCard.getSuit(), selectedCard.getRank());
			}
			
		}
		// Else we look to trump suit
		else {
			
			// If we can play a trump
			if( hasSuit(hand, trump) == true ) {
				
				// If maxTrump() is a winner, play it
				
				// If maxTrump() is not a winner, play minTrump()
				
			}
		
			if( We are starting, want to play a max card ) {
				//Play a max card, any suit
			}
			
		}
		
		if( We have to play a burner card ) {
			Card selectedCard = selectLowest(hand);
			return hand.getCard(selectedCard.getSuit(), selectedCard.getRank());
		}*/
		
		
		Card selectedCard = selectLowest(hand);

		
		return hand.getCard(selectedCard.getSuit(), selectedCard.getRank());
	}
	
	
	
	//Play card with lowest rank and in most populated suit
	public Card selectLowest(Hand hand) {

		ArrayList<Card> cards = hand.getCardList();
		
		// Get Lowest Rank
		Rank lowestRank = null;
		Rank ranks[] = Rank.values();
		for(int i = ranks.length - 1; i >= 0; i--) {
			for(Card card : cards) {
				if(card.getRank().toString().equals(ranks[i].toString())) {
					lowestRank = ranks[i];
					break;
				}
			}
			if(lowestRank != null) {
				//System.out.println("Lowest Rank in hand: " + lowestRank);
				break;
			}
		}
		
		// Get most populated suit that has the lowest rank
		Suit suits[] = Suit.values();
		Suit mostPopulated = null;
		int currMaxPopulation = 0;
		int currPopulation;
		boolean applicible;
		for(int i = 0; i < suits.length; i++) {
			currPopulation = 0;
			applicible = false;
			for(Card card : cards) {
				if(card.getSuit().toString().equals(suits[i].toString())) {
					currPopulation++;
					if( card.getRank().toString().equals(lowestRank.toString()) ) {
						applicible = true;
					}
				}
			}
			if(currPopulation > currMaxPopulation) {
				if(applicible == true) {
					currMaxPopulation = currPopulation;
					mostPopulated = suits[i];
				}
			}
		}
		
		Card selectedCard = hand.getCard(mostPopulated, lowestRank);
		System.out.println("Suit: "+ mostPopulated + " Rank: " +lowestRank);
		System.out.println(selectedCard);
		return selectedCard;
	}
	
	
	// Gets the minimum ranked card from a specified suit from a given hand
	public Card minSuit(Hand hand, Suit suit) {
		
		Card selected;
		int selectedIndex = 0;
		int currMin = 0;
		ArrayList<Card> suitCards = hand.getCardsWithSuit(suit);
		for(int i = 1; i < suitCards.size(); i++) {
			// if i is less than currentMin (reverse order)
			if(suitCards.get(i).getRankId() > suitCards.get(currMin).getRankId()) {
				currMin = i;
			}
		}
		selected = suitCards.get(currMin);
		
		return selected;
	}
	
	
	// Gets the maximum ranked card from a specified suit from a given hand
	public Card maxSuit(Hand hand, Suit suit) {
		
		ArrayList<Card> suitCardList =  hand.getCardsWithSuit(suit);
		
		Card maxCard = suitCardList.get(0);
		for (int i = 1; i < suitCardList.size(); i++) {
			if ( maxCard.getRankId() < suitCardList.get(i).getRankId()) {
				maxCard = suitCardList.get(i);
			}
		}
	
		return maxCard;
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
	
	
	// Returns true if incoming wins over currWinner 
	public boolean isWinner(Card incoming, Card currWinner, Suit lead, Suit trump) {
		
		// card2 is lead
		if( currWinner.getSuit().toString().equals(lead.toString()) ) {
			
			// card2 is lead and trump
			if( currWinner.getSuit().toString().equals(trump.toString()) ) {
				
				if( incoming.getSuit().toString().equals(trump.toString()) ) {
					if( incoming.getRankId() < currWinner.getRankId() ) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
				
			}
			
			if( incoming.getSuit().toString().equals(trump.toString()) ) {
				return true;
			}
			else if( incoming.getSuit().toString().equals(lead.toString()) ) {
				if( incoming.getRankId() < currWinner.getRankId() ) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
				
		}
		
		// card2 is trump
		else if( currWinner.getSuit().toString().equals(trump.toString()) ) {
			
			if( incoming.getSuit().toString().equals(trump.toString()) ) {
				if( incoming.getRankId() < currWinner.getRankId() ) {
					return false;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
			
		}
		
		// card2 is neither lead or trump
		else {
			if( incoming.getSuit().toString().equals(lead.toString()) 
					|| incoming.getSuit().toString().equals(trump.toString())) {
				return true;
			}
			else {
				if( incoming.getRankId() < currWinner.getRankId() ) {
					return true;
				}
				else {
					return false;
				}
			}

		}
	  
	  return false;
}