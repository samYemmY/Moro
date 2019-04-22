package io.samyemmy.github;

import com.badlogic.gdx.scenes.scene2d.Actor;

import io.samyemmy.github.action.SleepAction;
import io.samyemmy.github.action.WakeUpAction;
import io.samyemmy.github.screen.MainScreen;

public class MainScreenTabBar extends BaseTabBar
{
    MainScreen mainScreen;

    public MainScreenTabBar(MainScreen screen)
    {
        super(screen);
        this.mainScreen = screen;
        addButton("Stats");
        addButton("Actions");
        addButton("Sleep");
    }

    @Override
    void onClick(String action, Actor actor)
    {
        if (action.equals("Actions"))
        {
            SoundHandler.getInstance().playSound("click");
            mainScreen.getStatsDialog().hide();
            mainScreen.toggleDialog(mainScreen.getActionDialog());
        }
        else if (action.equals("Stats"))
        {
            SoundHandler.getInstance().playSound("click");
            mainScreen.getActionDialog().hide();
            mainScreen.toggleDialog(mainScreen.getStatsDialog());
        }
        else if (action.equals("Sleep"))
        {
            SoundHandler.getInstance().playSound("select");
            Tamagotchi tamagotchi = MyGame.getInstance().getTamagotchi();
            if (tamagotchi.isSleeping)
            {
                MyGame.getInstance().getActionQueue().add(new WakeUpAction());
            }
            else
            {
                MyGame.getInstance().getActionQueue().add(new SleepAction());
            }
        }
    }
}
