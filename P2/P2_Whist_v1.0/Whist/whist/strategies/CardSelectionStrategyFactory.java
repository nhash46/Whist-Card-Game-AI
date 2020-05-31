package strategies; 

public class CardSelectionStrategyFactory {
	
	public CardSelectionStrategyFactory(){
		
	}
	
	public ICardSelectionStrategy getCardSelectionStrategy(String strategyType) {

		if(strategyType.equals("ORIGINAL")){
			return new RandomSelectionStrategy();
		}
		
		if(strategyType.equals("HUMAN")){
			return new HumanSelectionStrategy();
		}
		
		if(strategyType.equals("LEGAL")){
			return new LegalSelectionStrategy();
		}
		
		
		return null;
	}

}
