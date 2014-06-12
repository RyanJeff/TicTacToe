package com.badlogic.androidgames.uttt;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class OptionsScreen extends Screen 
{
	public OptionsScreen(Game game) 
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
                if(event.x > 65 && event.x < 255) 
                {
                	//how to play
                	if(event.y > 140 && event.y < 195)
                	{
                		game.setScreen(new HelpScreen(game));
	                    if(Settings.soundEnabled)
	                    {
	                        Assets.click.play(1);
	                    }
	                    return;
                	}
                	//credits
                	if(event.y > 205 && event.y < 260)
                	{
                		game.setScreen(new CreditsScreen(game));
	                    if(Settings.soundEnabled)
	                    {
	                        Assets.click.play(1);
	                    }
	                    return;
                	}
                	//back
                	if(event.y > 410 && event.y < 460)
                	{
                		game.setScreen(new MainMenuScreen(game));
	                    if(Settings.soundEnabled)
	                    {
	                        Assets.click.play(1);
	                    }
	                    return;
                	}
                }
                //toggle sound 
                if(event.x > 210 && event.x < 260)
                {	
                	//off 
                	if(event.y > 270 && event.y < 310)
                	{
                		Settings.soundEnabled = !Settings.soundEnabled;
                	}
                	//on (default)
	            	else
	            	{
	            		Settings.soundEnabled = true;
	            	}
                	if(Settings.soundEnabled)
            		{
                    	Assets.click.play(1);
            		}
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) 
    {
        Graphics g = game.getGraphics();      
        g.drawPixmap(Assets.options, 0, 0);
        if(Settings.soundEnabled)
        {
            g.drawPixmap(Assets.buttons, 207, 260, 0, 0, 64, 64);
		}
        else
        {
            g.drawPixmap(Assets.buttons, 207, 260, 64, 0, 64, 64);
		}
        g.drawPixmap(Assets.buttons, 207, 308, 64, 0, 64, 64);
    }

    @Override
    public void pause() 
    {

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