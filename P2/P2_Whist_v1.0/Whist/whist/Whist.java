// Whist.java

import properties.CardGameProperties;
import properties.CardGameProperties.Suit;
import properties.CardGameProperties.Rank;

import properties.OriginalProperties;
import properties.RandomProperty;
import properties.LegalProperties;
import properties.SmartProperties;
import strategies.CardGamePropertyFactory;
import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Properties;

@SuppressWarnings("serial")
public class Whist extends CardGame {
  
  final String trumpImage[] = {"bigspade.gif","bigheart.gif","bigdiamond.gif","bigclub.gif"};

  //static final Random random = ThreadLocalRandom.current();
  //static final Random random = new Random(30006);
  
  // return random Enum value
  public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
      int x = random.getRandom().nextInt(clazz.getEnumConstants().length);
      return clazz.getEnumConstants()[x];
  }
  
  // return random Card from Hand
  public static Card randomCard(Hand hand){
      int x = random.getRandom().nextInt(hand.getNumberOfCards());
      return hand.get(x);
  }
 
  // return random Card from ArrayList
  public static Card randomCard(ArrayList<Card> list){
      int x = random.getRandom().nextInt(list.size());
      return list.get(x);
  }
  
  public boolean rankGreater(Card card1, Card card2) {
	  return card1.getRankId() < card2.getRankId(); // Warning: Reverse rank order of cards (see comment on enum)
  }
	 
  private final String version = "1.0";
  private final int handWidth = 400;
  private final int trickWidth = 40;
  private final Deck deck = new Deck(Suit.values(), Rank.values(), "cover");
  private final Location[] handLocations = {
			  new Location(350, 625),
			  new Location(75, 350),
			  new Location(350, 75),
			  new Location(625, 350)
	  };
  private final Location[] scoreLocations = {
			  new Location(575, 675),
			  new Location(25, 575),
			  new Location(575, 25),
			  new Location(650, 575)
	  };
  private Actor[] scoreActors = {null, null, null, null };
  private final Location trickLocation = new Location(350, 350);
  private final Location textLocation = new Location(350, 450);
  private final int thinkingTime = 2000;
  private Hand[] hands;
  private Location hideLocation = new Location(-500, - 500);
  private Location trumpsActorLocation = new Location(50, 50);
  private boolean enforceRules=false;

  public void setStatus(String string) { setStatusText(string); }
  
private int[] scores = new int[properties.getNumPlayers()];

Font bigFont = new Font("Serif", Font.BOLD, 36);

private void initScore() {
	 for (int i = 0; i < properties.getNumPlayers(); i++) {
		 scores[i] = 0;
		 scoreActors[i] = new TextActor("0", Color.WHITE, bgColor, bigFont);
		 addActor(scoreActors[i], scoreLocations[i]);
	 }
  }

private void updateScore(int player) {
	removeActor(scoreActors[player]);
	scoreActors[player] = new TextActor(String.valueOf(scores[player]), Color.WHITE, bgColor, bigFont);
	addActor(scoreActors[player], scoreLocations[player]);
}

private Card selected;

private void initRound() {
		 hands = deck.dealingOut(properties.getNumPlayers(), properties.getNumStartCards()); // Last element of hands is leftover cards; these are ignored
		 for (int i = 0; i < properties.getNumPlayers(); i++) {
			   hands[i].sort(Hand.SortType.SUITPRIORITY, true);
		 }
		 
		 // graphics
	    RowLayout[] layouts = new RowLayout[properties.getNumPlayers()];
	    for (int i = 0; i < properties.getNumPlayers(); i++) {
	      layouts[i] = new RowLayout(handLocations[i], handWidth);
	      layouts[i].setRotationAngle(90 * i);
	      // layouts[i].setStepDelay(10);
	      hands[i].setView(this, layouts[i]);
	      hands[i].setTargetArea(new TargetArea(trickLocation));
	      hands[i].draw();
	    }
//	    for (int i = 1; i < nbPlayers; i++)  // This code can be used to visually hide the cards in a hand (make them face down)
//	      hands[i].setVerso(true);
	    // End graphics
 }

