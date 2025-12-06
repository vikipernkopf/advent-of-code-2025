package at.htlleonding._06.TrashCompactor;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

public class TrashCompactorMain extends MainBaseClass<CalculationPair[]> {
	@Override
	protected IUtils<CalculationPair[]> getUtils() {
		return new TrashCompactorUtils();
	}
}
