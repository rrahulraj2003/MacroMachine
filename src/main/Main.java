/*

Issues/Fix these stuff:
* Wrong dimensions (not really fixed but might cause problems, see Issue 1.a
* Complilation and Execution
* Resizing of window and keeping all other buttons along the sides somehow
* 

*/

package main;

import java.awt.*;

//import javax.imageio.ImageIO;
//import javax.imageio.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
//import java.awt.image.BufferedImage;
//import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;




public class Main{

    //Basic Text Font
    public static Font TITLE = new Font(Font.SERIF, Font.BOLD, 36);
    //private static Color PRIME = new Color(85, 221, 255);
    public static Color SKY = new Color(120, 240, 255);
    public static Color DARKER = new Color(0, 140, 160);
    public static Color SELECTION = new Color(210, 250, 255);
    public static Color SELECTION2 = new Color(195,250, 255);
    //private static Font TEXT = new Font(Font.SERIF, Font.BOLD, 15); //figure out font for button text

    //JFrame and JPanel and stuff
    public static JFrame frame = new JFrame();		//main frame
    private static JPanel panel = new JPanel();					//main panel
    private static JPanel tpanel = new JPanel(); 				//title panel
    private static JPanel lpanel = new JPanel(); 				//left panel
    private static JPanel cpanel = new JPanel(); 				//center panel
    private static JPanel rpanel = new JPanel(); 				//right panel
    private static JPanel bpanel = new JPanel();				//bottom panel
    private static CardLayout cardlayout = new CardLayout();	//CardLayout
    
    private static JButton badd;
    private static JButton bplay;
    private static JButton bedit;
    private static JButton btrash;

    public static File folder;
    public static File directory;
    private static ArrayList<String> macross;
    private static String[] macros;
    private static JList<String> list;
    private static JScrollPane scp;
    private static JPanel mainmenu;
    private static JLabel bottom;
    private static ArrayList<Action> actions;
    public static java.net.URL create = Main.class.getResource("/main/create.png");
    public static java.net.URL pcreate = Main.class.getResource("/main/pcreate.png");
    
    //Basic Colors
    //private static final Color BK = new Color(5, 19, 54); //background color
    //private static final Color WHITE = Color.WHITE;
    //private static final Color YELLOW = Color.YELLOW;

    //Misc.
    //private static int x = 0;
    //private static int y = 0;
    //private static int cascade = 0;
    
