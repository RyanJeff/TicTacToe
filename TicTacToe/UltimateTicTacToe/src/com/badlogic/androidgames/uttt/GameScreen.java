package com.badlogic.androidgames.uttt;

import java.util.List;
//import android.util.Log;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
//import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.uttt.Board;
import java.lang.Object;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;

public class GameScreen extends Screen 
{
    enum GameState 
    {
        Ready,
        Running,
        Paused,
        GameOver
    }

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
	    	//touch screen to start
	        if(touchEvents.size() > 0)
	        {
	        	if(touchEvents.get(0).type == TouchEvent.TOUCH_UP)
	        	{
	        		state = GameState.Running;
	        	}
	        } 
    	}
    }
    
    public class ArrayList
    {
    	ArrayList FilledArray = new ArrayList();
    }
    
    int masterboard_id;
    int miniboard_id;
    int nextMove;
    boolean isPlayer1Turn = true;
    boolean boardActive = true;
    boolean isFirstMove = true;
    //boolean boardCompleted = false;
    
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
                {
                	masterboard_id = 0;   //top left
                }
                if(row == 0 && col == 1)
                {
                	masterboard_id = 1;   //top mid
                }
                if(row == 0 && col == 2)
                {
                	masterboard_id = 2;   //top right
                }
                if(row == 1 && col == 0)
                {
                	masterboard_id = 3;   //mid left
                }
                if(row == 1 && col == 1)
                {
                	masterboard_id = 4;   //mid mid
                }
                if(row == 1 && col == 2)
                {
                	masterboard_id = 5;   //mid right
                }
                if(row == 2 && col == 0)
                {
                	masterboard_id = 6;   //btm left
                }
                if(row == 2 && col == 1)
                {
                	masterboard_id = 7;   //btm mid
                }
                if(row == 2 && col == 2)
                {
                	masterboard_id = 8;   //btm right
                }
                
           //miniboard ids
                if(rowP == 0 && colP == 0)
                {
                	miniboard_id = 0;   //top left
                }
                if(rowP == 0 && colP == 1)
                {
                	miniboard_id = 1;   //top mid
                }
                if(rowP == 0 && colP == 2)
                {
                	miniboard_id = 2;   //top right
                }
                if(rowP == 1 && colP == 0)
                {
                	miniboard_id = 3;   //mid left
                }
                if(rowP == 1 && colP == 1)
                {
                	miniboard_id = 4;   //mid mid
                }
                if(rowP == 1 && colP == 2)
                {
                	miniboard_id = 5;   //mid right
                }
                if(rowP == 2 && colP == 0)
                {
                	miniboard_id = 6;   //btm left
                }
                if(rowP == 2 && colP == 1)
                {
                	miniboard_id = 7;   //btm mid
                }
                if(rowP == 2 && colP == 2)
                {
                	miniboard_id = 8;   //btm right
                }
                
                //System.out.println("Masterboard id: " + masterboard_id);
<<<<<<< HEAD
                //System.out.println("Miniboards: rowP = " + rowP + ", colP = " + colP);
                System.out.println("Masterboard id: " + masterboard_id);
                System.out.println("Miniboards: rowP = " + rowP + ", colP = " + colP);
=======
                //System.out.println("Miniboard id: " + miniboard_id);
                
                Graphics g = game.getGraphics();
                
