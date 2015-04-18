import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Dad on 4/18/2015.
 */
public class LevelGUI extends JFrame implements ActionListener, MouseListener {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 900;
    private static final int MAZE = 8*HEIGHT/10;
    private static final int MAZEY = HEIGHT/10;
    private static final int MAZEX = WIDTH-MAZE-MAZEY;
    public static int DIM;

    protected static JLabel box;
    protected static JLabel [][] cell;


    public LevelGUI(int levelNumber) throws IOException{
        Level l;
        BufferedReader input=new BufferedReader(new FileReader("levels.txt" ));
        boolean foundLevel=false;
        while(foundLevel==false) {
            String[] lineread=input.readLine().split(" ");
            DIM=Integer.parseInt(lineread[1]);
            if (Integer.parseInt(lineread[0]) == levelNumber) {
                System.out.println("Found Level "+levelNumber+".  DIM is "+DIM);
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
        System.out.println("Found Level "+levelNumber+".  DIM is "+DIM);
        l=new Level(levelNumber,DIM,mA);
        //System.out.println("Level "+l.number+".  Maes x,y is "+l.maes.getX()+","+l.maes.getY());
        this.setResizable(false);
        Container pane = getContentPane();
        pane.setLayout(null);



        cell=new JLabel[DIM][DIM];

        for(int i=0;i<DIM;i++){
            for(int j=0;j<DIM;j++){
                if(l.mapArray[j][i]==1) {
                    System.out.println("wall at "+j+","+i);
                    cell[j][i] = new JLabel();
                    cell[j][i].setSize(MAZE/DIM,MAZE/DIM);
                    cell[j][i].setLocation(MAZEX+(j*(MAZE/DIM)),MAZEY+(i*(MAZE/DIM)));
                    cell[j][i].setBackground(Color.BLUE);
                    cell[j][i].setOpaque(true);
                    pane.add(cell[j][i]);
                }
            }
        }

        box=new JLabel();
        box.setSize(MAZE,MAZE);
        box.setLocation(MAZEX, MAZEY);
        box.setBackground(Color.LIGHT_GRAY);
        box.setOpaque(true);
        pane.add(box);

        pane.addMouseListener(this);
        this.setSize(WIDTH,HEIGHT);
        this.setVisible(true);
        this.setTitle("Maes in the Maze");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }





    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
