/**
 * Created by Dad on 4/18/2015.
 */
public class Maes {
    private int x;
    private int y;
    private boolean canMove;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public Maes(int xi, int yi){
        this.x=xi;
        this.y=yi;
        this.canMove=true;
    }
}
