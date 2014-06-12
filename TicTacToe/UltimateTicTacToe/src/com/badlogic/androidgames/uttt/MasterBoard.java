package com.badlogic.androidgames.uttt;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.uttt.Board;

//import java.util.Random;

public class MasterBoard
{
	static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    public boolean gameOver = false;
    public X x;
    public O o;
    public Game game;
    
    public MasterBoard() 
    {
    	//x = new X(/*0,0,0,0*/);
    }
    
    public void init()
    {
    	
    }
    
    
    
    public void update(float deltaTime) 
    {
        if (gameOver)
        {
            return;
        }
    }
    
    public void present()
    {
    	
    }
    
    public void destroy()
    {
    	
    }
}
