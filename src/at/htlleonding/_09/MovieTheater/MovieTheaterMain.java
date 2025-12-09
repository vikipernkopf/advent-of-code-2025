package at.htlleonding._09.MovieTheater;

import at.htlleonding.IUtils;
import at.htlleonding.MainBaseClass;

public class MovieTheaterMain extends MainBaseClass<int[][]> {
    @Override
    public IUtils<int[][]> getUtils() {
        return new MovieTheaterUtils();
    }
}
