package io.samyemmy.github.tamagotchi;

public class IdleAction implements Action
{
    @Override
    public float execute(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
        return 0;
    }

    @Override
    public void dispose(Tamagotchi tamagotchi) {

    }
}
