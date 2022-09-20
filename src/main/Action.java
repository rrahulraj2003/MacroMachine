package main;
import java.util.*;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.awt.*;

public class Action extends TimerTask{

    //1 Click                   X and Y
    //2 Move                    X and Y
    //3 Release                 X and Y
    //4 Scroll                  Number of scrolls up or down
    //5 Keypress                Which key to press
    //6 Keyrelease              Which key to release
    
    //Irrelevant
    //5 Wait                    Number of seconds to wait
    //6 Detect Cursor Color     X and Y (hover) and some method

    public int task = 0;
    private static int X;
    private static int Y;
    private static int button;

    private static Robot bot;
    private static long time;

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }

    public int getButton(){
        return button;
    }

    public void set(int x, int y){
        X = x;
        Y = y;
    }

    public long time(){
        return time;
    }

    public void increment(){
        if(X > 0){
            X++;
        }else{
            X--;
        }
    }

    public Action(int x, int y, int b, int cdr, long t){ //1 Click and 2 Drag and 3 Release
        task = cdr;
        X = x;
        Y = y;
        if(b == 1){
            button = 1024;
        }else if(b == 2){
            button = 2048;
        }else{
            button = 4096;
        }
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    public static void click() throws AWTException{
        bot.mouseMove(X, Y); 
        bot.mousePress(button);
    }

    public static void move() throws AWTException{
        bot.mouseMove(X, Y);
    }

    public void release() throws AWTException{
        bot.mouseRelease(button);
    }



    public Action(boolean scroll, long t){ //4 Scroll
        task = 4;
        X = scroll ? 1 : -1;
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void scroll() throws AWTException{
        bot.mouseWheel(X);
    }



    public Action(int k, boolean pressOrRelease, long t){ //4 KeyPress and //5 KeyRelease
        task = pressOrRelease ? 5 : 6;
        X = k;
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void keyPress() throws AWTException {
    	bot.keyPress(X);
    }

    public static void keyRelease() throws AWTException {
    	bot.keyRelease(X);
    }

    //a



    public String toString(){

        switch(task){
            case 1: //Click
                System.out.println("Click: " + X + ", " + Y);
            break;

            case 2: //Move
                System.out.println("Move: " + X + ", " + Y);
            break;

            case 3: //Release
                System.out.println("Release: " + X + ", " + Y);
            break;

            case 4: //Scroll
                System.out.println("Scroll: " + X);
            break;
            
            case 5: //Keypress
                System.out.println("Keypress: " + NativeKeyEvent.getKeyText(X));
            break;

            case 6: //Keyrelease
                System.out.println("Keyrelease: " + NativeKeyEvent.getKeyText(X));
            break;
            
        }

        return "";
    }

    @Override
    public void run() {
        
        switch(task){
            case 1: //Click
                try {
                    click();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            break;

            case 2: //Move
                try {
                    move();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            break;

            case 3: //Release
                try {
                    release();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            break;
            
            case 4: //Scroll
                try {
                    //keystroke();
                    scroll();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            break;

            case 5: //KeyPress
                try {
                    keyPress();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            break;

            case 6: //KeyRelease
                try {
                    keyRelease();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            break;
            
        }
        
    }
    
}