package properties;
import strategy.iCardSelectionStrategy; 

public abstract class cardgameproperties {
	protected  int nbPlayers;
	protected  int nbStartCards;
	protected  int winningScore;
	protected iCardSelectionStrategy playerStrategies[];
	/* public cardStartegies playerStrategies[] */
	
	/*
	public cardgameproperties(int nbPlayers,int  nbStartCards,int winningScore) {
		this.nbPlayers = nbPlayers;
		this.nbStartCards = nbStartCards;
		this.winningScore = winningScore;
	}
	*/
	
	public cardgameproperties() {
	}

}
