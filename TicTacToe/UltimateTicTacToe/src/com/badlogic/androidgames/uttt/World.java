package com.badlogic.androidgames.uttt;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

//import java.util.Random;

public class /*MasterBoard*/World /*extends Screen*/
{
	//model of the world 
    static final int BOARD_WIDTH = 9;
    static final int BOARD_HEIGHT = 9;
    Game game;
    public boolean gameOver = false;
    
    public boolean currMiniActive = false;
    public int MB_id;

    public /*MasterBoard*/World() 
    {
    	//super(game);
    }
    
    public void update(float deltaTime) 
    {
    	List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        Graphics g = game.getGraphics();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) 
        {
        	TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) 
            {
            	if(event.y >= 128 && event.y <= 224) 
                {
            		if(event.x >= 16 && event.x <= 112) 
                    {
            			MB_id = 0;
            	        g.drawPixmap(Assets.x, event.x, event.y);
                    }
            		else if(event.x > 112 && event.x <= 208) 
                    {
            			MB_id = 1;
            			g.drawPixmap(Assets.x, event.x, event.y);
                    }
            		else if(event.x > 208 && event.x <= 304) 
                    {
            			MB_id = 2;
            			g.drawPixmap(Assets.x, event.x, event.y);
                    }
                }
            	else if(event.y > 224 && event.y <= 320) 
                {
            		if(event.x >= 16 && event.x <= 112) 
                    {
            			MB_id = 3;
            			g.drawPixmap(Assets.x, event.x, event.y);
                    }
            		else if(event.x > 112 && event.x <= 208) 
                    {
            			MB_id = 4;
            			g.drawPixmap(Assets.x, event.x, event.y);
                    }
            		else if(event.x > 208 && event.x <= 304) 
                    {
            			MB_id = 5;
            			g.drawPixmap(Assets.x, event.x, event.y);
                    }
                }
            	else if(event.y > 320 && event.y <= 416) 
                {
            		if(event.x >= 16 && event.x <= 112) 
                    {
            			MB_id = 6;
            			g.drawPixmap(Assets.x, event.x, event.y);
                    }
            		else if(event.x > 112 && event.x <= 208) 
                    {
            			MB_id = 7;
            			g.drawPixmap(Assets.x, event.x, event.y);
                    }
            		else if(event.x > 208 && event.x <= 304) 
                    {
            			MB_id = 8;
            			g.drawPixmap(Assets.x, event.x, event.y);
                    }
                }
            }
        }
    	
        if (gameOver)
        {
            return;
        }
    }
}
