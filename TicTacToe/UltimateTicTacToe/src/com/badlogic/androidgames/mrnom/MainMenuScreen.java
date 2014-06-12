package com.badlogic.androidgames.mrnom;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class MainMenuScreen extends Screen 
{		
    public MainMenuScreen(Game game) 
    {
        super(game);               
    }   

    @Override
    public void update(float deltaTime) 
    {   
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) 
        {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) 
            {
            	//play
            	if(inBounds(event, 60, 225, 205, 55)) 
                {
            		
            		game.setScreen(new GameScreen(game));
                    if(Settings.soundEnabled)
                    {
                        Assets.click.play(1);
                    }
                    return;
                }
            	//options
            	if(inBounds(event, 60, 305, 205, 55)) 
                {
                    game.setScreen(new OptionsScreen(game));
                    if(Settings.soundEnabled)
                    {
                        Assets.click.play(1);
                    }
                    return;
                }
            	//quit
                if(inBounds(event, 60, 390, 205, 55)) 
                {
                	if(Settings.soundEnabled)
                    {
                        Assets.click.play(1);
                    }
                	System.exit(0);
                    return;
                }
            }
        }
    }
    
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) 
    {
        if(event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1) 
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    //draw the assets
    public void present(float deltaTime) 
    {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.mainMenu, 0, 0);
    }

    @Override
    public void pause() 
    {   
    	//save the current game settings to pause     
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() 
    {

    }

    @Override
    public void dispose() 
    {

    }
}
