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
    private long time;
    private boolean bool;

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

    public void setX(int x){
        X = x;
    }

    public void setY(int y){
        Y = y;
    }

    public void setB(int b){
        button = b;
    }

    public void setTime(long t){
        time = t;
    }

    public void setBool(boolean b){
        bool = b;
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

    public Action(int t){
        task = t;
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    public void click() throws AWTException{
        bot.mouseMove(X, Y);
        int butt = -1;
        if(button == 1){
            butt = 1024;
        }else if(button == 2){
            butt = 2048;
        }else if(button == 3){
            butt = 4096;
        }else{
            System.out.println("Mouse button ERROR (CLICK)");
        }
        bot.mousePress(butt);
    }

    public void move() throws AWTException{
        bot.mouseMove(X, Y);
    }

    public void release() throws AWTException{
        int butt = -1;
        if(button == 1){
            butt = 1024;
        }else if(button == 2){
            butt = 2048;
        }else if(button == 3){
            butt = 4096;
        }else{
            System.out.println("Mouse button ERROR (RELEASE)");
        }
        bot.mouseRelease(butt);
    }

    public void scroll() throws AWTException{
        int updown = -1;
        if(bool){
            updown = 1;
        }else{
            updown = -1;
        }
        bot.mouseWheel(updown);
    }

    public void keyPress() throws AWTException {
    	bot.keyPress(X);
    }

    public void keyRelease() throws AWTException {
    	bot.keyRelease(X);
    }

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