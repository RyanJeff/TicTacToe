package com.badlogic.androidgames.uttt;

//import java.util.Random;

public class MasterBoard
{
	//model of the world 
    //static final int WORLD_WIDTH = 10;
    //static final int WORLD_HEIGHT = 13;

    public boolean gameOver = false;

    public MasterBoard() 
    {
        
    }
    
    public void update(float deltaTime) 
    {
        if (gameOver)
        {
            return;
        }
    }
}
