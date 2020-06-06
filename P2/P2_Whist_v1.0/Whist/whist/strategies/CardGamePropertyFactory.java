package strategies;

import properties.CardGameProperties;
import properties.LegalProperties;
import properties.OriginalProperties;
import properties.SmartProperties;

public class CardGamePropertyFactory {
	
	public CardGamePropertyFactory(){
		
	}
	
	public CardGameProperties getCardGamePropertyType(String propertyType) {

		if(propertyType.equals("ORIGINAL")){
			return new OriginalProperties();
		}
		
		if(propertyType.equals("LEGAL")){
			return new LegalProperties();
		}
		
		if(propertyType.equals("SMART")){
			return new SmartProperties();
		}

		
		return null;
	}

}