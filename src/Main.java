import java.io.*;
import java.util.Scanner;

/**
 * Created by Dad on 4/18/2015.
 */
public class Main {

    public static int character=4;
    public static LevelGUI lg;
    public static int currentLevel=1;
    public static int[] maxLevel=new int[8];
    public static String saveName;
    public static TitleScreen ts;
    public static CharacterSelect cs;
    public static String levelfile="";

    public static void main(String [] args) throws IOException {

        ts=new TitleScreen();
        //currentLevel=maxLevel;
        //currentLevel=9;
        //lg=new LevelGUI(currentLevel, character);

    }
}
