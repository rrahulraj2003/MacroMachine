/*

    ---------------------------
    || RIP CreateWindow.java ||
    ||       2022-2022       ||
    ---------------------------

*/
package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.event.*;

import javax.swing.*;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelListener;

public class CreateMenu extends JPanel implements NativeMouseInputListener, NativeMouseWheelListener, NativeKeyListener {
	
	private static final long serialVersionUID = 1L;
	
	public static Font HEAD = new Font(Font.SANS_SERIF, Font.BOLD, 14);
	private static JButton record;
	private static File macro;
	private static JTextField namefield;
	private static JTextField infofield;
	private static JTextArea codearea;
	private static FileWriter writer;

	public static int isKey(String key){

		switch (key) {
			case "A": return KeyEvent.VK_A;
			case "B": return KeyEvent.VK_B;
			case "C": return KeyEvent.VK_C;
			case "D": return KeyEvent.VK_D;
			case "E": return KeyEvent.VK_E;
			case "F": return KeyEvent.VK_F;
			case "G": return KeyEvent.VK_G;
			case "H": return KeyEvent.VK_H;
			case "I": return KeyEvent.VK_I;
			case "J": return KeyEvent.VK_J;
			case "K": return KeyEvent.VK_K;
			case "L": return KeyEvent.VK_L;
			case "M": return KeyEvent.VK_M;
			case "N": return KeyEvent.VK_N;
			case "O": return KeyEvent.VK_O;
			case "P": return KeyEvent.VK_P;
			case "Q": return KeyEvent.VK_Q;
			case "R": return KeyEvent.VK_R;
			case "S": return KeyEvent.VK_S;
			case "T": return KeyEvent.VK_T;
			case "U": return KeyEvent.VK_U;
			case "V": return KeyEvent.VK_V;
			case "W": return KeyEvent.VK_W;
			case "X": return KeyEvent.VK_X;
			case "Y": return KeyEvent.VK_Y;
			case "Z": return KeyEvent.VK_Z;
			case "Back Quote": return KeyEvent.VK_BACK_QUOTE;
			case "0": return KeyEvent.VK_0;
			case "1": return KeyEvent.VK_1;
			case "2": return KeyEvent.VK_2;
			case "3": return KeyEvent.VK_3;
			case "4": return KeyEvent.VK_4;
			case "5": return KeyEvent.VK_5;
			case "6": return KeyEvent.VK_6;
			case "7": return KeyEvent.VK_7;
			case "8": return KeyEvent.VK_8;
			case "9": return KeyEvent.VK_9;
			case "Minus": return KeyEvent.VK_MINUS;
			case "Equals": return KeyEvent.VK_EQUALS;
			case "Open Bracket": return KeyEvent.VK_OPEN_BRACKET;
			case "Close Bracket": return KeyEvent.VK_CLOSE_BRACKET;
			case "Back Slash": return KeyEvent.VK_BACK_SLASH;
			case "Semicolon": return KeyEvent.VK_SEMICOLON;
			case "Quote": return KeyEvent.VK_QUOTE;
			case "Comma": return KeyEvent.VK_COMMA;
			case "Period": return KeyEvent.VK_PERIOD;
			case "Slash": return KeyEvent.VK_SLASH;
			
			case "Ctrl": return KeyEvent.VK_CONTROL;
			case "Shift": return KeyEvent.VK_SHIFT;
			case "Tab": return KeyEvent.VK_TAB;
			case "Enter": return KeyEvent.VK_ENTER;
			case "Space": return KeyEvent.VK_SPACE;
			case "Meta": return KeyEvent.VK_META;
			case "Caps Lock": return KeyEvent.VK_CAPS_LOCK;
			case "Backspace": return KeyEvent.VK_BACK_SPACE;

			case "Up": return KeyEvent.VK_UP;
			case "Down": return KeyEvent.VK_DOWN;
			case "Left": return KeyEvent.VK_LEFT;
			case "Right": return KeyEvent.VK_RIGHT;
			
			case "F1": return KeyEvent.VK_F1;
			case "F2": return KeyEvent.VK_F2;
			case "F3": return KeyEvent.VK_F3;
			case "F4": return KeyEvent.VK_F4;
			case "F5": return KeyEvent.VK_F5;
			case "F6": return KeyEvent.VK_F6;
			case "F7": return KeyEvent.VK_F7;
			case "F8": return KeyEvent.VK_F8;
			case "F9": return KeyEvent.VK_F9;
			case "F10": return KeyEvent.VK_F10;
			case "F11": return KeyEvent.VK_F11;
			case "F12": return KeyEvent.VK_F12;

			case "Delete": return KeyEvent.VK_DELETE;
			case "Home": return KeyEvent.VK_HOME;
			case "End": return KeyEvent.VK_END;
			case "Insert": return KeyEvent.VK_INSERT;
			case "Page Up": return KeyEvent.VK_PAGE_UP;
			case "Page Down": return KeyEvent.VK_PAGE_DOWN;
		}

		return -1;
			
	}
	
