package com.badlogic.androidgames.uttt;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import java.util.Random;

public class Board
{
    final int GRID_START_X = 16;
    final int GRID_START_Y = 128;
    final int GRID_WIDTH = 96;
    final int GRID_HEIGHT = 96;
    final int SQUARE_SIZE = 32;
    float xLoc = 0;
    float yLoc = 0;
    static TicTacToeGrid grids[][] = new TicTacToeGrid[3][3];
<<<<<<< HEAD
=======
    public static boolean gameOver = false;
    //TicTacToeGrid grids[][] = new TicTacToeGrid[3][3];
>>>>>>> origin/master
    public Game game;
    Board board;
    public boolean isPlayer1Turn = true;
    public int nextMoveRow;
    public int nextMoveCol;
    public boolean gameOver1 = false;
    public boolean gameOver2 = false;
    public boolean gameOverC = false;
    
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
<<<<<<< HEAD

=======
    	
>>>>>>> origin/master
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
    					//Assets.Piece.play(1);
    				}
    				//if the grid square is o
    				else if(grids[row][col].grid[i] == 2)
    				{
    					xLoc = GRID_START_X + col * GRID_WIDTH + (i % 3) * SQUARE_SIZE;
    					yLoc = GRID_START_Y + row * GRID_HEIGHT + (i / 3) * SQUARE_SIZE;
    					g.drawPixmap(Assets.o, (int)xLoc, (int)yLoc);
    					//Assets.Piece.play(1);
    				}
    			}
    		}
    	}
    }
