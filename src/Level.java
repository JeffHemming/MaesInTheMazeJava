import java.util.ArrayList;

/**
 * Created by Dad on 4/18/2015.
 */


public class Level {
    public ArrayList<RedTurret> rtList;
    public ArrayList<GreenTurret> gtList;
    public ArrayList<PurpleTurret> ptList;
    public ArrayList<CyanTurret> ctList;
    public int[][] mapArray;
    public int number;
    public int dimension;
    public Maes maes;
    public Finn finn;
    public Nadia nadia;
    public Gwen gwen;
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
        System.out.println("Dimension: "+dimension);
        for(int i=0;i<this.dimension;i++){
            for(int j=0;j<this.dimension;j++){
                System.out.print(mapArray[j][i]);
                switch(this.mapArray[j][i]){
                    case 0:break;
                    case -1:this.maes=new Maes(j,i);
                        break;
                    case -2:this.finn=new Finn(j,i);
                        break;
                    case -3:this.nadia=new Nadia(j,i);
                        break;
                    case -4:this.gwen=new Gwen(j,i);
                        break;
                    case -5:this.goalx=j;
                        this.goaly=i;
                        break;
                    case 11:RedTurret rt=new RedTurret(i,j,1);
                        rtList.add(rt);
                        break;
                    case 12:RedTurret rt2=new RedTurret(i,j,2);
                        rtList.add(rt2);
                        break;
                    case 13:RedTurret rt3=new RedTurret(i,j,3);
                        rtList.add(rt3);
                        break;
                    case 14:RedTurret rt4=new RedTurret(i,j,4);
                        rtList.add(rt4);
                        break;
                    case 21:GreenTurret gt=new GreenTurret(i,j,1);
                        gtList.add(gt);
                        break;
                    case 22:GreenTurret gt2=new GreenTurret(i,j,2);
                        gtList.add(gt2);
                        break;
                    case 23:GreenTurret gt3=new GreenTurret(i,j,3);
                        gtList.add(gt3);
                        break;
                    case 24:GreenTurret gt4=new GreenTurret(i,j,4);
                        gtList.add(gt4);
                        break;
                    case 31:PurpleTurret pt=new PurpleTurret(i,j,1);
                        ptList.add(pt);
                        break;
                    case 32:PurpleTurret pt2=new PurpleTurret(i,j,2);
                        ptList.add(pt2);
                        break;
                    case 33:PurpleTurret pt3=new PurpleTurret(i,j,3);
                        ptList.add(pt3);
                        break;
                    case 34:PurpleTurret pt4=new PurpleTurret(i,j,4);
                        ptList.add(pt4);
                        break;
                    case 41:CyanTurret ct=new CyanTurret(i,j,1);
                        ctList.add(ct);
                        break;
                    case 42:CyanTurret ct2=new CyanTurret(i,j,2);
                        ctList.add(ct2);
                        break;
                    case 43:CyanTurret ct3=new CyanTurret(i,j,3);
                        ctList.add(ct3);
                        break;
                    case 44:CyanTurret ct4=new CyanTurret(i,j,4);
                        ctList.add(ct4);
                        break;
                }
            }

            System.out.println();
        }
    }

}
