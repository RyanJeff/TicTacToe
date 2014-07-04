package com.badlogic.androidgames.uttt;

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
        Assets.playMenu = g.newPixmap("PlayMenu.png", PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
        Assets.options = g.newPixmap("OptionsMenu.png", PixmapFormat.ARGB4444);
        Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
        Assets.credits = g.newPixmap("CreditsScreen.png", PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);  //touch to start
        Assets.pause = g.newPixmap("PauseMenu.png", PixmapFormat.ARGB4444);
        Assets.gameOver1 = g.newPixmap("gameOver1.png", PixmapFormat.ARGB4444);
        Assets.gameOver2 = g.newPixmap("gameOver2.png", PixmapFormat.ARGB4444);
        Assets.gameOverC = g.newPixmap("gameOverC.png", PixmapFormat.ARGB4444);
        Assets.x = g.newPixmap("x.png", PixmapFormat.ARGB4444);
        Assets.o = g.newPixmap("o.png", PixmapFormat.ARGB4444);
        Assets.GSec0 = g.newPixmap("GSec0.png", PixmapFormat.ARGB4444);
        Assets.GSec1 = g.newPixmap("GSec1.png", PixmapFormat.ARGB4444);
        Assets.GSec2 = g.newPixmap("GSec2.png", PixmapFormat.ARGB4444);
        Assets.GSec3 = g.newPixmap("GSec3.png", PixmapFormat.ARGB4444);
        Assets.GSec4 = g.newPixmap("GSec4.png", PixmapFormat.ARGB4444);
        Assets.GSec5 = g.newPixmap("GSec5.png", PixmapFormat.ARGB4444);
        Assets.GSec6 = g.newPixmap("GSec6.png", PixmapFormat.ARGB4444);
        Assets.GSec7 = g.newPixmap("GSec7.png", PixmapFormat.ARGB4444);
        Assets.GSec8 = g.newPixmap("GSec8.png", PixmapFormat.ARGB4444);
        Assets.bigX = g.newPixmap("bigX.png", PixmapFormat.ARGB4444);
        Assets.bigO = g.newPixmap("bigO.png", PixmapFormat.ARGB4444);
        Assets.click = game.getAudio().newSound("click.mp3");
        Assets.clinkSound = game.getAudio().newSound("clinkSound.mp3");
        Assets.BGM = game.getAudio().newSound("BGM.mp3");
        Settings.load(game.getFileIO());
        game.setScreen(new SplashScreen(game));
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