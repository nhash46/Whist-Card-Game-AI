package strategies;

import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public interface ICardSelectionStrategy {
	
	public Card selectCard(Hand hand);
	
}
