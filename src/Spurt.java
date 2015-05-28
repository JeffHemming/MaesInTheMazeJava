/**
 * Created by Dad on 5/26/2015.
 */
public class Spurt {
    private int x;
    private int y;
    private int state;
    private int type;

    public Spurt(int ix, int iy, int itype, int istate){
        x=ix;
        y=iy;
        type=itype;
        state=istate;
    }

    public void changeState(){
        if(this.state==3)this.state=1;
        else this.state++;
    }

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
