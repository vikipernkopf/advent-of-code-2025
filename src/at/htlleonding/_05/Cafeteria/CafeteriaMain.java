package at.htlleonding._05.Cafeteria;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

public class CafeteriaMain extends MainBaseClass<ArrayPair> {
	@Override
	protected IUtils<ArrayPair> getUtils() {
		return new CafeteriaUtils();
	}
}
