import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Dad on 5/27/2015.
 */
public class TitleScreen extends JFrame implements ActionListener {
    public JTextField nameInput;
    public JLabel background;
    public JLabel askName;
    public JButton start,options,quit;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double height = screenSize.getHeight();
    private static final int DIM = (int) (9*height/10);


    public TitleScreen(){
        this.setResizable(true);
        Container pane = getContentPane();
        pane.setLayout(null);
        Font askFont = new Font("Georgia", Font.BOLD,32);
        Font titleFont = new Font("Georgia", Font.BOLD,24);

        askName=new JLabel("Enter your name:", SwingConstants.CENTER);
        askName.setForeground(Color.WHITE);
        askName.setSize(DIM/3, 50);
        askName.setLocation(DIM/3, DIM/2-60);
        askName.setFont(askFont);
        pane.add(askName);

        nameInput=new JTextField();
        nameInput.setLocation(DIM/3,DIM/2);
        nameInput.setSize(DIM / 3, 50);
        nameInput.setFont(askFont);
        nameInput.setHorizontalAlignment(JTextField.CENTER);
        nameInput.setActionCommand("Start Game");
        nameInput.addActionListener(action);
        pane.add(nameInput);

        start = new JButton("Play");
        start.setLocation(4*DIM/9,DIM/2+60);
        start.setSize(DIM / 9, 50);
        start.setFont(titleFont);
        start.setActionCommand("Start Game");
        start.addActionListener(action);
        pane.add(start);

        quit=new JButton("Exit");
        quit.setLocation(4*DIM/9,DIM/2+130);
        quit.setSize(DIM / 9, 50);
        quit.setFont(titleFont);
        quit.setActionCommand("Exit Game");
        quit.addActionListener(action);
        pane.add(quit);

        start.addActionListener(this);
        quit.addActionListener(this);

        background=new JLabel();
        background.setLocation(0,0);
        background.setSize(DIM, DIM);
        BufferedImage gImage = null;
        try {
            gImage = ImageIO.read(new File("image/titleBack.png"));
        } catch (IOException e) {
            System.out.println("Not Found");
        }
        Image m2= gImage.getScaledInstance(DIM, DIM, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(m2));
        pane.add(background);

        this.setSize(DIM,DIM);
        pane.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setTitle("Maes in the Maze");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    Action action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Start Game".equals(e.getActionCommand())) {
                String name=nameInput.getText().toString();
                Main.saveName=name+".sav";
                File f=new File(Main.saveName);
                if(f.exists()){
                    loadGame();
                }
                else{
                    //need to check with user if they want a new game
                    Object[] options = {"Yes, let's do this.",
                            "No, let me try my name again."};
                    int n = JOptionPane.showOptionDialog(Main.ts,
                            "I can't find a save file under that name."
                                    + "  Do you want to start a new game?",
                            "New Name",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);

                    if(n==0) {
                        BufferedWriter os = null;
                        try {
                            os = new BufferedWriter(new FileWriter(Main.saveName));
                            os.write("1 1 1 1 1 1 1 1");
                        } catch (IOException ef) {
                            ef.printStackTrace();
                        } finally {
                            try {
                                if (os != null) os.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        loadGame();
                    }
                }
            }
            else {
                System.exit(0);
            }
        }
    };



  /*
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Start Game".equals(e.getActionCommand())) {
            String name=nameInput.getText().toString();
            Main.saveName=name+".sav";
            File f=new File(Main.saveName);
            if(f.exists()){
                loadGame();
            }
            else{
                //need to check with user if they want a new game
                Object[] options = {"Yes, let's do this.",
                        "No, let me try my name again."};
                int n = JOptionPane.showOptionDialog(this,
                        "I can't find a save file under that name."
                                + "  Do you want to start a new game?",
                        "New Name",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if(n==0) {
                    BufferedWriter os = null;
                    try {
                        os = new BufferedWriter(new FileWriter(Main.saveName));
                        os.write("1 1 1 1 1 1 1 1");
                    } catch (IOException ef) {
                        ef.printStackTrace();
                    } finally {
                        try {
                            if (os != null) os.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    loadGame();
                }
            }
        }
        else {
            System.exit(0);
        }
    }
*/
    public static void loadGame(){
        BufferedReader is=null;
        try {
            is = new BufferedReader(new FileReader(Main.saveName));
            String[] mls=is.readLine().split(" ");
            for(int i=0;i<8;i++){
                Main.maxLevel[i]=Integer.parseInt(mls[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }//done getting max levels
        Main.ts.dispose();
        Main.cs=new CharacterSelect();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