<<<<<<< HEAD
    

    public boolean miniBoardWonX = false;
    public boolean miniBoardWonO = false;

    public boolean CheckGrid(int playerNumber, TicTacToeGrid theGrid)
    {
    	//horizontal checks for miniboards
		if(theGrid.grid[0] == playerNumber && theGrid.grid[1] == playerNumber && theGrid.grid[2] == playerNumber ||
			theGrid.grid[3] == playerNumber && theGrid.grid[4] == playerNumber && theGrid.grid[5] == playerNumber ||
			theGrid.grid[6] == playerNumber && theGrid.grid[7] == playerNumber && theGrid.grid[8] == playerNumber)
		{
			if(playerNumber == 1)
=======
    // Make this a while !GameOver Loop?  ++i as player for every time the loop restarts,
    //								  if (i == 2) i = 0; ++i;?
    public static boolean CheckWin()
    {
    	int i = 0;
    	int boardWon = 0;
    	while(boardWon != 1)
    	{
		// player i increments from Player 1 -> Player 2 and back
		
			++i;
			if(i >= 3)
	        {
	        	i = 0;
	        }
	        // check rows (horizontal)
	        for (int row = 0; row < 3; row++) 
>>>>>>> origin/master
			{
				miniBoardWonX = true;
			}
			else 
			{
				miniBoardWonO = true;
			}
			return true;
		}		
		//vertical checks for miniboards
		if(theGrid.grid[0] == playerNumber && theGrid.grid[3] == playerNumber && theGrid.grid[6] == playerNumber ||
			theGrid.grid[1] == playerNumber && theGrid.grid[4] == playerNumber && theGrid.grid[7] == playerNumber ||
			theGrid.grid[2] == playerNumber && theGrid.grid[5] == playerNumber && theGrid.grid[8] == playerNumber)
		{
			if(playerNumber == 1)
			{
				miniBoardWonX = true;
			}
			else 
			{
				miniBoardWonO = true;
			}
			return true;
		}
		//diagonal checks for miniboards
		if(theGrid.grid[0] == playerNumber && theGrid.grid[4] == playerNumber && theGrid.grid[8] == playerNumber ||
			theGrid.grid[2] == playerNumber && theGrid.grid[4] == playerNumber && theGrid.grid[6] == playerNumber)
		{
			if(playerNumber == 1)
			{
				miniBoardWonX = true;
			}
			else 
			{
				miniBoardWonO = true;
			}
			return true;
		}
		
    	return false;
    }
    
<<<<<<< HEAD
    public void checkGameOver()
    {
		//horizontal checks for masterboard
		if(CheckGrid(1, grids[0][0]) && CheckGrid(1, grids[0][1]) && CheckGrid(1, grids[0][2]) ||
			CheckGrid(1, grids[1][0]) && CheckGrid(1, grids[1][1]) && CheckGrid(1, grids[1][2]) ||
			CheckGrid(1, grids[2][0]) && CheckGrid(1, grids[2][1]) && CheckGrid(1, grids[2][2]))
		{
			gameOver1 = true;
		}
		else if(CheckGrid(2, grids[0][0]) && CheckGrid(2, grids[0][1]) && CheckGrid(2, grids[0][2]) ||
				CheckGrid(2, grids[1][0]) && CheckGrid(2, grids[1][1]) && CheckGrid(2, grids[1][2]) ||
				CheckGrid(2, grids[2][0]) && CheckGrid(2, grids[2][1]) && CheckGrid(2, grids[2][2]))
		{
			gameOver2 = true;
		}
		
		//vertical checks for masterboard
		if(CheckGrid(1, grids[0][0]) && CheckGrid(1, grids[1][0]) && CheckGrid(1, grids[2][0]) ||
			CheckGrid(1, grids[0][1]) && CheckGrid(1, grids[1][1]) && CheckGrid(1, grids[2][1]) ||
			CheckGrid(1, grids[0][2]) && CheckGrid(1, grids[1][2]) && CheckGrid(1, grids[2][2]))
		{
			gameOver1 = true;
		}
		else if(CheckGrid(2, grids[0][0]) && CheckGrid(2, grids[1][0]) && CheckGrid(2, grids[2][0]) ||
				CheckGrid(2, grids[0][1]) && CheckGrid(2, grids[1][1]) && CheckGrid(2, grids[2][1]) ||
				CheckGrid(2, grids[0][2]) && CheckGrid(2, grids[1][2]) && CheckGrid(2, grids[2][2]))
		{
			gameOver2 = true;
		}
		
		//diagonal checks for masterboard
		if(CheckGrid(1, grids[0][0]) && CheckGrid(1, grids[1][1]) && CheckGrid(1, grids[2][2]) ||
			CheckGrid(1, grids[2][0]) && CheckGrid(1, grids[1][1]) && CheckGrid(1, grids[0][2]))
		{
			gameOver1 = true;
		}
		else if(CheckGrid(2, grids[0][0]) && CheckGrid(2, grids[1][1]) && CheckGrid(2, grids[2][2]) ||
				CheckGrid(2, grids[2][0]) && CheckGrid(2, grids[1][1]) && CheckGrid(2, grids[0][2]))
		{
			gameOver2 = true;
		}
		
		//NumMoves();
		if(NumMoves() == 0)
		{
			gameOverC = true;
		}
    }
    
/*    
=======
>>>>>>> origin/master
    //takes an index of a valid move and executes that move.
    public void MakeMove(int index)
	{
    	int validMoveCount = 0;
    	//go through the grid
		for(int i = 0; i < 9; ++i)
		{
    		//if the grid square is empty (0) then
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
    */
    
    public int numAvailMoves = 0;
    
    public int NumMoves()
    {
		for(int i = 0; i < 9; ++i)
		{
    		if(grids[nextMoveRow][nextMoveCol].grid[i] == 0)
    		{
    			numAvailMoves++;
    		}
		}
		return numAvailMoves;
    }
<<<<<<< HEAD
    /*
=======
    
    private void AIPlayer()
    {
    	// use java.util.Random
    	// Make this take-over for player 2
    	while(isPlayer1Turn == false)
    	{
    		
    	}
    	
    	// create a bunch of rules
    	//		Rule 1: If I have a winning move, take it.
    	//		Rule 2: If the opponent has a winning move, block it.
    	//		Rule 3: If I can create a fork (two winning ways) after this move, do it.
    	//		Rule 4: Do not let the opponent creating a fork after my move. (Opponent may block your winning move and create a fork.)
    	//		Rule 5: Place in the position such as I may win in the most number of possible ways.
    }
    
>>>>>>> origin/master
	//deep copy
    protected Board Clone()
    {
		Board retVal = new Board();
		retVal.isPlayer1Turn = isPlayer1Turn;
		for(int i = 0; i < 9; ++i)
		{
    		if(grids[nextMoveRow][nextMoveCol].grid[i] == 0)
    		{
    				retVal.grids[nextMoveRow][nextMoveCol].grid[i] = this.grids[nextMoveRow][nextMoveCol].grid[i];
			}
		}
		return retVal;
    }
    */
    public void destroy()
    {
    	
    }
    
}
