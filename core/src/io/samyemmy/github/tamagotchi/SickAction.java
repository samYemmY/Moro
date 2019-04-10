package io.samyemmy.github.tamagotchi;

import io.samyemmy.github.MyGame;

public class SickAction implements Action
{
    @Override
    public float execute(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/sick", 0.2f);
        MyGame.getInstance().getMainScreen().showSick();
        MyGame.getInstance().getMainScreen().hideAttention();
        tamagotchi.isSick = true;
        return 0;
    }

    @Override
    public void dispose(Tamagotchi tamagotchi) {

    }
}
