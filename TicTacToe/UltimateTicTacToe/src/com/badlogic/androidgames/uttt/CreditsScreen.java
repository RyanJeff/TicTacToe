package com.badlogic.androidgames.UTTT;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class CreditsScreen extends Screen
{
	public CreditsScreen(Game game) 
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
                if(event.x > 135 && event.x < 360) 
                {
                	//back
                	if(event.y > 380 && event.y < 440)
                	{
                		game.setScreen(new OptionsScreen(game));
                		if(Settings.soundEnabled)
                		{
                        	Assets.click.play(1);
                		}
                		return;
                	}
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) 
    {
        Graphics g = game.getGraphics();      
        g.drawPixmap(Assets.credits, 0, 0);
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