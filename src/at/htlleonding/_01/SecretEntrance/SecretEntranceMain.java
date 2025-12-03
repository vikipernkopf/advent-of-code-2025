package at.htlleonding._01.SecretEntrance;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

import java.util.List;

public class SecretEntranceMain extends MainBaseClass<List<ValuePair>> {
    @Override
    protected IUtils<List<ValuePair>> getUtils() {
        return new SecretEntranceUtils();
    }
}
