import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Dad on 4/18/2015.
 */
public class LevelGUI extends JFrame implements MouseListener, KeyListener {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double height = screenSize.getHeight();
    private static final int WIDTH = (int) (9*height/10);
    private static final int HEIGHT = WIDTH;
    private static final int MAZE = 8*HEIGHT/10;
    private static final int MAZEY = (int) ((HEIGHT/15)*1.25);
    private static final int MAZEX = (WIDTH-MAZE)/2;
    public static int DIM;
    public static int currentPlayer=1;
    public static boolean maesDone=false;

    protected static JLabel box, maesLabel,deadLabel;
    protected static JLabel [][] cell;
    protected static JLabel [] topBorder;
    protected static ArrayList<JLabel> rTurretList;
    protected static ArrayList<JLabel> gTurretList;
    protected static ArrayList<JLabel> pTurretList;
    protected static ArrayList<JLabel> cTurretList;
    protected static ArrayList<JLabel> mList;
    protected static ArrayList<JLabel> sList;
    protected static ArrayList<JLabel> rlasers;
    protected static ArrayList<JLabel> glasers;
    protected static ArrayList<JLabel> plasers;
    protected static ArrayList<JLabel> clasers;
    protected static ArrayList<JLabel> flameSpurts;
    protected static ArrayList<JLabel> doors;
    protected static ArrayList<JLabel> switches;
    protected static ArrayList<JLabel> waterways;
    protected static ArrayList<JLabel> gates;
    protected static Level l;
    protected static boolean alreadyFired=false;
    public static String WALL, TOP, DOOR, DOORSWITCH, FLOOR, GOAL, SPOUT1, SPOUT2, SPURT, PLAYER, PRESSEDSWITCH;
    public static String SPURTSOUND, BGM, SWITCHSOUND, DOORSOUND, VICTORYSOUND, STARTSOUND;

