package com.badlogic.androidgames.uttt;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;

public class SplashScreen extends Screen 
{
	private float timer = 4.0f;
	
    public SplashScreen(Game game) 
    {
        super(game);
    }

    @Override
    public void update(float deltaTime) 
    {
    	timer -= deltaTime;
    	if(timer <= 0)
    	{
    		game.setScreen(new MainMenuScreen(game));
    	}
    }

    @Override
    public void present(float deltaTime) 
    {
    	Graphics g = game.getGraphics();
        g.drawPixmap(Assets.splash, 0, 0);
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