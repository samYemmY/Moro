package io.samyemmy.github.tamagotchi;

public class SadAction implements Action
{
    @Override
    public float execute(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/sad");
        return 2;
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
    }
}
