package io.samyemmy.github.action;

import io.samyemmy.github.SoundHandler;
import io.samyemmy.github.Tamagotchi;

public class SoundJumpAction implements Action
{
    @Override
    public boolean shouldExecute(Tamagotchi tamagotchi) {
        return true;
    }

    @Override
    public void execute(Tamagotchi tamagotchi)
    {
        SoundHandler.getInstance().playSound("jump");
        tamagotchi.setAnimation("anim/tamagotchi/happy");
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
    }

    @Override
    public long getDuration() {
        return 800;
    }
}
