import java.util.ArrayList;

/**
 * Created by Dad on 4/18/2015.
 */


public class Level {
    public ArrayList<RedTurret> rtList;
    public ArrayList<GreenTurret> gtList;
    public ArrayList<PurpleTurret> ptList;
    public ArrayList<CyanTurret> ctList;
    public ArrayList<Monster> mList;
    public ArrayList<Spurt> sList;
    public int[][] mapArray;
    public int number;
    public int dimension;
    public Maes maes;
    public int goalx;
    public int goaly;

    public Level(int num,int dimen, int [][] mA ){
        this.number=num;
        this.dimension=dimen;
        this.mapArray=mA;
        rtList=new ArrayList<RedTurret>();
        gtList=new ArrayList<GreenTurret>();
        ptList=new ArrayList<PurpleTurret>();
        ctList=new ArrayList<CyanTurret>();
        mList=new ArrayList<Monster>();
        sList=new ArrayList<Spurt>();
        for(int i=0;i<this.dimension;i++){
            for(int j=0;j<this.dimension;j++){
                switch(this.mapArray[j][i]){
                    case 0:break;
                    case -1:this.maes=new Maes(j,i);
                        break;
                    case -5:this.goalx=j;
                        this.goaly=i;
                        break;
                    case 11:RedTurret rt=new RedTurret(j,i,1);
                        rtList.add(rt);
                        break;
                    case 12:RedTurret rt2=new RedTurret(j,i,2);
                        rtList.add(rt2);
                        break;
                    case 13:RedTurret rt3=new RedTurret(j,i,3);
                        rtList.add(rt3);
                        break;
                    case 14:RedTurret rt4=new RedTurret(j,i,4);
                        rtList.add(rt4);
                        break;
                    case 21:GreenTurret gt=new GreenTurret(j,i,1);
                        gtList.add(gt);
                        break;
                    case 22:GreenTurret gt2=new GreenTurret(j,i,2);
                        gtList.add(gt2);
                        break;
                    case 23:GreenTurret gt3=new GreenTurret(j,i,3);
                        gtList.add(gt3);
                        break;
                    case 24:GreenTurret gt4=new GreenTurret(j,i,4);
                        gtList.add(gt4);
                        break;
                    case 31:PurpleTurret pt=new PurpleTurret(j,i,1);
                        ptList.add(pt);
                        break;
                    case 32:PurpleTurret pt2=new PurpleTurret(j,i,2);
                        ptList.add(pt2);
                        break;
                    case 33:PurpleTurret pt3=new PurpleTurret(j,i,3);
                        ptList.add(pt3);
                        break;
                    case 34:PurpleTurret pt4=new PurpleTurret(j,i,4);
                        ptList.add(pt4);
                        break;
                    case 41:CyanTurret ct=new CyanTurret(j,i,1);
                        ctList.add(ct);
                        break;
                    case 42:CyanTurret ct2=new CyanTurret(j,i,2);
                        ctList.add(ct2);
                        break;
                    case 43:CyanTurret ct3=new CyanTurret(j,i,3);
                        ctList.add(ct3);
                        break;
                    case 44:CyanTurret ct4=new CyanTurret(j,i,4);
                        ctList.add(ct4);
                        break;
                    case 51:Monster m=new Monster(51,j,i);
                        mList.add(m);
                        break;
                    case 52:Monster m2=new Monster(52,j,i);
                        mList.add(m2);
                        break;
                    case 53:Monster m3=new Monster(53,j,i);
                        mList.add(m3);
                        break;
                    case 54:Monster m4=new Monster(54,j,i);
                        mList.add(m4);
                        break;
                    case 55:Monster m5=new Monster(55,j,i);
                        mList.add(m5);
                        break;
                    case 56:Monster m6=new Monster(56,j,i);
                        mList.add(m6);
                        break;
                    case 57:Monster m7=new Monster(57,j,i);
                        mList.add(m7);
                        break;
                    case -90:Spurt s=new Spurt(j,i,1,1);
                        sList.add(s);
                        break;
                    case -91:Spurt s1=new Spurt(j,i,1,2);
                        sList.add(s1);
                        break;
                    default: break;
                }
            }
        }
        for(int i=0;i<rtList.size();i++){
            for(int j=rtList.get(i).getY()-1;(j>=0)&&(mapArray[rtList.get(i).getX()][j]<1);j--){
                rtList.get(i).setUpMax(rtList.get(i).getUpMax()+1);
            }
            for(int j=rtList.get(i).getY()+1;(j<dimension)&&(mapArray[rtList.get(i).getX()][j]<1);j++){
                rtList.get(i).setDownMax(rtList.get(i).getDownMax() + 1);
            }
            for(int j=rtList.get(i).getX()-1;(j>=0)&&(mapArray[j][rtList.get(i).getY()]<1);j--){
                rtList.get(i).setLeftMax(rtList.get(i).getLeftMax() + 1);
            }
            for(int j=rtList.get(i).getX()+1;(j<dimension)&&(mapArray[j][rtList.get(i).getY()]<1);j++){
                rtList.get(i).setRightMax(rtList.get(i).getRightMax()+1);
            }
        }
        for(int i=0;i<gtList.size();i++){
            for(int j=gtList.get(i).getY()-1;(j>=0)&&(mapArray[gtList.get(i).getX()][j]<1);j--){
                gtList.get(i).setUpMax(gtList.get(i).getUpMax()+1);
            }
            for(int j=gtList.get(i).getY()+1;(j<dimension)&&(mapArray[gtList.get(i).getX()][j]<1);j++){
                gtList.get(i).setDownMax(gtList.get(i).getDownMax() + 1);
            }
            for(int j=gtList.get(i).getX()-1;(j>=0)&&(mapArray[j][gtList.get(i).getY()]<1);j--){
                gtList.get(i).setLeftMax(gtList.get(i).getLeftMax() + 1);
            }
            for(int j=gtList.get(i).getX()+1;(j<dimension)&&(mapArray[j][gtList.get(i).getY()]<1);j++){
                gtList.get(i).setRightMax(gtList.get(i).getRightMax()+1);
            }
        }
        for(int i=0;i<ptList.size();i++){
            for(int j=ptList.get(i).getY()-1;(j>=0)&&(mapArray[ptList.get(i).getX()][j]<1);j--){
                ptList.get(i).setUpMax(ptList.get(i).getUpMax()+1);
            }
            for(int j=ptList.get(i).getY()+1;(j<dimension)&&(mapArray[ptList.get(i).getX()][j]<1);j++){
                ptList.get(i).setDownMax(ptList.get(i).getDownMax() + 1);
            }
            for(int j=ptList.get(i).getX()-1;(j>=0)&&(mapArray[j][ptList.get(i).getY()]<1);j--){
                ptList.get(i).setLeftMax(ptList.get(i).getLeftMax() + 1);
            }
            for(int j=ptList.get(i).getX()+1;(j<dimension)&&(mapArray[j][ptList.get(i).getY()]<1);j++){
                ptList.get(i).setRightMax(ptList.get(i).getRightMax()+1);
            }
        }
        for(int i=0;i<ctList.size();i++){
            for(int j=ctList.get(i).getY()-1;(j>=0)&&(mapArray[ctList.get(i).getX()][j]<1);j--){
                ctList.get(i).setUpMax(ctList.get(i).getUpMax()+1);
            }
            for(int j=ctList.get(i).getY()+1;(j<dimension)&&(mapArray[ctList.get(i).getX()][j]<1);j++){
                ctList.get(i).setDownMax(ctList.get(i).getDownMax() + 1);
            }
            for(int j=ctList.get(i).getX()-1;(j>=0)&&(mapArray[j][ctList.get(i).getY()]<1);j--){
                ctList.get(i).setLeftMax(ctList.get(i).getLeftMax() + 1);
            }
            for(int j=ctList.get(i).getX()+1;(j<dimension)&&(mapArray[j][ctList.get(i).getY()]<1);j++){
                ctList.get(i).setRightMax(ctList.get(i).getRightMax()+1);
            }
        }
    }

}
