package io.samyemmy.github.action;

import io.samyemmy.github.Tamagotchi;

public class SadAction implements Action
{
    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi) {
        return true;
    }

    @Override
    public void execute(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/sad");
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
    }

    @Override
    public long getDuration() {
        return 2000;
    }
}
