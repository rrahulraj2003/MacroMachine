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
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class CreateMenu extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public static Font HEAD = new Font(Font.SANS_SERIF, Font.BOLD, 14);

	public boolean recording = false;
	private JTextField recorded;
	private StringBuilder display = new StringBuilder("empty");

	public void displayRecording(KeyEvent e){
		System.out.println(e.getKeyCode());

		if(display.substring(0).equals("empty")){
			display.delete(0, 5);
			display.append("Ctrl");
			recorded.setText(display.toString());
		}else if(display.substring(0).equals("Ctrl") && e.getKeyCode() == 16){
			display.append(" + Shift");
			recorded.setText(display.toString());
		}else if(display.substring(7).equals("Shift")){
			display.append(" + " + e.getKeyChar());
			recorded.setText(display.toString());
			recording = false;
		}
		
		
	}

	public CreateMenu(){
		
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
		ImageIcon icon = new ImageIcon(Main.pcreate);
		JLabel createmacro = new JLabel("Create Your Macro");
		JButton general = new JButton("General");
		JButton code = new JButton("Code");
		
		add(tpanel, BorderLayout.NORTH);
		tpanel.setLayout(new FlowLayout());
		tpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //remove when formatting is done
		tpanel.setBackground(Color.WHITE);
		tpanel.add(new JLabel(icon));
		tpanel.add(createmacro);
		createmacro.setBorder(BorderFactory.createEmptyBorder(4, 0, 5, 15));
		createmacro.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		general.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		general.setBackground(Color.WHITE);
		general.setFocusable(false);
		general.setPreferredSize(new Dimension(70, 30));
		code.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		code.setBackground(Color.WHITE);
		code.setFocusable(false);
		code.setPreferredSize(new Dimension(50, 30));
		tpanel.add(general);
		JLabel space = new JLabel(".");
		space.setForeground(Color.WHITE);
		space.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 8));
		tpanel.add(space);
		tpanel.add(code);
		
		//GeneralCard
		JLabel nameanddesc = new JLabel("Name and Description");
		JTextField namefield = new JTextField();
		TextPrompt nametext = new TextPrompt("Name", namefield); nametext.getClass(); //to get rid of yellow line
		JTextField infofield = new JTextField();
		TextPrompt infotext = new TextPrompt("Information about the macro", infofield); infotext.getClass();
		JLabel recordhotkey = new JLabel("Record Hotkey");
		JButton recordkey = new JButton("Record Key");
		recorded = new JTextField();
		JButton settona = new JButton("Set to N/A");
		JLabel recordmacro = new JLabel("Record Macro");
		JButton record = new JButton("Record");
		
		namefield.setPreferredSize(new Dimension(400, 23));
		infofield.setPreferredSize(new Dimension(400, 23));

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
		recordhotkey.setFont(HEAD);
		generalcard.add(recordhotkey, g);
		
		g.gridx = 0;
		g.gridy = 4;
		g.gridwidth = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		recordkey.setBackground(Color.WHITE);
		recordkey.setFocusable(false);
		generalcard.add(recordkey, g);

		recordkey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recording = true;
				recorded.setText("");
				display.delete(0, display.length());
				display.append("empty");
			}
		});
		
		g.gridx = 1;
		g.gridy = 4;
		g.gridwidth = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		recorded.setPreferredSize(new Dimension(200, 25));
		recorded.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
		recorded.setFocusable(false);
		recorded.setHorizontalAlignment(JTextField.CENTER);
		recorded.setFont(new FontUIResource(Font.DIALOG, Font.PLAIN, 18));
		recorded.setForeground(Color.BLACK);
		generalcard.add(recorded, g);
		
		g.gridx = 2;
		g.gridy = 4;
		g.gridwidth = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		settona.setBackground(Color.WHITE);
		settona.setFocusable(false);
		generalcard.add(settona, g);
		
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
		JTextArea codearea = new JTextArea("//Macro Code Here");
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
		save.setPreferredSize(new Dimension(50, 25));
		close.setBackground(Color.WHITE);
		close.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		close.setPreferredSize(new Dimension(55, 25));
		add(saveclose, BorderLayout.SOUTH);
		saveclose.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		saveclose.setLayout(new FlowLayout());
		saveclose.setBackground(Color.WHITE);
		saveclose.add(save);
		saveclose.add(close);
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.showGeneral();
				namefield.setText("");
				infofield.setText("");
				recorded.setText("");
				display.delete(0, display.length());
				display.append("empty");
				Main.revert();
			}
		});
		
		general.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(cards, "1");
				space.setForeground(Color.WHITE);
			}
		});
		
		code.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(cards, "2");
				space.setForeground(Color.BLACK);
			}
		});
		
	}

}
