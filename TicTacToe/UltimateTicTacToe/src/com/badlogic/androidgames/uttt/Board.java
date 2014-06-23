package com.badlogic.androidgames.uttt;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;

public class Board
{
<<<<<<< HEAD
    final int GRID_START_X = 16;
    final int GRID_START_Y = 128;
    final int GRID_WIDTH = 96;
    final int GRID_HEIGHT = 96;
    final int SQUARE_SIZE = 32;
=======
    static final int GRID_START_X = 16;
    static final int GRID_START_Y = 128;
    static final int GRID_WIDTH = 96;
    static final int GRID_HEIGHT = 96;
    static final int SQUARE_SIZE = 32;
    float xLoc = 0;
    float yLoc = 0;
>>>>>>> origin/master
    TicTacToeGrid grids[][] = new TicTacToeGrid[3][3];
    //public boolean gameOver = false;
    public Game game;
    
    public Board(Game game)
    {
    	this.game = game;
    	grids = new TicTacToeGrid[3][3];
    	for(int i = 0; i < 3; ++i)
    	{
    		grids[i] = new TicTacToeGrid[3];
    		for(int j = 0; j < 3; ++j)
    		{
    			grids[i][j] = new TicTacToeGrid();
    		}
    	}
    }
    
    public void update(float deltaTime) 
    {
    	
    }
    
    public void present()
    {
    	Graphics g = game.getGraphics();
    	for(int row = 0; row < 3; ++row)
    	{
    		for(int col = 0; col < 3; ++col)
    		{
    			for(int i = 0; i < 9; ++i)
    			{
    				//if the grid square is x
    				if(grids[row][col].grid[i] == 1)
    				{
    					xLoc = GRID_START_X + col * GRID_WIDTH + (i % 3) * SQUARE_SIZE;
    					yLoc = GRID_START_Y + row * GRID_HEIGHT + (i / 3) * SQUARE_SIZE;

    					g.drawPixmap(Assets.x, (int)xLoc, (int)yLoc);
    				}
    				//if the grid square is o
    				else if(grids[row][col].grid[i] == 2)
    				{
    					xLoc = GRID_START_X + col * GRID_WIDTH + (i % 3) * SQUARE_SIZE;
    					yLoc = GRID_START_Y + row * GRID_HEIGHT + (i / 3) * SQUARE_SIZE;
    					g.drawPixmap(Assets.o, (int)xLoc, (int)yLoc);
    					
    				}
    			}
    		}
    	}
    }
    
    public void destroy()
    {
    	
    }
}
