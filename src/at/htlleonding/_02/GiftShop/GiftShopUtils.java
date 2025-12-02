package at.htlleonding._02.GiftShop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GiftShopUtils {
    public static List<LongPair> parseInput(Path path) throws IOException {
        String text = Files.readString(path);

        String[] lines = text.split(",");
        List<LongPair> values = new ArrayList<>();

        for (String line : lines) {
            String[] ids = line.split("-");
            values.add(new LongPair(Long.parseLong(ids[0]), Long.parseLong(ids[1])));
        }

        return values;
    }

    public static long addAllInvalidIds(List<LongPair> values) {
        long sum = 0;

        for (LongPair pair : values) {
            for (long i = pair.start(); i <= pair.end(); i++) {
                long powerOfTen = (long) Math.pow(10, (double) getDigits(i) / 2);
                long secondHalf = i % powerOfTen;
                long firstHalf = (i - secondHalf) / powerOfTen;

                if (firstHalf == secondHalf && getDigits(i) % 2 == 0) {
                    sum += i;
                }
            }
        }

        return sum;
    }

    public static long addAllInvalidIds2(List<LongPair> values) {
        long sum = 0;

        for (LongPair pair : values) {
            for (long i = pair.start(); i <= pair.end(); i++) {
                int digits = getDigits(i);
                boolean idInvalid = false;

                for (int digitsPerPart = 1; digitsPerPart < digits & !idInvalid; digitsPerPart++) {
                    if (digits % digitsPerPart == 0) {
                        /*int partsNum = digits / digitsPerPart;
                        long product = (long) Math.pow(10, (double) getDigits(i) / partsNum);
                        long[] parts = new long[partsNum];
                        long current = i;

                        for (int partIndex = 0; partIndex < partsNum; partIndex++) {
                            parts[partIndex] = current % (long) Math.pow(product, partsNum - (partIndex + 1));
                            current = (current - parts[partIndex]) / product;
                        }*/

                        int partsNum = digits / digitsPerPart;
                        long[] parts = new long[partsNum];
                        String str = Long.toString(i);

                        for (int j = 0; j < partsNum; j++) {
                            parts[j] = Long.parseLong(str.substring(j * digitsPerPart, j * digitsPerPart + digitsPerPart));
                        }

                        if (partsEqual(parts)) {
                            sum += i;

                            idInvalid = true;
                        }
                    }
                }
            }
        }

        return sum;
    }

    private static boolean partsEqual(long[] values) {
        for (int i = 1; i < values.length; i++) {
            if (values[i] != values[i - 1]) {
                return false;
            }
        }

        return true;
    }

    private static int getDigits(long number) {
        return (int) (Math.log10(number) + 1);
    }
}
