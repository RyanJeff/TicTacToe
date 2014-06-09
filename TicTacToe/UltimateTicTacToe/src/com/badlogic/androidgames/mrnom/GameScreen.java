package com.badlogic.androidgames.mrnom;

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


// The view of the MVC
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

    //start in a ready state
    GameState state = GameState.Ready;
    World world;  
    
    public GameScreen(Game game) 
    {
        super(game);
        world = new World();
    }

    @Override
    public void update(float deltaTime) 
    {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        //update ready state check touch containers to see if there is more than one event
        //only start game when user touches the screen
        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);        
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
                		//if(Settings.soundEnabled)
                        	//Assets.click.play(1);
                		state = GameState.Paused;
	                    return;
                	}  
                }
            }
        }
        
		/* if(event.type == TouchEvent.TOUCH_DOWN) 
		{
		    if(event.x < 64 && event.y > 416) 
		    {
		        world.snake.turnLeft();
		    }
		    if(event.x > 256 && event.y > 416) 
		    {
		        world.snake.turnRight();
		    }
		}*/
        
        world.update(deltaTime);
        if(world.gameOver) 
        {
            //if(Settings.soundEnabled)
                //Assets.bitten.play(1);
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
                        //if(Settings.soundEnabled)
                            //Assets.click.play(1);
                		state = GameState.Running;
                		return;
                    } 
                    //main menu
                    if(event.y > 325 && event.y < 400) 
                    {
                        //if(Settings.soundEnabled)
                            //Assets.click.play(1);
                        game.setScreen(new MainMenuScreen(game));                        
                        return;
                    }
                }
            }
        } 
    }
    
    //updates game over and returns main menu
    private void updateGameOver(List<TouchEvent> touchEvents) 
    {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) 
        {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) 
            {
                if(event.x >= 128 && event.x <= 192 &&
                   event.y >= 200 && event.y <= 264) 
                {
                    //if(Settings.soundEnabled)
                        //Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) 
    {
        Graphics g = game.getGraphics();
        //draw the game world background
        g.drawPixmap(Assets.background, 0, 0);
        
        drawWorld(world);
        if(state == GameState.Ready) 
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();               
    }

    private void drawWorld(World world) 
    {
 
    }
    
    private void drawReadyUI() 
    {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.ready, 0, 0);
    }
    
    private void drawRunningUI() 
    {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
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
        if(world.gameOver) 
        {
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