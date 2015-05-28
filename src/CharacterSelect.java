import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dad on 5/27/2015.
 */
public class CharacterSelect extends JFrame implements ActionListener {
    JLabel maesback,finnback,nadiaback,gwendolynback,annabethback,olivierback,pipback,bridgerback;
    JButton maesb,finnb,nadiab,gwendolynb,annabethb,olivierb,pipb,bridgerb,exit;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double height = screenSize.getHeight();
    private static final int DIM = (int) (9*height/10);

    public CharacterSelect(){
        this.setResizable(true);
        Container pane = getContentPane();
        pane.setLayout(null);
        Font buttonfont = new Font("Georgia", Font.BOLD,14);

        maesb=new JButton();
        maesb.setSize(DIM/4,DIM/4);
        maesb.setLocation(DIM / 16, DIM / 16);
        maesb.setOpaque(false);
        maesb.setContentAreaFilled(false);
        maesb.setBorderPainted(false);
        maesb.setFont(buttonfont);
        maesb.setForeground(Color.GREEN);
        maesb.setActionCommand("m");
        pane.add(maesb);

        finnb=new JButton();
        finnb.setSize(DIM/4,DIM/4);
        finnb.setLocation(6 * DIM / 16, DIM / 16);
        finnb.setOpaque(false);
        finnb.setContentAreaFilled(false);
        finnb.setBorderPainted(false);
        finnb.setFont(buttonfont);
        finnb.setForeground(Color.BLUE);
        finnb.setActionCommand("f");
        pane.add(finnb);

        nadiab=new JButton();
        nadiab.setSize(DIM/4,DIM/4);
        nadiab.setLocation(11 * DIM / 16, DIM / 16);
        nadiab.setOpaque(false);
        nadiab.setContentAreaFilled(false);
        nadiab.setBorderPainted(false);
        nadiab.setFont(buttonfont);
        nadiab.setForeground(Color.YELLOW);
        nadiab.setActionCommand("n");
        pane.add(nadiab);

        gwendolynb=new JButton();
        gwendolynb.setSize(DIM/4,DIM/4);
        gwendolynb.setLocation(DIM / 16, 6 * DIM / 16);
        gwendolynb.setOpaque(false);
        gwendolynb.setContentAreaFilled(false);
        gwendolynb.setBorderPainted(false);
        gwendolynb.setFont(buttonfont);
        gwendolynb.setForeground(Color.PINK);
        gwendolynb.setActionCommand("g");
        pane.add(gwendolynb);

        annabethb=new JButton();
        annabethb.setSize(DIM/4,DIM/4);
        annabethb.setLocation(11 * DIM / 16, 6 * DIM / 16);
        annabethb.setOpaque(false);
        annabethb.setContentAreaFilled(false);
        annabethb.setBorderPainted(false);
        annabethb.setFont(buttonfont);
        annabethb.setForeground(Color.MAGENTA);
        annabethb.setActionCommand("a");
        pane.add(annabethb);

        olivierb=new JButton();
        olivierb.setSize(DIM/4,DIM/4);
        olivierb.setLocation(DIM / 16, 11 * DIM / 16);
        olivierb.setOpaque(false);
        olivierb.setContentAreaFilled(false);
        olivierb.setBorderPainted(false);
        olivierb.setFont(buttonfont);
        olivierb.setForeground(Color.ORANGE);
        olivierb.setActionCommand("o");
        pane.add(olivierb);

        pipb=new JButton();
        pipb.setSize(DIM/4,DIM/4);
        pipb.setLocation(6 * DIM / 16, 11 * DIM / 16);
        pipb.setOpaque(false);
        pipb.setContentAreaFilled(false);
        pipb.setBorderPainted(false);
        pipb.setFont(buttonfont);
        pipb.setForeground(Color.GREEN);
        pipb.setActionCommand("p");
        pane.add(pipb);

        bridgerb=new JButton();
        bridgerb.setSize(DIM/4,DIM/4);
        bridgerb.setLocation(11 * DIM / 16, 11 * DIM / 16);
        bridgerb.setOpaque(false);
        bridgerb.setContentAreaFilled(false);
        bridgerb.setBorderPainted(false);
        bridgerb.setFont(buttonfont);
        bridgerb.setForeground(Color.BLUE);
        bridgerb.setActionCommand("b");
        pane.add(bridgerb);

        maesback=new JLabel();
        maesback.setSize(DIM/4,DIM/4);
        maesback.setLocation(DIM/16,DIM/16);
        BufferedImage tImage = null;
        try {
            tImage = ImageIO.read(new File("image/maesback.png"));
        } catch (IOException e) {
            System.out.println("Not Found");
        }
        Image m4= tImage.getScaledInstance(DIM/4, DIM/4, Image.SCALE_SMOOTH);
        maesback.setIcon(new ImageIcon(m4));
        pane.add(maesback);

        finnback=new JLabel();
        finnback.setSize(DIM/4,DIM/4);
        finnback.setLocation(6*DIM/16,DIM/16);
        tImage = null;
        try {
            tImage = ImageIO.read(new File("image/finnback.png"));
        } catch (IOException e) {
            System.out.println("Not Found");
        }
        m4= tImage.getScaledInstance(DIM/4, DIM/4, Image.SCALE_SMOOTH);
        finnback.setIcon(new ImageIcon(m4));
        pane.add(finnback);

        nadiaback=new JLabel();
        nadiaback.setSize(DIM/4,DIM/4);
        nadiaback.setLocation(11*DIM/16,DIM/16);
        tImage = null;
        try {
            tImage = ImageIO.read(new File("image/nadiaback.png"));
        } catch (IOException e) {
            System.out.println("Not Found");
        }
        m4= tImage.getScaledInstance(DIM/4, DIM/4, Image.SCALE_SMOOTH);
        nadiaback.setIcon(new ImageIcon(m4));
        pane.add(nadiaback);

        gwendolynback=new JLabel();
        gwendolynback.setSize(DIM/4,DIM/4);
        gwendolynback.setLocation(DIM/16,6*DIM/16);
        tImage = null;
        try {
            tImage = ImageIO.read(new File("image/gwendolynback.png"));
        } catch (IOException e) {
            System.out.println("Not Found");
        }
        m4= tImage.getScaledInstance(DIM/4, DIM/4, Image.SCALE_SMOOTH);
        gwendolynback.setIcon(new ImageIcon(m4));
        pane.add(gwendolynback);

        annabethback=new JLabel();
        annabethback.setSize(DIM/4,DIM/4);
        annabethback.setLocation(11*DIM/16,6*DIM/16);
        tImage = null;
        try {
            tImage = ImageIO.read(new File("image/annabethback.png"));
        } catch (IOException e) {
            System.out.println("Not Found");
        }
        m4= tImage.getScaledInstance(DIM/4, DIM/4, Image.SCALE_SMOOTH);
        annabethback.setIcon(new ImageIcon(m4));
        pane.add(annabethback);

        olivierback=new JLabel();
        olivierback.setSize(DIM/4,DIM/4);
        olivierback.setLocation(DIM/16,11*DIM/16);
        tImage = null;
        try {
            tImage = ImageIO.read(new File("image/olivierback.png"));
        } catch (IOException e) {
            System.out.println("Not Found");
        }
        m4= tImage.getScaledInstance(DIM/4, DIM/4, Image.SCALE_SMOOTH);
        olivierback.setIcon(new ImageIcon(m4));
        pane.add(olivierback);

        pipback=new JLabel();
        pipback.setSize(DIM/4,DIM/4);
        pipback.setLocation(6*DIM/16,11*DIM/16);
        tImage = null;
        try {
            tImage = ImageIO.read(new File("image/pipback.png"));
        } catch (IOException e) {
            System.out.println("Not Found");
        }
        m4= tImage.getScaledInstance(DIM/4, DIM/4, Image.SCALE_SMOOTH);
        pipback.setIcon(new ImageIcon(m4));
        pane.add(pipback);

        bridgerback=new JLabel();
        bridgerback.setSize(DIM/4,DIM/4);
        bridgerback.setLocation(11*DIM/16,11*DIM/16);
        tImage = null;
        try {
            tImage = ImageIO.read(new File("image/bridgerback.png"));
        } catch (IOException e) {
            System.out.println("Not Found");
        }
        m4= tImage.getScaledInstance(DIM/4, DIM/4, Image.SCALE_SMOOTH);
        bridgerback.setIcon(new ImageIcon(m4));
        pane.add(bridgerback);

        maesb.addActionListener(this);
        finnb.addActionListener(this);
        nadiab.addActionListener(this);
        gwendolynb.addActionListener(this);
        annabethb.addActionListener(this);
        olivierb.addActionListener(this);
        pipb.addActionListener(this);
        bridgerb.addActionListener(this);

        this.setSize(DIM, DIM);
        pane.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setTitle("Maes in the Maze");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if ("m".equals(e.getActionCommand())) {
            Main.character=1;
            Main.currentLevel=Main.maxLevel[0];
            Main.cs.dispose();
            Main.levelfile="maes.lvl";
            try {
                Main.lg=new LevelGUI(Main.currentLevel, Main.character);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if ("f".equals(e.getActionCommand())) {
            Main.character=2;
            Main.currentLevel=Main.maxLevel[1];
            Main.cs.dispose();
            Main.levelfile="finn.lvl";
            try {
                Main.lg=new LevelGUI(Main.currentLevel, Main.character);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if ("n".equals(e.getActionCommand())) {
            Main.character=3;
            Main.currentLevel=Main.maxLevel[2];
            Main.cs.dispose();
            Main.levelfile="nadia.lvl";
            try {
                Main.lg=new LevelGUI(Main.currentLevel, Main.character);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if ("g".equals(e.getActionCommand())) {
            Main.character=4;
            Main.currentLevel=Main.maxLevel[3];
            Main.cs.dispose();
            Main.levelfile="gwendolyn.lvl";
            try {
                Main.lg=new LevelGUI(Main.currentLevel, Main.character);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if ("a".equals(e.getActionCommand())) {
            Main.character=5;
            Main.currentLevel=Main.maxLevel[4];
            Main.cs.dispose();
            Main.levelfile="annabeth.lvl";
            try {
                Main.lg=new LevelGUI(Main.currentLevel, Main.character);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if ("o".equals(e.getActionCommand())) {
            Main.character=6;
            Main.currentLevel=Main.maxLevel[5];
            Main.cs.dispose();
            Main.levelfile="olivier.lvl";
            try {
                Main.lg=new LevelGUI(Main.currentLevel, Main.character);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if ("p".equals(e.getActionCommand())) {
            Main.character=7;
            Main.currentLevel=Main.maxLevel[6];
            Main.cs.dispose();
            Main.levelfile="pip.lvl";
            try {
                Main.lg=new LevelGUI(Main.currentLevel, Main.character);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if ("b".equals(e.getActionCommand())) {
            Main.character=8;
            Main.currentLevel=Main.maxLevel[7];
            Main.levelfile="bridger.lvl";
            Main.cs.dispose();
            try {
                Main.lg=new LevelGUI(Main.currentLevel, Main.character);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
