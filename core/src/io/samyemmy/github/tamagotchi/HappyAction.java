package io.samyemmy.github.tamagotchi;

public class HappyAction implements Action
{
    @Override
    public float execute(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/happy");
        return 2;
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
    }
}