>>>>>>> origin/master
                
                if(isFirstMove)
                {
                	i = miniboard_id;
    				board.grids[row][col].grid[i] = 1;
    				isPlayer1Turn = false;
    				nextMove = miniboard_id;
    				if(miniboard_id != masterboard_id)
    				{
    					boardActive = false;
    				}
    				else
    				{
    					boardActive = true;
    				}
                	isFirstMove = false;
                }
                
                if(!isFirstMove)
                {
	                switch(masterboard_id)
	                {
	                	case 0:
		                	i = miniboard_id;
		                	boardActive = true;
		                	if(isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[0][0].grid[i] = 1;
		        				isPlayer1Turn = false;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        					// make this miniboard push to the array
		        					System.out.println(miniboard_id);
		        				}
		        			}
		                	else if(!isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[0][0].grid[i] = 2;
		        				isPlayer1Turn = true;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
		                	break;
		                
	                	case 1:
	                		i = miniboard_id;
	                		boardActive = true;
		        			if(isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[0][1].grid[i] = 1;
		        				isPlayer1Turn = false;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
		        			else if(!isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[0][1].grid[i] = 2;
		        				isPlayer1Turn = true;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
		        			break;
		        		
	                	case 2:
	                		i = miniboard_id;
	                		boardActive = true;
	                		if(isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[0][2].grid[i] = 1;
		        				isPlayer1Turn = false;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        					
		        				}
		        			}
	                		else if(!isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[0][2].grid[i] = 2;
		        				isPlayer1Turn = true;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
		        			break;
		        			
	                	case 3:
	                		i = miniboard_id;
	                		boardActive = true;
	                		if(isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[1][0].grid[i] = 1;
		        				isPlayer1Turn = false;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
	                		else if(!isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[1][0].grid[i] = 2;
		        				isPlayer1Turn = true;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
		        			break;
		        		
	                	case 4:
	                		i = miniboard_id;
	                		boardActive = true;
	                		if(isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[1][1].grid[i] = 1;
		        				isPlayer1Turn = false;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
	                		else if(!isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[1][1].grid[i] = 2;
		        				isPlayer1Turn = true;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
		        			break;
		        			
	                	case 5:
	                		i = miniboard_id;
	                		boardActive = true;
	                		if(isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[1][2].grid[i] = 1;
		        				isPlayer1Turn = false;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
	                		else if(!isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[1][2].grid[i] = 2;
		        				isPlayer1Turn = true;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
		        			break;
		        			
	                	case 6:
	                		i = miniboard_id;
	                		boardActive = true;
	                		if(isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[2][0].grid[i] = 1;
		        				isPlayer1Turn = false;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
	                		else if(!isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[2][0].grid[i] = 2;
		        				isPlayer1Turn = true;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
		        			break;
		        			
	                	case 7:
	                		i = miniboard_id;
	                		boardActive = true;
	                		if(isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[2][1].grid[i] = 1;
		        				isPlayer1Turn = false;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
	                		else if(!isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[2][1].grid[i] = 2;
		        				isPlayer1Turn = true;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
		        			break;
		        			
	                	case 8:
	                		i = miniboard_id;
	                		boardActive = true;
	                		if(isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[2][2].grid[i] = 1;
		        				isPlayer1Turn = false;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
	                		else if(!isPlayer1Turn && boardActive && nextMove == masterboard_id)
		        			{
		        				board.grids[2][2].grid[i] = 2;
		        				isPlayer1Turn = true;
		        				nextMove = miniboard_id;
		        				if(miniboard_id != masterboard_id)
		        				{
		        					boardActive = false;
		        				}
		        				else
		        				{
		        					boardActive = true;
		        				}
		        			}
		        			break;
	                }
	            }
            }
        }
        
        //if(masterBoard.gameOver) 
        //{
            //if(Settings.soundEnabled)
                //Assets.bitten.play(1);
            //state = GameState.GameOver;
        //}
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
        /*int len = touchEvents.size();
        for(int i = 0; i < len; i++) 
        {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) 
            {
                if(event.x >= 128 && event.x <= 192 &&
                   event.y >= 200 && event.y <= 264) 
                {
                	if(Settings.soundEnabled)
                    {
                        Assets.click.play(1);
                    }
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }*/
    }

    @Override
    public void present(float deltaTime) 
    {    	
        if(state == GameState.Ready)
        {
            drawReadyUI();
        }
        if(state == GameState.Running)
        {
        	drawRunningUI();
        }
        if(state == GameState.Paused)
        {
            drawPausedUI();
        }
        if(state == GameState.GameOver)
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
        if(nextMove == 0 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec0, 0, 0);
        }
        else if(nextMove == 1 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec1, 0, 0);
        }
        else if(nextMove == 2 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec2, 0, 0);
        }
        else if(nextMove == 3 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec3, 0, 0);
        }
        else if(nextMove == 4 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec4, 0, 0);
        }
        else if(nextMove == 5 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec5, 0, 0);
        }
        else if(nextMove == 6 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec6, 0, 0);
        }
        else if(nextMove == 7 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec7, 0, 0);
        }
        else if(nextMove == 8 && !isFirstMove)
        {
        	g.drawPixmap(Assets.GSec8, 0, 0);
        }
    }
    
    private void drawPausedUI() 
    {
        Graphics g = game.getGraphics();      
        g.drawPixmap(Assets.pause, 0, 0);
    }

    private void drawGameOverUI() 
    {
        //Graphics g = game.getGraphics();
        //g.drawPixmap(Assets.gameOver, 0, 0);
    }
    
    @Override
    public void pause() 
    {
        if(state == GameState.Running)
        {
            state = GameState.Paused;
        }
        //if(masterBoard.gameOver) 
        //{
            //Settings.save(game.getFileIO());
        //} 
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