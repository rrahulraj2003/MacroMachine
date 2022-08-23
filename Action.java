import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Action extends TimerTask{

    //1 Click                   X and Y
    //2 Hover                   Also X and Y
    //3 Drag                    Old X and Y and New X and Y
    //4 Keystroke               Which key to press or keyevent
    //5 Wait                    Number of seconds to wait
    //6 Detect Cursor Color     X and Y (hover) and some method

    private int task = 1;
    private int X;
    private int Y;
    private int newX;
    private int newY;
    private static ArrayList<Integer> keys;


    public Action(int x, int y, boolean clickOrNah){ //1 Click and 2 Hover
        if(!clickOrNah) task = 2;
        X = x;
        Y = y;
    }
    
    public static void click(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);    
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void hover(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);
    }



    public Action(int oldx, int oldy, int newx, int newy){ //3 Drag
        task = 3;
        X = oldx;
        Y = oldy;
        newX = newx;
        newY = newy;
    }

    public static void drag(int oldx, int oldy, int newx, int newy) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(oldx, oldy);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseMove(newx, newy);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }



    public Action(ArrayList<Integer> k){ //4 KeyStroke
        task = 4;
        keys = k;
    }

    public static void keystroke() throws AWTException {
    	Robot bot = new Robot();
        for(int i: keys){
            bot.keyPress(i);
        }
    }



    public Action(){ //5 Wait
        task = 5; //does nothing :), might cause problems tho
    }



    @Override
    public void run() {
        
        switch(task){
            case 1: //Click
                try {
                    click(X, Y);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            break;

            case 2: //Hover
                try {
                    hover(X, Y);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            break;
            
            case 3: //Drag
                try {
                    drag(X, Y, newX, newY);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            break;
            
            case 4: //Keystroke
                try {
                    keystroke();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            break;
            
        }
        
    }
    
}
