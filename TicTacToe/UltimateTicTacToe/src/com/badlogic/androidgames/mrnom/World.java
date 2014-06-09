package com.badlogic.androidgames.mrnom;

//import java.util.Random;

public class World 
{
	//model of the world 
    //static final int WORLD_WIDTH = 10;
    //static final int WORLD_HEIGHT = 13;

    public boolean gameOver = false;

    public World() 
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
