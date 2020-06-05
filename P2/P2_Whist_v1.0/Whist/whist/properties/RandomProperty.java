package properties;

import java.util.Random;

public class RandomProperty extends CardGameProperties {
	
	protected static final Random random = new Random(30006);
	
	public RandomProperty(){
		
	}
	
	public Random getRandom() {
		return random;
	}
	
}
