package com.badlogic.androidgames.uttt;

import java.util.List;

//import android.util.Log;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
//import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.uttt.Board;

//import java.lang.Object;
//import java.util.AbstractCollection;
//import java.util.AbstractList;
//import java.util.ArrayList;

public class GameScreen extends Screen 
{
    enum GameState 
    {
        Ready,
        Running,
        Paused,
        GameOver,
        Play
        //SinglePlayer,
        //TwoPlayer
    }
    
    public static boolean PlayOption = true;

    GameState state = GameState.Ready;
    Board board;
    
    public GameScreen(Game game) 
    {
        super(game);
        board = new Board(game);
    }

    @Override
    public void update(float deltaTime) 
    {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        //only start game when user touches the screen
        if(state == GameState.Ready)
        {
            updateReady(touchEvents);
        }
        else if(state == GameState.Running)
        {
        	updateRunning(touchEvents, deltaTime);
        }
        else if(state == GameState.Paused)
        {
            updatePaused(touchEvents);
        }
        else if(state == GameState.GameOver)
        {
            updateGameOver(deltaTime);     
        }
        
    }
    
    private void updateReady(List<TouchEvent> touchEvents) 
    {
    	if(state == GameState.Ready)
    	{
	    	//ready/touch screen to start
	        if(touchEvents.size() > 0)
	        {
	        	if(touchEvents.get(0).type == TouchEvent.TOUCH_UP)
	        	{
	        		state = GameState.Running;
	        	}
	        } 
    	}
    }
    
