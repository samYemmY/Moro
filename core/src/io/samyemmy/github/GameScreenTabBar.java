package io.samyemmy.github;

import com.badlogic.gdx.scenes.scene2d.Actor;

import io.samyemmy.github.screen.GameScreen;

public class GameScreenTabBar extends BaseTabBar
{
    GameScreen gameScreen;

    public GameScreenTabBar(GameScreen gameScreen)
    {
        super(gameScreen);
        this.gameScreen = gameScreen;
        addButton("Close");
    }

    @Override
    void onClick(String action, Actor actor)
    {
        if (action.equals("Close"))
        {
            SoundHandler.getInstance().playSound("select");
            gameScreen.quit();
        }
    }
}
