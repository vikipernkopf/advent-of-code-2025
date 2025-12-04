package at.htlleonding._04.PrintingDepartment;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

public class PrintingDepartmentMain extends MainBaseClass<boolean[][]> {
    @Override
    protected IUtils<boolean[][]> getUtils() {
        return new PrintingDepartmentUtils();
    }
}
