package com.badlogic.androidgames.mrnom;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen 
{
    public LoadingScreen(Game game) 
    {
        super(game);
    }

    @Override
    public void update(float deltaTime) 
    {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
        Assets.splash = g.newPixmap("SplashScreen.png", PixmapFormat.ARGB4444);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
        Assets.options = g.newPixmap("OptionsMenu.png", PixmapFormat.ARGB4444);
        Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
        Assets.credits = g.newPixmap("CreditsScreen.png", PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);  //touch to start
        Assets.pause = g.newPixmap("PauseMenu.png", PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
        
        Assets.click = game.getAudio().newSound("click.ogg");
        //Assets.eat = game.getAudio().newSound("eat.ogg");
        //Assets.bitten = game.getAudio().newSound("bitten.ogg");
        
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) 
    {

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