package main;

import java.awt.event.*;

import java.awt.*;

public class Action{

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
    private int X;
    private int Y;
    private int button;

    private Robot bot;
    private int time;

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }

    public int getButton(){
        return button;
    }

    public long getTime(){
        return time;
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

    public Action(int x, int y, int b, int cdr, int t){ //1 Click and 2 Drag and 3 Release
        task = Integer.valueOf(cdr);
        X = Integer.valueOf(x);
        Y = Integer.valueOf(y);

        time = Integer.valueOf(t);
        
        
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
    
    public void click() throws AWTException{
        bot.mouseMove(X, Y); 
        bot.mousePress(button);
    }

    public void move() throws AWTException{
        bot.mouseMove(X, Y);
    }

    public void release() throws AWTException{
        bot.mouseRelease(button);
    }



    public Action(boolean scroll, int t){ //4 Scroll
        task = Integer.valueOf(4);
        X = scroll ? 1 : -1;
        time = Integer.valueOf(t);
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void scroll() throws AWTException{
        bot.mouseWheel(X);
    }



    public Action(int k, boolean pressOrRelease, int t){ //5 KeyPress and //6 KeyRelease
        task = pressOrRelease ? 5 : 6;
        X = Integer.valueOf(k);
        time = Integer.valueOf(t);
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void keyPress() throws AWTException {
    	bot.keyPress(X);
    }

    public void keyRelease() throws AWTException {
    	bot.keyRelease(X);
    }

    //a



    public String toString(){

        switch(task){
            case 1: //Click
                System.out.print("Click: " + X + ", " + Y + ", " + time);
            break;

            case 2: //Move
                System.out.print("Move: " + X + ", " + Y + ", " + time);
            break;

            case 3: //Release
                System.out.print("Release: " + X + ", " + Y + ", " + time);
            break;

            case 4: //Scroll
                System.out.print("Scroll: " + X + ", " + time);
            break;
            
            case 5: //Keypress
                System.out.print("Keypress: " + KeyEvent.getKeyText(X) + ", (" + X + "), " + time);
            break;

            case 6: //Keyrelease
                System.out.print("Keyrelease: " + KeyEvent.getKeyText(X) + ", (" + X + "), " + time);
            break;
            
        }

        return "";
    }

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