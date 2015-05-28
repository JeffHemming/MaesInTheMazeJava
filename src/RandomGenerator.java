import java.util.Random;

/**
 * Created by Dad on 5/12/2015.
 */
public class RandomGenerator {
    public static int randomInt(int min, int max){
        Random gen=new Random();
        return gen.nextInt(max-min+1)+min;
    }
}