	public static boolean validName(String str){

		if(str.contains("\\") || str.contains("/") || str.contains(":") || str.contains("*") || str.contains("?") || str.contains("\"") || str.contains("<") || str.contains(">") || str.contains("|")){
			return false;
		}
		return true;
	
	}

	public static boolean originalName(String str, File file){
		try (Scanner s = new Scanner(file)) {

			while(s.hasNextLine()){
				if(str.toLowerCase().equals(s.nextLine().toLowerCase())){
					return false;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}

	private static boolean runnin = false;
	private static ArrayList<String> tasks = new ArrayList<String>();
	private static long startTime = 0;

	public static void start(){
		runnin = true;
		startTime = System.currentTimeMillis();
	}

	static ArrayList<String> a = new ArrayList<String>();

	public static void add(int x, int y, int b, int task, boolean bool, long time){
		if(task < 4){
			String t = "Click -> 1";
			if(task == 2){
				t = "Move -> 2";
			}else if(task == 3){
				t = "Release -> 3";
			}
			tasks.add(t + " " + x + " " + y + " " + b + " " + time);
		}else if(task == 4){
			tasks.add("Scroll -> 4 " + bool + " " + time);
		}else if(task == 5 || task == 6){
			tasks.add((task == 5 ? "KeyPress -> 5" : "KeyRelease -> 6") + " " + x + " " + bool + " " + time);
		}
	}

	public void nativeMousePressed(NativeMouseEvent e) { //1 Click	
		System.out.println("Mouse press: (" + e.getButton() + ") " + e.getX() + " , " + e.getY());
		if(!runnin) start();
		Integer ee = e.getX();
		Integer x = Integer.valueOf(ee);
		add(x, e.getY(), e.getButton(), 1, false, System.currentTimeMillis() - startTime);
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
		if(!runnin) start();
		add(e.getX(), e.getY(), 1 /* keep it to left click for now */, 2, false, System.currentTimeMillis() - startTime);
	}

	public void nativeMouseDragged(NativeMouseEvent e) { //2 Drag
		System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
		if(!runnin) start();
		add(e.getX(), e.getY(), 1 /* keep it to left click for now */, 2, false, System.currentTimeMillis() - startTime);
	}

	public void nativeMouseReleased(NativeMouseEvent e){ //3 Release
		System.out.println("Mouse Released: " + e.getX() + ", " + e.getY());
		if(!runnin) start();
		add(e.getX(), e.getY(), e.getButton(), 3, false, System.currentTimeMillis() - startTime);
	}

	public void nativeMouseWheelMoved(NativeMouseWheelEvent e) { //4 Scroll
		System.out.println("Mouse Wheel Moved: " + e.getWheelRotation());
		if(!runnin) start();
		add(0, 0, 0, 4, e.getWheelRotation() == 1 ? true : false, System.currentTimeMillis() - startTime);
	}

	public void nativeKeyPressed(NativeKeyEvent e) { //5 KeyPress
		System.out.println("Key Pressed: " + e.getKeyCode() + ", " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		if(!runnin) start();
		
		if(e.getKeyCode() != NativeKeyEvent.VC_ESCAPE) add(isKey(NativeKeyEvent.getKeyText(e.getKeyCode())), 0, 0, 5, true, System.currentTimeMillis() - startTime);

		//Stop recording
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) { //Doesn't work twice, figure out why
            try {
            	GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
            	nativeHookException.printStackTrace();
            }
			Main.frame.setAlwaysOnTop(true);

			try {
				
				for(String act: tasks){
					writer.write(act + "\n");
				}

				writer.close();

				JOptionPane.showMessageDialog(Main.frame, "Recording Successful.", "Recording", JOptionPane.INFORMATION_MESSAGE);

				Main.showGeneral();
		    	namefield.setText("");
		    	infofield.setText("");
		    	//recorded.setText("");
				Main.revert();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
				
        }

	}

	public void nativeKeyReleased(NativeKeyEvent e){ //6 KeyRelease
		//System.out.println("Key Released: " + e.getKeyCode() + ", " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		if(!runnin) start();

		add(isKey(NativeKeyEvent.getKeyText(e.getKeyCode())), 0, 0, 6, false, System.currentTimeMillis() - startTime);

	}

	/*vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv*/
	/*vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv*/
	/*vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv*/
	/*vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv*/
	/*vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv*/

	private static void record(){
		record.setText("Record");
		Main.frame.setAlwaysOnTop(false);
		Main.frame.toBack();

		//Starts NativeHook
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			
			System.exit(1);
		}
				
		//Mouse Listeners
		CreateMenu example = new CreateMenu();
		GlobalScreen.addNativeMouseListener(example);
		GlobalScreen.addNativeMouseMotionListener(example);

		GlobalScreen.addNativeMouseWheelListener(new CreateMenu());
		GlobalScreen.addNativeKeyListener(new CreateMenu());
	}

	public static boolean saveMacro(boolean diff){
		if(!validName(namefield.getText()) || namefield.getText().toString().equals("")){
			JOptionPane.showMessageDialog(Main.frame, "Please enter a valid name", "Invalid Macro", JOptionPane.ERROR_MESSAGE);
			if(diff) return true;
		}else if(originalName(namefield.getText(), Main.directory)){
			
			macro = new File(Main.folder.getPath() + "\\" + namefield.getText() + ".txt");
			try {
				macro.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				writer = new FileWriter(macro.getPath());
				writer.write(namefield.getText() + "\n");
				writer.write(infofield.getText() + "\n");
				writer.write(codearea.getText() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}

				JOptionPane.showMessageDialog(Main.frame, "Macro Successfully Created.", "Success", JOptionPane.INFORMATION_MESSAGE);
				
			

			Main.addToDirectory(namefield.getText());

			if(diff) return false;
			
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Main.showGeneral();
			namefield.setText("");
			infofield.setText("");
			//recorded.setText("");
			Main.revert();
			Main.frame.pack();

		}else{

			JOptionPane.showMessageDialog(Main.frame, "Name already exists.", "Copycat Macro", JOptionPane.ERROR_MESSAGE);
			if(diff) return true;
		}

		return false;

		//figure out if the name of the current macro's name is the same as another macro.
		//prompt user to change name
		//if not, create a new macro folder with its own nameinfo txt and code txt
		//terminate createmenu smooth sailing
	}
	

	public CreateMenu() {
		
		//Panels
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, Color.BLACK));
		JPanel tpanel = new JPanel();
		JPanel cards = new JPanel();
		JPanel generalcard = new JPanel();
		JPanel codecard = new JPanel();
		JPanel saveclose = new JPanel();
		CardLayout cardlayout = new CardLayout();
		
		//TPanel
		ImageIcon icon = new ImageIcon(Main.class.getResource("/main/ccreate.png"));
		JLabel createmacro = new JLabel(" Create Your Macro");
		
		add(tpanel, BorderLayout.NORTH);
		tpanel.setLayout(new FlowLayout());
		tpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //remove when formatting is done
		tpanel.setBackground(Color.WHITE);
		tpanel.add(new JLabel(icon));
		tpanel.add(createmacro);
		createmacro.setBorder(BorderFactory.createEmptyBorder(4, 0, 5, 15));
		createmacro.setFont(new Font(Font.SERIF, Font.BOLD, 22));
		
		//GeneralCard
		JLabel nameanddesc = new JLabel("Name and Description");
		namefield = new JTextField();
		TextPrompt nametext = new TextPrompt("Name", namefield); nametext.getClass(); //to get rid of yellow line
		infofield = new JTextField();
		TextPrompt infotext = new TextPrompt("Information about the macro", infofield); infotext.getClass();
		JLabel recordmacro = new JLabel("Record Macro");
		record = new JButton("Record");
		
		namefield.setPreferredSize(new Dimension(370, 23));
		infofield.setPreferredSize(new Dimension(370, 23));

		//make namefield and infofield so that it can be focused and unfocused

		generalcard.setBackground(Color.WHITE);
		generalcard.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		g.insets = new Insets(2, 0, 2, 0);
		
		g.gridx = 0;
		g.gridy = 0;
		g.gridwidth = 3;
		g.fill = GridBagConstraints.HORIZONTAL;
		nameanddesc.setFont(HEAD);
		generalcard.add(nameanddesc, g);
		
		g.gridx = 0;
		g.gridy = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.ipady = 2;
		g.gridwidth = 3;
		generalcard.add(namefield, g);
		
		g.gridx = 0;
		g.gridy = 2;
		g.gridwidth = 3;
		g.fill = GridBagConstraints.HORIZONTAL;
		generalcard.add(infofield, g);
		
		g.gridx = 0;
		g.gridy = 3;
		g.gridwidth = 3;
		g.fill = GridBagConstraints.HORIZONTAL;
		
		g.gridx = 0;
		g.gridy = 4;
		g.gridwidth = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		
		g.gridx = 1;
		g.gridy = 4;
		g.gridwidth = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		
		g.gridx = 2;
		g.gridy = 4;
		g.gridwidth = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		
		g.gridx = 0;
		g.gridy = 5;
		g.gridwidth = 3;
		g.fill = GridBagConstraints.HORIZONTAL;
		recordmacro.setFont(HEAD);
		generalcard.add(recordmacro, g);

		g.gridx = 0;
		g.gridy = 6;
		g.gridwidth = 3;
		g.fill = GridBagConstraints.HORIZONTAL;
		record.setBackground(Color.WHITE);
		record.setFocusable(false);
		generalcard.add(record, g);
		
		//CodeCard
		codecard.setLayout(new GridLayout(1, 1));
		codearea = new JTextArea("//Macro Code Here");
		JScrollPane scp = new JScrollPane(codearea);
		TextPrompt scptext = new TextPrompt("", codearea); scptext.getClass();
		codearea.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		codecard.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		codecard.add(scp);
		
		//Cards
		add(cards, BorderLayout.CENTER);
		cards.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //remove when formatting is done
		cards.setLayout(cardlayout);
		cards.add(generalcard, "1");
		cards.add(codecard, "2");
		
		//SaveCloseCard
		JButton save = new JButton("Save");
		JButton close = new JButton("Close");
		save.setBackground(Color.WHITE);
		save.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		save.setFocusable(false);
		save.setPreferredSize(new Dimension(50, 25));
		close.setBackground(Color.WHITE);
		close.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		close.setPreferredSize(new Dimension(55, 25));
		close.setFocusable(false);
		add(saveclose, BorderLayout.SOUTH);
		saveclose.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		saveclose.setLayout(new FlowLayout());
		saveclose.setBackground(Color.WHITE);
		saveclose.add(save);
		saveclose.add(close);

		Timer timer = new Timer();

		ActionListener recording = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				if(saveMacro(true)) return;
				
				TimerTask task1 = new TimerTask() {
					@Override
					public void run() {
						record.setText("3");
						System.out.println("3");
					}
				};

				TimerTask task2 = new TimerTask() {
					@Override
					public void run() {
						record.setText("2");
						System.out.println("2");
					}
				};

				TimerTask task3 = new TimerTask() {
					@Override
					public void run() {
						record.setText("1");
						System.out.println("1");

					}
				};

				TimerTask task4 = new TimerTask(){
					@Override
					public void run() {
						
						record();
						
					}
				};

				JOptionPane.showMessageDialog(Main.frame, "To stop the macro recording,\n" + "you need to press Esc.", "Ending the Macro Recording", JOptionPane.INFORMATION_MESSAGE);

				timer.schedule(task1, 0);

				timer.schedule(task2, 1000);

				timer.schedule(task3, 2000);

				timer.schedule(task4, 3000);
				
			}
			
		};

		record.addActionListener(recording);

		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				saveMacro(true);
				
			}

		});
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(GlobalScreen.isNativeHookRegistered()){
					try {
                		GlobalScreen.unregisterNativeHook();
            		} catch (NativeHookException nativeHookException) {
                		nativeHookException.printStackTrace();
            		}
				}

				if(!namefield.getText().equals("")){
					String[] options = {"Yes", "No"};
					int i = JOptionPane.showOptionDialog(Main.frame, "Are you sure you want\n to discard your macro?", "Incomplete Macro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(i == 0){
						Main.showGeneral();
						namefield.setText("");
						infofield.setText("");
						record.setText("Record");
						//recorded.setText("");
						Main.revert();
						
					}
				}else{
					Main.showGeneral();
					namefield.setText("");
					infofield.setText("");
					record.setText("Record");
					//recorded.setText("");
					Main.revert();
				}

				
			}
		});
		
	}

}