    public int masterboard_id;
    public int miniboard_id;
    boolean isFirstMove = true;
    public boolean mini0Playable = true;
    public boolean mini1Playable = true;
    public boolean mini2Playable = true;
    public boolean mini3Playable = true;
    public boolean mini4Playable = true;
    public boolean mini5Playable = true;
    public boolean mini6Playable = true;
    public boolean mini7Playable = true;
    public boolean mini8Playable = true;
    
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) 
    {    
    	int len = touchEvents.size();
        for(int i = 0; i < len; i++) 
        {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) 
            {
                if(event.x > 90 && event.x < 235)
                {	
                	//pause
                	if(event.y > 440) 
                	{
                		if(Settings.soundEnabled)
                        {
                            Assets.click.play(1);
                        }
                		state = GameState.Paused;
	                    return;
                	}  
                }
                
                int col = (int)Math.floor((event.x - board.GRID_START_X) / board.GRID_WIDTH);
                int row = (int)Math.floor((event.y - board.GRID_START_Y) / board.GRID_HEIGHT);
                int colP = (int)Math.floor((event.x - (board.GRID_START_X + col * board.GRID_WIDTH)) / board.SQUARE_SIZE);
                int rowP = (int)Math.floor((event.y - (board.GRID_START_Y + row * board.GRID_HEIGHT)) / board.SQUARE_SIZE);
                
           //masterboard ids
                if(row == 0 && col == 0)
                	masterboard_id = 0;   //top left
                	mini0Playable = true;
                if(row == 0 && col == 1)
                	masterboard_id = 1;   //top mid
                	mini1Playable = true;
                if(row == 0 && col == 2)
                	masterboard_id = 2;   //top right
            		mini2Playable = true;
                if(row == 1 && col == 0)
                	masterboard_id = 3;   //mid left
            		mini3Playable = true;
                if(row == 1 && col == 1)
                	masterboard_id = 4;   //mid mid
            		mini4Playable = true;
                if(row == 1 && col == 2)
                	masterboard_id = 5;   //mid right
            		mini5Playable = true;
                if(row == 2 && col == 0)
                	masterboard_id = 6;   //btm left
            		mini6Playable = true;
                if(row == 2 && col == 1)
                	masterboard_id = 7;   //btm mid
            		mini7Playable = true;
                if(row == 2 && col == 2)
                	masterboard_id = 8;   //btm right
            		mini8Playable = true;
                
           //miniboard ids
                if(rowP == 0 && colP == 0)
                	miniboard_id = 0;   //top left
                if(rowP == 0 && colP == 1)
                	miniboard_id = 1;   //top mid
                if(rowP == 0 && colP == 2)
                	miniboard_id = 2;   //top right
                if(rowP == 1 && colP == 0)
                	miniboard_id = 3;   //mid left
                if(rowP == 1 && colP == 1)
                	miniboard_id = 4;   //mid mid
                if(rowP == 1 && colP == 2)
                	miniboard_id = 5;   //mid right
                if(rowP == 2 && colP == 0)
                	miniboard_id = 6;   //btm left
                if(rowP == 2 && colP == 1)
                	miniboard_id = 7;   //btm mid
                if(rowP == 2 && colP == 2)
                	miniboard_id = 8;   //btm right
                
                //System.out.println("Masterboard id: " + masterboard_id);
                //System.out.println("Miniboard id: " + miniboard_id);
                
                if(isFirstMove)
                {
  
    				board.grids[row][col].grid[i] = 1;
    				board.isPlayer1Turn = false;
    				board.nextMoveRow = rowP;
    				board.nextMoveCol = colP;
                	isFirstMove = false;
                }
                
                if(!isFirstMove && board.grids[row][col].grid[i] == 0)
                {
	                switch(masterboard_id)
	                {
	                	case 0:
		                	if(board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini0Playable)
		        			{
		        				board.grids[0][0].grid[i] = 1;
		        				board.CheckGrid(1, board.grids[0][0]);
		        				board.isPlayer1Turn = false;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        			}
		                	else if(!board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini0Playable)
		        			{
		        				board.grids[0][0].grid[i] = 2;
		        				board.CheckGrid(2, board.grids[0][0]);
		        				
			                	if(board.isPlayer1Turn)
			                	{
			                		
			                		if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
			                		{
				        				board.grids[0][0].grid[miniboard_id] = 1;
				        				board.isPlayer1Turn = false;
				        				board.nextMoveRow = rowP;
				        				board.nextMoveCol = colP;
				        				board.CheckGrid(1, board.grids[0][0]);
			                		}
			        			}
		        			}
		                	else if(PlayOption == false) 
		        			{
		                		//do AI selection
		                		if( board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		                		{
		                			int move = board.AIPlayer();
		                			int validMoveNum = 0;
		                			for(int j = 0; j < 9; ++j)
		                			{
		                				if(board.grids[0][0].grid[j] == 0)
		                				{
		                					if(validMoveNum == move)
		                					{
		                						board.grids[0][0].grid[j] = 2;
		    			        				board.isPlayer1Turn = true;
		    			        				board.nextMoveRow = rowP;
		    			        				board.nextMoveCol = colP;
		    			        				board.CheckGrid(2, board.grids[0][0]);
		    			        				break;
		                					}
		                					else
		                					{
		                						validMoveNum++;
		                					}
		                				}
		                			}
		                		}
		        			}
		                	else if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		        			{
		        				board.grids[0][0].grid[miniboard_id] = 2;
		        				board.isPlayer1Turn = true;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        			}
		                	break;
		                
	                	case 1:
		        			if(board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini1Playable)
		        			{
		        				board.grids[0][1].grid[i] = 1;
		        				board.CheckGrid(1, board.grids[0][1]);
		        				board.isPlayer1Turn = false;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
		        			else if(!board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini1Playable)
		        			{
		        				board.grids[0][1].grid[i] = 2;
		        				board.CheckGrid(2, board.grids[0][1]);
		        			
			        			if(board.isPlayer1Turn)
			                	{
			                		
			                		if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
			                		{
				        				board.grids[0][1].grid[miniboard_id] = 1;
				        				board.isPlayer1Turn = false;
				        				board.nextMoveRow = rowP;
				        				board.nextMoveCol = colP;
				        				board.CheckGrid(1, board.grids[0][1]);
			                		}
			        			}
		        			}
		                	else if(PlayOption == false) 
		        			{
		                		//do AI selection
		                		if( board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		                		{
		                			int move = board.AIPlayer();
		                			int validMoveNum = 0;
		                			for(int j = 0; j < 9; ++j)
		                			{
		                				if(board.grids[0][1].grid[j] == 0)
		                				{
		                					if(validMoveNum == move)
		                					{
		                						board.grids[0][1].grid[j] = 2;
		    			        				board.isPlayer1Turn = true;
		    			        				board.nextMoveRow = rowP;
		    			        				board.nextMoveCol = colP;
		    			        				board.CheckGrid(2, board.grids[0][1]);
		                					}
		                					else
		                					{
		                						validMoveNum++;
		                					}
		                				}
		                			}
		                		}
		        			}
		                	else if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		        			{
		        				board.grids[0][1].grid[miniboard_id] = 2;
		        				board.isPlayer1Turn = true;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
		                	break;
		        		
	                	case 2:
	                		if(board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini2Playable)
		        			{
		        				board.grids[0][2].grid[i] = 1;
		        				board.CheckGrid(1, board.grids[0][2]);
		        				board.isPlayer1Turn = false;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;	
		        				
		        			}
	                		else if(!board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini2Playable)
		        			{
		        				board.grids[0][2].grid[i] = 2;
		        				board.CheckGrid(2, board.grids[0][2]);
		        				
		                		if(board.isPlayer1Turn)
			                	{
			                		if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
			                		{
				        				board.grids[0][2].grid[miniboard_id] = 1;
				        				board.isPlayer1Turn = false;
				        				board.nextMoveRow = rowP;
				        				board.nextMoveCol = colP;
				        				board.CheckGrid(1, board.grids[0][2]);
			                		}
				        		}
		        			}
		                	else if(PlayOption == false) 
		        			{
		                		//do AI selection
		                		if( board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		                		{
		                			int move = board.AIPlayer();
		                			int validMoveNum = 0;
		                			for(int j = 0; j < 9; ++j)
		                			{
		                				if(board.grids[0][2].grid[j] == 0)
		                				{
		                					if(validMoveNum == move)
		                					{
		                						board.grids[0][2].grid[j] = 2;
		    			        				board.isPlayer1Turn = true;
		    			        				board.nextMoveRow = rowP;
		    			        				board.nextMoveCol = colP;
		    			        				board.CheckGrid(2, board.grids[0][2]);
		                					}
		                					else
		                					{
		                						validMoveNum++;
		                					}
		                				}
		                			}
		                		}
		        			}
		                	else if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		        			{
		        				board.grids[0][2].grid[miniboard_id] = 2;
		        				board.isPlayer1Turn = true;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
		        			break;
		        			
	                	case 3:
	                		if(board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini3Playable)
		        			{
		        				board.grids[1][0].grid[i] = 1;
		        				board.CheckGrid(1, board.grids[1][0]);
		        				board.isPlayer1Turn = false;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
	                		else if(!board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini3Playable)
		        			{
		        				board.grids[1][0].grid[i] = 2;
		        				board.CheckGrid(2, board.grids[1][0]);
		        				
		                		if(board.isPlayer1Turn)
			                	{
			                		
			                		if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
			                		{
				        				board.grids[1][0].grid[miniboard_id] = 1;
				        				board.isPlayer1Turn = false;
				        				board.nextMoveRow = rowP;
				        				board.nextMoveCol = colP;
				        				board.CheckGrid(1, board.grids[1][0]);
			                		}
			        			}
		        			}
		                	else if(PlayOption == false) 
		        			{
		                		//do AI selection
		                		if( board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		                		{
		                			int move = board.AIPlayer();
		                			int validMoveNum = 0;
		                			for(int j = 0; j < 9; ++j)
		                			{
		                				if(board.grids[1][0].grid[j] == 0)
		                				{
		                					if(validMoveNum == move)
		                					{
		                						board.grids[1][0].grid[j] = 2;
		    			        				board.isPlayer1Turn = true;
		    			        				board.nextMoveRow = rowP;
		    			        				board.nextMoveCol = colP;
		    			        				board.CheckGrid(2, board.grids[1][0]);
		                					}
		                					else
		                					{
		                						validMoveNum++;
		                					}
		                				}
		                			}
		                		}
		        			}
		                	else if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		        			{
		        				board.grids[1][0].grid[miniboard_id] = 2;
		        				board.isPlayer1Turn = true;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
		                	break;
		        		
	                	case 4:
	                		if(board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini4Playable)
		        			{
		        				board.grids[1][1].grid[i] = 1;
		        				board.CheckGrid(1, board.grids[1][1]);
		        				board.isPlayer1Turn = false;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
	                		else if(!board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini4Playable)
		        			{
		        				board.grids[1][1].grid[i] = 2;
		        				board.CheckGrid(2, board.grids[1][1]);
		        				
		                		if(board.isPlayer1Turn)
			                	{
			                		
			                		if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
			                		{
				        				board.grids[1][1].grid[miniboard_id] = 1;
				        				board.isPlayer1Turn = false;
				        				board.nextMoveRow = rowP;
				        				board.nextMoveCol = colP;
				        				board.CheckGrid(1, board.grids[1][1]);
			                		}
			        			}
		        			}
		                	else if(PlayOption == false) 
		        			{
		                		//do AI selection
		                		if( board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		                		{
		                			int move = board.AIPlayer();
		                			int validMoveNum = 0;
		                			for(int j = 0; j < 9; ++j)
		                			{
		                				if(board.grids[1][1].grid[j] == 0)
		                				{
		                					if(validMoveNum == move)
		                					{
		                						board.grids[1][1].grid[j] = 2;
		    			        				board.isPlayer1Turn = true;
		    			        				board.nextMoveRow = rowP;
		    			        				board.nextMoveCol = colP;
		    			        				board.CheckGrid(2, board.grids[1][1]);
		                					}
		                					else
		                					{
		                						validMoveNum++;
		                					}
		                				}
		                			}
		                		}
		        			}
		                	else if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		        			{
		        				board.grids[1][1].grid[miniboard_id] = 2;
		        				board.isPlayer1Turn = true;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
		                	break;
		        			
	                	case 5:
	                		if(board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini5Playable)
		        			{
		        				board.grids[1][2].grid[i] = 1;
		        				board.CheckGrid(1, board.grids[1][2]);
		        				board.isPlayer1Turn = false;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
	                		else if(!board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini5Playable)
		        			{
		        				board.grids[1][2].grid[i] = 2;
		        				board.CheckGrid(2, board.grids[1][2]);
		        				
		                		if(board.isPlayer1Turn)
			                	{
			                		
			                		if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
			                		{
				        				board.grids[1][2].grid[miniboard_id] = 1;
				        				board.isPlayer1Turn = false;
				        				board.nextMoveRow = rowP;
				        				board.nextMoveCol = colP;
				        				board.CheckGrid(1, board.grids[1][2]);
			                		}
			        			}
		        			}
	                		
		                	else if(PlayOption == false) 
		        			{
		                		//do AI selection
		                		if( board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		                		{
		                			int move = board.AIPlayer();
		                			int validMoveNum = 0;
		                			for(int j = 0; j < 9; ++j)
		                			{
		                				if(board.grids[1][2].grid[j] == 0)
		                				{
		                					if(validMoveNum == move)
		                					{
		                						board.grids[1][2].grid[j] = 2;
		    			        				board.isPlayer1Turn = true;
		    			        				board.nextMoveRow = rowP;
		    			        				board.nextMoveCol = colP;
		    			        				board.CheckGrid(2, board.grids[1][2]);
		                					}
		                					else
		                					{
		                						validMoveNum++;
		                					}
		                				}
		                			}
		                		}
		        			}
		                	else if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		        			{
		        				board.grids[1][2].grid[miniboard_id] = 2;
		        				board.isPlayer1Turn = true;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
		                	break;
		        			
	                	case 6:
	                		if(board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini6Playable)
		        			{
		        				board.grids[2][0].grid[i] = 1;
		        				board.CheckGrid(1, board.grids[2][0]);
		        				board.isPlayer1Turn = false;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
	                		else if(!board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini6Playable)
		        			{
		        				board.grids[2][0].grid[i] = 2;
		        				board.CheckGrid(2, board.grids[2][0]);
		        				
		                		if(board.isPlayer1Turn)
			                	{
			                		
			                		if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
			                		{
				        				board.grids[2][0].grid[miniboard_id] = 1;
				        				board.isPlayer1Turn = false;
				        				board.nextMoveRow = rowP;
				        				board.nextMoveCol = colP;
				        				board.CheckGrid(1, board.grids[2][0]);
			                		}
			        			}
		        			}
	                		
		                	else if(PlayOption == false) 
		        			{
		                		//do AI selection
		                		if( board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		                		{
		                			int move = board.AIPlayer();
		                			int validMoveNum = 0;
		                			for(int j = 0; j < 9; ++j)
		                			{
		                				if(board.grids[2][0].grid[j] == 0)
		                				{
		                					if(validMoveNum == move)
		                					{
		                						board.grids[2][0].grid[j] = 2;
		    			        				board.isPlayer1Turn = true;
		    			        				board.nextMoveRow = rowP;
		    			        				board.nextMoveCol = colP;
		    			        				board.CheckGrid(2, board.grids[2][0]);
		                					}
		                					else
		                					{
		                						validMoveNum++;
		                					}
		                				}
		                			}
		                		}
		        			}
		                	else if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		        			{
		        				board.grids[2][0].grid[miniboard_id] = 2;
		        				board.isPlayer1Turn = true;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
		                	break;
		        			
	                	case 7:
	                		if(board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini7Playable)
		        			{
		        				board.grids[2][1].grid[i] = 1;
		        				board.CheckGrid(1, board.grids[2][1]);
		        				board.isPlayer1Turn = false;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        				
		        			}
	                		else if(!board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini7Playable)
		        			{
		        				board.grids[2][1].grid[i] = 2;
		        				board.CheckGrid(2, board.grids[2][1]);
		        				
			                	if(board.isPlayer1Turn)
			                	{
			                		
			                		if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
			                		{
				        				board.grids[2][1].grid[miniboard_id] = 1;
				        				board.isPlayer1Turn = false;
				        				board.nextMoveRow = rowP;
				        				board.nextMoveCol = colP;
				        				board.CheckGrid(1, board.grids[2][1]);
			                		}
			        			}
		        			}
	                		
		                	else if(PlayOption == false) 
		        			{
		                		//do AI selection
		                		if( board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		                		{
		                			int move = board.AIPlayer();
		                			int validMoveNum = 0;
		                			for(int j = 0; j < 9; ++j)
		                			{
		                				if(board.grids[2][1].grid[j] == 0)
		                				{
		                					if(validMoveNum == move)
		                					{
		                						board.grids[2][1].grid[j] = 2;
		    			        				board.isPlayer1Turn = true;
		    			        				board.nextMoveRow = rowP;
		    			        				board.nextMoveCol = colP;
		    			        				board.CheckGrid(2, board.grids[2][1]);
		                					}
		                					else
		                					{
		                						validMoveNum++;
		                					}
		                				}
		                			}
		                		}
		        			}
		                	else if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		        			{
		        				board.grids[2][1].grid[miniboard_id] = 2;
		        				board.isPlayer1Turn = true;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        			}
		                	break;
		        			
	                	case 8:
	                		if(board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini8Playable)
		        			{
		        				board.grids[2][2].grid[i] = 1;
		        				board.CheckGrid(1, board.grids[2][2]);
		        				board.isPlayer1Turn = false;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        			}
	                		else if(!board.isPlayer1Turn && board.nextMoveRow == row && board.nextMoveCol == col && mini8Playable)
		        			{
		        				board.grids[2][2].grid[i] = 2;
		        				board.CheckGrid(2, board.grids[2][2]);
		        				
			                	if(board.isPlayer1Turn)
			                	{
			                		
			                		if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
			                		{
				        				board.grids[2][2].grid[miniboard_id] = 1;
				        				board.isPlayer1Turn = false;
				        				board.nextMoveRow = rowP;
				        				board.nextMoveCol = colP;
				        				board.CheckGrid(1, board.grids[2][2]);
			                		}
			        			}
		        			}
		                	else if(PlayOption == false) 
		        			{
		                		//do AI selection
		                		if( board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		                		{
		                			int move = board.AIPlayer();
		                			int validMoveNum = 0;
		                			for(int j = 0; j < 9; ++j)
		                			{
		                				if(board.grids[2][2].grid[j] == 0)
		                				{
		                					if(validMoveNum == move)
		                					{
		                						board.grids[2][2].grid[j] = 2;
		    			        				board.isPlayer1Turn = true;
		    			        				board.nextMoveRow = rowP;
		    			        				board.nextMoveCol = colP;
		    			        				board.CheckGrid(2, board.grids[2][2]);
		                					}
		                					else
		                					{
		                						validMoveNum++;
		                					}
		                				}
		                			}
		                		}
		        			}
		                	else if(board.nextMoveRow == row && board.nextMoveCol == col && board.grids[row][col].grid[miniboard_id] == 0)
		        			{
		        				board.grids[2][2].grid[miniboard_id] = 2;
		        				board.isPlayer1Turn = true;
		        				board.nextMoveRow = rowP;
		        				board.nextMoveCol = colP;
		        			}
		                	break;
	                } 
	                board.NumMoves();
	            }
                
            }
        }
        
        board.checkGameOver();
        if(board.gameOver1 || board.gameOver2 || board.gameOverC)
        {
        	state = GameState.GameOver;
        }
    }
    
	private void updatePaused(List<TouchEvent> touchEvents) 
    {
    	int len = touchEvents.size();
        for(int i = 0; i < len; i++) 
        {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) 
            { 
            	if(event.x > 70 && event.x < 260) 
                {
                	//resume
                    if(event.y > 215 && event.y < 280) 
                    {
                    	if(Settings.soundEnabled)
                        {
                            Assets.click.play(1);
                        }
                		state = GameState.Running;
                		return;
                    } 
                    //main menu
                    if(event.y > 325 && event.y < 400) 
                    {
                    	if(Settings.soundEnabled)
                        {
                            Assets.click.play(1);
                        }
                        game.setScreen(new MainMenuScreen(game));                        
                        return;
                    }
                }
            }
        } 
    }
    
    private void updateGameOver(float deltaTime) 
    {
    	float timer = 4.0f;
    	if(board.gameOver1 || board.gameOver2 || board.gameOverC)
    	{
	    	timer -= deltaTime;
	    	if(timer <= 0)
	    	{
	    		game.setScreen(new MainMenuScreen(game));
	    	}
    	}
    }

    @Override
    public void present(float deltaTime) 
    {   
    	if(state == GameState.Play)
        {
            drawPlayUI();
        }
        if(state == GameState.Ready)
        {
            drawReadyUI();
        }
        else if(state == GameState.Running)
        {
        	drawRunningUI();
        }
        else if(state == GameState.Paused)
        {
            drawPausedUI();
        }
        else if(state == GameState.GameOver)
        {
            drawGameOverUI(); 
        }
    }

    private void drawReadyUI() 
    {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.ready, 0, 0);
    }
    
    private void drawRunningUI() 
    {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        board.present();
        if(board.nextMoveRow == 0 &&  board.nextMoveCol == 0 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec0, 0, 0);
        }
        else if(board.nextMoveRow == 0 &&  board.nextMoveCol == 1 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec1, 0, 0);
        }
        else if(board.nextMoveRow == 0 &&  board.nextMoveCol == 2 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec2, 0, 0);
        }
        else if(board.nextMoveRow == 1 &&  board.nextMoveCol == 0 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec3, 0, 0);
        }
        else if(board.nextMoveRow == 1 &&  board.nextMoveCol == 1 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec4, 0, 0);
        }
        else if(board.nextMoveRow == 1 &&  board.nextMoveCol == 2 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec5, 0, 0);
        }
        else if(board.nextMoveRow == 2 &&  board.nextMoveCol == 0 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec6, 0, 0);
        }
        else if(board.nextMoveRow == 2 &&  board.nextMoveCol == 1 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec7, 0, 0);
        }
        else if(board.nextMoveRow == 2 &&  board.nextMoveCol == 2 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec8, 0, 0);
        }

    	if(board.miniBoardWonX)
    	{
        	if(board.CheckGrid(1, board.grids[0][0]))
        	{
        		if(board.NumAvailMoves != 0)
        		{
        			g.drawPixmap(Assets.bigX, board.GRID_START_X, board.GRID_START_Y);
        		}
        		else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X, board.GRID_START_Y);
        		}
        		mini0Playable = false;
        	}
        	else if(board.CheckGrid(1, board.grids[0][1]))
        	{
        		if(board.NumAvailMoves != 0)
        		{
        			g.drawPixmap(Assets.bigX, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y);
        		}
        		else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y);
        		}
        		mini1Playable = false;
        	}
        	else if(board.CheckGrid(1, board.grids[0][2]))
        	{
        		if(board.NumAvailMoves != 0)
        		{
        			g.drawPixmap(Assets.bigX, board.GRID_START_X + board.GRID_WIDTH*2, board.GRID_START_Y);
        		}
        		else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH*2, board.GRID_START_Y);
        		}
        		mini2Playable = false;
        	}
        	
        	else if(board.CheckGrid(1, board.grids[1][0]))
        	{
        		if(board.NumAvailMoves != 0)
        		{
        			g.drawPixmap(Assets.bigX, board.GRID_START_X, board.GRID_START_Y + board.GRID_HEIGHT);
        		}
        		else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X, board.GRID_START_Y + board.GRID_HEIGHT);
        		}
        		mini3Playable = false;
        	}
        	else if(board.CheckGrid(1, board.grids[1][1]))
        	{
        		if(board.NumAvailMoves != 0)
        		{
        			g.drawPixmap(Assets.bigX, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT);
        		}
        		else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT);
        		}
        		mini4Playable = false;
        	}
        	else if(board.CheckGrid(1, board.grids[1][2]))
        	{
        		if(board.NumAvailMoves != 0)
        		{
        			g.drawPixmap(Assets.bigX, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		mini5Playable = false;
        	}
        	
        	else if(board.CheckGrid(1, board.grids[2][0]))
        	{
        		if(board.NumAvailMoves != 0)
        		{
        			g.drawPixmap(Assets.bigX, board.GRID_START_X, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		mini6Playable = false;
        	}
        	else if(board.CheckGrid(1, board.grids[2][1]))
        	{
        		if(board.NumAvailMoves != 0)
        		{
        			g.drawPixmap(Assets.bigX, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		mini7Playable = false;
        	}
        	else if(board.CheckGrid(1, board.grids[2][2]))
        	{
        		if(board.NumAvailMoves != 0)
        		{
        			g.drawPixmap(Assets.bigX, board.GRID_START_X + board.GRID_WIDTH*2, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH*2, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		mini8Playable = false;
        	}
    	}
    	else if(board.miniBoardWonO)
    	{
    		if(board.CheckGrid(2, board.grids[0][0]))
        	{
    			if(board.NumAvailMoves != 0)
        		{
    				g.drawPixmap(Assets.bigO, board.GRID_START_X, board.GRID_START_Y);
        		}
    			else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X, board.GRID_START_Y);
        		}
        		mini0Playable = false;
        	}
    		else if(board.CheckGrid(2, board.grids[0][1]))
        	{
    			if(board.NumAvailMoves != 0)
        		{
    				g.drawPixmap(Assets.bigO, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y);
        		}
    			else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y);
        		}
        		mini1Playable = false;
        	}
    		else if(board.CheckGrid(2, board.grids[0][2]))
        	{
    			if(board.NumAvailMoves != 0)
        		{
    				g.drawPixmap(Assets.bigO, board.GRID_START_X + board.GRID_WIDTH*2, board.GRID_START_Y);
        		}
    			else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH*2, board.GRID_START_Y);
        		}
        		mini2Playable = false;
        	}
        	
    		else if(board.CheckGrid(2, board.grids[1][0]))
        	{
    			if(board.NumAvailMoves != 0)
        		{
    				g.drawPixmap(Assets.bigO, board.GRID_START_X, board.GRID_START_Y + board.GRID_HEIGHT);
        		}
    			else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X, board.GRID_START_Y + board.GRID_HEIGHT);
        		}
        		mini3Playable = false;
        	}
    		else if(board.CheckGrid(2, board.grids[1][1]))
        	{
    			if(board.NumAvailMoves != 0)
        		{
    				g.drawPixmap(Assets.bigO, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT);
        		}
    			else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT);
        		}
        		mini4Playable = false;
        	}
    		else if(board.CheckGrid(2, board.grids[1][2]))
        	{
    			if(board.NumAvailMoves != 0)
        		{
    				g.drawPixmap(Assets.bigO, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
    			else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		mini5Playable = false;
        	}
        	
    		else if(board.CheckGrid(2, board.grids[2][0]))
        	{
    			if(board.NumAvailMoves != 0)
        		{
    				g.drawPixmap(Assets.bigO, board.GRID_START_X, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
    			else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		mini6Playable = false;
        	}
    		else if(board.CheckGrid(2, board.grids[2][1]))
        	{
    			if(board.NumAvailMoves != 0)
        		{
    				g.drawPixmap(Assets.bigO, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
    			else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		mini7Playable = false;
        	}
    		else if(board.CheckGrid(2, board.grids[2][2]))
        	{
    			if(board.NumAvailMoves != 0)
        		{
    				g.drawPixmap(Assets.bigO, board.GRID_START_X + board.GRID_WIDTH*2, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
    			else
        		{
        			g.drawPixmap(Assets.miniCats, board.GRID_START_X + board.GRID_WIDTH*2, board.GRID_START_Y + board.GRID_HEIGHT*2);
        		}
        		mini8Playable = false;
        	}
    	}
    }

    private void drawPlayUI() 
    {
        Graphics g = game.getGraphics();      
        g.drawPixmap(Assets.playMenu, 0, 0);
    }
    
    private void drawPausedUI() 
    {
        Graphics g = game.getGraphics();      
        g.drawPixmap(Assets.pause, 0, 0);
    }

    private void drawGameOverUI() 
    {
       Graphics g = game.getGraphics();
    	if(board.gameOver1)
    	{
    		g.drawPixmap(Assets.gameOver1, 0, 0);
    	}
    	else if(board.gameOver2)
    	{
    		g.drawPixmap(Assets.gameOver2, 0, 0);
    	}
    	else if(board.gameOverC)
    	{
    		g.drawPixmap(Assets.gameOverC, 0, 0);
    	}
    }
    
    @Override
    public void pause() 
    {
        if(state == GameState.Running)
        {
            state = GameState.Paused;
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() 
    {
        if(state == GameState.Paused)
        {
        	state = GameState.Running;
        }
    }

    @Override
    public void dispose() 
    {
        
    }
}