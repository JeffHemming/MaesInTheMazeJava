/**
 * Created by Dad on 5/26/2015.
 */
public class Monster {
    private int type; // 51=bat, 52=rat, 53=spider, 54=troll, 55=skeleton, 56=phantom, 57=reaper
    private int x;
    private int y;
    private int behavior;
    private boolean spotted;

    public Monster(int itype,int ix,int iy){
        type=itype;
        x=ix;
        y=iy;
        spotted=false;
        if(type==51||type==52)behavior=1;
        else if(type==53||type==54||type==55)behavior=2;
        else behavior=3;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getBehavior() {
        return behavior;
    }

    public void setBehavior(int behavior) {
        this.behavior = behavior;
    }

    public boolean isSpotted() {
        return spotted;
    }

    public void setSpotted(boolean spotted) {
        this.spotted = spotted;
    }
}
