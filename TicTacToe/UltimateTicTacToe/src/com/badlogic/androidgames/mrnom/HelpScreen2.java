package com.badlogic.androidgames.mrnom;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class HelpScreen2 extends Screen 
{

    public HelpScreen2(Game game) 
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
            	if(event.x > 140 && event.x < 365) 
                {
                	if(event.y > 420)
                	{
                		game.setScreen(new HelpScreen3(game));
	                    //if(Settings.soundEnabled)
	                        //Assets.click.play(1);
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
        //g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.help2, 0, 0);
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
