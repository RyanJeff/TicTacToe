package com.badlogic.androidgames.uttt;

import java.nio.Buffer;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.impl.GLGame;
import com.badlogic.androidgames.framework.impl.GLGraphics;
import com.badlogic.androidgames.uttt.Board;
import android.util.Log;
import android.util.FloatMath;

//The view of the MVC
//target space for buttons and HUD based on source x and y
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
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(deltaTime);        
    }

    private void updateReady(List<TouchEvent> touchEvents) 
    {
    	if(state == GameState.Ready)
    	{
	    	//touch screen to start
	        if(touchEvents.size() > 0)
	        {
	        	state = GameState.Running;
	        } 
    	}
    }
    
    boolean isPlayer1Turn = true;
    int masterboard_id;
    int miniboard_id;
    
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) 
    {    
    	state = GameState.Running;
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
                
                System.out.println("Masterboard id: " + masterboard_id);
                System.out.println("Miniboards: rowP = " + rowP + ", colP = " + colP);
                
                if(masterboard_id == 0)
                {
                	i = miniboard_id;
                	if(isPlayer1Turn)
        			{
        				board.grids[0][0].grid[i] = 1;
        				isPlayer1Turn = false;
        			}
        			else
        			{
        				board.grids[0][0].grid[i] = 2;
        				isPlayer1Turn = true;
        			}
                }	
                if(masterboard_id == 1)
                {
                	i = miniboard_id;
        			if(isPlayer1Turn)
        			{
        				board.grids[0][1].grid[i] = 1;
        				isPlayer1Turn = false;
        			}
        			else
        			{
        				board.grids[0][1].grid[i] = 2;
        				isPlayer1Turn = true;
        			}
                }
                if(masterboard_id == 2)
                {
                	i = miniboard_id;
        			if(isPlayer1Turn)
        			{
        				board.grids[0][2].grid[i] = 1;
        				isPlayer1Turn = false;
        			}
        			else
        			{
        				board.grids[0][2].grid[i] = 2;
        				isPlayer1Turn = true;
        			}
                }
                if(masterboard_id == 3)
                {
                	i = miniboard_id;
        			if(isPlayer1Turn)
        			{
        				board.grids[1][0].grid[i] = 1;
        				isPlayer1Turn = false;
        			}
        			else
        			{
        				board.grids[1][0].grid[i] = 2;
        				isPlayer1Turn = true;
        			}
                }
                
                if(masterboard_id == 4)
                {
                	i = miniboard_id;
        			if(isPlayer1Turn)
        			{
        				board.grids[1][1].grid[i] = 1;
        				isPlayer1Turn = false;
        			}
        			else
        			{
        				board.grids[1][1].grid[i] = 2;
        				isPlayer1Turn = true;
        			}
                }
                
                if(masterboard_id == 5)
                {
                	i = miniboard_id;
        			if(isPlayer1Turn)
        			{
        				board.grids[1][2].grid[i] = 1;
        				isPlayer1Turn = false;
        			}
        			else
        			{
        				board.grids[1][2].grid[i] = 2;
        				isPlayer1Turn = true;
        			}
                }
            	
                if(masterboard_id == 6)
                {
                	i = miniboard_id;
        			if(isPlayer1Turn)
        			{
        				board.grids[2][0].grid[i] = 1;
        				isPlayer1Turn = false;
        			}
        			else
        			{
        				board.grids[2][0].grid[i] = 2;
        				isPlayer1Turn = true;
        			}
                }
                if(masterboard_id == 7)
                {
                	i = miniboard_id;
        			if(isPlayer1Turn)
        			{
        				board.grids[2][1].grid[i] = 1;
        				isPlayer1Turn = false;
        			}
        			else
        			{
        				board.grids[2][1].grid[i] = 2;
        				isPlayer1Turn = true;
        			}
                }
                if(masterboard_id == 8)
                {
                	i = miniboard_id;
        			if(isPlayer1Turn)
        			{
        				board.grids[2][2].grid[i] = 1;
        				isPlayer1Turn = false;
        			}
        			else
        			{
        				board.grids[2][2].grid[i] = 2;
        				isPlayer1Turn = true;
        			}
                }
            }
        }
        
		/* if(event.type == TouchEvent.TOUCH_DOWN) 
		{
		    if(event.x < 64 && event.y > 416) 
		    {
		        masterBoard.snake.turnLeft();
		    }
		    if(event.x > 256 && event.y > 416) 
		    {
		        masterBoard.snake.turnRight();
		    }
		}*/

        //masterBoard.update(deltaTime);
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
        //Graphics g = game.getGraphics();
        //g.drawPixmap(Assets.background, 0, 0);
    	
        if(state == GameState.Ready) 
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();               
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
    }
    
    private void drawPausedUI() 
    {
        Graphics g = game.getGraphics();      
        g.drawPixmap(Assets.pause, 0, 0);
    }

    private void drawGameOverUI() 
    {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.gameOver, 0, 0);
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