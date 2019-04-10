package io.samyemmy.github.tamagotchi;

import io.samyemmy.github.AnimatedToast;
import io.samyemmy.github.Candy;
import io.samyemmy.github.MyGame;

public class CandyAction implements Action
{
    @Override
    public float execute(Tamagotchi tamagotchi)
    {
        if (tamagotchi.isSick)
        {
            MyGame.getInstance().getAndroid().toast("Your Tamagotchi is not hungry.");
            return 0;
        }
        else
        {
            tamagotchi.setAnimation("anim/tamagotchi/eating");
            MyGame.getInstance().getMainScreen().spawnFood(new Candy());
            tamagotchi.setHealth(tamagotchi.getHealth() - 20);
            tamagotchi.setSaturation(tamagotchi.getSaturation() + 20);
            tamagotchi.setHappiness(tamagotchi.getHappiness() + 30);
            return 1.5f;
        }
    }

    @Override
    public void dispose(Tamagotchi tamagotchi)
    {
        tamagotchi.setAnimation("anim/tamagotchi/idle");
        tamagotchi.executeAction(new HappyAction());
    }
}
