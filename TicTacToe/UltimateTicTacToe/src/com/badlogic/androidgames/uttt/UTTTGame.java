package com.badlogic.androidgames.uttt;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class UTTTGame extends AndroidGame 
{
    @Override
    public Screen getStartScreen() 
    {
        return new LoadingScreen(this); 
    }
}