    public void addLButton(JPanel panel, JButton button, JLabel label) {
    	panel.add(button);
        panel.add(label);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(SKY);
        button.setForeground(Color.BLACK);
        button.setFocusable(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    }
    
    public static void revert() {
    	tpanel.setBackground(SKY);
		lpanel.setBackground(Color.WHITE);
		rpanel.setBackground(Color.WHITE);
		bpanel.setBackground(SKY);
		badd.setBackground(SKY);
		bplay.setBackground(SKY);
		bedit.setBackground(SKY);
		btrash.setBackground(SKY);
		tpanel.setPreferredSize(new Dimension(500, 50));
        bpanel.setPreferredSize(new Dimension(500, 28));
        lpanel.setPreferredSize(new Dimension(65, 500));
        rpanel.setPreferredSize(new Dimension(65, 500));
    }
    
    public static void showGeneral() {
    	cardlayout.show(cpanel, "m");
    }

    public static void addToDirectory(String name){

        //Add new macro name to directory
        
        directory.getName();

        File newDir = new File(folder.getPath() + "\\newdirectory.txt");
        try {
            newDir.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(newDir)) {
            for(String s: macross){
                writer.write(s + "\n");
            }
            writer.write(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Someday, in the near future, optimize this absolute mess

        directory = new File(newDir.getPath());
        newDir = new File(folder.getPath() + "\\macro-directory.txt");
        newDir.renameTo(new File(folder.getPath() + "\\newdir.txt"));
        directory.renameTo(new File(folder.getPath() + "\\macro-directory.txt"));
        
        directory = new File(folder.getPath() + "\\macro-directory.txt");
        newDir = new File(folder.getPath() + "\\newdir.txt");

        newDir.delete();

        //Change menu card in home layout
        macross.add(name);
        macros = new String[macross.size()];
        for(int i = 0; i < macross.size(); i++){
            macros[i] = macross.get(i);
        }
        list.setListData(macros);
        mainmenu.remove(scp);
        scp = new JScrollPane(list);
        mainmenu.add(scp);
        mainmenu.setLayout(new GridLayout(1, 1));
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setFocusable(false);
        list.setSelectionBackground(SELECTION2);
        scp.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, SKY));
        frame.setVisible(true);

    }

    static int num;

    public static void runMacro(){
        
        if(list.getSelectedValue() != null){

            actions = new ArrayList<Action>();
            Action a = new Action(0);

            try {
                Scanner s = new Scanner(new File(folder.getPath() + "\\" + list.getSelectedValue() + ".txt"));
                s.nextLine(); s.nextLine(); s.nextLine();
                

                while(s.hasNextLine()){

                    StringBuilder task = new StringBuilder(s.nextLine()); task.delete(0, task.indexOf(">") + 2);

                    if(task.charAt(0) == '1' || task.charAt(0) == '2' || task.charAt(0) == '3'){
                        a = new Action(Integer.parseInt(task.charAt(0) + "")); task.delete(0, task.indexOf(" ") + 1);
                        a.setX(Integer.parseInt(task.substring(0, task.indexOf(" ")))); task.delete(0, task.indexOf(" ") + 1);
                        a.setY(Integer.parseInt(task.substring(0, task.indexOf(" ")))); task.delete(0, task.indexOf(" ") + 1);
                        a.setB(Integer.parseInt(task.substring(0, task.indexOf(" ")))); task.delete(0, task.indexOf(" ") + 1);
                        a.setTime(Long.parseLong(task.toString()) + 3000);
                        actions.add(a);
                    }else if(task.charAt(0) == '4'){
                        a = new Action(Integer.parseInt(task.charAt(0) + "")); task.delete(0, task.indexOf(" ") + 1);
                        a.setBool(Boolean.parseBoolean(task.substring(0, task.indexOf(" ")))); task.delete(0, task.indexOf(" ") + 1);
                        a.setTime(Long.parseLong(task.toString()) + 3000);
                        actions.add(a);
                    }else if(task.charAt(0) == '5' || task.charAt(0) == '6'){
                        a = new Action(Integer.parseInt(task.charAt(0) + "")); task.delete(0, task.indexOf(" ") + 1);
                        a.setX(Integer.parseInt(task.substring(0, task.indexOf(" ")))); task.delete(0, task.indexOf(" ") + 1);
                        a.setBool(Boolean.parseBoolean(task.substring(0, task.indexOf(" ")))); task.delete(0, task.indexOf(" ") + 1);
                        a.setTime(Long.parseLong(task.toString()) + 3000);
                        actions.add(a);
                    }else{
                        System.out.println("Couldn't read properly, dummy");
                    }
                }
            } catch (FileNotFoundException e1) { e1.printStackTrace(); JOptionPane.showMessageDialog(Main.frame, "File not found.", "Nonexistence Error", JOptionPane.INFORMATION_MESSAGE); }
            
            //run the actions
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    bottom.setText(3 + "");
                }
            }, 0);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    bottom.setText(2 + "");
                }
            }, 1000);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    bottom.setText(1 + "");
                }
            }, 2000);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    bottom.setText(" ");
                    Main.frame.setAlwaysOnTop(false);
                    Main.frame.toBack();
                }
            }, 3000);
                

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Main.frame.setAlwaysOnTop(true);
                    bottom.setText("Macro Played!");
                }
            }, (actions.get(actions.size() - 1).getTime()) + 3000);


            for(int x = 0 ; x < actions.size(); x++){
                int z = x;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        actions.get(z).run();
                    }
                }, actions.get(z).getTime());
                
            }
        
            //timer.schedule(task1, 1000);

        }
    }

    public Main() throws IOException, AWTException{

        //Frame
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
        //System.out.println(frame.getSize());
        frame.setLocation((int)size.getWidth()/2 - 300, (int)size.getHeight()/2 - 150);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.add(panel);
        
        //Panel
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(500, 280));
        
        //TPanel
        tpanel.setLayout(new BorderLayout());
        tpanel.setPreferredSize(new Dimension(500, 50));
        tpanel.setBackground(SKY);
        
        JLabel title = new JLabel("MacroMaster");
        title.setFont(TITLE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.BLACK);
        tpanel.add(title, BorderLayout.CENTER);
        
        panel.add(tpanel, BorderLayout.NORTH);
        
        //BPanel
        bottom = new JLabel("info about the selected macro or whatever");
        bottom.setForeground(Color.BLACK);
        bpanel.add(bottom);
        bpanel.setBackground(SKY);
        bpanel.setPreferredSize(new Dimension(400, 28));
        panel.add(bpanel, BorderLayout.SOUTH);
        
        //LPanel
        badd = new JButton("Add");
        bplay = new JButton("Play"); //play/stop
        JLabel ladd = new JLabel("Add");
        JLabel lplay = new JLabel("Play");
        
        lpanel.setLayout(new BoxLayout(lpanel, BoxLayout.Y_AXIS));
        lpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lpanel.setBackground(Color.WHITE);
        panel.add(lpanel, BorderLayout.WEST);
        
        addLButton(lpanel, badd, ladd);
        addLButton(lpanel, bplay, lplay);
        
        
        //RPanel
        bedit = new JButton("Edit");
        btrash = new JButton("Trs");
        JLabel ledit = new JLabel("Edit");
        JLabel ltrash = new JLabel("Trs");
        
        rpanel.setLayout(new BoxLayout(rpanel, BoxLayout.Y_AXIS));
        rpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rpanel.setBackground(Color.WHITE);
        panel.add(rpanel, BorderLayout.EAST);
        
        addLButton(rpanel, bedit, ledit);
        addLButton(rpanel, btrash, ltrash);
        
        //CPanel, the motherload, container of the "cards"
        mainmenu = new JPanel();
        CreateMenu createmenu = new CreateMenu();
        JPanel settingsmenu = new JPanel();
        
        panel.add(cpanel, BorderLayout.CENTER);
        cpanel.setLayout(cardlayout);
        mainmenu.setBackground(Color.WHITE);
        mainmenu.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, SKY));
        cpanel.add(mainmenu, "m");
        cpanel.add(createmenu, "c");
        cpanel.add(settingsmenu, "s");
        frame.setVisible(true);
        
        //Menu card, homepage if you will
        macross = new ArrayList<String>();
        list = new JList<String>();
        scp = new JScrollPane(list);

        //Settings menu
        settingsmenu.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, SKY));
        settingsmenu.setBackground(Color.WHITE);

        //Frame is packing
        frame.pack();

        //File Choosing Process
        JOptionPane.showMessageDialog(frame, "Please select a folder of your\n" + "choice to store your macros.", "Macro Initiation", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(frame);
        folder = fc.getSelectedFile();

        //Homepage filling
        if(folder == null){
            System.exit(0);
        }else{
            directory = new File(folder.getPath() + "\\macro-directory.txt");

            if(!directory.exists()){
                directory.createNewFile();
            }else{
                Scanner s = new Scanner(directory);
                while(s.hasNextLine()){
                    macross.add(s.nextLine());
                }
                s.close();

                macros = new String[macross.size()];
                for(int i = 0; i < macross.size(); i++){
                    macros[i] = macross.get(i);
                }
                list = new JList<String>(macros);
                scp = new JScrollPane(list);
                mainmenu.add(scp);
                mainmenu.setLayout(new GridLayout(1, 1));
                list.setLayoutOrientation(JList.VERTICAL);
                list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                list.setSelectionBackground(SELECTION2);
                list.setFocusable(false);
                scp.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, SKY));
                frame.setVisible(true);
            }
        }

        //Changing infobar to display info of selected
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                if(list.getSelectedValue() != null){

                    try (Scanner s = new Scanner(new File(folder.getPath() + "\\" + list.getSelectedValue() + ".txt"))) {
                        s.nextLine();
                        String str = s.nextLine();
                        bottom.setText(str);
                        s.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        badd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(cpanel, "c");
                panel.setPreferredSize(new Dimension(500, 280));
				tpanel.setBackground(Color.BLACK);
				lpanel.setBackground(Color.BLACK);
				rpanel.setBackground(Color.BLACK);
				bpanel.setBackground(Color.BLACK);
				badd.setBackground(Color.BLACK);
				bplay.setBackground(Color.BLACK);
				bedit.setBackground(Color.BLACK);
				btrash.setBackground(Color.BLACK);
				tpanel.setPreferredSize(new Dimension(400, 20));
                bpanel.setPreferredSize(new Dimension(400, 20));
                lpanel.setPreferredSize(new Dimension(40, 500));
                rpanel.setPreferredSize(new Dimension(40, 500));
				//new CreateWindow(); //RIP CreateWindow
			}
        	
        });

        bedit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(list.getSelectedValue() != null){
                    ProcessBuilder p = new ProcessBuilder("Notepad.exe", folder.getPath() + "\\" + list.getSelectedValue() + ".txt");
                    try {
                        p.start();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                
            }

        });

        bplay.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                runMacro();
            }
        });

        btrash.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                //delete list.getSelectedValue() macro and update 
                
            }

        });
        
        //Keystroke Execution, uhhh might be useless but reuse mouse click methods and such
        frame.addKeyListener(new KeyListener(){

        	//Action Creation
            @Override
            public void keyPressed(KeyEvent e) { //"m" = 77, "a" = 65
                //System.out.println(e.getKeyCode());
                //keyPress(e);
                //if(createmenu.recording){
                //    createmenu.displayRecording(e);
                //}
            }

            @Override
            public void keyReleased(KeyEvent e) { }
            
            @Override
            public void keyTyped(KeyEvent e) { }
            
        });

        //Closing Window Execution
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //any method that is executed when the window is closed
            }
        });

    }

    public static void main(String[] args) throws AWTException, IOException {

        new Main();
        //System.out.println(MouseInfo.getPointerInfo().getLocation());
        //System.out.println( java.awt.Toolkit.getDefaultToolkit().getScreenSize() );

    }
    
}

/*

    JTextArea t = new JTextArea("OOO");
    t.setFont(TEXT);
    t.setForeground(COLOR);
    t.setBounds(0, 40, 100, 50);
    panel.add(t);

*/