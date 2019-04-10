package io.samyemmy.github;

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
    void onClick(String action)
    {
        if (action.equals("Close"))
        {
            gameScreen.quit();
        }
    }
}
