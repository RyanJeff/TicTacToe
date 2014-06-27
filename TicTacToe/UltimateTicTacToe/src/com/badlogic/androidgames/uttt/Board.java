package com.badlogic.androidgames.uttt;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;

public class Board
{
    final int GRID_START_X = 16;
    final int GRID_START_Y = 128;
    final int GRID_WIDTH = 96;
    final int GRID_HEIGHT = 96;
    final int SQUARE_SIZE = 32;
    float xLoc = 0;
    float yLoc = 0;
<<<<<<< HEAD
    static TicTacToeGrid grids[][] = new TicTacToeGrid[3][3];
    public static boolean gameOver = false;
    
=======
    TicTacToeGrid grids[][] = new TicTacToeGrid[3][3];
    //public boolean gameOver = false;
>>>>>>> origin/master
    public Game game;
    Board board;
    public boolean isPlayer1Turn = true;
    public int nextMoveCol;
    public int nextMoveRow;

    
    public Board()
    {
    	
    }
    
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
    	TempFunc();
    	CheckWin();
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
    // Make this a while !GameOver Loop?  ++i as player for every time the loop restarts,
    //								  if (i == 2) i = 0; ++i;?
    
    public static boolean CheckWin()
    {
    	int boardWon = 0;
    	while(boardWon != 1)
    	{
		// player i increments from Player 1 -> Player 2 and back
		int i = 1;
			++i;
			if(i >= 3)
	        {
	        	i = 0;
	        }
	        // check rows (horizontal)
	        for (int row = 0; row < 3; row++) 
			{
	            if (grids[row][0].grid[0] == i && grids[row][1].grid[0] == i 
	            							   && grids[row][2].grid[0] == i) 
				{
	            	boardWon = 1;	
				}
	        }
	        // check cols (vertical)
	        for (int col = 0; col < 3; col++) 
			{
	            if (grids[0][col].grid[0] == i && grids[1][col].grid[0] == i
	                    					   && grids[2][col].grid[0] == i) 
				{
	            	boardWon = 1;
	            }
	        }
	        // check diagonals
	        if (grids[0][0].grid[0] == 1 && grids[1][1].grid[0] == i
	        							 && grids[2][2].grid[0] == i) 
			{
	        	boardWon = 1;
	        } 
	        else if (grids[0][2].grid[0] == 1 && grids[1][1].grid[0] == i
	                						  && grids[2][0].grid[0] == i) 
			{
	        	boardWon = 1;
	        }
    	}
		return false;
	}
    
<<<<<<< HEAD
    // This is temporary -- It helps me check if everything is working
    public static void TempFunc()
    {
    	if(CheckWin())
    	{
    		System.out.println("True");
   		}
    	else
    	{
    		System.out.println("False");
    	}
    	
    }
 
=======
    
    //takes an index of a valid move and executes that move.
    public void MakeMove(int index)
	{
    	int validMoveCount = 0;
    	//go through the grid
		for(int i = 0; i < 9; ++i)
		{
    		//if the grid sqare is empty (0) then
    		if(grids[nextMoveRow][nextMoveCol].grid[i] == 0)
    		{
    			//if the valid move count == the index of the move.
    			if(validMoveCount == index)
    			{
    				//then set the square to the correct value based on whos move it is
    				if(isPlayer1Turn)
    				{
        				grids[nextMoveRow][nextMoveCol].grid[i] = 1;
    				}
    				else
    				{
    					grids[nextMoveRow][nextMoveCol].grid[i] = 2;
    				}
    				
    				//set board constraint for next turn
    				nextMoveRow = i / 3;
    				nextMoveCol = i % 3;
    				
    				break;
    			}
    			//otherwise increase the valid move count by 1
    			else
    			{
    				validMoveCount++;
    			}
    		}
		}
    	//change whos turn it is
    	if(isPlayer1Turn)
    	{
    		isPlayer1Turn = false;
    	}
    	else
    	{
    		isPlayer1Turn = true;
    	}
	}

    //int moves = board.NumMoves();
    //for(int i = 0; i < moves; ++i)
	//{
	//	Board child = board.Clone();
	//	child.MakeMove(i);
		//add child to the tree
	//}
    
    public int NumMoves()
    {
    	int numAvailMoves = 0;
		for(int i = 0; i < 9; ++i)
		{
    		if(grids[nextMoveRow][nextMoveCol].grid[i] == 0)
    		{
    			numAvailMoves++;
    		}
		}
		return numAvailMoves;
    }
    
	//deep copy
    protected Board Clone()
    {
		Board retVal = new Board();
		retVal.isPlayer1Turn = isPlayer1Turn;
		for(int row = 0; row < 3; ++row)
    	{
    		for(int col = 0; col < 3; ++col)
    		{
    			for(int i = 0; i < 9; ++i)
    			{
    				retVal.grids[row][col].grid[i] = this.grids[row][col].grid[i];
    			}
			}
		}
		return retVal;
    }
    
>>>>>>> origin/master
    public void destroy()
    {
    	
    }
}
