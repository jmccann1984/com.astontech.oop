package common.helpers;

/**
 * Created by Joshua.McCann on 6/26/2017.
 */
public class MathHelper extends CommonHelper {

    public static final double E = 2.71;
    public static final double PI = 3.14;

    public static int square(int val) {
        return val * val;
    }

    public static int biggerInt(int int1, int int2){
        if (int1>int2)
            return int1;
        else
            return int2;
    }

    public static int smallerInt(int int1, int int2){
        if (int1<int2)
            return int2;
        else
            return int1;
    }
}