private Optional<Integer> playRound() {  // Returns winner, if any
	// Select and display trump suit
		final Suit trumps = randomEnum(Suit.class);
		final Actor trumpsActor = new Actor("sprites/"+trumpImage[trumps.ordinal()]);
	    addActor(trumpsActor, trumpsActorLocation);
	// End trump suit
	Hand trick;
	int winner;
	//Card winningCard;
	Suit lead = null;
	int nextPlayer = random.getRandom().nextInt(properties.getNumPlayers()); // randomly select player to lead for this round
	for (int i = 0; i < properties.getNumStartCards(); i++) {
		trick = new Hand(deck);
    	selected = null;
    	lead = null;
    	properties.getRoundInfo().resetTurnsTaken();
    	properties.getRoundInfo().resetCardsPlayed();
        if (0 == nextPlayer) {  // Select lead depending on player type
    		setStatus("Player 0 double-click on card to lead.");
        } else {
    		setStatusText("Player " + nextPlayer + " thinking...");
            delay(thinkingTime);
        }
        selected = properties.getPlayeStrategies()[nextPlayer].selectCard(hands[nextPlayer], lead, trumps);
        properties.getRoundInfo().setCardsPlayed(properties.getRoundInfo().getTurnsTaken(), selected);
        properties.getRoundInfo().addTurnTaken();
        // Lead with selected card
	        trick.setView(this, new RowLayout(trickLocation, (trick.getNumberOfCards()+2)*trickWidth));
			trick.draw();
			selected.setVerso(false);
			// No restrictions on the card being lead
			lead = (Suit) selected.getSuit();
			selected.transfer(trick, true); // transfer to trick (includes graphic effect)
			winner = nextPlayer;
			properties.getRoundInfo().setWinningCard(selected);
			//winningCard = selected;
		// End Lead
		for (int j = 1; j < properties.getNumPlayers(); j++) {
			if (++nextPlayer >= properties.getNumPlayers()) nextPlayer = 0;  // From last back to first
			selected = null;
	        if (0 == nextPlayer) {
	    		setStatus("Player 0 double-click on card to follow.");
	        } else {
		        setStatusText("Player " + nextPlayer + " thinking...");
		        delay(thinkingTime);
	        }
	        selected = properties.getPlayeStrategies()[nextPlayer].selectCard(hands[nextPlayer], lead, trumps);
	        properties.getRoundInfo().setCardsPlayed(properties.getRoundInfo().getTurnsTaken(), selected);
	        properties.getRoundInfo().addTurnTaken();
	        // Follow with selected card
		        trick.setView(this, new RowLayout(trickLocation, (trick.getNumberOfCards()+2)*trickWidth));
				trick.draw();
				selected.setVerso(false);  // In case it is upside down
				// Check: Following card must follow suit if possible
					if (selected.getSuit() != lead && hands[nextPlayer].getNumberOfCardsWithSuit(lead) > 0) {
						 // Rule violation
						 String violation = "Follow rule broken by player " + nextPlayer + " attempting to play " + selected;
						 System.out.println(violation);
						 if (enforceRules) 
							 try {
								 throw(new BrokeRuleException(violation));
								} catch (BrokeRuleException e) {
									e.printStackTrace();
									System.out.println("A cheating player spoiled the game!");
									System.exit(0);
								}  
					 }
				// End Check
				 selected.transfer(trick, true); // transfer to trick (includes graphic effect)
				 System.out.println("winning: suit = " + properties.getRoundInfo().getWinningCard().getSuit() + ", rank = " + properties.getRoundInfo().getWinningCard().getRankId());
				 System.out.println(" played: suit = " +    selected.getSuit() + ", rank = " +    selected.getRankId());
				 if ( // beat current winner with higher card
					 (selected.getSuit() == properties.getRoundInfo().getWinningCard().getSuit() && rankGreater(selected, properties.getRoundInfo().getWinningCard())) ||
					  // trumped when non-trump was winning
					 (selected.getSuit() == trumps && properties.getRoundInfo().getWinningCard().getSuit() != trumps)) {
					 System.out.println("NEW WINNER");
					 winner = nextPlayer;
					 properties.getRoundInfo().setWinningCard(selected);
				 }
			// End Follow
		}
		delay(600);
		trick.setView(this, new RowLayout(hideLocation, 0));
		trick.draw();		
		nextPlayer = winner;
		setStatusText("Player " + nextPlayer + " wins trick.");
		scores[nextPlayer]++;
		updateScore(nextPlayer);
		if (properties.getWinningScore() == scores[nextPlayer]) return Optional.of(nextPlayer);
	}
	removeActor(trumpsActor);
	return Optional.empty();
}

  public Whist()
  {
    super(700, 700, 30);
    setTitle("Whist (V" + version + ") Constructed for UofM SWEN30006 with JGameGrid (www.aplu.ch)");
    setStatusText("Initializing...");
    initScore();
    Optional<Integer> winner;
    do { 
      initRound();
      winner = playRound();
    } while (!winner.isPresent());
    addActor(new Actor("sprites/gameover.gif"), textLocation);
    setStatusText("Game over. Winner is player: " + winner.get());
    refresh();
  }
  
  public static CardGameProperties properties;
  public static RandomProperty random;
  
  public static void main(String[] args) throws IOException
  {	
	  // File file = new File("whist.properties");
	  // System.out.println(file.getAbsolutePath());
	  // properties = new OriginalProperties();
	  
	  random = new RandomProperty();
	  CardGamePropertyFactory propertyFactory = new CardGamePropertyFactory();
	  
	  Properties propertyFileInfo = new Properties();
	  propertyFileInfo.setProperty("Property", "ORIGINAL");
	  // Read properties
	  FileReader inStream = null;
	  try {
		  inStream = new FileReader("whist.properties");
		  propertyFileInfo.load(inStream);
	  } finally {
		  if (inStream != null) {
            inStream.close();
		  }
	  }
	  
	  String propertyType = propertyFileInfo.getProperty("Property");
	  System.out.println("Property Type: " + propertyType);
	  properties = propertyFactory.getCardGamePropertyType(propertyType);
	  System.out.println("properties: " + properties);
	  
	  // System.out.println("Working Directory = " + System.getProperty("user.dir"));
	  new Whist();
  }

}