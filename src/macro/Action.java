package src.macro;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Action extends TimerTask{

    //1 Click                   X and Y
    //2 Hover                   Also X and Y
    //3 Drag                    Old X and Y and New X and Y
    //4 Keystroke               Which key to press or keyevent
    //5 Scroll                  How much to scroll???
    //6 Wait                    Number of seconds to wait
    //7 Detect Cursor Color     X and Y (hover) and some method

    private int task = 1;
    private static int X;
    private int Y;
    private int newX;
    private int newY;
    private static ArrayList<Integer> keys;


    public Action(int x, int y, boolean clickOrNah){ //1 Click and 2 Hover
        if(!clickOrNah) task = 2;
        X = x;
        Y = y;
    }
    
    public static void click(int x, int y){
        Robot bot;
        try {
            bot = new Robot();
            bot.mouseMove(x, y);    
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
    }

    public static void hover(int x, int y){
        Robot bot;
        try {
            bot = new Robot();
            bot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }



    public Action(int oldx, int oldy, int newx, int newy){ //3 Drag
        task = 3;
        X = oldx;
        Y = oldy;
        newX = newx;
        newY = newy;
    }

    public static void drag(int oldx, int oldy, int newx, int newy){
        Robot bot;
        try {
            bot = new Robot();
            bot.mouseMove(oldx, oldy);
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseMove(newx, newy);
            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
    }



    public Action(ArrayList<Integer> k){ //4 KeyStroke
        task = 4;
        keys = k;
    }

    public static void keystroke(){
    	try {
            Robot bot = new Robot();
            for(int i: keys){
                bot.keyPress(i);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
    }



    public Action(int scroll){
        X = scroll;
    }

    public static void scroll(){
        Robot r;
        try {
            r = new Robot();
            r.mouseWheel(X);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }



    public Action(){ //6 Wait
        task = 5; //does nothing :), might cause problems tho
    }



    @Override
    public void run() {
        
        switch(task){
            case 1: //Click
                click(X, Y);
            break;

            case 2: //Hover
                hover(X, Y);
            break;
            
            case 3: //Drag
                drag(X, Y, newX, newY);
            break;
            
            case 4: //Keystroke
                keystroke();
            break;

            case 5: //Scroll
                scroll();
            break;
            
        }
        
    }
    
}
