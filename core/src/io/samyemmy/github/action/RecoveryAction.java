package io.samyemmy.github.action;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.SoundHandler;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.screen.MainScreen;

public class RecoveryAction implements Action
{
    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi) {
        return true;
    }

    @Override
    public void execute(Tamagotchi tamagotchi)
    {
        MainScreen mainScreen = MyGame.getInstance().getMainScreen();
        mainScreen.hideSick();
        tamagotchi.isSick = false;
        SoundHandler.getInstance().playSound("win");
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        if (!tamagotchi.isSleeping)
        {
            tamagotchi.setAnimation("anim/tamagotchi/idle");
        }
    }

    @Override
    public long getDuration() {
        return 0;
    }
}
