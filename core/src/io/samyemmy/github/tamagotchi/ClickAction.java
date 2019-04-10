package io.samyemmy.github.tamagotchi;

import io.samyemmy.github.MyGame;

public class ClickAction implements Action
{
    @Override
    public float execute(Tamagotchi tamagotchi)
    {
        if (tamagotchi.isSick)
            return 0;

        if (tamagotchi.needsAttention)
        {
            MyGame.getInstance().getMainScreen().hideAttention();
            tamagotchi.needsAttention = false;
        }

        return 2;
    }

    @Override
    public void dispose(Tamagotchi tamagotchi) {

    }
}
