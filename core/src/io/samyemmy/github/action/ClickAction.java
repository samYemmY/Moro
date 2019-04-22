package io.samyemmy.github.action;

import io.samyemmy.github.MyGame;
import io.samyemmy.github.Tamagotchi;

public class ClickAction implements Action
{
    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi) {
        return true;
    }

    @Override
    public void execute(Tamagotchi tamagotchi)
    {
        if (tamagotchi.getSaturation() > 50 && tamagotchi.getHappiness() > 50)
        {
            MyGame.getInstance().getActionQueue().add(new HappyAction());
        }
        else
        {
            MyGame.getInstance().getActionQueue().add(new SadAction());
        }
    }

    @Override

    public void dispose(Tamagotchi tamagotchi)
    {

    }

    @Override
    public long getDuration() {
        return 0;
    }
}
