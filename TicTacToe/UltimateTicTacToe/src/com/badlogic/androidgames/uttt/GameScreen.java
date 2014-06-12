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
import com.badlogic.androidgames.uttt.X;
import com.badlogic.androidgames.uttt.O;
<<<<<<< HEAD
import com.badlogic.androidgames.uttt.Board;
import android.util.Log;
=======
import android.util.Log;

>>>>>>> origin/master

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
<<<<<<< HEAD
  //  MasterBoard masterBoard;
    Board board;
    public X x;
    public O o;
=======
    MasterBoard masterBoard;
    X x;
    O o;
>>>>>>> origin/master
    
    public GameScreen(Game game) 
    {
        super(game);
        board = new Board(game);
        //x = new X(0, 0, 32, 32);
        //o = new O(32, 32, 32, 32);
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
    	//touch screen to start
        if(touchEvents.size() > 0)
        {
        	state = GameState.Running;
        }   
    }
    
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
                
                
                
                int MB_id = -1;
                Graphics g = game.getGraphics();
                if(event.y >= 128 && event.y <= 224) 
                {
            		if(event.x >= 16 && event.x <= 112) 
                    {
<<<<<<< HEAD
            			//MB_id = 0;
            			board.grids[0][0].grid[0] = 1;
                    }
            		if(event.x > 112 && event.x <= 208) 
                    {
            			//MB_id = 1;
            			board.grids[0][1].grid[0] = 1;
                    }
            		if(event.x > 208 && event.x <= 304) 
                    {
            			//MB_id = 2;
            			board.grids[0][2].grid[0] = 1;
=======
            			MB_id = 0;
            	        g.drawPixmap(Assets.x, event.x, event.y);
            			Log.v("ID", "Box Zero / 0");
            			
                    }
            		if(event.x > 112 && event.x <= 208) 
                    {
            			MB_id = 1;
            			g.drawPixmap(Assets.x, event.x, event.y);
            			Log.v("ID", "Box One / 1");
                    }
            		if(event.x > 208 && event.x <= 304) 
                    {
            			MB_id = 2;
            			g.drawPixmap(Assets.x, event.x, event.y);
            			Log.v("ID", "Box Two / 2");
>>>>>>> origin/master
                    }
                }
            	if(event.y > 224 && event.y <= 320) 
                {
            		if(event.x >= 16 && event.x <= 112) 
                    {
<<<<<<< HEAD
            			//MB_id = 3;
            			board.grids[1][0].grid[0] = 1;
                    }
            		if(event.x > 112 && event.x <= 208) 
                    {
            			//MB_id = 4;
            			board.grids[1][1].grid[0] = 1;
                    }
            		if(event.x > 208 && event.x <= 304) 
                    {
            			//MB_id = 5;
            			board.grids[1][2].grid[0] = 1;
=======
            			MB_id = 3;
            			g.drawPixmap(Assets.x, event.x, event.y);
            			Log.v("ID", "Box Three / 3");
                    }
            		if(event.x > 112 && event.x <= 208) 
                    {
            			MB_id = 4;
            			g.drawPixmap(Assets.x, event.x, event.y);
            			Log.v("ID", "Box Four / 4");
                    }
            		if(event.x > 208 && event.x <= 304) 
                    {
            			MB_id = 5;
            			g.drawPixmap(Assets.x, event.x, event.y);
            			Log.v("ID", "Box Five / 5");
>>>>>>> origin/master
                    }
                }
            	if(event.y > 320 && event.y <= 416) 
                {
            		if(event.x >= 16 && event.x <= 112) 
                    {
<<<<<<< HEAD
            			//MB_id = 6;
            			board.grids[2][0].grid[0] = 1;
                    }
            		if(event.x > 112 && event.x <= 208) 
                    {
            			//MB_id = 7;
            			board.grids[2][1].grid[0] = 1;
                    }
            		if(event.x > 208 && event.x <= 304) 
                    {
            			//MB_id = 8;
            			board.grids[2][2].grid[0] = 1;
=======
            			MB_id = 6;
            			g.drawPixmap(Assets.x, event.x, event.y);
            			Log.v("ID", "Box Six / 6");
                    }
            		if(event.x > 112 && event.x <= 208) 
                    {
            			MB_id = 7;
            			g.drawPixmap(Assets.x, event.x, event.y);
            			Log.v("ID", "Box Seven / 7");
                    }
            		if(event.x > 208 && event.x <= 304) 
                    {
            			MB_id = 8;
            			g.drawPixmap(Assets.x, event.x, event.y);
            			Log.v("ID", "Box Eight / 8");
>>>>>>> origin/master
                    }
                }
            	/*
            	if(MB_id == 0);
            	{
            		
            	}
            	
                */
                
                
                
                
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
    
    //updates game over and returns main menu
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
        Graphics g = game.getGraphics();
        //draw the game masterBoard background
        //g.drawPixmap(Assets.background, 0, 0);
        
        //drawMasterBoard(masterBoard);
        if(state == GameState.Ready) 
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();               
    }

    
    
    private void drawMasterBoard(MasterBoard masterBoard) 
    {
    	X x = masterBoard.x;
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