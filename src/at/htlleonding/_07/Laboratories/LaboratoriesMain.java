package at.htlleonding._07.Laboratories;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

public class LaboratoriesMain extends MainBaseClass<Field[][]> {
    @Override
    protected IUtils<Field[][]> getUtils() {
        return new LaboratoriesUtils();
    }
}
