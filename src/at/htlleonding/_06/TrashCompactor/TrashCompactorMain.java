package at.htlleonding._06.TrashCompactor;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

public class TrashCompactorMain extends MainBaseClass<InputPair> {
	@Override
	protected IUtils<InputPair> getUtils() {
		return new TrashCompactorUtils();
	}
}
