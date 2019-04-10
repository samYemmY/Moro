package io.samyemmy.github;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainScreenTabBar extends BaseTabBar
{
    MainScreen mainScreen;

    public MainScreenTabBar(MainScreen screen)
    {
        super(screen);
        this.mainScreen = screen;
        addButton("Stats");
        addButton("Actions");
        addButton("Light");
    }

    @Override
    void onClick(String action)
    {
        if (action.equals("Actions"))
        {
            mainScreen.getStatsDialog().hide();
            mainScreen.toggleDialog(mainScreen.getActionDialog());
        }
        else if (action.equals("Stats"))
        {
            mainScreen.getActionDialog().hide();
            mainScreen.toggleDialog(mainScreen.getStatsDialog());
        }
    }
}
