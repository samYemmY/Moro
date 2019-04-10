package io.samyemmy.github.tamagotchi;

import io.samyemmy.github.EndScreen;
import io.samyemmy.github.MyGame;

public class DieAction implements Action
{
    @Override
    public float execute(Tamagotchi tamagotchi)
    {
        tamagotchi.isAlive = false;
        tamagotchi.setAnimation("anim/tamagotchi/dead");
        MyGame.getInstance().setScreen(new EndScreen());
        return 0;
    }

    @Override
    public void dispose(Tamagotchi tamagotchi) {

    }
}
