package io.samyemmy.github.action;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.Tamagotchi;
import io.samyemmy.github.screen.MainScreen;

public class SickAction implements Action
{
    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi) {
        return true;
    }

    @Override
    public void execute(Tamagotchi tamagotchi)
    {
        MainScreen mainScreen = MyGame.getInstance().getMainScreen();
        mainScreen.showSick();
        tamagotchi.setAnimation("anim/tamagotchi/sick", 0.2f);
        tamagotchi.isSick = true;
    }

    @Override
    public void dispose(Tamagotchi tamagotchi) {

    }

    @Override
    public long getDuration() {
        return 0;
    }
}
