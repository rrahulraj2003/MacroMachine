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
    private static JMenuBar menubar = new JMenuBar();			//menu bar
    private static int flip = 0;
    
    private static JButton bplay;
    private static JButton bstop;
    private static JButton bloop;
    private static JButton bedit;
    private static JButton breorder;
    private static JButton bdelete;
    private static JButton bcreate;
    private static JButton bsettings;

    public static File folder;
    public static File directory;
    private static ArrayList<String> macross;
    private static String[] macros;
    private static JList<String> list;
    private static JScrollPane scp;
    private static JPanel mainmenu;
    private static JLabel bottom;
    public static java.net.URL create = Main.class.getResource("/main/create.png");
    public static java.net.URL pcreate = Main.class.getResource("/main/pcreate.png");
    private static java.net.URL settings = Main.class.getResource("/main/settings.png");
    private static java.net.URL psettings = Main.class.getResource("/main/psettings.png");
    private static java.net.URL black = Main.class.getResource("/main/black.png");
    
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
		bplay.setBackground(SKY);
		bstop.setBackground(SKY);
		bloop.setBackground(SKY);
		bedit.setBackground(SKY);
		breorder.setBackground(SKY);
		bdelete.setBackground(SKY);
		tpanel.setPreferredSize(new Dimension(600, 50));
        bpanel.setPreferredSize(new Dimension(600, 28));
		bcreate.setIcon(new ImageIcon(create));
		bcreate.setPressedIcon(new ImageIcon(pcreate));
		bsettings.setIcon(new ImageIcon(settings));
		bsettings.setPressedIcon(new ImageIcon(psettings));
    }
    
    public static void showGeneral() {
    	cardlayout.show(cpanel, "m");
    	flip = 0;
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

    public Main() throws IOException, AWTException{

        //Frame
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
        //System.out.println(frame.getSize());
        frame.setLocation((int)size.getWidth()/2 - 300, (int)size.getHeight()/2 - 165);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.add(panel);
        
        //Panel
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(600, 350));
        
        //Toolbar
        UIManager.put("MenuItem.selectionBackground", Color.RED);
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu omg = new JMenu("omg");
        JMenu fsettings = new JMenu("Settings");
        JMenu help = new JMenu("Help");
        //menubar.setBackground(Color.WHITE);
        menubar.setBorder(BorderFactory.createEmptyBorder());
        menubar.add(file);
        menubar.add(edit);
        menubar.add(omg);
        menubar.add(fsettings);
        menubar.add(help);
        frame.setJMenuBar(menubar);				//add or subtract menubar?
        
        //TPanel
        tpanel.setLayout(new BorderLayout());
        tpanel.setPreferredSize(new Dimension(600, 50));
        tpanel.setBackground(SKY);
        
        JLabel title = new JLabel("MacroMaster");
        title.setFont(TITLE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.BLACK);
        tpanel.add(title, BorderLayout.CENTER);
        
        bcreate = new JButton(new ImageIcon(create));
        bcreate.setBackground(Color.WHITE);
        bcreate.setFocusable(false);
        bcreate.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bcreate.setPressedIcon(new ImageIcon(pcreate));
        tpanel.add(bcreate, BorderLayout.WEST);
        
        bsettings = new JButton(new ImageIcon(settings));
        bsettings.setBackground(SKY);
        bsettings.setFocusable(false);
        bsettings.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bsettings.setPressedIcon(new ImageIcon(psettings));
        tpanel.add(bsettings, BorderLayout.EAST);
        
        panel.add(tpanel, BorderLayout.NORTH);
        
        //BPanel
        bottom = new JLabel("info about the selected macro or whatever");
        bottom.setForeground(Color.BLACK);
        bpanel.add(bottom);
        bpanel.setBackground(SKY);
        bpanel.setPreferredSize(new Dimension(600, 28));
        panel.add(bpanel, BorderLayout.SOUTH);
        
        //LPanel
        bplay = new JButton("Play");
        bstop = new JButton("Stop"); //play/stop
        bloop = new JButton("Loop");
        bedit = new JButton("Edit");
        JLabel lplay = new JLabel("Play");
        JLabel lstop = new JLabel("Stop");
        JLabel lloop = new JLabel("Loop");
        JLabel ledit = new JLabel("Edit");
        
        lpanel.setLayout(new BoxLayout(lpanel, BoxLayout.Y_AXIS));
        lpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lpanel.setBackground(Color.WHITE);
        panel.add(lpanel, BorderLayout.WEST);
        
        addLButton(lpanel, bplay, lplay);
        addLButton(lpanel, bstop, lstop);
        addLButton(lpanel, bloop, lloop);
        addLButton(lpanel, bedit, ledit);
        
        bplay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//play the macro my guy
				
			}
        	
        });
        
        
        //RPanel
        breorder = new JButton("Loop");
        bdelete = new JButton("Dele");
        JLabel lreorder = new JLabel("Reor");
        JLabel ldelete = new JLabel("Dele");
        
        rpanel.setLayout(new BoxLayout(rpanel, BoxLayout.Y_AXIS));
        rpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rpanel.setBackground(Color.WHITE);
        panel.add(rpanel, BorderLayout.EAST);
        
        addLButton(rpanel, breorder, lreorder);
        addLButton(rpanel, bdelete, ldelete);
        
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
                try (Scanner s = new Scanner(new File(folder.getPath() + "\\" + list.getSelectedValue() + ".txt"))) {
                    s.nextLine();
                    String str = s.nextLine();
                    bottom.setText(str);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        bcreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(cpanel, "c");
				flip = 2;
                panel.setPreferredSize(new Dimension(400, 400));
				tpanel.setBackground(Color.BLACK);
				lpanel.setBackground(Color.BLACK);
				rpanel.setBackground(Color.BLACK);
				bpanel.setBackground(Color.BLACK);
				bplay.setBackground(Color.BLACK);
				bstop.setBackground(Color.BLACK);
				bloop.setBackground(Color.BLACK);
				bedit.setBackground(Color.BLACK);
				breorder.setBackground(Color.BLACK);
				bdelete.setBackground(Color.BLACK);
				tpanel.setPreferredSize(new Dimension(600, 20));
                bpanel.setPreferredSize(new Dimension(600, 20));
				bcreate.setIcon(new ImageIcon(black));
				bcreate.setPressedIcon(new ImageIcon(black));
				bsettings.setIcon(new ImageIcon(black));
				bsettings.setPressedIcon(new ImageIcon(black));
				//new CreateWindow(); //RIP CreateWindow
			}
        	
        });
        
        
        //Settings menu
        bsettings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(flip == 0){		//changes from main to settings
					cardlayout.show(cpanel, "s");
					flip = 1;
				}else if(flip == 1){				//changes from settings to main
					cardlayout.show(cpanel, "m");
					flip = 0;
				}
				
			}
        	
        });

        //Keystroke Execution, uhhh might be useless but reuse mouse click methods and such
        frame.addKeyListener(new KeyListener(){

        	//Action Creation
            @Override
            public void keyPressed(KeyEvent e) { //"m" = 77, "a" = 65
                System.out.println(e.getKeyCode());
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