    public LevelGUI(int levelNumber, int character) throws IOException{
        //assign images
        addKeyListener(this);
        switch(Main.character){
            case 1: PLAYER="image/maes.png";
                break;
            case 2: PLAYER="image/finn.png";
                break;
            case 3: PLAYER="image/nadia.png";
                break;
            case 4: PLAYER="image/gwendolyn.png";
                break;
            case 5: PLAYER="image/annabeth.png";
                break;
            case 6: PLAYER="image/olivier.png";
                break;
            case 7: PLAYER="image/pip.png";
                break;
            case 8: PLAYER="image/bridger.png";
                break;
            default: PLAYER="image/maes.png";
                break;
        }
        WALL="image/wall"+Main.character+".png";
        TOP="image/top"+Main.character+".png";
        DOOR="image/door"+Main.character+".png";
        DOORSWITCH="image/doorswitch"+Main.character+".png";
        FLOOR="image/floor"+Main.character+".png";
        SPOUT1="image/spurt1"+Main.character+".png";
        SPOUT2="image/spurt2"+Main.character+".png";
        SPURT="image/spout"+Main.character+".png";
        PRESSEDSWITCH="image/pressedswitch"+Main.character+".png";
        GOAL="image/goal"+Main.character+".png";
        SPURTSOUND="sound\\spurt"+Main.character+".wav";



        BufferedReader input=new BufferedReader(new FileReader(Main.levelfile));
        boolean foundLevel=false;
        while(foundLevel==false) {
            String[] lineread=input.readLine().split(" ");
            DIM=Integer.parseInt(lineread[1]);
            if (Integer.parseInt(lineread[0]) == levelNumber) {
                foundLevel = true;

            }
            else{
                for(int i=0;i<=DIM;i++){
                    String garbage=input.readLine();
                }
            }
        }
        int[][] mA=new int[DIM][DIM];
        for(int i=0;i<DIM;i++){
            String[] row=input.readLine().split(" ");
            for(int j=0;j<row.length;j++){
                mA[j][i]=Integer.parseInt(row[j]);
            }
        }
        l=new Level(levelNumber,DIM,mA);
        this.setResizable(true);
        Container pane = getContentPane();
        pane.setLayout(null);

        deadLabel=new JLabel("DEAD",SwingConstants.CENTER);
        deadLabel.setForeground(Color.RED);
        deadLabel.setSize(WIDTH, HEIGHT);
        deadLabel.setLocation(0, 0);
        Font deadFont = new Font("Georgia", Font.BOLD,HEIGHT/5);
        deadLabel.setFont(deadFont);
        deadLabel.setVisible(false);
        pane.add(deadLabel);

        flameSpurts=new ArrayList<JLabel>();
        for(int i=0;i<l.sList.size();i++){
            JLabel temp=new JLabel();
            flameSpurts.add(temp);
            flameSpurts.get(i).setSize(MAZE/DIM,2*MAZE/DIM);
            flameSpurts.get(i).setLocation(MAZEX+(l.sList.get(i).getX() * (MAZE/DIM)),MAZEY+(l.sList.get(i).getY()*(MAZE/DIM))-(MAZE/DIM));
            pane.add(flameSpurts.get(i));
        }

        maesLabel=new JLabel();
        maesLabel.setSize(MAZE / DIM, MAZE / DIM);
        maesLabel.setLocation(MAZEX + (l.maes.getX() * (MAZE / DIM)), MAZEY + (l.maes.getY() * (MAZE / DIM)));
        BufferedImage maesImage = null;
        try {
            maesImage = ImageIO.read(new File(PLAYER));
        } catch (IOException e) {
            System.out.println("Not Found");
        }
        Image m2= maesImage.getScaledInstance(MAZE / DIM, MAZE / DIM, Image.SCALE_SMOOTH);
        maesLabel.setIcon(new ImageIcon(m2));
       // maesLabel.setFocusable(true);
       // maesLabel.grabFocus();
      //  maesLabel.addKeyListener();
        pane.add(maesLabel);



        cell=new JLabel[DIM][DIM];
        rlasers=new ArrayList<JLabel>();
        for(int i=0;i<l.rtList.size();i++){
            JLabel temp=new JLabel();
            rlasers.add(temp);
            rlasers.get(i).setSize(MAZE / DIM, MAZE / DIM);
            rlasers.get(i).setLocation(MAZEX + (l.rtList.get(i).getX() * (MAZE / DIM)), MAZEY + (l.rtList.get(i).getY() * (MAZE / DIM)));
            pane.add(rlasers.get(i));
        }
        glasers=new ArrayList<JLabel>();
        for(int i=0;i<l.gtList.size();i++){
            JLabel temp=new JLabel();
            glasers.add(temp);
            glasers.get(i).setSize(MAZE / DIM, MAZE / DIM);
            glasers.get(i).setLocation(MAZEX+(l.gtList.get(i).getX() * (MAZE/DIM)),MAZEY+(l.gtList.get(i).getY()*(MAZE/DIM)));
            pane.add(glasers.get(i));
        }
        plasers=new ArrayList<JLabel>();
        for(int i=0;i<l.ptList.size();i++){
            JLabel temp=new JLabel();
            plasers.add(temp);
            plasers.get(i).setSize(MAZE / DIM, MAZE / DIM);
            plasers.get(i).setLocation(MAZEX+(l.ptList.get(i).getX() * (MAZE/DIM)),MAZEY+(l.ptList.get(i).getY()*(MAZE/DIM)));
            pane.add(plasers.get(i));
        }
        clasers=new ArrayList<JLabel>();
        for(int i=0;i<l.ctList.size();i++){
            JLabel temp=new JLabel();
            clasers.add(temp);
            clasers.get(i).setSize(MAZE / DIM, MAZE / DIM);
            clasers.get(i).setLocation(MAZEX+(l.ctList.get(i).getX() * (MAZE/DIM)),MAZEY+(l.ctList.get(i).getY()*(MAZE/DIM)));
            pane.add(clasers.get(i));
        }

        mList=new ArrayList<JLabel>();
        for(int i=0;i<l.mList.size();i++){
            JLabel temp=new JLabel();
            mList.add(temp);
            mList.get(i).setSize(MAZE / DIM, ((MAZE / DIM)));
            mList.get(i).setLocation(MAZEX+(l.mList.get(i).getX() * (MAZE/DIM)),MAZEY+(l.mList.get(i).getY()*(MAZE/DIM)));
            BufferedImage tImage = null;
            try {
                if(l.mList.get(i).getType()==51)tImage = ImageIO.read(new File("image/bat.png"));
                else if(l.mList.get(i).getType()==52)tImage = ImageIO.read(new File("image/rat.png"));
                else if(l.mList.get(i).getType()==53)tImage = ImageIO.read(new File("image/spider.png"));
                else if(l.mList.get(i).getType()==54)tImage = ImageIO.read(new File("image/troll.png"));
                else if(l.mList.get(i).getType()==55)tImage = ImageIO.read(new File("image/skeleton.png"));
                else if(l.mList.get(i).getType()==56)tImage = ImageIO.read(new File("image/phantom.png"));
                else tImage = ImageIO.read(new File("image/reaper.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image m4= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)), Image.SCALE_SMOOTH);
            mList.get(i).setIcon(new ImageIcon(m4));
            pane.add(mList.get(i));
        }



        JLabel goal=new JLabel();
        goal.setLocation(MAZEX + (l.goalx * (MAZE / DIM)), MAZEY + (l.goaly * (MAZE / DIM)));
        goal.setSize(MAZE/DIM,MAZE/DIM);
        BufferedImage gImage = null;
        try {
           gImage = ImageIO.read(new File(GOAL));
        } catch (IOException e) {
            System.out.println("Not Found");
        }
         m2= gImage.getScaledInstance(MAZE / DIM, MAZE / DIM, Image.SCALE_SMOOTH);
        goal.setIcon(new ImageIcon(m2));
        pane.add(goal);

        for(int i=0;i<DIM;i++){
            for(int j=0;j<DIM;j++){
                if(l.mapArray[j][i]==1) {
                    cell[j][i] = new JLabel();
                    cell[j][i].setSize(MAZE/DIM,MAZE/DIM);
                    cell[j][i].setLocation(MAZEX+(j*(MAZE/DIM)),MAZEY+(i*(MAZE/DIM)));
                    BufferedImage cImage = null;
                    try {
                        cImage = ImageIO.read(new File(TOP));
                    } catch (IOException e) {
                        System.out.println("Not Found");
                    }
                    Image top= cImage.getScaledInstance(MAZE / DIM, MAZE / DIM, Image.SCALE_SMOOTH);
                    cell[j][i].setIcon(new ImageIcon(top));
                    pane.add(cell[j][i]);
                }
            }
        }
        topBorder=new JLabel[DIM];
        for(int i=0;i<DIM;i++){
            topBorder[i]=new JLabel();
            topBorder[i].setSize(MAZE/DIM,HEIGHT/15);
            topBorder[i].setLocation(MAZEX+(i*(MAZE/DIM)),MAZEY-(HEIGHT/15));
            BufferedImage cImage = null;
            try {
                cImage = ImageIO.read(new File(TOP));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image top= cImage.getScaledInstance(MAZE / DIM,HEIGHT/15, Image.SCALE_SMOOTH);
            topBorder[i].setIcon(new ImageIcon(top));
            pane.add(topBorder[i]);
        }
        topBorder=new JLabel[DIM];
        for(int i=0;i<DIM;i++){
            topBorder[i]=new JLabel();
            topBorder[i].setSize(MAZE/DIM,HEIGHT/15);
            topBorder[i].setLocation(MAZEX+(i*(MAZE/DIM)),MAZEY+MAZE);
            BufferedImage cImage = null;
            try {
                cImage = ImageIO.read(new File(TOP));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image top= cImage.getScaledInstance(MAZE / DIM, HEIGHT/15, Image.SCALE_SMOOTH);
            topBorder[i].setIcon(new ImageIcon(top));
            pane.add(topBorder[i]);
        }
        topBorder=new JLabel[DIM];
        for(int i=0;i<DIM;i++){
            topBorder[i]=new JLabel();
            topBorder[i].setSize(HEIGHT/15,MAZE/DIM);
            topBorder[i].setLocation(MAZEX-(HEIGHT/15),MAZEY+(i*(MAZE/DIM)));
            BufferedImage cImage = null;
            try {
                cImage = ImageIO.read(new File(TOP));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image top= cImage.getScaledInstance(HEIGHT/15, MAZE / DIM, Image.SCALE_SMOOTH);
            topBorder[i].setIcon(new ImageIcon(top));
            pane.add(topBorder[i]);
        }
        topBorder=new JLabel[DIM];
        for(int i=0;i<DIM;i++){
            topBorder[i]=new JLabel();
            topBorder[i].setSize(HEIGHT/15,MAZE/DIM);
            topBorder[i].setLocation(MAZEX+MAZE,MAZEY+(i*(MAZE/DIM)));
            BufferedImage cImage = null;
            try {
                cImage = ImageIO.read(new File(TOP));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image top= cImage.getScaledInstance(HEIGHT/15, MAZE / DIM, Image.SCALE_SMOOTH);
            topBorder[i].setIcon(new ImageIcon(top));
            pane.add(topBorder[i]);
        }
        maesDone=l.maes!=null;
        rTurretList=new ArrayList<JLabel>();
        for(int i=0;i<l.rtList.size();i++){
            JLabel temp=new JLabel();
            rTurretList.add(temp);
            rTurretList.get(i).setSize(MAZE / DIM,((MAZE / DIM)*3/2));
            rTurretList.get(i).setLocation(MAZEX+(l.rtList.get(i).getX() * (MAZE/DIM)),MAZEY+(l.rtList.get(i).getY()*(MAZE/DIM)));
            BufferedImage tImage = null;
            try {
                if(l.rtList.get(i).getFace()==1)tImage = ImageIO.read(new File("image/rturret.png"));
                else if(l.rtList.get(i).getFace()==2)tImage = ImageIO.read(new File("image/rturret2.png"));
                else if(l.rtList.get(i).getFace()==3)tImage = ImageIO.read(new File("image/rturret3.png"));
                else tImage = ImageIO.read(new File("image/rturret4.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image m3= tImage.getScaledInstance(MAZE / DIM,((MAZE / DIM)*3/2), Image.SCALE_SMOOTH);
            rTurretList.get(i).setIcon(new ImageIcon(m3));
            pane.add(rTurretList.get(i));
        }
        gTurretList=new ArrayList<JLabel>();
        for(int i=0;i<l.gtList.size();i++){
            JLabel temp=new JLabel();
            gTurretList.add(temp);
            gTurretList.get(i).setSize(MAZE / DIM, ((MAZE / DIM)*3/2));
            gTurretList.get(i).setLocation(MAZEX+(l.gtList.get(i).getX() * (MAZE/DIM)),MAZEY+(l.gtList.get(i).getY()*(MAZE/DIM)));
            BufferedImage tImage = null;
            try {
                if(l.gtList.get(i).getFace()==1)tImage = ImageIO.read(new File("image/gturret.png"));
                else if(l.gtList.get(i).getFace()==2)tImage = ImageIO.read(new File("image/gturret2.png"));
                else if(l.gtList.get(i).getFace()==3)tImage = ImageIO.read(new File("image/gturret3.png"));
                else tImage = ImageIO.read(new File("image/gturret4.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image m4= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)*3/2), Image.SCALE_SMOOTH);
            gTurretList.get(i).setIcon(new ImageIcon(m4));
            pane.add(gTurretList.get(i));
        }
        pTurretList=new ArrayList<JLabel>();
        for(int i=0;i<l.ptList.size();i++){
            JLabel temp=new JLabel();
            pTurretList.add(temp);
            pTurretList.get(i).setSize(MAZE / DIM,((MAZE / DIM)*3/2));
            pTurretList.get(i).setLocation(MAZEX+(l.ptList.get(i).getX() * (MAZE/DIM)),MAZEY+(l.ptList.get(i).getY()*(MAZE/DIM)));
            BufferedImage tImage = null;
            try {
                if(l.ptList.get(i).getFace()==1)tImage = ImageIO.read(new File("image/pturret.png"));
                else if(l.ptList.get(i).getFace()==2)tImage = ImageIO.read(new File("image/pturret2.png"));
                else if(l.ptList.get(i).getFace()==3)tImage = ImageIO.read(new File("image/pturret3.png"));
                else tImage = ImageIO.read(new File("image/pturret4.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            m2= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)*3/2), Image.SCALE_SMOOTH);
            pTurretList.get(i).setIcon(new ImageIcon(m2));
            pane.add(pTurretList.get(i));
        }
        cTurretList=new ArrayList<JLabel>();
        for(int i=0;i<l.ctList.size();i++){
            JLabel temp=new JLabel();
            cTurretList.add(temp);
            cTurretList.get(i).setSize(MAZE / DIM, ((MAZE / DIM)*3/2));
            cTurretList.get(i).setLocation(MAZEX+(l.ctList.get(i).getX() * (MAZE/DIM)),MAZEY+(l.ctList.get(i).getY()*(MAZE/DIM)));
            BufferedImage tImage = null;
            try {
                if(l.ctList.get(i).getFace()==1)tImage = ImageIO.read(new File("image/cturret.png"));
                else if(l.ctList.get(i).getFace()==2)tImage = ImageIO.read(new File("image/cturret2.png"));
                else if(l.ctList.get(i).getFace()==3)tImage = ImageIO.read(new File("image/cturret3.png"));
                else tImage = ImageIO.read(new File("image/cturret4.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            m2= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)*3/2), Image.SCALE_SMOOTH);
            cTurretList.get(i).setIcon(new ImageIcon(m2));
            pane.add(cTurretList.get(i));
        }

        for(int i=0;i<DIM;i++){
            for(int j=0;j<DIM;j++){
                if(i==0||l.mapArray[j][i-1]==1) {
                    cell[j][i] = new JLabel();
                    cell[j][i].setSize(MAZE/DIM,MAZE/DIM);
                    cell[j][i].setLocation(MAZEX+(j*(MAZE/DIM)),MAZEY+(i*(MAZE/DIM)));
                    BufferedImage cImage = null;
                    try {
                        cImage = ImageIO.read(new File(WALL));
                    } catch (IOException e) {
                        System.out.println("Not Found");
                    }
                    Image top= cImage.getScaledInstance(MAZE / DIM, MAZE / DIM, Image.SCALE_SMOOTH);
                    cell[j][i].setIcon(new ImageIcon(top));
                    pane.add(cell[j][i]);
                }
            }
        }
        for(int i=0;i<DIM;i++){
            for(int j=0;j<DIM;j++){
                if(l.mapArray[j][i]<-79&&l.mapArray[j][i]>-85) {
                    cell[j][i] = new JLabel();
                    cell[j][i].setSize(MAZE/DIM,MAZE/DIM);
                    cell[j][i].setLocation(MAZEX+(j*(MAZE/DIM)),MAZEY+(i*(MAZE/DIM)));
                    BufferedImage cImage = null;
                    try {
                        if(l.mapArray[j][i]==-80)cImage = ImageIO.read(new File("image/ice.png"));
                        if(l.mapArray[j][i]==-81)cImage = ImageIO.read(new File("image/upArrow.png"));
                        if(l.mapArray[j][i]==-82)cImage = ImageIO.read(new File("image/rightArrow.png"));
                        if(l.mapArray[j][i]==-83)cImage = ImageIO.read(new File("image/downArrow.png"));
                        if(l.mapArray[j][i]==-84)cImage = ImageIO.read(new File("image/leftArrow.png"));
                    } catch (IOException e) {
                        System.out.println("Not Found");
                    }
                    Image top= cImage.getScaledInstance(MAZE / DIM, MAZE / DIM, Image.SCALE_SMOOTH);
                    cell[j][i].setIcon(new ImageIcon(top));
                    pane.add(cell[j][i]);
                }
            }
        }

        sList=new ArrayList<JLabel>();
        for(int i=0;i<l.sList.size();i++){
            JLabel temp=new JLabel();
            sList.add(temp);
            sList.get(i).setSize(MAZE / DIM, ((MAZE / DIM)));
            sList.get(i).setLocation(MAZEX+(l.sList.get(i).getX() * (MAZE/DIM)),MAZEY+(l.sList.get(i).getY()*(MAZE/DIM)));
            BufferedImage tImage = null;
            try {
                if(l.sList.get(i).getState()==2)tImage = ImageIO.read(new File(SPOUT1));
                else tImage = ImageIO.read(new File(SPOUT2));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            m2= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)), Image.SCALE_SMOOTH);
            sList.get(i).setIcon(new ImageIcon(m2));
            pane.add(sList.get(i));
        }

        doors=new ArrayList<JLabel>();
        for(int i=0;i<DIM;i++){
            for(int j=0;j<DIM;j++) {
                for(int k=60;k<70;k++){
                    if(l.mapArray[j][i]==k) {
                        JLabel temp = new JLabel();
                        doors.add(temp);
                        doors.get(doors.size()-1).setSize(MAZE / DIM, ((MAZE / DIM)));
                        doors.get(doors.size()-1).setLocation(MAZEX+(j * (MAZE/DIM)),MAZEY+(i*(MAZE/DIM)));
                        BufferedImage tImage = null;
                        try {
                            tImage = ImageIO.read(new File(DOOR));
                        } catch (IOException e) {
                            System.out.println("Not Found");
                        }
                        Image m4= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)), Image.SCALE_SMOOTH);
                        doors.get(doors.size()-1).setIcon(new ImageIcon(m4));
                        pane.add(doors.get(doors.size()-1));
                    }
                }
            }
        }

        switches=new ArrayList<JLabel>();
        for(int i=0;i<DIM;i++){
            for(int j=0;j<DIM;j++) {
                for(int k=-60;k>-70;k--){
                    if(l.mapArray[j][i]==k) {
                        JLabel temp = new JLabel();
                        switches.add(temp);
                        switches.get(switches.size()-1).setSize(MAZE / DIM, ((MAZE / DIM)));
                        switches.get(switches.size()-1).setLocation(MAZEX+(j * (MAZE/DIM)),MAZEY+(i*(MAZE/DIM)));
                        BufferedImage tImage = null;
                        try {
                            tImage = ImageIO.read(new File(DOORSWITCH));
                        } catch (IOException e) {
                            System.out.println("Not Found");
                        }
                        Image m4= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)), Image.SCALE_SMOOTH);
                        switches.get(switches.size()-1).setIcon(new ImageIcon(m4));
                        pane.add(switches.get(switches.size()-1));
                    }
                }
            }
        }

        for(int i=0;i<DIM;i++){
            for(int j=0;j<DIM;j++){
                JLabel floorcell = new JLabel();
                floorcell.setSize(MAZE/DIM,MAZE/DIM);
                floorcell.setLocation(MAZEX+(j*(MAZE/DIM)),MAZEY+(i*(MAZE/DIM)));
                BufferedImage cImage = null;
                try {
                    cImage = ImageIO.read(new File(FLOOR));
                } catch (IOException e) {
                    System.out.println("Not Found");
                }
                Image top= cImage.getScaledInstance(MAZE / DIM, MAZE / DIM, Image.SCALE_SMOOTH);
                floorcell.setIcon(new ImageIcon(top));
                pane.add(floorcell);
            }
        }
        box=new JLabel();
        box.setSize(MAZE,MAZE);
        box.setLocation(MAZEX, MAZEY);
        box.setBackground(Color.BLACK);
        box.setOpaque(true);
        pane.add(box);

        pane.addMouseListener(this);
        this.setSize(WIDTH,HEIGHT);
        pane.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setTitle("Maes in the Maze");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
       /*
        int x = e.getX();
        int y = e.getY();

        if(currentPlayer==1&&l.maes!=null&&l.maes.isCanMove()){
            int maesCenterx=(l.maes.getX()*MAZE/DIM)+MAZEX+MAZE/DIM/2;
            int maesCentery=(l.maes.getY()*MAZE/DIM)+MAZEY+MAZE/DIM/2;
            if(x<maesCenterx){
                if(maesCentery-y>maesCenterx-x){
                    //move up
                    if(l.maes.getY()>0&&l.mapArray[l.maes.getX()][l.maes.getY()-1]<1){
                        l.maes.setY(l.maes.getY() - 1);
                        updateMaes();
                        checkSlide();
                        iceUpCheck();
                        l.maes.setCanMove(false);
                    }
                }
                else if(y-maesCentery>maesCenterx-x){
                    //move down
                    if(l.maes.getY()<DIM-1&&l.mapArray[l.maes.getX()][l.maes.getY()+1]<1){
                        l.maes.setY(l.maes.getY() + 1);
                        updateMaes();
                        checkSlide();
                        iceDownCheck();
                        l.maes.setCanMove(false);
                    }
                }
                else {
                    //move left
                    if(l.maes.getX()>0&&l.mapArray[l.maes.getX()-1][l.maes.getY()]<1){
                        l.maes.setX(l.maes.getX() - 1);
                        updateMaes();
                        checkSlide();
                        iceLeftCheck();
                        l.maes.setCanMove(false);
                    }
                }
            }
            else{
                if(maesCentery-y>x-maesCenterx){
                    //move up
                    if(l.maes.getY()>0&&l.mapArray[l.maes.getX()][l.maes.getY()-1]<1){
                        l.maes.setY(l.maes.getY() - 1);
                        updateMaes();
                        checkSlide();
                        iceUpCheck();
                        l.maes.setCanMove(false);
                    }
                }
                else if(y-maesCentery>x-maesCenterx){
                    //move down
                    if(l.maes.getY()<DIM-1&&l.mapArray[l.maes.getX()][l.maes.getY()+1]<1){
                        l.maes.setY(l.maes.getY() + 1);
                        updateMaes();
                        checkSlide();
                        iceDownCheck();
                        l.maes.setCanMove(false);
                    }
                }
                else {
                    //move right
                    if(l.maes.getX()<DIM-1&&l.mapArray[l.maes.getX()+1][l.maes.getY()]<1){
                        l.maes.setX(l.maes.getX() + 1);
                        updateMaes();
                        checkSlide();
                        iceRightCheck();
                        l.maes.setCanMove(false);
                    }
                }
            }
        }

        //END TURN
        if(!alreadyFired&&!l.maes.isCanMove()){
            //WIN
            if(l.maes.getX()==l.goalx&&l.maes.getY()==l.goaly) {
                Main.currentLevel++;
                if(Main.currentLevel>Main.maxLevel[Main.character-1]){
                    Main.maxLevel[Main.character-1]=Main.currentLevel;
                    BufferedWriter os=null;
                    try {
                        os = new BufferedWriter(new FileWriter("mitm.sav"));
                        os.write(Main.maxLevel[0]+" "+Main.maxLevel[1]+" "+Main.maxLevel[2]+" "+Main.maxLevel[3]+" "
                                +Main.maxLevel[4]+" "+Main.maxLevel[5]+" "+Main.maxLevel[6]+" "+Main.maxLevel[7]);
                    } catch (IOException ef) {
                        ef.printStackTrace();
                    } finally {
                        try {
                            if (os != null)os.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                deadLabel.setText("CLEAR!");
                deadLabel.setForeground(Color.GREEN);
                deadLabel.setVisible(true);
                Timer victoryTimer = new Timer();
                victoryTimer.schedule(new endLevel(), 2000);
            }//end WIM
            else {
                checkSwitches();
                alreadyFired = true;

                for(int i=0;i<l.mList.size();i++){
                    monsterMove(l.mList.get(i),i);
                }
                for(int i= 0; i<l.sList.size(); i++){
                    spurting(l.sList.get(i), i);
                }

                for (int i = 0; i < l.ctList.size(); i++) {
                    cfire(l.ctList.get(i), i);
                }

                for (int i = 0; i < l.ptList.size(); i++) {
                    pfire(l.ptList.get(i), i);
                }

                Timer timer1 = new Timer();
                if (l.ctList.size() > 0 || l.ptList.size() > 0) timer1.schedule(new redrawLasers(), 1000);
                else timer1.schedule(new redrawLasers(), 0);
            }
        }
        */
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void spurting(Spurt s, int i){
        s.changeState();
        if(s.getState()==3){
            Sound.soundEffect(SPURTSOUND);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File(SPURT));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(MAZE / DIM, 2 *(MAZE / DIM), Image.SCALE_SMOOTH);
            flameSpurts.get(i).setIcon(new ImageIcon(l22));
            flameSpurts.get(i).setVisible(true);

            if((l.maes.getX()==s.getX()&&l.maes.getY()==s.getY())){
                gameOver();
            }
        }
        changeSpurtLabels();
    }

    public static void cfire(CyanTurret t,int i){
        Sound.soundEffect("sound\\laser.wav");
        if(t.getFace()==1&&t.getUpMax()>0){
            clasers.get(i).setSize(MAZE / DIM, t.getUpMax() * (MAZE / DIM));
            clasers.get(i).setLocation(t.getX() * (MAZE / DIM) + MAZEX, (t.getY() - t.getUpMax()) * (MAZE / DIM) + MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/vcLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(MAZE / DIM, t.getUpMax()*(MAZE / DIM), Image.SCALE_SMOOTH);
            clasers.get(i).setIcon(new ImageIcon(l22));
            clasers.get(i).setVisible(true);

            if((l.maes.getX()==t.getX()&&l.maes.getY()<t.getY()&&l.maes.getY()>=t.getY()-t.getUpMax())){
                gameOver();
            }

        }
        else if(t.getFace()==3&&t.getDownMax()>0){
            clasers.get(i).setSize(MAZE/DIM,t.getDownMax()*(MAZE/DIM));
            clasers.get(i).setLocation(t.getX()*(MAZE/DIM)+MAZEX,(t.getY()+1)*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/vcLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(MAZE / DIM, t.getDownMax()*(MAZE / DIM), Image.SCALE_SMOOTH);
            clasers.get(i).setIcon(new ImageIcon(l22));
            clasers.get(i).setVisible(true);

            if((l.maes!=null&&l.maes.getX()==t.getX()&&l.maes.getY()>t.getY()&&l.maes.getY()<=t.getY()+t.getDownMax())){
                gameOver();
            }

        }
        if(t.getFace()==4&&t.getLeftMax()>0){
            clasers.get(i).setSize(t.getLeftMax()*(MAZE/DIM),MAZE/DIM);
            clasers.get(i).setLocation((t.getX()-t.getLeftMax())*(MAZE/DIM)+MAZEX,(t.getY())*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/hcLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(t.getLeftMax()*(MAZE/DIM),MAZE/DIM, Image.SCALE_SMOOTH);
            clasers.get(i).setIcon(new ImageIcon(l22));
            clasers.get(i).setVisible(true);

            if((l.maes!=null&&l.maes.getY()==t.getY()&&l.maes.getX()<t.getX()&&l.maes.getX()>=t.getX()-t.getLeftMax())){
                gameOver();
            }
        }
        if(t.getFace()==2&&t.getRightMax()>0){
            clasers.get(i).setSize(t.getRightMax()*(MAZE/DIM),MAZE/DIM);
            clasers.get(i).setLocation((t.getX()+1)*(MAZE/DIM)+MAZEX,(t.getY())*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/hcLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(t.getRightMax()*(MAZE/DIM),MAZE/DIM, Image.SCALE_SMOOTH);
            clasers.get(i).setIcon(new ImageIcon(l22));
            clasers.get(i).setVisible(true);

            if((l.maes!=null&&l.maes.getY()==t.getY()&&l.maes.getX()>t.getX()&&l.maes.getX()<=t.getX()+t.getRightMax())){
                gameOver();
            }
        }
    }
    public static void pfire(PurpleTurret t,int i){
        Sound.soundEffect("sound\\laser.wav");
        if(t.getFace()==1&&t.getUpMax()>0){
            plasers.get(i).setSize(MAZE / DIM, t.getUpMax() * (MAZE / DIM));
            plasers.get(i).setLocation(t.getX() * (MAZE / DIM) + MAZEX, (t.getY() - t.getUpMax()) * (MAZE / DIM) + MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/vpLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(MAZE / DIM, t.getUpMax()*(MAZE / DIM), Image.SCALE_SMOOTH);
            plasers.get(i).setIcon(new ImageIcon(l22));
            plasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getX()==t.getX()&&l.maes.getY()<t.getY()&&l.maes.getY()>=t.getY()-t.getUpMax())){
                gameOver();
            }
        }
        else if(t.getFace()==3&&t.getDownMax()>0){
            plasers.get(i).setSize(MAZE/DIM,t.getDownMax()*(MAZE/DIM));
            plasers.get(i).setLocation(t.getX()*(MAZE/DIM)+MAZEX,(t.getY()+1)*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/vpLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(MAZE / DIM, t.getDownMax()*(MAZE / DIM), Image.SCALE_SMOOTH);
            plasers.get(i).setIcon(new ImageIcon(l22));
            plasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getX()==t.getX()&&l.maes.getY()>t.getY()&&l.maes.getY()<=t.getY()+t.getDownMax())){
                gameOver();
            }
        }
        if(t.getFace()==4&&t.getLeftMax()>0){
            plasers.get(i).setSize(t.getLeftMax()*(MAZE/DIM),MAZE/DIM);
            plasers.get(i).setLocation((t.getX()-t.getLeftMax())*(MAZE/DIM)+MAZEX,(t.getY())*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/hpLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(t.getLeftMax()*(MAZE/DIM),MAZE/DIM, Image.SCALE_SMOOTH);
            plasers.get(i).setIcon(new ImageIcon(l22));
            plasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getY()==t.getY()&&l.maes.getX()<t.getX()&&l.maes.getX()>=t.getX()-t.getLeftMax())){
                gameOver();
            }
        }
        if(t.getFace()==2&&t.getRightMax()>0){
            plasers.get(i).setSize(t.getRightMax()*(MAZE/DIM),MAZE/DIM);
            plasers.get(i).setLocation((t.getX()+1)*(MAZE/DIM)+MAZEX,(t.getY())*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/hpLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(t.getRightMax()*(MAZE/DIM),MAZE/DIM, Image.SCALE_SMOOTH);
            plasers.get(i).setIcon(new ImageIcon(l22));
            plasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getY()==t.getY()&&l.maes.getX()>t.getX()&&l.maes.getX()<=t.getX()+t.getRightMax())){
                gameOver();
            }
        }
    }
    public static void rfire(RedTurret t,int i){
        Sound.soundEffect("sound\\laser.wav");
        if(t.getFace()==1&&t.getUpMax()>0){
            rlasers.get(i).setSize(MAZE / DIM, t.getUpMax() * (MAZE / DIM));
            rlasers.get(i).setLocation(t.getX() * (MAZE / DIM) + MAZEX, (t.getY() - t.getUpMax()) * (MAZE / DIM) + MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/vrLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(MAZE / DIM, t.getUpMax()*(MAZE / DIM), Image.SCALE_SMOOTH);
            rlasers.get(i).setIcon(new ImageIcon(l22));
            rlasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getX()==t.getX()&&l.maes.getY()<t.getY()&&l.maes.getY()>=t.getY()-t.getUpMax())){
                gameOver();
            }
        }
        else if(t.getFace()==3&&t.getDownMax()>0){
            rlasers.get(i).setSize(MAZE/DIM,t.getDownMax()*(MAZE/DIM));
            rlasers.get(i).setLocation(t.getX()*(MAZE/DIM)+MAZEX,(t.getY()+1)*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/vrLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(MAZE / DIM, t.getDownMax()*(MAZE / DIM), Image.SCALE_SMOOTH);
            rlasers.get(i).setIcon(new ImageIcon(l22));
            rlasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getX()==t.getX()&&l.maes.getY()>t.getY()&&l.maes.getY()<=t.getY()+t.getDownMax())){
                gameOver();
            }
        }
        else if(t.getFace()==4&&t.getLeftMax()>0){
            rlasers.get(i).setSize(t.getLeftMax()*(MAZE/DIM),MAZE/DIM);
            rlasers.get(i).setLocation((t.getX()-t.getLeftMax())*(MAZE/DIM)+MAZEX,(t.getY())*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/hrLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(t.getLeftMax()*(MAZE/DIM),MAZE/DIM, Image.SCALE_SMOOTH);
            rlasers.get(i).setIcon(new ImageIcon(l22));
            rlasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getY()==t.getY()&&l.maes.getX()<t.getX()&&l.maes.getX()>=t.getX()-t.getLeftMax())){
                gameOver();
            }
        }
        else if(t.getFace()==2&&t.getRightMax()>0){
            rlasers.get(i).setSize(t.getRightMax()*(MAZE/DIM),MAZE/DIM);
            rlasers.get(i).setLocation((t.getX()+1)*(MAZE/DIM)+MAZEX,(t.getY())*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/hrLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(t.getRightMax()*(MAZE/DIM),MAZE/DIM, Image.SCALE_SMOOTH);
            rlasers.get(i).setIcon(new ImageIcon(l22));
            rlasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getY()==t.getY()&&l.maes.getX()>t.getX()&&l.maes.getX()<=t.getX()+t.getRightMax())){
                gameOver();
            }
        }
    }
    public static void gfire(GreenTurret t,int i){
        Sound.soundEffect("sound\\Laser.wav");
        if(t.getFace()==1&&t.getUpMax()>0){
            glasers.get(i).setSize(MAZE / DIM, t.getUpMax() * (MAZE / DIM));
            glasers.get(i).setLocation(t.getX() * (MAZE / DIM) + MAZEX, (t.getY() - t.getUpMax()) * (MAZE / DIM) + MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/vgLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(MAZE / DIM, t.getUpMax()*(MAZE / DIM), Image.SCALE_SMOOTH);
            glasers.get(i).setIcon(new ImageIcon(l22));
            glasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getX()==t.getX()&&l.maes.getY()<t.getY()&&l.maes.getY()>=t.getY()-t.getUpMax())){
                gameOver();
            }
        }
        else if(t.getFace()==3&&t.getDownMax()>0){
            glasers.get(i).setSize(MAZE/DIM,t.getDownMax()*(MAZE/DIM));
            glasers.get(i).setLocation(t.getX()*(MAZE/DIM)+MAZEX,(t.getY()+1)*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/vgLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(MAZE / DIM, t.getDownMax()*(MAZE / DIM), Image.SCALE_SMOOTH);
            glasers.get(i).setIcon(new ImageIcon(l22));
            glasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getX()==t.getX()&&l.maes.getY()>t.getY()&&l.maes.getY()<=t.getY()+t.getDownMax())){
                gameOver();
            }
        }
        if(t.getFace() ==4&&t.getLeftMax()>0){
            glasers.get(i).setSize(t.getLeftMax()*(MAZE/DIM),MAZE/DIM);
            glasers.get(i).setLocation((t.getX()-t.getLeftMax())*(MAZE/DIM)+MAZEX,(t.getY())*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/hgLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(t.getLeftMax()*(MAZE/DIM),MAZE/DIM, Image.SCALE_SMOOTH);
            glasers.get(i).setIcon(new ImageIcon(l22));
            glasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getY()==t.getY()&&l.maes.getX()<t.getX()&&l.maes.getX()>=t.getX()-t.getLeftMax())){
                gameOver();
            }
        }
        if(t.getFace()==2&&t.getRightMax()>0){
            glasers.get(i).setSize(t.getRightMax()*(MAZE/DIM),MAZE/DIM);
            glasers.get(i).setLocation((t.getX()+1)*(MAZE/DIM)+MAZEX,(t.getY())*(MAZE/DIM)+MAZEY);
            BufferedImage lImage = null;
            try {
                lImage = ImageIO.read(new File("image/hgLaser.png"));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image l22 = lImage.getScaledInstance(t.getRightMax()*(MAZE/DIM),MAZE/DIM, Image.SCALE_SMOOTH);
            glasers.get(i).setIcon(new ImageIcon(l22));
            glasers.get(i).setVisible(true);
            if((l.maes!=null&&l.maes.getY()==t.getY()&&l.maes.getX()>t.getX()&&l.maes.getX()<=t.getX()+t.getRightMax())){
                gameOver();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode ();
        if((c==KeyEvent.VK_UP||c==KeyEvent.VK_DOWN||c==KeyEvent.VK_LEFT||c==KeyEvent.VK_RIGHT)&&l.maes.isCanMove()) {

            if (c == KeyEvent.VK_UP) {
                if (l.maes.getY() > 0 && l.mapArray[l.maes.getX()][l.maes.getY() - 1] < 1) {
                    l.maes.setY(l.maes.getY() - 1);
                    updateMaes();
                    checkSlide();
                    iceUpCheck();
                    l.maes.setCanMove(false);
                }
            } else if (c == KeyEvent.VK_DOWN) {
                if (l.maes.getY() < DIM - 1 && l.mapArray[l.maes.getX()][l.maes.getY() + 1] < 1) {
                    l.maes.setY(l.maes.getY() + 1);
                    updateMaes();
                    checkSlide();
                    iceDownCheck();
                    l.maes.setCanMove(false);
                }
            } else if (c == KeyEvent.VK_LEFT) {
                if (l.maes.getX() > 0 && l.mapArray[l.maes.getX() - 1][l.maes.getY()] < 1) {
                    l.maes.setX(l.maes.getX() - 1);
                    updateMaes();
                    checkSlide();
                    iceLeftCheck();
                    l.maes.setCanMove(false);
                }
            } else if (c == KeyEvent.VK_RIGHT) {
                if (l.maes.getX() < DIM - 1 && l.mapArray[l.maes.getX() + 1][l.maes.getY()] < 1) {
                    l.maes.setX(l.maes.getX() + 1);
                    updateMaes();
                    checkSlide();
                    iceRightCheck();
                    l.maes.setCanMove(false);
                }
            }
            //END TURN
            if (!alreadyFired && !l.maes.isCanMove()) {
                //WIN
                if (l.maes.getX() == l.goalx && l.maes.getY() == l.goaly) {
                    Main.currentLevel++;
                    if (Main.currentLevel > Main.maxLevel[Main.character - 1]) {
                        Main.maxLevel[Main.character - 1] = Main.currentLevel;
                        BufferedWriter os = null;
                        try {
                            os = new BufferedWriter(new FileWriter("mitm.sav"));
                            os.write(Main.maxLevel[0] + " " + Main.maxLevel[1] + " " + Main.maxLevel[2] + " " + Main.maxLevel[3] + " "
                                    + Main.maxLevel[4] + " " + Main.maxLevel[5] + " " + Main.maxLevel[6] + " " + Main.maxLevel[7]);
                        } catch (IOException ef) {
                            ef.printStackTrace();
                        } finally {
                            try {
                                if (os != null) os.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    deadLabel.setText("CLEAR!");
                    deadLabel.setForeground(Color.GREEN);
                    deadLabel.setVisible(true);
                    Timer victoryTimer = new Timer();
                    victoryTimer.schedule(new endLevel(), 2000);
                }//end WIM
                else {
                    checkSwitches();
                    alreadyFired = true;

                    for (int i = 0; i < l.mList.size(); i++) {
                        monsterMove(l.mList.get(i), i);
                    }
                    for (int i = 0; i < l.sList.size(); i++) {
                        spurting(l.sList.get(i), i);
                    }

                    for (int i = 0; i < l.ctList.size(); i++) {
                        cfire(l.ctList.get(i), i);
                    }

                    for (int i = 0; i < l.ptList.size(); i++) {
                        pfire(l.ptList.get(i), i);
                    }

                    Timer timer1 = new Timer();
                    if (l.ctList.size() > 0 || l.ptList.size() > 0) timer1.schedule(new redrawLasers(), 1000);
                    else timer1.schedule(new redrawLasers(), 0);
                }
            }
        }
        else if(c==KeyEvent.VK_R){//restart level
            Main.lg.dispose();
            try {
                Main.lg=new LevelGUI(Main.currentLevel,Main.character);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if(c==KeyEvent.VK_Q){//go to character select
            Main.cs=new CharacterSelect();
            Main.lg.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private static class redrawLasers extends TimerTask {
        @Override
        public void run() {
            for(int i=0;i<clasers.size();i++){
                clasers.get(i).setVisible(false);
            }
            for(int i=0;i<plasers.size();i++){
                plasers.get(i).setVisible(false);
            }
            for(int i=0;i<l.rtList.size();i++){
                l.rtList.get(i).turn();
                BufferedImage tImage = null;
                try {
                    if(l.rtList.get(i).getFace()==1)tImage = ImageIO.read(new File("image/rturret.png"));
                    else if(l.rtList.get(i).getFace()==2)tImage = ImageIO.read(new File("image/rturret2.png"));
                    else if(l.rtList.get(i).getFace()==3)tImage = ImageIO.read(new File("image/rturret3.png"));
                    else tImage = ImageIO.read(new File("image/rturret4.png"));
                } catch (IOException e2) {
                    System.out.println("Not Found");
                }
                Image m2= tImage.getScaledInstance(MAZE / DIM, MAZE / DIM*3/2, Image.SCALE_SMOOTH);
                rTurretList.get(i).setIcon(new ImageIcon(m2));
                rfire(l.rtList.get(i), i);
            }
            for(int i=0;i<l.gtList.size();i++){
                l.gtList.get(i).turn();
                BufferedImage tImage = null;
                try {
                    if(l.gtList.get(i).getFace()==1)tImage = ImageIO.read(new File("image/gturret.png"));
                    else if(l.gtList.get(i).getFace()==2)tImage = ImageIO.read(new File("image/gturret2.png"));
                    else if(l.gtList.get(i).getFace()==3)tImage = ImageIO.read(new File("image/gturret3.png"));
                    else tImage = ImageIO.read(new File("image/gturret4.png"));
                } catch (IOException e2) {
                    System.out.println("Not Found");
                }
                Image m2= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)*3/2), Image.SCALE_SMOOTH);
                gTurretList.get(i).setIcon(new ImageIcon(m2));
                gfire(l.gtList.get(i), i);
            }
            for(int i=0;i<l.ptList.size();i++){
                l.ptList.get(i).turn();
                BufferedImage tImage = null;
                try {
                    if(l.ptList.get(i).getFace()==1)tImage = ImageIO.read(new File("image/pturret.png"));
                    else if(l.ptList.get(i).getFace()==2)tImage = ImageIO.read(new File("image/pturret2.png"));
                    else if(l.ptList.get(i).getFace()==3)tImage = ImageIO.read(new File("image/pturret3.png"));
                    else tImage = ImageIO.read(new File("image/pturret4.png"));
                } catch (IOException e2) {
                    System.out.println("Not Found");
                }
                Image m2= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)*3/2), Image.SCALE_SMOOTH);
                pTurretList.get(i).setIcon(new ImageIcon(m2));
                pfire(l.ptList.get(i),i);
            }
            for(int i=0;i<l.ctList.size();i++){
                l.ctList.get(i).turn();
                BufferedImage tImage = null;
                try {
                    if(l.ctList.get(i).getFace()==1)tImage = ImageIO.read(new File("image/cturret.png"));
                    else if(l.ctList.get(i).getFace()==2)tImage = ImageIO.read(new File("image/cturret2.png"));
                    else if(l.ctList.get(i).getFace()==3)tImage = ImageIO.read(new File("image/cturret3.png"));
                    else tImage = ImageIO.read(new File("image/cturret4.png"));
                } catch (IOException e2) {
                    System.out.println("Not Found");
                }
                Image m2= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)*3/2), Image.SCALE_SMOOTH);
                cTurretList.get(i).setIcon(new ImageIcon(m2));
                cfire(l.ctList.get(i),i);
            }

            Timer timer2 = new Timer();
            timer2.schedule(new redrawAllLasers(), 1000);

        }
    }
    private static class redrawAllLasers extends TimerTask {
        @Override
        public void run() {

            for(int i=0;i<sList.size();i++){
                flameSpurts.get(i).setVisible(false);
            }
            for(int i=0;i<clasers.size();i++){
                clasers.get(i).setVisible(false);
            }
            for(int i=0;i<plasers.size();i++){
                plasers.get(i).setVisible(false);
            }
            for(int i=0;i<rlasers.size();i++){
                rlasers.get(i).setVisible(false);
            }
            for(int i=0;i<glasers.size();i++){
                glasers.get(i).setVisible(false);
            }
            l.maes.setCanMove(true);
            currentPlayer=1;
            alreadyFired=false;
        }
    }

    public static void gameOver(){
        deadLabel.setVisible(true);
        Timer deadTimer = new Timer();
        deadTimer.schedule(new endLevel(), 2000);
    }

    private static class endLevel extends TimerTask {
        @Override
        public void run() {
            Main.lg.dispose();
            try {
                Main.lg=new LevelGUI(Main.currentLevel, Main.character);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void checkSlide(){
        if(l.mapArray[l.maes.getX()][l.maes.getY()]==-81){
            l.maes.setY(l.maes.getY()-1);
            Timer slideTimer = new Timer();
            slideTimer.schedule(new sliding(), 250);
        }
        else if(l.mapArray[l.maes.getX()][l.maes.getY()]==-82){
            l.maes.setX(l.maes.getX() + 1);
            Timer slideTimer = new Timer();
            slideTimer.schedule(new sliding(), 250);
        }
        else if(l.mapArray[l.maes.getX()][l.maes.getY()]==-83){
            l.maes.setY(l.maes.getY() + 1);
            Timer slideTimer = new Timer();
            slideTimer.schedule(new sliding(), 250);
        }
        else if(l.mapArray[l.maes.getX()][l.maes.getY()]==-84){
            l.maes.setX(l.maes.getX() - 1);
            Timer slideTimer = new Timer();
            slideTimer.schedule(new sliding(), 250);
        }
    }

    public static void iceUpCheck() {
        if (l.mapArray[l.maes.getX()][l.maes.getY()] == -80) {
            l.maes.setY(l.maes.getY() - 1);
            Timer slideTimer = new Timer();
            slideTimer.schedule(new iceUp(), 250);
        }
    }
    public static void iceRightCheck() {
        if (l.mapArray[l.maes.getX()][l.maes.getY()] == -80) {
            l.maes.setX(l.maes.getX() + 1);
            Timer slideTimer = new Timer();
            slideTimer.schedule(new iceRight(), 250);
        }
    }
    public static void iceDownCheck() {
        if (l.mapArray[l.maes.getX()][l.maes.getY()] == -80) {
            l.maes.setY(l.maes.getY() + 1);
            Timer slideTimer = new Timer();
            slideTimer.schedule(new iceDown(), 250);
        }
    }
    public static void iceLeftCheck(){
        if(l.mapArray[l.maes.getX()][l.maes.getY()]==-80){
            l.maes.setX(l.maes.getX() - 1);
            Timer slideTimer = new Timer();
            slideTimer.schedule(new iceLeft(), 250);
        }
    }

    public static void updateMaes(){
        maesLabel.setLocation(MAZEX + (l.maes.getX() * (MAZE / DIM)), MAZEY + (l.maes.getY() * (MAZE / DIM)));
    }

    private static class sliding extends TimerTask {
        @Override
        public void run() {
            updateMaes();
            checkSlide();
        }
    }

    private static class iceDown extends TimerTask {
        @Override
        public void run() {
            updateMaes();
            iceDownCheck();
        }
    }
    private static class iceRight extends TimerTask {
        @Override
        public void run() {
            updateMaes();
            iceRightCheck();
        }
    }
    private static class iceUp extends TimerTask {
        @Override
        public void run() {
            updateMaes();
            iceUpCheck();
        }
    }
    private static class iceLeft extends TimerTask {
        @Override
        public void run() {
            updateMaes();
            iceLeftCheck();
        }
    }
    public static void changeSpurtLabels(){
        for(int i=0;i<sList.size();i++){
            BufferedImage tImage = null;
            try {
                if(l.sList.get(i).getState()==2)tImage = ImageIO.read(new File(SPOUT1));
                else tImage = ImageIO.read(new File(SPOUT2));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image m2= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)), Image.SCALE_SMOOTH);
            sList.get(i).setIcon(new ImageIcon(m2));
        }
    }

    public static void monsterMove(Monster m, int i){
        if(m.getBehavior()==1){
            boolean moved=false;
            int counter=0;
            while(!moved&&counter<10){
                counter++;
                int dir=RandomGenerator.randomInt(1,4);
                int target=getTarget(m, dir);
                if(target ==0){
                    moved = true;
                    l.mapArray[l.mList.get(i).getX()][l.mList.get(i).getY()]=0;
                    moveIt(m, dir, i);
                }
            }
        }
        if(m.getBehavior()==2){
            boolean moved=false;
            int counter=0;
            while(!moved&&counter<10){
                counter++;
                int dir=RandomGenerator.randomInt(1,4);
                if(RandomGenerator.randomInt(1,3)%2==0||onTarget(m, dir, i)) {
                    int target = getTarget(m, dir);
                    if (target == 0) {
                        moved = true;
                        l.mapArray[l.mList.get(i).getX()][l.mList.get(i).getY()] = 0;
                        moveIt(m, dir, i);
                    }
                }
            }
        }
        if(m.getBehavior()==3){
            boolean moved=false;
            int counter=0;
            while(!moved&&counter<10){
                counter++;
                int dir=RandomGenerator.randomInt(1,4);
                if(RandomGenerator.randomInt(1,3)%2==0||onTarget(m, dir, i)) {
                    int target = getTarget(m, dir);
                    if (target == 0||target==1) {
                        moved = true;
                        l.mapArray[l.mList.get(i).getX()][l.mList.get(i).getY()] = 0;
                        moveIt(m, dir, i);
                    }
                }
            }
        }
    }

    public static boolean onTarget(Monster m, int d, int i){
        switch(d){
            case 1:return l.mList.get(i).getY()>l.maes.getY();
            case 2:return l.mList.get(i).getX()<l.maes.getX();
            case 3:return l.mList.get(i).getY()<l.maes.getY();
            case 4:return l.mList.get(i).getX()>l.maes.getX();
            default: return false;
        }
    }
    public static int getTarget(Monster m, int dir){
        switch(dir){
            case 1: if(m.getY()==0)return 1;
            else return checkSpot(m.getX(),m.getY()-1);
            case 2: if(m.getX()==l.dimension-1)return 1;
            else return checkSpot(m.getX()+1,m.getY());
            case 3: if(m.getY()==l.dimension-1)return 1;
            else return checkSpot(m.getX(),m.getY()+1);
            case 4: if(m.getX()==0)return 1;
            else return checkSpot(m.getX()-1,m.getY());
            default: return 1;
        }
    }

    public static void moveIt(Monster m, int dir, int i) {
            switch (dir) {
                case 1:
                    m.setY(m.getY() - 1);
                    break;
                case 2:
                    m.setX(m.getX() + 1);
                    break;
                case 3:
                    m.setY(m.getY() + 1);
                    break;
                case 4:
                    m.setX(m.getX() - 1);
                    break;
            }
        l.mapArray[l.mList.get(i).getX()][l.mList.get(i).getY()]=l.mList.get(i).getType();
            mList.get(i).setLocation(MAZEX+(l.mList.get(i).getX() * (MAZE/DIM)),MAZEY+(l.mList.get(i).getY()*(MAZE/DIM)));
            if (m.getX() == l.maes.getX() && m.getY() == l.maes.getY()) {
                gameOver();
            }
    }
    public static int checkSpot(int x, int y){
        return l.mapArray[x][y];
    }

    public static void printMap(){
        for(int i=0;i<l.dimension;i++){
            for(int j=0;j<l.dimension;j++){
                System.out.print(l.mapArray[j][i]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void checkSwitches(){
        if(l.mapArray[l.maes.getX()][l.maes.getY()]<-59&&l.mapArray[l.maes.getX()][l.maes.getY()]>-70){
            int switchNo=l.mapArray[l.maes.getX()][l.maes.getY()];
            BufferedImage tImage = null;
            try {
                tImage = ImageIO.read(new File(PRESSEDSWITCH));
            } catch (IOException e) {
                System.out.println("Not Found");
            }
            Image m4= tImage.getScaledInstance(MAZE / DIM, ((MAZE / DIM)), Image.SCALE_SMOOTH);
            switches.get(switchNo&10).setIcon(new ImageIcon(m4));
            openDoor(switchNo-(2*switchNo));
        }
    }

    public static void openDoor(int door){
        for(int i=0;i<l.dimension;i++){
            for(int j=0;j<l.dimension;j++){
                if(l.mapArray[j][i]==door)l.mapArray[j][i]=0;
                doors.get(door%10).setVisible(false);
            }
        }
    }
}
