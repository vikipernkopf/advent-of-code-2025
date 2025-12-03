package at.htlleonding._02.GiftShop;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

import java.util.List;

public class GiftShopMain extends MainBaseClass<List<LongPair>> {
    @Override
    protected IUtils<List<LongPair>> getUtils() {
        return new GiftShopUtils();
    }
}
