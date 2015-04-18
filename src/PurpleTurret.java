/**
 * Created by Dad on 4/18/2015.
 */
public class PurpleTurret {
    private int x;
    private int y;
    private int face;
    private int upMax;
    private int downMax;
    private int leftMax;
    private int rightMax;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getFace() {
        return face;
    }
    public int getUpMax() {
        return upMax;
    }
    public int getDownMax() {
        return downMax;
    }
    public int getLeftMax() {
        return leftMax;
    }
    public int getRightMax() {
        return rightMax;
    }
    public void setUpMax(int upMax) {
        this.upMax = upMax;
    }
    public void setDownMax(int downMax) {
        this.downMax = downMax;
    }
    public void setLeftMax(int leftMax) {
        this.leftMax = leftMax;
    }
    public void setRightMax(int rightMax) {
        this.rightMax = rightMax;
    }

    public void turn(){
        if(this.face==4)this.face=1;
        else this.face++;
    }
    public PurpleTurret(int xi, int yi, int f){
        this.x=xi;
        this.y=yi;
        this.face=f;
    }
}
