package at.htlleonding._02.GiftShop;

import at.htlleonding.MainBaseClass;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GiftShopMain extends MainBaseClass {
    public final int day;

    public GiftShopMain() {
        this.day = getDay();
    }

    @Override
    public void runPartOne(Path path) {
        List<LongPair> values;

        try {
            values = GiftShopUtils.parseInput(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long sum = GiftShopUtils.addAllInvalidIds(values);

        System.out.println(sum);
    }

    @Override
    public void runPartTwo(Path path) {
        List<LongPair> values;

        try {
            values = GiftShopUtils.parseInput(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long sum = GiftShopUtils.addAllInvalidIds2(values);

        System.out.println(sum);
    }
}
