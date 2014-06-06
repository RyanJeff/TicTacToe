package com.badlogic.androidgames.mrnom;

import java.util.Random;

public class World 
{
	//model of the world 
    static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INITIAL = 0.5f;
    // change the amount of time we require between ticks
    static final float TICK_DECREMENT = 0.05f;

    //public Snake snake;
    
    //public Stain stain;
    //variable for death collision stains
    //public StrikerStain strikerStain;
    public boolean gameOver = false;;
    //public int score = 0;
    // keep track of where to keep stains
    //boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    //Random random = new Random();
    //tick variable 
    float tickTime = 0;
    static float tick = TICK_INITIAL;

    public World() 
    {
        //snake = new Snake();
        //placeStain();
        //placeStrikerStain();
    }
    //add stain to the map set fields array to false default
    //iterate through snake and set tiles to true
    // pick a random position to place stain
    /*private void placeSquirrel()
    {
    	for (int x = 0; x < WORLD_WIDTH; x++) 
        {
            for (int y = 0; y < WORLD_HEIGHT; y++) 
            {
                fields[x][y] = false;
            }
        }
    	
    }
    private void placeStain() 
    {
        for (int x = 0; x < WORLD_WIDTH; x++) 
        {
            for (int y = 0; y < WORLD_HEIGHT; y++) 
            {
                fields[x][y] = false;
            }
        }

        int len = snake.parts.size();
        for (int i = 0; i < len; i++) 
        {
            SnakePart part = snake.parts.get(i);
            fields[part.x][part.y] = true;
        }

        int stainX = random.nextInt(WORLD_WIDTH);
        int stainY = random.nextInt(WORLD_HEIGHT);
        while (true) 
        {
            if (fields[stainX][stainY] == false)
                break;
            stainX += 1;
            if (stainX >= WORLD_WIDTH) 
            {
                stainX = 0;
                stainY += 1;
                if (stainY >= WORLD_HEIGHT) 
                {
                    stainY = 0;
                }
            }
        }
        stain = new Stain(stainX, stainY, random.nextInt(3));
    }

    private void placeStrikerStain() 
    {
        for (int x = 0; x < WORLD_WIDTH; x++) 
        {
            for (int y = 0; y < WORLD_HEIGHT; y++) 
            {
                fields[x][y] = false;
            }
        }

        int len = snake.parts.size();
        for (int i = 0; i < len; i++) 
        {
            SnakePart part = snake.parts.get(i);
            fields[part.x][part.y] = true;
        }

        int strikerStainX = random.nextInt(WORLD_WIDTH);
        int strikerStainY = random.nextInt(WORLD_HEIGHT);
        while (true) 
        {
            if (fields[strikerStainX][strikerStainY] == false)
                break;
            strikerStainX += 1;
            if (strikerStainX >= WORLD_WIDTH) 
            {
            	strikerStainX = 0;
                strikerStainY += 1;
                if (strikerStainY >= WORLD_HEIGHT) 
                {
                	strikerStainY = 0;
                }
            }
        }
        strikerStain = new StrikerStain(strikerStainX, strikerStainY, random.nextInt(3));
    }*/

    
    public void update(float deltaTime) 
    {
        if (gameOver)
            return;

        tickTime += deltaTime;

        while (tickTime > tick) 
        {
            tickTime -= tick;
            //snake.advance();
            /*if (snake.checkBitten()) 
            {
                gameOver = true;
                return;
            }
            //if the snake eats the red or blue pill either way he dies
            SnakePart head = snake.parts.get(0);
            if(head.x == strikerStain.x && head.y == strikerStain.y)
            {
            	gameOver = true;
            	snake.eat();
            	score -= SCORE_INCREMENT;
                return;
            	
            }
            if (head.x == stain.x && head.y == stain.y) 
            {
                score += SCORE_INCREMENT;
                snake.eat();
                // if the size of the snake is the same as the size of the world game ends
           
                if (snake.parts.size() == WORLD_WIDTH * WORLD_HEIGHT) 
                {
                    gameOver = true;
                    return;
                } 
                else 
                {
                	//if not size of the world place another stain
                    //placeStain();
                    //placeStrikerStain();
                    //placeSquirrel();
                }
                
              
                if (score % 100 == 0 && tick - TICK_DECREMENT > 0) 
                {
                    tick -= TICK_DECREMENT;
                }*/
            }
        }
    }

