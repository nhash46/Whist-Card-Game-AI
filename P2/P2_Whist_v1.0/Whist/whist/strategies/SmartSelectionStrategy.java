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
		
		/*ArrayList<Card> legalCards = new ArrayList<Card>();
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
		}*/
		
		/*if( legalCards.isEmpty() ) {
			//If no legal cards, play card with lowest rank
			//return selectLowest(hand);
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
		}*/
		
		Card selectedCard = selectLowest(hand);

		
		return hand.getCard(selectedCard.getSuit(), selectedCard.getRank());
	}
	
	
	
	public Card selectLowest(Hand hand) {
		
		ArrayList<Card> cards = hand.getCardList();
		
		// Get Lowest Rank
		Rank lowestRank = null;
		Rank ranks[] = Rank.values();
		for(int i = ranks.length - 1; i >= 0; i--) {
			System.out.println(ranks[i]);
			for(Card card : cards) {
				if(card.getRank().toString().equals(ranks[i].toString())) {
					lowestRank = ranks[i];
					break;
				}
			}
			if(lowestRank != null) {
				System.out.println("Lowest Rank in hand: " + lowestRank);
				break;
			}
		}
		
		// Get most populated suit that has the lowest rank
		Suit suits[] = Suit.values();
		Suit mostPopulated = null;
		int currMaxPopulation = 0;
		int currPopulation;
		for(int i = 0; i < suits.length; i++) {
			currPopulation = 0;
			for(Card card : cards) {
				if(card.getSuit().toString().equals(suits[i].toString())) {
					currPopulation++;
				}
			}
			if(currPopulation > currMaxPopulation) {
				currMaxPopulation = currPopulation;
				mostPopulated = suits[i];
			}
		}
		
		Card selectedCard = hand.getCard(mostPopulated, lowestRank);
		//System.out.println("Suit: "+ mostPopulated + " Rank: " +lowestRank);
		//System.out.println(selectedCard);
		return selectedCard;
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