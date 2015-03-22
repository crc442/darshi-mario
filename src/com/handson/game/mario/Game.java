package com.handson.game.mario;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sunny
 * Date: 3/21/15
 * Time: 11:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends Canvas implements Runnable{

    public static final String TITLE = "Mario";
    public static final int WIDTH = 250;
    public static final int HEIGHT = WIDTH/15 * 10;
    public static final int SCALE = 4;

    private Thread thread;
    private boolean running = false;

    //Game
    public Game(){
        Dimension dimension = new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }

    // Main method
    public static void main(String args[]){
        Game game = new Game();
        JFrame frame = new JFrame(TITLE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
    }

    private synchronized void start(){
        if(running){
           return;
        }
        running = true;
        thread = new Thread(this);
    }

    private synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        long time = System.nanoTime();
        long currentTime = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0/60.0;
        int frames = 0;
        int ticks = 0;
        while(running){
            long now = System.nanoTime();
            delta = delta + (now-time)/ns;
            time = now;
            while(delta>=1){
                tick();
                ticks++;
                delta--;
            }
            render();
            frames ++;
        }
    }

    // render everything
    public void render(){

    }

    //update everything
    public void tick(){

    }
}

